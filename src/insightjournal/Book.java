/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournal;

import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Benjamin
 */
public class Book {
    private String bookName;
    private int numChapters;
    
    public Book() {
        bookName = null;
        numChapters = 0;
    }
    
    public Book(String book) {
        
        if (book.equals("D&C")) {
            book = "Doctrine and Covenants";
        }
        
        String[] theNumChapters = book.split(":");

        if (theNumChapters.length < 2) {
            numChapters = 0;
            bookName = book;
        }
        else {
            numChapters = parseInt(theNumChapters[1]);
            bookName = theNumChapters[0];
        }
    }

    public void setNumChapters(int numChapters) {
        this.numChapters = numChapters;
    }

    public int getNumChapters() {
        return numChapters;
    }
         
    public String getBookName() {
        return bookName;
    }
    
    
    public void setBookName(String theBookName) {
        bookName = theBookName;
    }
    
}
