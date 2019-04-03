package com.mike4christ.incomemanager.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static java.util.UUID.randomUUID;

public class Record extends RealmObject {



    @PrimaryKey
    private String id = randomUUID().toString();
    RealmList<Monthly> monthly;
    RealmList<Yearly> yearly;
    RealmList<Weekly> weekly;
    RealmList<Daily> daily;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public RealmList<Monthly> getMonthly() {
        return monthly;
    }

    public void setMonthly(RealmList<Monthly> monthly) {
        this.monthly = monthly;
    }

    public RealmList<Yearly> getYearly() {
        return yearly;
    }

    public void setYearly(RealmList<Yearly> yearly) {
        this.yearly = yearly;
    }

    public RealmList<Weekly> getWeekly() {
        return weekly;
    }

    public void setWeekly(RealmList<Weekly> weekly) {
        this.weekly = weekly;
    }

    public RealmList<Daily> getDaily() {
        return daily;
    }

    public void setDaily(RealmList<Daily> daily) {
        this.daily = daily;
    }


    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", monthly=" + monthly +
                ", yearly=" + yearly +
                ", weekly=" + weekly +
                ", daily=" + daily +
                '}';
    }



}
