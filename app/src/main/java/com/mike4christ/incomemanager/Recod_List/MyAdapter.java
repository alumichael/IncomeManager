package com.mike4christ.incomemanager.Recod_List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mike4christ.incomemanager.ExpensesActivity;
import com.mike4christ.incomemanager.R;
import com.mike4christ.incomemanager.model.Expenses;
import com.mike4christ.incomemanager.model.Monthly;

import java.time.Month;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class MyAdapter extends RealmRecyclerViewAdapter<Monthly,MyViewHolder>{


    Context context;

    Realm realm;
    long income_total;
    Long total_income,saving_goal,total_balance;
    String date;

    EditText income_total_update,saving_goal_update,date_update;
    Button update_btn;

    public MyAdapter(RealmResults<Monthly>list , Context context) {
        super(list,true,true);
        this.context=context;
        realm=Realm.getDefaultInstance();

    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.model,parent,false);

        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
           final  Monthly temp=getItem(position);
        holder.total_income_txt.setText(String.valueOf(temp.getTotal_income()));
        holder.saving_goal_txt.setText(String.valueOf(temp.getSaving_goal()));
        holder.balance_txt.setText(String.valueOf(temp.getBalance()));
        holder.date.setText(temp.getDate());
        holder.check_expenses_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!temp.isValid()){
                    return;
                }

                // Assuming we had a person class with a @PrimaryKey on the 'id' field ...
                Intent intent = new Intent(context, ExpensesActivity.class);
                intent.putExtra("id", temp.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!temp.isValid()){
                    return;
                }
                asyncRemoveRecord(temp.getId());
            }
        });

    }



    //To Delete Record
    private void asyncRemoveRecord(final String id){
        AsyncTask<Void,Void,Void>remoteItem =new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                realm=Realm.getDefaultInstance();

                Monthly monthly=realm.where(Monthly.class).equalTo("id",id).findFirst();
                if(monthly !=null){
                    realm.beginTransaction();
                    monthly.deleteFromRealm();
                    realm.commitTransaction();
                }
                realm.close();
                return null;
            }
        };
        remoteItem.execute();
    }



}
