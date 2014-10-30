/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournal;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
public class Scripture {
    private String standardWork;
    private Book bookName;
    private int chapter;
    private int startverse;
    private int endverse;
    
    /**
     * 
     */
    public Scripture() {
    
    }
    
    /**
     * 
     * @param theStandardWork
     * @param theBookName
     * @param theChapter 
     */
    public Scripture(String theStandardWork, Book theBookName, int theChapter) {
        standardWork = theStandardWork;
        bookName = theBookName;
        chapter = theChapter;
    }
    
    /**
     * 
     * @param theBookName
     * @param theChapter
     * @param theVerses 
     */
    public Scripture(Book theBookName, int theChapter, ArrayList<Integer> theVerses) {
        bookName = theBookName;
        chapter = theChapter;
    }
    
    /**
     * 
     * @param theBookName
     * @param theChapter
     * @param startVerse
     * @param endVerse 
     */
    public Scripture(Book theBookName, int theChapter, int startVerse, int endVerse) {
        
        bookName = theBookName;
        chapter = theChapter;
        startverse = startVerse;
        endverse = endVerse;
    }
    
    /**
     * 
     * @param theScripture 
     */
    public Scripture(String theScripture) {
        String temp[];
        temp = theScripture.split(" ");
        if (theScripture.contains("chapter")) {

            if (temp[0].matches("\\d")) {
                temp[0] = temp[0] + " " + temp[1];
                temp[1] = temp[3];
                // resume work here
            } else if (temp.length > 4) {
                temp[0] = temp[0] + " " + temp[1] + " " +  temp[2];
                temp[1] = temp[4];
            } else {
                temp[1] = temp[2];
            }
            bookName = new Book(temp[0]);
            //chapter = parseInt(temp[1])
                if (temp[1].contains(":")) {
                    String chapterVerse[] = temp[1].split(":");
                    chapter = parseInt(chapterVerse[0]);
                    if (chapterVerse[1].contains("-")) {
                        String verses[] = chapterVerse[1].split("-");
                        startverse = parseInt(verses[0]);
                        endverse = parseInt(verses[1]);
                    } else {
                        startverse = parseInt(chapterVerse[1]);
                    } 
                } else {
                    chapter = parseInt(temp[1]);
                }
        } else  {
            // parse the book in form book 0:0
            if (temp[0].matches("\\d")) {
                temp[0] = temp[0] + " " + temp[1];
                temp[1] = temp[2];
            } else if (temp.length > 3) {
                temp[0] = temp[0] + " " + temp[1] + " " +  temp[2];
                temp[1] = temp[3];
            } else{
                if (temp[1].matches("Declaration")) {
                    temp[0] = temp[0] + temp[1];
                    temp[1] = temp[2];
                    
                }
                
            }            
            bookName = new Book(temp[0]);
            if (temp[1].contains(":")) {
                String verseChapter[] = temp[1].split(":");
                chapter = parseInt(verseChapter[0]);
                if (verseChapter[1].contains("-")) {
                    String verses[] = verseChapter[1].split("-");
                    startverse = parseInt(verses[0]);
                    endverse = parseInt(verses[1]);
                } else {
                    startverse = parseInt(verseChapter[1]);
                }
            } else {
                chapter = parseInt(temp[1]);
            }
        }
    }

    /**
     * 
     * @return 
     */
    public int getStartverse() {
        return startverse;
    }

    /**
     * 
     * @return 
     */
    public int getEndverse() {
        return endverse;
    }
    
    /**
     * 
     * @return 
     */
    public Book getBookName() {
        return bookName;
    }
    
    /**
     * 
     * @return 
     */
    public int getChapter() {
        return chapter;
    }
    
    /**
     * 
     * @return 
     */
    public String getStandardWork() {
        return standardWork;
    }
    
    /**
     * 
     */
    private void checkScriptureReference() {
        
    }
    
    /**
     * 
     */
    private void findStandardWork() {
        
    }
    
    /**
     * 
     * @param scriptureReference 
     */
    private void convertScriptureReference(String scriptureReference) {
        
    }


}

