package com.mike4christ.incomemanager;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mike4christ.incomemanager.Recod_List.MyAdapter;
import com.mike4christ.incomemanager.model.Expenses;
import com.mike4christ.incomemanager.model.Monthly;
import com.mike4christ.incomemanager.model.Person;
import com.mike4christ.incomemanager.model.RealmHelper;
import com.mike4christ.incomemanager.model.Record;

import java.time.Month;
import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class IncomeInput extends Fragment {


    private Realm realm;
    Long total_income,saving_goal,total_balance;
    String date;
    IncomeInput mContext;
    Button total_income_btn,balance_btn,saving_goal_btn;
    String monthly_record_Id;


//Saving instance On orientation change
    private static final String TOTALINCOME = "total_income";
    private static final String SAVING_GOAL = "savingGoal";
    private static final String TOTAL_BALANCE= "total_balance";
    private static final String DATE= "date";



    //ArrayList<String> records;
    MyAdapter adapter;
    RecyclerView recyclerView;

    public IncomeInput() {
        // Required empty public constructor
    }
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            monthly_record_Id = getArguments().getString("id");
        }

    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            outState.putCharSequence(TOTALINCOME, total_income_btn.getText());
            outState.putCharSequence(SAVING_GOAL, saving_goal_btn.getText());
            outState.putCharSequence(TOTAL_BALANCE, balance_btn.getText());
        }catch (Exception e){
            Toast.makeText(getActivity().getBaseContext(),"Okay",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_income_input, container, false);

        realm= Realm.getDefaultInstance();

        final EditText input_income=rootView.findViewById(R.id.input_income);
        final EditText input_saving_goal=rootView.findViewById(R.id.saving_goal);
        final EditText date_input=rootView.findViewById(R.id.set_date);
         final Button total_income_btn=rootView.findViewById(R.id.total_income_btn);
        final Button balance_btn=rootView.findViewById(R.id.balance_btn);
        final Button saving_goal_btn=rootView.findViewById(R.id.saving_goal_btn);

        if (savedInstanceState != null) {

            Long savedTotalIncome = savedInstanceState.getLong(TOTALINCOME);
            total_income_btn.setText(savedTotalIncome.toString());
            total_income = savedTotalIncome;

            Long savedSavingGoal = savedInstanceState.getLong(SAVING_GOAL);
            saving_goal_btn.setText(savedSavingGoal.toString());
            saving_goal = savedSavingGoal;

            Long savedBalance = savedInstanceState.getLong(TOTAL_BALANCE);
            balance_btn.setText(savedBalance.toString());
            total_balance = savedBalance;

            String savedDate = savedInstanceState.getString(DATE);
             date= savedDate;
        }


       //Setup RecyclerView
        recyclerView=rootView.findViewById(R.id.recycler);


        //retrieve data
        final RealmResults<Monthly>results;
        //String title;
        results=realm.where(Monthly.class).findAll().sort("total_income", Sort.DESCENDING);

        //Bind
        adapter=new MyAdapter(results,getActivity().getBaseContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);




        Button save_btn=rootView.findViewById(R.id.save_income);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                total_income=Long.parseLong(input_income.getText().toString().trim());
                saving_goal=Long.parseLong(input_saving_goal.getText().toString().trim());
                date=date_input.getText().toString().trim();
                total_balance=total_income-saving_goal;

                if((total_income.equals(""))||(saving_goal.equals(""))||(date.equals(""))){
                    Snackbar.make(v, "Invalid Input", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                //Getting Data
                Monthly month=new Monthly();
                month.setSaving_goal(saving_goal);
                month.setTotal_income(total_income);
                month.setBalance(total_balance);
                month.setDate(date);

                //Saving Data
                RealmHelper helper=new RealmHelper(realm);
                helper.save_month_record(month);

                Snackbar.make(getView(), "Income saved Successfully, Click List to Add Expenses", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                String naira="#";

                total_income_btn.setText(String.format("%s%s", naira, String.valueOf(month.getTotal_income())));
                saving_goal_btn.setText(String.format("%s%s", naira, String.valueOf(month.getSaving_goal())));
                balance_btn.setText(String.format("%s%s", naira, String.valueOf(month.getBalance())));


                input_income.setText("");
                input_saving_goal.setText("");
                date_input.setText("");

                //refresh
                adapter=new MyAdapter(results,getActivity().getBaseContext());
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getBaseContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);



            }
        });

        return rootView;
    }


}
