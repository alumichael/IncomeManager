package com.mike4christ.incomemanager;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mike4christ.incomemanager.model.Monthly;
import com.mike4christ.incomemanager.model.Record;

import io.realm.Realm;
import io.realm.RealmResults;


public class History extends Fragment {

    Realm realm;

    public History() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        final Button delete_record = rootView.findViewById(R.id.delete_record_btn);


        delete_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RealmResults<Monthly> results = realm.where(Monthly.class).findAll();

// All changes to data must happen in a transaction
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        // Delete all matches

                        results.deleteAllFromRealm();
                        Toast.makeText(getActivity().getBaseContext(), "Record Deleted", Toast.LENGTH_LONG).show();
                    }
                });

            }


        });
        return rootView;
    }
}

