package com.gosilama.journal.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class JournalTest {

    private Journal journal = new Journal();

    @Test
    public void getJournalTitle() {
        String expectedTitle = "Title";

        journal.setJournalTitle(expectedTitle);

        assertEquals(expectedTitle, journal.getJournalTitle());
    }

    @Test
    public void setJournalTitle() {
        String title = "Journal title";
        journal.setJournalTitle(title);

        assertEquals(journal.getJournalTitle(), title);
    }

    @Test
    public void getJournalEntry() {
        String expectedEntry = "Entry";
        journal.setJournalEntry(expectedEntry);

        assertEquals(expectedEntry, journal.getJournalEntry());
    }

    @Test
    public void setJournalEntry() {
        String entry = "Journal Entry";
        journal.setJournalEntry(entry);

        assertEquals(journal.getJournalEntry(), entry);
    }

    @Test
    public void getDateCreated() {
        Long expectedDate = System.currentTimeMillis();
        journal.setDateCreated(expectedDate);

        assertEquals(expectedDate, journal.getDateCreated());
    }

    @Test
    public void setDateCreated() {
        Long date = System.currentTimeMillis();
        journal.setDateCreated(date);

        assertEquals(journal.getDateCreated(), date);
    }

    @Test
    public void getId() {
        int expectedId = 1;
        journal.setId(expectedId);

        assertEquals(expectedId, journal.getId());
    }

    @Test
    public void setId() {
        int id = 2;
        journal.setId(id);

        assertEquals(journal.getId(), id);
    }

    @Test
    public void getUserId() {
        String expectedId = "User Id";

        journal.setUserId(expectedId);

        assertEquals(expectedId, journal.getUserId());
    }

    @Test
    public void setUserId() {
        String id = "User Id";
        journal.setUserId(id);

        assertEquals(journal.getUserId(), id);
    }

}