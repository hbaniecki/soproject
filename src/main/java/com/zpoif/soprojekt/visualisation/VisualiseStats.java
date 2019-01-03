package com.zpoif.soprojekt.visualisation;

import com.zpoif.soprojekt.api.SiteStatsGetter;
import tech.tablesaw.api.Table;

public class VisualiseStats {

    public static String visualise(){
        String message;

        Table testData = SiteStatsGetter.receiveData();
        message = testData.printHtml();

        return message;
    }
}
