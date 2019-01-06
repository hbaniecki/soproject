package com.zpoif.soprojekt.api;

import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Question;
import com.google.code.stackexchange.schema.StackExchangeSite;
import com.google.code.stackexchange.schema.TimePeriod;
import tech.tablesaw.api.Table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class QuestionCounter {
    private String[] dates;
    private int[] count;

    private final StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory
            .newInstance("4ccOeYINdrpbkpWOgBTZSw((",
                    StackExchangeSite.STACK_OVERFLOW);
    private TimePeriod timePeriod;
    private int numberOfDays;

    public QuestionCounter(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
        Duration d =
                Duration.between(
                        timePeriod.getStartDate().toInstant() ,
                        timePeriod.getEndDate().toInstant()
                );
        int n = (int) d.toDays();       // n potrzebne żeby zaimplementować tablice o podanej długości
        this.dates = new String[n];
        this.count = new int[n];
        this.numberOfDays = n;       // jak już je policzyliśmy to równie dobrze możemy zapisać
        System.out.println("Liczba dni pomiędzy wynosi " + n);
    }

    public class QuestionThread implements Callable<Integer> {
        /* wątek, który docelowo pobiera i zlicza pytania dla jednego dnia */

        private TimePeriod newTimePeriod;

        QuestionThread(Date unixDate) {
            Instant after = unixDate.toInstant().plus(Duration.ofDays(1));
            Date unixDateAfter = Date.from(after);
            this.newTimePeriod = new TimePeriod(unixDate, unixDateAfter);
        }

        @Override
        public Integer call() {
            int backoff;
            int j=1;
            int size;
            int counter = 0;

            while(true) {
                Paging paging = new Paging(j, 100);
                PagedList <Question> questionList = queryFactory.newQuestionApiQuery().withTimePeriod(newTimePeriod)
                        .withPaging(paging).withFilter("default")
                        .list();
                size = questionList.size();
                backoff = 1000*questionList.getBackoff();
                if (backoff > 0) System.out.println(backoff);

                if (size == 100) {
                    counter += size;
                    j+=1;
                    try {
                        Thread.sleep(backoff+24);  // musi być, żeby nie blokowało adresu ip, bo za dużo zapytań
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    counter += size;
                    return counter;
                }
            }
        }
    }


    public Table receiveData(){
        /* funkcja zwraca przetworzone dane pytań w formie tabelki
        wykorzystuje bibliotekę do api oraz wielowątkowość by pobierać dane o wiele szybciej*/


        Date unixDate = timePeriod.getStartDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        ExecutorService executor = Executors.newFixedThreadPool(numberOfDays);
        List<Future<Integer>> outputList= new ArrayList<Future<Integer>>();

        for (int k = 0; k < numberOfDays; k++) {
            //policz liczbę pytań danego dnia
            QuestionThread input = new QuestionThread(unixDate);
            Future<Integer> output = executor.submit(input);
            outputList.add(output);

            //wpisz to dablicy ładnie sformatowaną date
            String strDate = dateFormat.format(unixDate);
            dates[k] = strDate;

            //dodaj dzień
            Instant after = unixDate.toInstant().plus(Duration.ofDays(1));
            unixDate = Date.from(after);
        }

        executor.shutdown();

        int i =0;
        for(Future<Integer> output : outputList) {
            try {
                count[i] = output.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            i++;
        }
        return TableMaker.makeInteger("data", dates, "liczba", count);
    }
}
