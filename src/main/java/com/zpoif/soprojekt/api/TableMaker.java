package com.zpoif.soprojekt.api;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

class TableMaker {
    //funkcja tworząca tabele z dwóch kolumn
    static Table makeInteger(String nazwA, String[] a, String nazwB, Integer[] b){
        Table temp = Table.create("nazwa");
        temp.addColumns(StringColumn.create(nazwA , a), DoubleColumn.create(nazwB , b));

        return temp;
    }

    static Table makeString(String nazwA, String[] a, String nazwB, String[] b){
        Table temp = Table.create("nazwa");
        temp.addColumns(StringColumn.create(nazwA , a), StringColumn.create(nazwB , b));

        return temp;
    }
}
