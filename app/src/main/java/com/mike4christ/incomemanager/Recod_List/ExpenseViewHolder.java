package com.mike4christ.incomemanager.Recod_List;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mike4christ.incomemanager.ExpensesActivity;
import com.mike4christ.incomemanager.R;
import com.mike4christ.incomemanager.model.Monthly;

public class ExpenseViewHolder extends RecyclerView.ViewHolder {


    TextView expense;
    TextView amount;
    ImageView thumbnail_image;
    ImageView delete_btn;



    public ExpenseViewHolder(View itemView) {
        super(itemView);
        expense = (TextView) itemView.findViewById(R.id.expenses_txt);
        amount=(TextView)itemView.findViewById(R.id.expense_amount);
        thumbnail_image = (ImageView) itemView.findViewById(R.id.thumnail);
        delete_btn=itemView.findViewById(R.id.delete_expense);

    }
}
