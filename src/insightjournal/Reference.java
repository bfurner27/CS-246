/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournal;

import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
public class Reference {

    private ArrayList<Book> books;
    private ArrayList<Topic> topics;
    private Date date;
    
    /**
     * 
     */
    public Reference () {
        
    }
    
    /**
     * 
     * @param theBooks
     * @param theTopics
     * @param theDate 
     */
    public Reference (ArrayList<Book> theBooks,ArrayList<Topic> theTopics, 
                Date theDate) {
        books = theBooks;
        topics = theTopics;
        date = theDate;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Book> getScriptures() {
        return books;  
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Topic> getTopics() {
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
