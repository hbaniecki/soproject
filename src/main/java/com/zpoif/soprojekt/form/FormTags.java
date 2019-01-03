package com.zpoif.soprojekt.form;


import javax.validation.constraints.NotNull;

public class FormTags {

    @NotNull
    @DateConstraint
    private String fromDate;

    @NotNull
    @DateConstraint
    private String toDate;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

}
