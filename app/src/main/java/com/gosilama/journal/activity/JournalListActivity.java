package com.gosilama.journal.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.gosilama.journal.R;
import com.gosilama.journal.adapter.JournalListAdapter;
import com.gosilama.journal.data.JournalDbHandler;
import com.gosilama.journal.model.Journal;
import com.gosilama.journal.util.Utils;

import java.util.ArrayList;

public class JournalListActivity extends AppCompatActivity {

    private JournalListAdapter journalListAdapter;
    private ArrayList<Journal> journalArrayList;
    private ArrayList<Journal> journalArrayListItem;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView journalRecyclerView;

    private JournalDbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_list);

        journalArrayList = new ArrayList<>();
        journalArrayListItem = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        journalListAdapter = new JournalListAdapter(this, journalArrayListItem);
        dbHandler = new JournalDbHandler(this);

        journalRecyclerView = findViewById(R.id.journal_recycler_view);
        journalRecyclerView.setLayoutManager(layoutManager);
        journalRecyclerView.setAdapter(journalListAdapter);

        journalArrayList = dbHandler.readAllJournalEntries();

        for (Journal j: journalArrayList) {
            Journal journal = new Journal();
            journal.setId(j.getId());
            journal.setJournalTitle(j.getJournalTitle());
            journal.setJournalEntry(j.getJournalEntry());
            journal.setDateCreated(j.getDateCreated());

            journalArrayListItem.add(journal);
        }

        journalListAdapter.notifyDataSetChanged();

        FloatingActionButton addNewJournalEntry = findViewById(R.id.add_journal_entry);
        addNewJournalEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JournalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() { }
}
