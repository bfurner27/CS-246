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
public class Index {
    private ArrayList<Reference> index;
    
    public Index () {
        
    }
    
    public Index(String filename) {
        readIndex();
    }
    public void readIndex() {
        
    }
    
    public ArrayList<Reference> getIndex() {
        return index;
    } 
    
    public void setIndex(ArrayList<Reference> references) {
        index = references;
    }
}
