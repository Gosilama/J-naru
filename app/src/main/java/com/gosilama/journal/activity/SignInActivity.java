package com.gosilama.journal.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gosilama.journal.R;

public class SignInActivity extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog registrationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button signInButton = findViewById(R.id.sign_in_button);
        Button registerButton = findViewById(R.id.register_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JournalListActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRegisterDialog();
            }
        });
    }

    private void createRegisterDialog() {
        View view = getLayoutInflater().inflate(R.layout.registration_popup, null);

        EditText registerEmail = view.findViewById(R.id.register_email);
        EditText registerPassword = view.findViewById(R.id.register_password);
        EditText registerConfirmPassword = view.findViewById(R.id.register_confirm_password);

        alertDialogBuilder = new AlertDialog.Builder(this).setView(view);
        registrationDialog = alertDialogBuilder.create();
        registrationDialog.show();
    }
}
