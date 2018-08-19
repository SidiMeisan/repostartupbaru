package com.example.yogapermanatanaya.sewain;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnRegister;
    private EditText editTextEmail, editTextPassword;
    private TextView textViewSignIn;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(MainActivity.this);

        btnRegister=(Button)findViewById(R.id.btnRegister);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        textViewSignIn=(TextView)findViewById(R.id.textViewSignIn);

        btnRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

    }


    //Registrasi
    public void regiterUser(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email kosong
            Toast.makeText(this, "Tolong Masukkan Email Anda",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password kosong
            Toast.makeText(this, "Tolong Masukkan Password Anda",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Mendaftarkan User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(MainActivity.this, "Registrasi Berhasil...",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    ToIntent();

                }else{

                    Toast.makeText(MainActivity.this, "Registrasi Gagal, Harap Ulangi",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });
    }

    private void ToIntent() {

        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onClick(View view){
        if(view==btnRegister){
            regiterUser();
        }
        if(view==textViewSignIn){
            ToIntent();
        }
    }
}
