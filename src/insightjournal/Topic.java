/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class Topic {
    private String topic;
    private List<String> synonyms;
    
    /**
     * 
     */
    public Topic() {
        topic = null;
        synonyms = null;
    }
    
    /**
     * 
     * @param lineOfTopics 
     */
    public Topic(String lineOfTopics) {
        synonyms = new ArrayList<>();
        String[] firstSplit = lineOfTopics.split(":");
        topic = firstSplit[0];
        String[] secondSplit = firstSplit[1].split(",");
        for (int i = 0; i < secondSplit.length; i++) {
            synonyms.add(secondSplit[i]);
        }
        
    }
    //Getters and Setters

    /**
     * 
     * @param Topic 
     */
    public void setTopic(String Topic) {
        this.topic = Topic;
    }

    /**
     * 
     * @param theSynonyms 
     */
    public void setSynonyms(List<String> theSynonyms) {
        this.synonyms = theSynonyms;
    }

    /**
     * 
     * @return 
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 
     * @return 
     */
    public List<String> getSynonyms() {
        return synonyms;
    }
   
}
