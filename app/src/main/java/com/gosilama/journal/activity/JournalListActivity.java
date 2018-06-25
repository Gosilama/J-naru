package com.gosilama.journal.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gosilama.journal.R;

public class JournalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_list);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String un = extras.getString("Username");
            String pwd = extras.getString("Password");

            Toast.makeText(this, un + " " + pwd, Toast.LENGTH_SHORT).show();
        }

        FloatingActionButton fab = findViewById(R.id.add_journal_entry);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
