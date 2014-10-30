/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournalapplication;

import insightjournal.Entry;
import insightjournal.InsightJournal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class ImportFileThread implements Runnable {
    private InsightJournal journal;
    private String filename;
    private List<Entry> journalEntries;
    private Updater updater;
    
    ImportFileThread (Updater theUpdater, String theFilename) {
        updater = theUpdater;
        filename = theFilename;
        journalEntries = new ArrayList<>();
        journal = new InsightJournal();
    }

    /**
     * 
     * @return 
     */
    public List<Entry> getJournalEntries() {
        return journalEntries;
    }

    /**
     * 
     */
    @Override
    public void run() {
        journalEntries = journal.readTextFile(filename, updater);
    }
}

