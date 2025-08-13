package com.example.adsmobapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Button btnRegister;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering...");

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("Enter Name");
                return;
            }
            if (email.isEmpty()) {
                etEmail.setError("Enter Email");
                return;
            }
            if (password.isEmpty()) {
                etPassword.setError("Enter Password");
                return;
            }

            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String uid = mAuth.getCurrentUser().getUid();

                            // Create user object
                            HashMap<String, Object> userMap = new HashMap<>();
                            userMap.put("name", name);
                            userMap.put("email", email);

                            // Save to Realtime Database
                            databaseReference.child(uid).setValue(userMap)
                                    .addOnCompleteListener(dbTask -> {
                                        progressDialog.dismiss();
                                        if (dbTask.isSuccessful()) {
                                            Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Register.this, MainActivity.class));
                                            finish();
                                        } else {
                                            Toast.makeText(Register.this, "Database Error: " + dbTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Register.this, "Auth Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}
