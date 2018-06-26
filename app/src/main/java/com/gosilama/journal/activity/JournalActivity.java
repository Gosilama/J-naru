package com.gosilama.journal.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gosilama.journal.R;
import com.gosilama.journal.data.JournalDbHandler;
import com.gosilama.journal.model.Journal;

public class JournalActivity extends AppCompatActivity {

    private EditText journalEntryTitle;
    private EditText journalEntryContent;
    private JournalDbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        journalEntryTitle = findViewById(R.id.journal_entry_title);
        journalEntryContent = findViewById(R.id.journal_entry_content);

        dbHandler = new JournalDbHandler(this);

        final FloatingActionButton saveJournalEntry = findViewById(R.id.save_action_btn);
        saveJournalEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(journalEntryTitle.getText())
                        && !TextUtils.isEmpty(journalEntryContent.getText())) {

                    Journal journal = new Journal();
                    journal.setJournalTitle(journalEntryTitle.getText().toString());
                    journal.setJournalEntry(journalEntryContent.getText().toString());

                    saveJournalEntry(journal);
                    Log.d("JOURNAL_ACTIVITY", "CHORE SAVED SUCCESSFULLY");

                } else {
                    Toast.makeText(getApplicationContext(), "Nothing on your mind?", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void saveJournalEntry(Journal journal) {
        dbHandler.createJournal(journal);
    }
}
