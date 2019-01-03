package com.zpoif.soprojekt.api;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

class TableMaker {
    //funkcja tworząca tabele z dwóch kolumn
    static <T> Table make(String nazwA, String[] a, String nazwB, T[] b){
        Table temp = Table.create("nazwa");
        temp.addColumns(StringColumn.create(nazwA , a), DoubleColumn.create(nazwB , (Number[]) b));
        return temp;
    }
}
