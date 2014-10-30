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
    
    public Entry()
    {
        text = null;
    }
    
    public Entry(String theText) {
        text = theText;
    }
    
    Entry(String theText, ArrayList<Scripture> theScriptures, ArrayList<String> theTopics,
            Date theDate) {
        text = theText;
        scriptures = theScriptures;
        topics = theTopics;
        date = theDate;
    }

    public void save() {
        
    }
    
    public String writeToXML() {
        return null; 
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String theText) {
        text = theText;
    }
    
    public void setScriptures(ArrayList<Scripture> scriptures) {
        this.scriptures = scriptures;
    }

    public void setTopics(ArrayList<String> topics) {
        this.topics = topics;
    }


    public ArrayList<Scripture> getScriptures() {
        return scriptures;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }

    public Date getDate() {
        return date;
    }
}
