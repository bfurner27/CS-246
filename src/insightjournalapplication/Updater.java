/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournalapplication;

import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 *
 * @author Benjamin
 */
public class Updater {
    public TextField textUpdate;

    
    public void update(int numEntries, int numScriptures, int numTopics) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String updateMessage = "The Number of Entries " + Integer.toString(numEntries) + "\n";
                updateMessage += "The Number of Scriptures " + Integer.toString(numScriptures) + "\n";
                updateMessage += "The Number of Topics " + Integer.toString(numTopics) + "\n";
                textUpdate.setText(updateMessage);
            }
        }); 
    }
}
