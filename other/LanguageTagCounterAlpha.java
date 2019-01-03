package com.zpoif.soprojekt.api;

import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.StackExchangeSite;
import com.google.code.stackexchange.schema.TimePeriod;
import tech.tablesaw.api.DoubleColumn;

import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;


public class LanguageTagCounterAlpha {
    /*private final  String[] languages = {"java", "c", "c++", "python", "c#", "javascript", ".net", "r", "php", "matlab",
            "swift", "objective-c", "assembly", "perl", "ruby", "delphi", "go", "mit-scratch", "sql", "vb.net"};
    private int[] count = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};*/

    private final  String[] languages = {"java", "c", "c++", "python", "c#", "javascript"};
    private int[] count = {0,0,0,0,0,0};


    public Table receiveData(TimePeriod timePeriod){
        Table data = Table.create("LanguageTagData");

        final StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory
                .newInstance("4ccOeYINdrpbkpWOgBTZSw((",
                        StackExchangeSite.STACK_OVERFLOW);
        String filter = "default";
        int counter = 0;
        int size;

        for(int i=0; i < languages.length; i++) {
            String tag = languages[i];
            System.out.println(tag);
            int j=1;
            while(true) {
                Paging paging = new Paging(j, 100);

                /*System.out.println(queryFactory.newQuestionApiQuery().withTimePeriod(timePeriod)
                        .withPaging(paging).withFilter(filter).withSort(Question.SortOrder.MOST_RECENTLY_CREATED)
                        .withTags(tag).list().get(1).getCreationDate());
                System.out.println(queryFactory.newQuestionApiQuery().withTimePeriod(timePeriod)
                        .withPaging(paging).withFilter(filter).withSort(Question.SortOrder.MOST_RECENTLY_CREATED)
                        .withTags(tag).list().get(1).getTags());*/

                size = queryFactory.newQuestionApiQuery().withTimePeriod(timePeriod)
                        .withPaging(paging).withFilter(filter)
                        .withTags(tag).list().size();
                if (size == 100) {
                    counter += size;
                    j+=1;
                } else {
                    counter += size;
                    count[i] = counter;
                    counter = 0;
                    break;
                }
            }
        }

        data.addColumns(StringColumn.create("jezyk",languages), DoubleColumn.create("liczba",count));

        return data;
    }



}
