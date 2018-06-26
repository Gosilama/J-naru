package com.gosilama.journal.model;

public class Journal {
    private String journalTitle;
    private String journalEntry;
    private Long timeCreated;
    private int id;

    public Journal(){}

    public Journal(String journalTitle, String journalEntry, Long timeCreated, int id) {
        this.journalTitle = journalTitle;
        this.journalEntry = journalEntry;
        this.timeCreated = timeCreated;
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

    public Long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
