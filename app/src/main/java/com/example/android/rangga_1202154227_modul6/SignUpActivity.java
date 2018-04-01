package com.example.android.rangga_1202154227_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    ProgressDialog dlg;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        user = findViewById(R.id.usr); pass = findViewById(R.id.pss);
        dlg = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if(user!=null){
                    Intent movehome = new Intent(SignUpActivity.this, HomeActivity.class);
                    movehome.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    startActivity(movehome);
                }
            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }


    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(listener);
    }


    public void daftar(View view) {

        dlg.setMessage("Creating account. . .");
        dlg.show();


        String inuser = user.getText().toString().trim();
        String inpass = pass.getText().toString().trim();

        if(!TextUtils.isEmpty(inuser)||!TextUtils.isEmpty(inpass)){

            auth.createUserWithEmailAndPassword(inuser, inpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                        Intent movehome = new Intent(SignUpActivity.this, MainActivity.class);
                        movehome.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                        finish();


                    }else{
                        Log.w("Firebase", task.getException());
                        Toast.makeText(SignUpActivity.this, "Account creation failed!", Toast.LENGTH_SHORT).show();
                        user.setText(null); pass.setText(null);
                    }

                    dlg.dismiss();
                }
            });


        }else{
            Toast.makeText(this, "The field is empty", Toast.LENGTH_SHORT).show();
            user.setText(null); pass.setText(null);
        }

    }
}
