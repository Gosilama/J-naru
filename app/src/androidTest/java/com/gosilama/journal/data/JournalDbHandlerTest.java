package com.gosilama.journal.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.gosilama.journal.model.Journal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static com.gosilama.journal.util.Constants.CURRENT_USER_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class JournalDbHandlerTest {
    private JournalDbHandler journalDbHandler;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        journalDbHandler = new JournalDbHandler(context);
    }

    @After
    public void tearDown() throws Exception {
        journalDbHandler.close();
    }

    @Test
    public void onCreate() {
        assertNotNull(journalDbHandler);
    }

    @Test
    public void createJournal() {
        Journal journal = new Journal();
        journal.setId(1);
        journal.setUserId("randomUid");
        journal.setDateCreated(System.currentTimeMillis());
        journal.setJournalEntry("Testing the journal Db handler");
        journal.setJournalTitle("Test");

        CURRENT_USER_ID = "randomUid";

        journalDbHandler.createJournal(journal);

        ArrayList<Journal> journals = journalDbHandler.readAllJournalEntries();
        int currentId = journals.size() - 1;

        assertThat(journals.get(currentId).getUserId(), is(journal.getUserId()));
        assertThat(journals.get(journal.getId()).getJournalTitle(), is(journal.getJournalTitle()));
        assertThat(journals.get(journal.getId()).getJournalEntry(), is(journal.getJournalEntry()));
    }

    @Test
    public void readAllJournalEntries() {
        assertNotNull(journalDbHandler.readAllJournalEntries());
    }

    @Test
    public void updateJournal() {
        CURRENT_USER_ID = "randomUid";
        ArrayList<Journal> journals = journalDbHandler.readAllJournalEntries();
        int currentId = journals.size() - 1;

        Journal journal = journals.get(currentId);
        journal.setJournalTitle("Updated Title");
        journal.setJournalEntry("Updated Journal Entry");

        journalDbHandler.updateJournal(journal);
        assertThat(journal.getId(), is(journals.get(currentId).getId()));
        assertThat(journal.getJournalTitle(), is("Updated Title"));
        assertThat(journal.getJournalEntry(), is("Updated Journal Entry"));
    }

    @Test
    public void deleteJournal() {
        CURRENT_USER_ID = "randomUid";

        ArrayList<Journal> journals = journalDbHandler.readAllJournalEntries();

        int currentCount = journals.size();

        if (currentCount > 0) {
            journalDbHandler.deleteJournal(currentCount);

            assertThat(journalDbHandler.getJournalEntryCount(), lessThan(currentCount));
        }
    }

    @Test
    public void getJournalEntryCount() {
        assertThat(journalDbHandler.getJournalEntryCount(), greaterThanOrEqualTo(0));
    }
}