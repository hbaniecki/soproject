package com.zpoif.soprojekt.api;

import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Question;
import com.google.code.stackexchange.schema.StackExchangeSite;
import com.google.code.stackexchange.schema.TimePeriod;

import tech.tablesaw.api.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class LanguageTagCounter{
    private final  String[] languages = {"java", "c", "c++", "python", "c#", "javascript", ".net", "r", "php", "matlab",
            "swift", "objective-c", "assembly", "perl", "ruby", "delphi", "go", "mit-scratch", "sql", "vb.net"};
    private int[] count = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    private final StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory
            .newInstance("4ccOeYINdrpbkpWOgBTZSw((",
                    StackExchangeSite.STACK_OVERFLOW);
    private TimePeriod timePeriod;

    public LanguageTagCounter(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public class LanguageThread implements Callable<Integer> {
        /* wątek, który docelowo pobiera i zlicza dane dla jednego języka programowania */
        private String tag;

        LanguageThread(String tag) {
            this.tag = tag;
        }

        @Override
        public Integer call() {
            int backoff;
            int j=1;
            int size;
            int counter = 0;
            while(true) {
                Paging paging = new Paging(j, 100);
                PagedList<Question> questionList = queryFactory.newQuestionApiQuery().withTimePeriod(timePeriod)
                        .withPaging(paging).withFilter("default")
                        .withTags(tag).list();
                size = questionList.size();
                backoff = 1000*questionList.getBackoff();
                if (backoff > 0) System.out.println(backoff);

                if (size == 100) {
                    counter += size;
                    j+=1;
                    try {
                        Thread.sleep(backoff+10);  // musi być, żeby nie blokowało adresu ip, bo za dużo zapytań
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
        /* funkcja zwraca przetworzone dane języków programowania w formie tabelki
        wykorzystuje bibliotekę do api oraz wielowątkowość by pobierać dane o wiele szybciej*/

        ExecutorService executor = Executors.newFixedThreadPool(languages.length);
        List<Future<Integer>> outputList= new ArrayList<Future<Integer>>();

        for (String tag : languages) {
            // dla każdego języka programowania licz pytania osobno
            LanguageThread input = new LanguageThread(tag);
            Future<Integer> output = executor.submit(input);
            outputList.add(output);
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

        //double[] doubles = Arrays.stream(count).asDoubleStream().toArray();
        return TableMaker.makeInteger("jezyk", languages, "liczba", count);
    }
}
