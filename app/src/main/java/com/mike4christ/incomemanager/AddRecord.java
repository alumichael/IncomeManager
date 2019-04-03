package com.mike4christ.incomemanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mike4christ.incomemanager.model.Monthly;
import com.mike4christ.incomemanager.model.Person;
import com.mike4christ.incomemanager.model.Record;

import java.util.UUID;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddRecord extends Fragment {
    Realm realm=Realm.getDefaultInstance();

    public AddRecord() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView =inflater.inflate(R.layout.fragment_add_record, container, false);


        final Button monthly_btn=rootView.findViewById(R.id.monthly_btn);
        final Button yearly_btn=rootView.findViewById(R.id.yearly_btn);
        final Button weekly_btn=rootView.findViewById(R.id.weekly_btn);
        final Button daily_btn=rootView.findViewById(R.id.daily_btn);


        monthly_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IncomeInput incomeInput =new IncomeInput();

                FragmentManager fragmentManager =
                       getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                // Add the SimpleFragment.
                fragmentTransaction.replace(R.id.fragment_container,
                        incomeInput).commit();



            }
        });
        yearly_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IncomeInput incomeInput =new IncomeInput();

                FragmentManager fragmentManager =
                        getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                // Add the SimpleFragment.
                fragmentTransaction.replace(R.id.fragment_container,
                        incomeInput).commit();



            }
        });
        weekly_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IncomeInput incomeInput =new IncomeInput();

                FragmentManager fragmentManager =
                        getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                // Add the SimpleFragment.
                fragmentTransaction.replace(R.id.fragment_container,
                        incomeInput).commit();



            }
        });
        daily_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IncomeInput incomeInput =new IncomeInput();

                FragmentManager fragmentManager =
                        getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                // Add the SimpleFragment.
                fragmentTransaction.replace(R.id.fragment_container,
                        incomeInput).addToBackStack("incomeInput").commit();



            }
        });
        return  rootView;

    }


}
