package com.gosilama.journal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gosilama.journal.R;

public class SignInActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;
    private String user_name;
    private String user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
        Button signInButton = findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user_name = username.getText().toString();
                user_password = password.getText().toString();

                Intent intent = new Intent(getApplicationContext(), JournalListActivity.class);
                intent.putExtra("Username", user_name);
                intent.putExtra("Password", user_password);

                startActivity(intent);
            }
        });
    }
}
