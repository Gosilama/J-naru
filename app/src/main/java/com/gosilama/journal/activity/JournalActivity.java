package com.gosilama.journal.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gosilama.journal.R;
import com.gosilama.journal.data.JournalDbHandler;
import com.gosilama.journal.model.Journal;

import static com.gosilama.journal.util.Constants.CURRENT_USER_ID;

public class JournalActivity extends AppCompatActivity {

    private EditText journalEntryTitle;
    private EditText journalEntryContent;
    private FloatingActionButton saveJournalEntry;

    private JournalDbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        journalEntryTitle = findViewById(R.id.edit_text_journal_entry_title);
        journalEntryContent = findViewById(R.id.edit_text_journal_entry_content);
        saveJournalEntry = findViewById(R.id.floating_action_button_save);

        dbHandler = new JournalDbHandler(this);

        Journal journal = (Journal) getIntent().getSerializableExtra("EXTRA_JOURNAL");
        if (journal != null) {
            journalEntryTitle.setText(journal.getJournalTitle());
            journalEntryContent.setText(journal.getJournalEntry());

            updateJournalEntry(journal);
        } else {
            saveJournalEntry();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Toast.makeText(this, "Nothing Saved.", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, JournalListActivity.class);
        startActivity(intent);
    }

    public void saveJournalEntry() {
        saveJournalEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(journalEntryTitle.getText())
                        && !TextUtils.isEmpty(journalEntryContent.getText())) {

                    Journal journal = new Journal();
                    journal.setUserId(CURRENT_USER_ID);
                    journal.setJournalTitle(journalEntryTitle.getText().toString());
                    journal.setJournalEntry(journalEntryContent.getText().toString());

                    dbHandler.createJournal(journal);

                    goToJournalList();
                } else {
                    Toast.makeText(getApplicationContext(), "Nothing on your mind?", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateJournalEntry(final Journal journal) {
        saveJournalEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(journalEntryTitle.getText())
                        && !TextUtils.isEmpty(journalEntryContent.getText())) {
                    journal.setJournalTitle(journalEntryTitle.getText().toString());
                    journal.setJournalEntry(journalEntryContent.getText().toString());

                    dbHandler.updateJournal(journal);

                    goToJournalList();

                    Toast.makeText(getApplicationContext(), "Journal updated", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void goToJournalList() {
        Intent intent = new Intent(getApplicationContext(), JournalListActivity.class);
        startActivity(intent);
        finish();
    }
}
