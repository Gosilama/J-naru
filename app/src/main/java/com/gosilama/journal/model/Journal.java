package com.gosilama.journal.model;

import java.io.Serializable;

public class Journal implements Serializable{
    private String journalTitle;
    private String journalEntry;
    private Long dateCreated;
    private int id;

    public Journal(){}

    public Journal(String journalTitle, String journalEntry, Long dateCreated, int id) {
        this.journalTitle = journalTitle;
        this.journalEntry = journalEntry;
        this.dateCreated = dateCreated;
        this.id = id;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(String journalEntry) {
        this.journalEntry = journalEntry;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
