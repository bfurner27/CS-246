/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournal;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Benjamin
 */
public class Entry {
    private String text;
    private ArrayList<Scripture> scriptures;
    private ArrayList<String> topics;
    private Date date;
    
    /**
     * 
     */
    public Entry()
    {
        text = null;
    }
    
    /**
     * 
     * @param theText 
     */
    public Entry(String theText) {
        text = theText;
    }
    
    /**
     * 
     * @param theText
     * @param theScriptures
     * @param theTopics
     * @param theDate 
     */
    Entry(String theText, ArrayList<Scripture> theScriptures, ArrayList<String> theTopics,
            Date theDate) {
        text = theText;
        scriptures = theScriptures;
        topics = theTopics;
        date = theDate;
    }

    /**
     * 
     */
    public void save() {
        
    }
    
    /**
     * 
     * @return 
     */
    public String writeToXML() {
        return null; 
    }
    
    /**
     * 
     * @return 
     */
    public String getText() {
        return text;
    }
    
    /**
     * 
     * @param theText 
     */
    public void setText(String theText) {
        text = theText;
    }
    
    /**
     * 
     * @param scriptures 
     */
    public void setScriptures(ArrayList<Scripture> scriptures) {
        this.scriptures = scriptures;
    }

    /**
     * 
     * @param topics 
     */
    public void setTopics(ArrayList<String> topics) {
        this.topics = topics;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Scripture> getScriptures() {
        return scriptures;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<String> getTopics() {
        return topics;
    }

    /**
     * 
     * @return 
     */
    public Date getDate() {
        return date;
    }
}
