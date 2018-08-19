package com.example.yogapermanatanaya.sewain;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnRegister;
    private EditText editTextEmail, editTextPassword, editTextName, editTextKtp;
    private EditText editTextPhone, editTextAlamat;
    private RadioButton radioButton;
    private RadioGroup radioGroup;


    private TextView textViewSignIn;

    private FirebaseAuth firebaseAuth;


    private ProgressDialog progressDialog;
    private ProgressBar progressBar;

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null){
            //handle already login user...

        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressBar=new ProgressBar(SignUpActivity.this);

        btnRegister=(Button)findViewById(R.id.btnRegister);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextPhone=(EditText)findViewById(R.id.editTextPhone);

        textViewSignIn=(TextView)findViewById(R.id.textViewSignIn);

        btnRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);




    }


    //Registrasi
    public void registerUser(){
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();



        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    email,
                                    phone
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "Registrasi Berhasil...", Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void ToIntent() {

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View view){
        if(view==btnRegister){
            registerUser();
        }
        if(view==textViewSignIn){
            ToIntent();
        }
    }
}
