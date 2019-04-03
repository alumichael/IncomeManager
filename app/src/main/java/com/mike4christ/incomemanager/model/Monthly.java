package com.mike4christ.incomemanager.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static java.util.UUID.randomUUID;

public class Monthly extends RealmObject {




    long total_income;
    long balance;
    long saving_goal;
    String date;
    long amount;
/*
    public Monthly(long total_income, long balance, long saving_goal) {
        this.total_income = total_income;
        this.balance = balance;
        this.saving_goal = saving_goal;

    }*/



    @PrimaryKey
    private String id=randomUUID().toString();




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public long getTotal_income() {
        return total_income;
    }

    public void setTotal_income(long total_income) {
        this.total_income = total_income;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getSaving_goal() {
        return saving_goal;
    }

    public void setSaving_goal(long saving_goal) {
        this.saving_goal = saving_goal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }




}
