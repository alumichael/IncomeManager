package com.mike4christ.incomemanager.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Weekly extends RealmObject {


    long total_income;
    long balance;
    long saving_goal;
    long date;
    long amount;
    RealmList<Expenses> expenses;

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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public RealmList<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(RealmList<Expenses> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "Monthly{" +
                "total_income=" + total_income +
                ", balance=" + balance +
                ", saving_income=" + saving_goal +
                ", date=" + date +
                ", amount=" + amount +
                ", expenses=" + expenses +
                '}';
    }


}
