package com.example.myfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Form extends AppCompatActivity {

    Button b;
    EditText phoneNo;
    EditText email;
    EditText pwd;
    EditText name;
    RadioButton r1, r2;
    RadioGroup rg;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.input_email);
        pwd = (EditText) findViewById(R.id.input_password);
        name = (EditText) findViewById(R.id.input_name);
        phoneNo = (EditText) findViewById(R.id.input_mobileNo);
        r1 = (RadioButton) findViewById(R.id.f);
        r2  = (RadioButton) findViewById(R.id.m);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        b = findViewById(R.id.btn_SignUp);

        mAuth = FirebaseAuth.getInstance();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailId = email.getText().toString();
                String password = pwd.getText().toString();
                final String mobileNo = phoneNo.getText().toString();
                final String userName = name.getText().toString();

                if(emailId.isEmpty()||password.isEmpty()||userName.isEmpty()||mobileNo.isEmpty())
                {
                    Toast.makeText(SignUp_Form.this,"One or more fields is empty, kindly fill them", Toast.LENGTH_SHORT).show();
                }
                else if(!(r1.isChecked()) && !(r2.isChecked()))
                {
                    Toast.makeText(SignUp_Form.this, "Please select a community", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(emailId, password).addOnCompleteListener(SignUp_Form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(SignUp_Form.this, "SignUp unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
                            }
                            else if(r1.isChecked())
                            {
                                Toast.makeText(SignUp_Form.this, "Creating account, Please wait", Toast.LENGTH_SHORT).show();

                                User user = new User(
                                        userName,
                                        emailId,
                                        mobileNo
                                );

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(!task.isSuccessful())
                                        {
                                            Toast.makeText(SignUp_Form.this, "Account Creation Failed, Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(SignUp_Form.this, "Account Created", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                Intent intent = new Intent(SignUp_Form.this,userHome.class);


                                startActivity(intent);
                            }
                        }
                    });
                }

            }
        });
    }
}
