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

public class MyViewHolder extends
        RecyclerView.ViewHolder {


    TextView total_income_txt;
    TextView saving_goal_txt;
    TextView balance_txt;
    TextView date;
    ImageView edit_btn,delete_btn,check_expenses_btn;
    //private Context context;


    public MyViewHolder(View itemView) {
        super(itemView);
        total_income_txt=itemView.findViewById(R.id.total_income_txt);
        saving_goal_txt=itemView.findViewById(R.id.saving_goal_txt);
        balance_txt=itemView.findViewById(R.id.balance_txt);
        date=itemView.findViewById(R.id.date_txt);
        //edit_btn=itemView.findViewById(R.id.edit);
        delete_btn=itemView.findViewById(R.id.delete);
        check_expenses_btn=itemView.findViewById(R.id.check_expenses);






    }
}
