package com.zpoif.soprojekt.api;

import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.StackExchangeSite;
import com.google.code.stackexchange.schema.TimePeriod;

import tech.tablesaw.api.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class LanguageTagCounter{
    private final  String[] languages = {"java", "c", "c++", "python", "c#", "javascript", ".net", "r", "php", "matlab",
            "swift", "objective-c", "assembly", "perl", "ruby", "delphi", "go", "mit-scratch", "sql", "vb.net"};
    private Integer[] count = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    /*private final  String[] languages = {"java", "c", "c++", "python", "c#", "javascript"};
    private int[] count = {0,0,0,0,0,0};*/

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
            int j=1;
            int size;
            Integer counter = 0;
            while(true) {
                Paging paging = new Paging(j, 100);
                String filter = "default";
                size = queryFactory.newQuestionApiQuery().withTimePeriod(timePeriod)
                        .withPaging(paging).withFilter(filter)
                        .withTags(tag).list().size();
                if (size == 100) {
                    counter += size;
                    j+=1;
                    try {
                        Thread.sleep(6);  // musi być, żeby nie blokowało adresu ip, bo za dużo zapytań
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
            LanguageThread input = new LanguageThread(tag);
            Future<Integer> output = executor.submit(input);
            outputList.add(output);
        }

        executor.shutdown();

        int i = 0;
        for(Future<Integer> output : outputList) {
            try {
                count[i] = output.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            i++;
        }

        /*Table data = Table.create("LanguageTagData").setName("Udział najpopularniejszych języków programowania w pytaniach na stronie");
        data.addColumns(StringColumn.create("jezyk",languages), DoubleColumn.create("liczba",count));*/
        //double[] doubles = Arrays.stream(count).asDoubleStream().toArray();
        return TableMaker.make("jezyk", languages, "liczba", count);
    }
}
