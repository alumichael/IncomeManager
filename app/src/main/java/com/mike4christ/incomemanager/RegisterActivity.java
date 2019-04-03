package com.mike4christ.incomemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.UUID;
import com.mike4christ.incomemanager.model.Person;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RegisterActivity extends AppCompatActivity {


    private RegisterActivity mContext;
    private EditText firstname;
    private EditText lastname;
    private EditText username;
    private EditText password;
    private EditText email;
    private Button register_button;
    private Button already_reg_btn;

    //test
    TextView user_log;
    private Realm realm;
    //Declaration of string for the input get
    String mFirstName,mLastName,mUsername,mPassword,mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext=RegisterActivity.this;
        firstname=findViewById(R.id.firstName);
        lastname=findViewById(R.id.lastName);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email_address);
        register_button=findViewById(R.id.register_button);
        already_reg_btn=findViewById(R.id.already_reg_btn);


        //instanciate Realm DB
        realm=Realm.getDefaultInstance();



        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirstName=firstname.getText().toString().trim();
                mLastName=lastname.getText().toString().trim();
                mUsername=username.getText().toString().trim();
                mPassword=password.getText().toString().trim();
                mEmail=email.getText().toString().trim();

                if((mPassword==null)||(mPassword.equals(""))){
                    Toast.makeText(RegisterActivity.this,"Invalid Password",Toast.LENGTH_LONG).show();
                }
                try {
                    save_into_db(mFirstName,mLastName,mUsername,mPassword,mEmail);


                }catch (Exception e ){
                    Toast.makeText(RegisterActivity.this,"Registration Failed, Try again",Toast.LENGTH_LONG).show();

                }


            }
        });



    }



    private void save_into_db( final String mFirstName, final String mLastName, final String mUsername, final String mPassword, final String mEmail) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Person person=bgRealm.createObject(Person.class, UUID.randomUUID().toString());
                person.setFirstname(mFirstName);
                person.setLastname(mLastName);
                person.setUsername(mUsername);
                person.setEmail(mEmail);
                person.setPassword(mPassword);


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                Log.v("Database",">>>>>>store>>>>Okay");

                Intent intent=new Intent(mContext,LoginActivity.class);
                startActivity(intent);

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.e("Database",error.getMessage());
                Toast.makeText(RegisterActivity.this,"Registration Failed, Try again",Toast.LENGTH_LONG).show();
            }
        });

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
