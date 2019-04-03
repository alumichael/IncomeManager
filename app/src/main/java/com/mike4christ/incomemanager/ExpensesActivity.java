package com.mike4christ.incomemanager;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mike4christ.incomemanager.Recod_List.ExpenseAdapter;
import com.mike4christ.incomemanager.Recod_List.MyAdapter;
import com.mike4christ.incomemanager.model.Expenses;
import com.mike4christ.incomemanager.model.Monthly;
import com.mike4christ.incomemanager.model.RealmHelper;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class ExpensesActivity extends AppCompatActivity {
Realm realm;
Context context;
    private Spinner expensesSpinner;
    private EditText amount;
    private Button expenses_btn;
    TextView txtclose,date,balance;
    Long total_balance;

    ExpenseAdapter adapter;
    RecyclerView recyclerView;

    public int mExpenses;
    public Long amount_input;


    public static final int Food = 0;
    public static final int Cloth = 1;
    public static final int Study = 2;
    public static final int House = 3;
    public static final int Health = 4;
    public static final int Communication = 5;
    public static final int Religion = 6;
    public static final int Transport = 7;
    public static final int others = 8;
    String monthId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        monthId = getIntent().getStringExtra("id");
        realm = Realm.getDefaultInstance();
        date=findViewById(R.id.date);
        balance=findViewById(R.id.balance);
        try {
            Monthly monthly = realm.where(Monthly.class).equalTo("id", monthId).findFirst();

                date.setText(monthly.getDate());
                total_balance=monthly.getBalance();
                balance.setText(String.format("#%s", String.valueOf(total_balance)));




        } finally {
            realm.close();
        }

        //Setup RecyclerView
        recyclerView=findViewById(R.id.expense_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Retrieve
        final RealmResults<Expenses>results;
        //String title;
        results=realm.where(Expenses.class).findAll().sort("amount", Sort.DESCENDING);

        //Bind
        adapter=new ExpenseAdapter(results,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogExpense();

            }
        });


      }
      public void dialogExpense(){
          final Dialog d=new Dialog(this);
          d.setTitle("Expenses");
          d.setContentView(R.layout.fragment_expenses__input);
          txtclose=d.findViewById(R.id.close);
          txtclose.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  d.cancel();
              }
          });
          expensesSpinner=d.findViewById(R.id.expenses_spinner);
          amount=d.findViewById(R.id.amount);

          expenses_btn=d.findViewById(R.id.expenses_btn);
          setupSpinner();
          expenses_btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  amount_input= Long.valueOf(amount.getText().toString());

                  if(amount_input.toString().equals("")){
                      Snackbar.make(v, "Invalid Input", Snackbar.LENGTH_LONG)
                              .setAction("Action", null).show();
                  }


                  //Getting Data
                  Expenses expenses=new Expenses();
                  expenses.setForeignKey(monthId);
                  expenses.setAmount(amount_input);
                  expenses.setExpenses(mExpenses);

                  //saving expenses
                  RealmHelper helper=new RealmHelper(realm);
                  helper.save_expenses_record(expenses);

                  Snackbar.make(v, "Expense Added Successfully and Priotized, You can delete at any time !", Snackbar.LENGTH_LONG)
                          .setAction("Action", null).show();
                  total_balance=total_balance-amount_input;
                  balance.setText(String.format("#%s", String.valueOf(total_balance)));
                  amount.setText("");


                  //refresh data
                  final RealmResults<Expenses> results;
                  results=realm.where(Expenses.class).findAll().sort("expenses",Sort.DESCENDING);

                  long amt=0;
                  for(Expenses exp: results){

                      amt+=exp.getAmount();
                      balance.setText(String.format("#%s", String.valueOf(total_balance)));
                  }

//refresh
                  adapter=new ExpenseAdapter(results,ExpensesActivity.this);

                  recyclerView.setAdapter(adapter);



              }
          });


          d.show();

      }


    private void setupSpinner() {

        ArrayAdapter<CharSequence> expensesAdapter = ArrayAdapter.createFromResource(this,
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
