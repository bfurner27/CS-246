/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournalapplication;

import insightjournal.Entry;
import insightjournal.InsightJournal;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Benjamin
 */
public class InsightJournalApplication extends Application {
    public InsightJournal journal;
    public TextArea entryField;
    public TextArea displayJournalArea;
    public TextField displayFileLoadUpdate;
    

    @Override
    public void start(Stage primaryStage) {
        
        journal = new InsightJournal();
        try {
           journal.runProcess();
        } catch (Exception e) {
            System.out.println("Gen Anpil Pwoblem la" + e);
            System.exit(0);
        }
            
        Button addEntry = new Button();
        addEntry.setText("Add Entry");
        addEntry.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                journal.addEntry(entryField.getText());
                
                List<Entry> entries = new ArrayList<>();
                entries = journal.getEntries();
                String displayText = "";
                for (Entry entrie : entries) {
                    displayText += entrie.getDate().stringDate() + "\n";
                    displayText += entrie.getText() + "\n\n";
                }
                
                displayJournalArea.setText(displayText);
                entryField.clear();
            }
        });
        
        // Format Title
        HBox titleBox = new HBox();
        Text title = new Text("Insight Journal Helper");
        title.setFont(new Font("Times New Roman", 23));
        title.setTextAlignment(TextAlignment.CENTER);
        titleBox.getChildren().add(title);
        
        // Format Import Options
        HBox fileInAndSearchBox = new HBox();
        Button importTextFile = new Button();
        importTextFile.setText("Import File");
        
        displayFileLoadUpdate = new TextField();
        displayFileLoadUpdate.setEditable(false);
        
        importTextFile.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                FileChooser theFile = new FileChooser();
                theFile.setTitle("Open File");
                
                File file = theFile.showOpenDialog(primaryStage);
                if (file != null) {
                    String filename = file.getPath();
                    
                
                    // Run on background thread
                    // This will ensure that the file does 
                    // not hang up the GUI
                    Updater theUpdater = new Updater();
                    theUpdater.textUpdate = displayFileLoadUpdate;
                    
                    /*
                    ImportFileThread loadFile = new ImportFileThread(filename);
                    
                    Thread loadFileThread = new Thread(loadFile);
                    loadFileThread.start();
                    
                    
                    // This will get the data that was read in from the 
                    //file read
                    
                    */
                    List<Entry> entries = new ArrayList<>();
                    //entries = loadFile.getJournalEntries(); 
                    
                    entries = journal.readTextFile(filename, theUpdater);
                   
                    journal.setEntries(entries);
                
                    String displayText = "";
                    for (Entry entrie : entries) {
                        String tempEntry = entrie.getText();
                        tempEntry = tempEntry.replace("\n", " ");

                        displayText += entrie.getDate().stringDate() + "\n";
                        displayText += tempEntry + "\n\n" ; 
                    }
                    displayJournalArea.setText(displayText);
                
                }
            }
        });
        
        Button importXMLFile = new Button();
        importXMLFile.setText("Read XML File");
        importXMLFile.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                FileChooser theFile = new FileChooser();
                theFile.setTitle("XML File");
                
                File file = theFile.showOpenDialog(primaryStage);
                if (file != null) {
                    String filename = file.getPath();
                
                    journal.readInXMLFile(filename);
                    
                    List<Entry> entries = new ArrayList<>();
                    entries = journal.getEntries();

                    String displayText = "";
                    for (Entry entrie : entries) {
                        String tempEntry = entrie.getText();
                        tempEntry = tempEntry.replace("\n", " ");
                        displayText += entrie.getDate().stringDate() + "\n";
                        displayText += tempEntry + "\n\n";
                    }
                    displayJournalArea.setText(displayText);
                }
            }
        });        
        
        Button searchBtn = new Button();
        searchBtn.setText("Search");
        TextField searchInput = new TextField();
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                List<Entry> foundEntries = new ArrayList<>();
                foundEntries = journal.searchEntries(searchInput.getText(),journal.getEntries());
                
                String displayText = Integer.toString(foundEntries.size()) + " results for"; 
                        displayText += " \"" + searchInput.getText() + "\"\n\n";
                searchInput.clear();
                if (foundEntries.size() == 0) {
                    displayText += "Entire Journal Displayed\n\n";
                    for (Entry entrie : journal.getEntries()) {
                        String tempEntry = entrie.getText();
                        tempEntry = tempEntry.replace("\n", " ");
                        displayText += entrie.getDate().stringDate() + "\n";
                        displayText += tempEntry + "\n\n";
                    }
                } else {
                    for (Entry entrie : foundEntries) {
                        String tempEntry = entrie.getText();
                        tempEntry = tempEntry.replace("\n", " ");
                        displayText += entrie.getDate().stringDate() + "\n";
                        displayText += tempEntry + "\n\n";
                    }
                }
                displayJournalArea.setText(displayText);
            }
        });        
        
        Button exportXMLBtn = new Button();
        exportXMLBtn.setText("Save File");
        exportXMLBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                FileChooser theFile = new FileChooser();
                theFile.setTitle("Save XML File");
                
                File file = theFile.showSaveDialog(primaryStage);
                if (file != null) {
                    String filename = file.getPath();
                
                    try {                
                        journal.writeXMLFile(journal.getEntries(), filename);
                    } catch (TransformerException | ParserConfigurationException ex) {
                        Logger.getLogger(InsightJournalApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });        
        
        Button exportTextFileBtn = new Button();
        exportTextFileBtn.setText("Export File");
        exportTextFileBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                FileChooser theFile = new FileChooser();
                theFile.setTitle("Export File");
                
                File file = theFile.showSaveDialog(primaryStage);
                if (file != null) {
                    String filename = file.getPath();
                

                    journal.exportTextFile(journal.getEntries(), filename);
                }
            }
        });        
        
        fileInAndSearchBox.setSpacing(15);
        fileInAndSearchBox.getChildren().add(importTextFile);
        fileInAndSearchBox.getChildren().add(importXMLFile);
        fileInAndSearchBox.getChildren().add(exportXMLBtn);
        fileInAndSearchBox.getChildren().add(exportTextFileBtn);
        
        
        //Entry Section
        Label entryFieldLabel = new Label("New Entry");
        entryFieldLabel.setFont(new Font("Times New Roman", 15));
        entryField = new TextArea();
        entryField.setWrapText(true);
        
        
        
        VBox entryAddition = new VBox();
        entryAddition.getChildren().add(entryFieldLabel);
        entryAddition.getChildren().add(entryField);
        entryAddition.getChildren().add(addEntry);
        
        // Journal Display Section
        Label journalDisplayLabel = new Label("Journal Entries");
        journalDisplayLabel.setFont(new Font("Times New Roman", 15));
        displayJournalArea = new TextArea();
        displayJournalArea.setMaxSize(500, 900);
        displayJournalArea.setWrapText(true);
        displayJournalArea.setEditable(false);
        
        VBox formatDisplayArea = new VBox();
        //formatDisplayArea.setMaxHeight(900);
        formatDisplayArea.setMinHeight(300);
        formatDisplayArea.setMinWidth(100);
        
        formatDisplayArea.getChildren().add(journalDisplayLabel);
        formatDisplayArea.getChildren().add(displayJournalArea);
        
        //format search section
        HBox searchArea = new HBox();
        searchArea.getChildren().add(searchInput);
        searchArea.getChildren().add(searchBtn);
        searchArea.setSpacing(5);
        
        GridPane root = new GridPane();
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(0,10,10,10));

        root.add(titleBox, 0, 0, 11, 1);
        root.add(fileInAndSearchBox, 0, 1, 6, 1);
        root.add(entryAddition, 0, 3, 6, 5);
        root.add(formatDisplayArea, 7, 1, 10, 7);
        root.add(searchArea, 0, 8, 6, 1);
        root.add(displayFileLoadUpdate, 7, 7, 7, 1);
        //root.add(title, columnIndex, rowIndex, colspan, rowspan);
        
        Scene scene = new Scene(root, 1000, 500);
        
        primaryStage.setTitle("Insight Journal Helper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
