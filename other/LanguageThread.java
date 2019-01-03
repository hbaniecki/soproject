/*
package com.zpoif.soprojekt.api;

import com.google.code.stackexchange.schema.Paging;

public class LanguageThread extends Thread {
    public int counter;
    public String tag;

    public LanguageThread(String tag) {
        this.tag = tag;
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void run(){
        int j=1;
        int size;
        while(true) {
            Paging paging = new Paging(j, 100);
            size = queryFactory.newQuestionApiQuery().withTimePeriod(timePeriod)
                    .withPaging(paging).withFilter(filter)
                    .withTags(tag).list().size();
            if (size == 100) {
                this.counter += size;
                j+=1;
            } else {
                this.counter += size;
                break;
            }
        }
    }
}
*/
