package com.mike4christ.incomemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mike4christ.incomemanager.model.Person;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class LoginActivity extends AppCompatActivity  {

    private LoginActivity mContext;
    private EditText email;
    private EditText password;
    private Button login_btn,not_reg;
    private Realm realm;


    TextView user_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       mContext=LoginActivity.this;
       email=findViewById(R.id.email_address);
       password=findViewById(R.id.password);
        login_btn=findViewById(R.id.login_button);
        not_reg=findViewById(R.id.not_register);
        user_auth=findViewById(R.id.user_auth);

        realm = Realm.getDefaultInstance();



        not_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mPassword=password.getText().toString().trim();
                String mEmail=email.getText().toString().trim();



                if((mPassword==null)||(mPassword.equals(""))){
                    Toast.makeText(LoginActivity.this,"Invalid Password",Toast.LENGTH_LONG).show();
                }
                try {

                    auth(mPassword,mEmail);


                }catch (Exception e ){
                    Toast.makeText(LoginActivity.this,"Registration Failed, Try again",Toast.LENGTH_LONG).show();

                }


            }
        });

    }

    private void auth( String mPassword, String mEmail) {
        // Build the query to read currently login users:
        String current_username="";
        String current_user_email="";
        String current_user_id="";

        Person persons = realm.where(Person.class).equalTo("email", mEmail).equalTo("password", mPassword).findFirst();
        current_username += persons.getUsername();
        current_user_email += persons.getEmail();
        current_user_id += persons.getId();

       /* for (Person user : persons) {

            current_username += user.getUsername();
            current_user_email += user.getEmail();
            current_user_id += user.getId();
            }
*/
            if((current_username!="")&&(current_user_email!="")){
                Intent intent=new Intent(mContext,DashBoard.class);
                intent.putExtra("email",current_user_email);
                intent.putExtra("username",current_username);
                intent.putExtra("id",current_user_id);
                startActivity(intent);
            }else{
            Toast.makeText(mContext,"Invalid Input",Toast.LENGTH_LONG).show();
            }



        //user_auth.setText(my_pass);
    }



}
