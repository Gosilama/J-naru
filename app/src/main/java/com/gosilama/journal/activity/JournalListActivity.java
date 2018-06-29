package com.gosilama.journal.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gosilama.journal.R;
import com.gosilama.journal.adapter.JournalListAdapter;
import com.gosilama.journal.data.JournalDbHandler;
import com.gosilama.journal.model.Journal;
import com.gosilama.journal.util.Utils;

import java.util.ArrayList;

import static com.gosilama.journal.util.Constants.CURRENT_USER_ID;

public class JournalListActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private JournalListAdapter journalListAdapter;
    private ArrayList<Journal> journalArrayList;
    private ArrayList<Journal> journalArrayListItem;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView journalRecyclerView;

    private JournalDbHandler dbHandler;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_list);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        CURRENT_USER_ID = firebaseUser != null ? firebaseUser.getUid() : null;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.journal_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout_menu_item) {
            firebaseAuth.signOut();

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.double_back_click, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
