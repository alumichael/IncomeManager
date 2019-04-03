package com.mike4christ.incomemanager;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mike4christ.incomemanager.model.Expenses;
import com.mike4christ.incomemanager.model.Monthly;
import com.mike4christ.incomemanager.model.RealmHelper;
import com.mike4christ.incomemanager.model.Record;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;


public class Expenses_Input extends Fragment {

    public Expenses_Input() {
        // Required empty public constructor
    }

    private Spinner expensesSpinner;
    private EditText amount;
    private Button expenses_btn;



    public int mExpenses;
    public Long amount_input;
    private  Expenses_Input context;

    public static final int Food = 0;
    public static final int Cloth = 1;
    public static final int Study = 2;
    public static final int House = 3;
    public static final int Health = 4;
    public static final int Communication = 5;
    public static final int Religion = 6;
    public static final int Transport = 7;
    public static final int others = 8;
    Realm realm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_expenses__input, container, false);
        TextView txtclose;
        txtclose=rootView.findViewById(R.id.close);
        expensesSpinner=rootView.findViewById(R.id.expenses_spinner);
        amount=rootView.findViewById(R.id.amount);
        expenses_btn=rootView.findViewById(R.id.expenses_btn);
        realm= Realm.getDefaultInstance();
        context=Expenses_Input.this;

        setupSpinner();

        expenses_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IncomeInput incomeInput=new IncomeInput();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                // remove the SimpleFragment.
                fragmentTransaction.replace(R.id.fragment_container,
                        incomeInput).commit();



              /*  realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm bgRealm) {
                        Expenses expenses_input=bgRealm.createObject(Expenses.class);

                        expenses_input.setAmount(amount_input);
                        expenses_input.setExpenses(mExpenses);


                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {



                        Log.v("Database",">>>>>>store>>>>Okay");
                        Snackbar.make(getView(), "Expenses Added Succesfully ", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        Expenses_Input expenses_input=new Expenses_Input();
                        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
                                .beginTransaction();

                        // remove the SimpleFragment.
                        fragmentTransaction.remove(
                                expenses_input).commit();


                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        // Transaction failed and was automatically canceled.
                        Log.e("Database",error.getMessage());
                        Snackbar.make(getView(), "Invalid Input", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
*/

            }
        });

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expenses_Input expenses_input=new Expenses_Input();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                // remove the SimpleFragment.
                fragmentTransaction.remove(
                        expenses_input).addToBackStack("expenses_input").commit();
            }
        });
return rootView;
    }

    private void setupSpinner() {

        ArrayAdapter<CharSequence>  expensesAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.expenses, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        expensesAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        expensesSpinner.setAdapter(expensesAdapter);

        expensesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.food))) {
                        mExpenses = Food;

                    } else if (selection.equals(getString(R.string.cloth))) {
                        mExpenses = Cloth;
                    }else if (selection.equals(getString(R.string.house))) {
                        mExpenses = House;
                    }else if (selection.equals(getString(R.string.study))) {
                        mExpenses = Study;
                    }else if (selection.equals(getString(R.string.health))) {
                        mExpenses = Health;
                    }else if (selection.equals(getString(R.string.communication))) {
                        mExpenses = Communication;
                    }else if (selection.equals(getString(R.string.Religion))) {
                        mExpenses = Religion;
                    } else if (selection.equals(getString(R.string.transport))) {
                        mExpenses = Transport;
                    }else {
                        mExpenses = others;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                mExpenses = Food;
            }
        });



    }


    }

