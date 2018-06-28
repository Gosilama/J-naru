package com.gosilama.journal.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private Button signInButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();

        userEmail = findViewById(R.id.input_email);
        userPassword = findViewById(R.id.input_password);

        signInButton = findViewById(R.id.sign_in_button);
        registerButton = findViewById(R.id.register_button);

        signInUser();
        registerUser();
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
                goToJournalEntryList();
            }
        });
    }

    private void registerUser() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRegisterDialog();

                if (!TextUtils.isEmpty(registerEmail.getText())
                        && !TextUtils.isEmpty(registerPassword.getText())
                        && registerConfirmPassword.getText().equals(registerPassword.getText())) {
                    Toast.makeText(getApplicationContext(),
                            R.string.registration_successful,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createRegisterDialog() {
        View view = getLayoutInflater().inflate(R.layout.registration_popup, null);

        registerEmail = view.findViewById(R.id.register_email);
        registerPassword = view.findViewById(R.id.register_password);
        registerConfirmPassword = view.findViewById(R.id.register_confirm_password);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this).setView(view);
        AlertDialog registrationDialog = alertDialogBuilder.create();
        registrationDialog.show();
    }

    private void goToJournalEntryList() {
        Intent intent = new Intent(getApplicationContext(), JournalListActivity.class);
        startActivity(intent);
    }
}
