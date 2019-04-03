package com.mike4christ.incomemanager.model;

import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper  {



    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }
    public void save_month_record(final Monthly monthly){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Monthly month=realm.copyToRealm(monthly);


            }
        });

    }
    public void save_expenses_record(final Expenses expenses){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Expenses expense=realm.copyToRealm(expenses);



            }
        });

    }


    public ArrayList<String> retrieve(){
        ArrayList<String> records=new ArrayList<>();

        RealmResults<Monthly> monthly = realm.where(Monthly.class).findAll();

        String income="  ";
        for(Monthly month: monthly){
            //records.add(month.getId());
            records.add(String.valueOf(month.getTotal_income()));
            records.add(String.valueOf(month.getSaving_goal()));
            records.add(String.valueOf(month.getBalance()));
            records.add(String.valueOf(month.getDate()));

            income+=String.valueOf(month.getTotal_income());
        }
        Log.v("Database", income);

        return records;
    }



}

