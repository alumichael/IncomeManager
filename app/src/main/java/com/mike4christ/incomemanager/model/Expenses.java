package com.mike4christ.incomemanager.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Expenses extends RealmObject{

    int expenses;
    long amount;


    String foreignKey;


    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }






}
