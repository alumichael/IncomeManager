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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.mike4christ.incomemanager.ExpensesActivity;
import com.mike4christ.incomemanager.R;
import com.mike4christ.incomemanager.model.Expenses;
import com.mike4christ.incomemanager.model.Monthly;

import java.time.Month;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class ExpenseAdapter extends RealmRecyclerViewAdapter<Expenses,ExpenseViewHolder>{


    Context context;

    Realm realm;

    TextView expense;
    TextView amount;
    ImageView thumbnail_image;
    ImageView delete_btn;

    private ColorGenerator mColorGenerator = ColorGenerator.DEFAULT;
    private TextDrawable mDrawableBuilder;


    public ExpenseAdapter(RealmResults<Expenses>list , Context context) {
        super(list,true,true);
        this.context=context;
        realm=Realm.getDefaultInstance();

    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.expense_model,parent,false);

        return new ExpenseViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ExpenseViewHolder holder, int position) {

        String letter = "A";
        final  Expenses temp=getItem(position);
        int test=temp.getExpenses();
        try{

            switch (test) {
                case 0:
                    holder.expense.setText("Food");
                    letter = "FO";
                    break;
                case 1:
                    holder.expense.setText("Cloth");
                    letter = "CL";
                    break;
                case 2:
                    holder.expense.setText("Study");
                    letter = "ST";
                    break;
                case 3:
                    holder.expense.setText("House");
                    letter = "HO";
                    break;
                case 4:
                    holder.expense.setText("Health");
                    letter = "HE";
                    break;
                case 5:
                    holder.expense.setText("Communication");
                    letter = "CO";
                    break;
                case 6:
                    holder.expense.setText("Religion");
                    letter = "RE";
                    break;
                case 7:
                    holder.expense.setText("Transport");
                    letter = "TR";
                    break;
                case 8:
                    holder.expense.setText("Others");
                    letter = "OT";
                    break;
                default:
                    holder.expense.setText("Null Input");
                    letter = "NU";
                    break;

            }

        holder.amount.setText(String.valueOf(temp.getAmount()));

        int color = mColorGenerator.getRandomColor();
        // Create a circular icon consisting of  a random background colour and first letter of title
        mDrawableBuilder = TextDrawable.builder()
                .buildRound(letter, color);
        holder.thumbnail_image.setImageDrawable(mDrawableBuilder);

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!temp.isValid()){
                    return;
                }
                asyncRemoveRecord(temp.getForeignKey());
            }
        });
        }catch (Exception e){
            Toast.makeText(context,"Waiting Input",Toast.LENGTH_LONG).show();
        }

    }


    //To Delete Record
    private void asyncRemoveRecord(final String id){
        AsyncTask<Void,Void,Void>remoteItem =new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                realm=Realm.getDefaultInstance();

                Expenses expenses=realm.where(Expenses.class).equalTo("foreignKey",id).findFirst();
                if(expenses !=null){
                    realm.beginTransaction();
                    expenses.deleteFromRealm();
                    realm.commitTransaction();
                }
                realm.close();
                return null;
            }
        };
        remoteItem.execute();
    }




}
