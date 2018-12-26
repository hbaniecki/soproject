package com.zpoif.soprojekt.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.*;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.PiePlot;

import java.util.Date;


public class testmain {
    public static void main(String args[]) {

        //LanguageTagCounter test
        /*TimePeriod timePeriod = new TimePeriod(new Date(1514761200000L), new Date(1543618800000L));
        Table testData = new LanguageTagCounter().receiveData(timePeriod);

        System.out.print(testData);
        //System.out.print(testData.printHtml());

        Plot.show(
                PiePlot.create("Dane", testData, "jezyk", "liczba"));*/

        // API test
        /*StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory
                .newInstance("4ccOeYINdrpbkpWOgBTZSw((",
                        StackExchangeSite.STACK_OVERFLOW);

        String filter = "default";
        String tag = "python";
        TimePeriod timePeriod = new TimePeriod(new Date(1543622400000L), new Date(1543622400000L));
        Paging paging = new Paging(1, 100); // pageNumber gets u next page
        PagedList<Question> questions = queryFactory.newQuestionApiQuery().withTimePeriod(timePeriod)
                .withPaging(paging).withFilter(filter)
                .withSort(Question.SortOrder.MOST_VOTED) //MOST_HOT
                .withTags(tag).list();

        System.out.println(questions.size());
        for(int i=1; i < questions.size(); i++){
            Question a = questions.get(i);
            System.out.print(a.getCreationDate());
            //System.out.println(a.getBody()); // null :C
            System.out.print(" View Count:" + a.getViewCount());
            System.out.print(" Score:" + a.getScore());
            //System.out.print(a.getAnswers());
            //System.out.print(a.getTags());
            System.out.println("  " + a.getTitle());

        }*/


        // przykład korzystający z API tags - pokazuje że to query nie działa
        /*StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory
                .newInstance("4ccOeYINdrpbkpWOgBTZSw((",
                        StackExchangeSite.STACK_OVERFLOW);

        TimePeriod timePeriod = new TimePeriod(new Date(1543622400000L), new Date(1545004800000L));
        Paging paging = new Paging(1, 100); // pageNumber gets u next page

        PagedList<Tag> tags = queryFactory.newTagApiQuery().withTimePeriod(timePeriod).withPaging(paging).withFilter("default").list();
        for(int i=1; i < tags.size(); i++){
            Tag a = tags.get(i);
            System.out.println(a.getName());
            System.out.println("Count:" + a.getCount());
        }*/

    }
}
