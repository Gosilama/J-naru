package com.gosilama.journal.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gosilama.journal.R;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText userEmail;
    private EditText userPassword;

    private EditText registerEmail;
    private EditText registerPassword;
    private EditText registerConfirmPassword;

    private String email;
    private String password;

    private Button signInButton;
    private Button signUpButton;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();

        userEmail = findViewById(R.id.input_email);
        userPassword = findViewById(R.id.input_password);

        signInButton = findViewById(R.id.sign_in_button);
        signUpButton = findViewById(R.id.sign_up_button);

        signInUser();
        signUpUser();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            goToJournalEntryList();
        }
    }

    private void signInUser() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!TextUtils.isEmpty(userEmail.getText())
                        && !TextUtils.isEmpty(userPassword.getText())) {

                    email = userEmail.getText().toString();
                    password = userPassword.getText().toString();

                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignInActivity.this,
                                    new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),
                                                "Welcome",
                                                Toast.LENGTH_LONG).show();

                                        goToJournalEntryList();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please fill all fields",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void signUpUser() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRegistrationDialog();
                registerUser();
            }
        });
    }

    private void registerUser() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(registerEmail.getText())
                        && !TextUtils.isEmpty(registerPassword.getText())
                        && registerConfirmPassword.getText().toString().equals(registerPassword.getText().toString())) {

                    email = registerEmail.getText().toString();
                    password = registerPassword.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignInActivity.this,
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(),
                                                        R.string.registration_successful,
                                                        Toast.LENGTH_LONG).show();

                                                goToJournalEntryList();
                                            } else {
                                                Toast.makeText(getApplicationContext(),
                                                        "Authentication Failed",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please fill all fields",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createRegistrationDialog() {
        View view = getLayoutInflater().inflate(R.layout.registration_popup, null);

        registerEmail = view.findViewById(R.id.register_email);
        registerPassword = view.findViewById(R.id.register_password);
        registerConfirmPassword = view.findViewById(R.id.register_confirm_password);
        registerButton = view.findViewById(R.id.register_button);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this).setView(view);
        AlertDialog registrationDialog = alertDialogBuilder.create();
        registrationDialog.show();
    }

    private void goToJournalEntryList() {
        Intent intent = new Intent(getApplicationContext(), JournalListActivity.class);
        startActivity(intent);
    }
}
