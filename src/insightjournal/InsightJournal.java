/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournal;

import insightjournalapplication.Updater;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Benjamin
 */
public class InsightJournal {
    
    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) {
    //    // TODO code application logic here
    //    InsightJournal start = new InsightJournal();
    //    start.runProcess();
    //    
    //}
    
    private Reference theReference;
    private Finder theFinder;
    private Docx sendData;
    private List<Entry> entries;
    private List<Topic> topicsList; 
    private List<Book> bookList; 
    private Index index;

    public Reference getTheReference() {
        return theReference;
    }

    public Finder getTheFinder() {
        return theFinder;
    }

    public Docx getSendData() {
        return sendData;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public List<Topic> getTopicsList() {
        return topicsList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Index getIndex() {
        return index;
    }
    /**
     * Name runProcess
     * 
     * @param files 
     */
    public void runProcess() {
        String propFile;
        propFile = "/propertiesPackage/insightJournalFiles.properties";
        Properties insightJournalProperties = new Properties();
        try {
            insightJournalProperties.load(getClass().getResourceAsStream(propFile));
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        
        String topicFile = insightJournalProperties.getProperty("topics");
        String bookAndChaptersFile = insightJournalProperties.getProperty("books");
      
        readInScriptureBooks(bookAndChaptersFile);
        readInTopics(topicFile);
    }
    
    
    /**
     * Name: readINScriptureBooks
     * Description: This will read in the list of books to compare
     * @param filename from source file
     */
    public void readInScriptureBooks(String filename) {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(filename));
                    
            bookList = new ArrayList<>();
            while (fin.ready()) {
                String scriptureBooks;
                scriptureBooks = fin.readLine();
                bookList.add(new Book(scriptureBooks));
                
            }
        } catch (IOException e) {
            System.out.println("ERROR: File failed to read" + e);
            System.exit(0);
        }
    }
    
    public void readInTopics(String filename) {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(filename));
                    
            topicsList = new ArrayList<>();
            while (fin.ready()) {
                String topicsLine;
                topicsLine= fin.readLine();
                topicsList.add(new Topic(topicsLine));
                
            }
        } catch (IOException e) {
            System.out.println("ERROR: File failed to read" + e);
            System.exit(0);
        }
    }
    
    /**
     * 
     * @param unformatedText a string of text to be parsed
     */
    public void addEntry(String unformatedText) {
        
        Date tempDate = new Date();
        tempDate.dateFromComputer();
        
        
        try {
            entries.add(parseText(unformatedText, tempDate));
        }   catch (NullPointerException e) {
            entries = new ArrayList<>();
            entries.add(parseText(unformatedText, tempDate));
        }
    }

    
    /**
     * 
     * @param filename xml file
     */
    public void readInXMLFile(String filename) {
        try {
            File file = new File(filename);
            DocumentBuilderFactory produceDocBuild = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuild = produceDocBuild.newDocumentBuilder();
            Document doc = docBuild.parse(file);
            
            entries = new ArrayList<>();
            
            NodeList entryList = doc.getElementsByTagName("entry");
           
            for (int i = 0; i < entryList.getLength(); i++) {
                
                ArrayList<Scripture> tempScriptureList = new ArrayList<>();
                Node entry = entryList.item(i);                
                Element entryElem = (Element) entry;
                Date tempDate = new Date(entryElem.getAttribute("date"));
                
                NodeList scriptures = entryElem.getElementsByTagName("scripture");
                NodeList topics = entryElem.getElementsByTagName("topic");
                NodeList allContent = entryElem.getElementsByTagName("content");
                
                
                for (int s = 0; s < scriptures.getLength(); s++) {
                    Element scriptureElem = (Element) scriptures.item(s);
                    String chapter = scriptureElem.getAttribute("chapter");
                    String book = scriptureElem.getAttribute("book");
                    String startverse = scriptureElem.getAttribute("startverse");
                    String endverse = scriptureElem.getAttribute("endverse");
                    
                    // populate the scripture object
                    Book temp = new Book(book);
                    int startVerse = 0;
                    int endVerse = 0;
                    if (startverse.equals("null")) {
                        startVerse = Integer.parseInt(startverse);
                        
                        if (endverse.equals("null"))
                            endVerse = Integer.parseInt(endverse);
                    }
                            
                    Scripture tempScrip = new Scripture(temp,parseInt(chapter),startVerse,endVerse);
                    tempScriptureList.add(tempScrip);
                            
                    
                    // populate the Calendar object
                    
                }
                
                ArrayList<String> keyWords = new ArrayList<>();
                for (int s = 0; s < topics.getLength(); s++) {
                    Element topicElem = (Element) topics.item(s);
                    keyWords.add(topicElem.getTextContent());
                }
                 
                // get the text value
                Element contentElem = (Element) allContent.item(0);  
                String tempContent = contentElem.getTextContent();
                tempContent = tempContent.replaceAll("\\n\\s+", "\n");
                // Eliminate White space
                
            entries.add(new Entry(tempContent,tempScriptureList,keyWords,tempDate));  
                
                
            }
        }
        catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            System.out.println("ERROR: The file could not be read!");
            System.exit(0);
        }
        
    }

    public void displayandSearchContents() {
        // Output Scriptures that match
        System.out.println("Topic References");
        for (int i = 0; i < topicsList.size(); i++) {
            String tempTopic = topicsList.get(i).getTopic();
            System.out.println(tempTopic);            
            for (int h = 0; h < entries.size(); h++) {
                Entry tempEntry = entries.get(h);
           
                Date tempDate = tempEntry.getDate();
                
                ArrayList<String> topics = tempEntry.getTopics();
                for (int j = 0; j < topics.size(); j++) {
                    if (tempTopic.equals(topics.get(j))) {
                        System.out.print("\t" + tempDate.getYear() + "-");
                        System.out.print(tempDate.getMonth() + "-");
                        System.out.println(tempDate.getDay());
                        
                    }
                }
                
            }
        }
        
        System.out.println("Scripture References");
        for (int i = 0; i < bookList.size(); i++) {
        String tempBookName = bookList.get(i).getBookName();
        System.out.println(tempBookName);            
            for (Entry tempEntry : entries) {
                Date tempDate = new Date();
                tempDate = tempEntry.getDate();
                
                ArrayList<Scripture> scriptures = new ArrayList<>();
                scriptures = tempEntry.getScriptures();
                for (int j = 0; j < scriptures.size(); j++) {
                    if (scriptures.get(j).getBookName().getBookName().equals(tempBookName)) {
                        System.out.print("\t" + tempDate.getYear() + "-");
                        System.out.print(tempDate.getMonth() + "-");
                        System.out.println(tempDate.getDay());
                    }
                }
            }   
        }
    }
    
    /**
     * 
     * @param filename text file
     * @param theUpdater
     * @return returns a list of entries
     */
    public List<Entry> readTextFile(String filename, Updater theUpdater) {
       
        List<Entry> journal = new LinkedList<>();

        try {
            BufferedReader fin = new BufferedReader(new FileReader(filename));
            String line = null;
            int numEntries = 0;
            int numScriptures = 0;
            int numTopics = 0;
            while (fin.ready()) {
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(InsightJournal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Date tempDate;

                String content = null;
                
                //read in the file
                if (line == null) {
                    line = fin.readLine();
                }
                
                line = fin.readLine();
                tempDate = new Date(line);
                while (fin.ready()) {
                    line = fin.readLine();
                    if (content != null) {
                        if (line.equals("-----")) {
                            break;
                        }  
                        content = content.concat("\n" + line);
                    } else if (!fin.ready()) {                      
                        content += "\n" + line;
                    } else {  
                        content = line;  
                    }
                }
                Entry tempEntry = parseText(content, tempDate);
                numScriptures += tempEntry.getScriptures().size();
                numTopics += tempEntry.getTopics().size();
                journal.add(tempEntry);
                numEntries++;
                
                theUpdater.update(numEntries, numScriptures, numTopics);
            } //end read in while loop;
        }
        catch (IOException e) {
            System.out.println("ERROR: could not read in text file " + e);
        }
        return journal;
    }

    /**
     * 
     * @param entries 
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * 
     * @param list
     * @param filename
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws ParserConfigurationException 
     */
    public void writeXMLFile(List<Entry> list, String filename) throws TransformerConfigurationException, TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
            
        Document doc = builder.newDocument();
        
        Element root = doc.createElement("journal");
        doc.appendChild(root);
        for (Entry fileToWrite1 : list) {
            Element entryNode = doc.createElement("entry");
            
            //format string
            String tempDate = fileToWrite1.getDate().getYear() + "-"
                    + fileToWrite1.getDate().getMonth() + "-" + fileToWrite1.getDate().getDay();
            entryNode.setAttribute("date", tempDate);
            
            root.appendChild(entryNode);
            
            //format scriptures
            List<Scripture> theScriptures = fileToWrite1.getScriptures();
            for (Scripture tempScript : theScriptures) {
                Element scriptureElmt = doc.createElement("scripture");
                if (tempScript.getStartverse() > 0) {
                    scriptureElmt.setAttribute("startverse", Integer.toString(tempScript.getStartverse()));
                    
                    if (tempScript.getStartverse() > 0) {
                        scriptureElmt.setAttribute("endverse", Integer.toString(tempScript.getEndverse()));
                    }
                }
                scriptureElmt.setAttribute("chapter", Integer.toString(tempScript.getChapter()));
                scriptureElmt.setAttribute("book", tempScript.getBookName().getBookName());
                entryNode.appendChild(scriptureElmt);
            }
            
            //format topics section
            ArrayList<String> theTopics = fileToWrite1.getTopics();
            for (String theTopic : theTopics) {
                Element topicElmt = doc.createElement("topic");
                topicElmt.appendChild(doc.createTextNode(theTopic));
                entryNode.appendChild(topicElmt);
            }
            
            //format content
            Element contentElmt = doc.createElement("content");
            contentElmt.appendChild(doc.createTextNode(fileToWrite1.getText()));
            entryNode.appendChild(contentElmt);
        }
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(filename));
        Source input = new DOMSource(doc);
        
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(input, output);
    }
    
    /**
     * 
     * @param exportList 
     * @param filename 
     */
    public void exportTextFile(List<Entry> exportList, String filename) {
        try {
            BufferedWriter fout = new BufferedWriter(new FileWriter(filename));
            for (Entry tempList : exportList) {
                fout.write("-----\n");
                String date = tempList.getDate().getYear() + "-" + tempList.getDate().getMonth() + "-"
                        + tempList.getDate().getDay() + "\n";
                fout.write(date);
                fout.write(tempList.getText() + "\n");
                
            }
            fout.close();
        } catch (IOException ex) {
            Logger.getLogger(InsightJournal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     * @return 
     */
    public List<Topic> getTopics() {
        return topicsList;
    }
    
    public List<Entry> searchEntries(String searchItem, List<Entry> journal) {
    
        List<Entry> includedEntries = new ArrayList<Entry>();
        for (Entry entrie : journal) {
            if (searchItem.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
                if (searchItem.equals(entrie.getDate().stringDate())) {
                    includedEntries.add(entrie);
                }
            } else {
                
                for (Scripture entryScriptures : entrie.getScriptures()) {
                    String compareScripture = entryScriptures.getBookName().getBookName() + " " 
                            + entryScriptures.getChapter();
                    if (searchItem.matches("(?i)" + compareScripture)) {
                        includedEntries.add(entrie);
                        break;
                    } 
                    
                    if (searchItem.matches("(?i)" + entryScriptures.getBookName().getBookName())) {
                        includedEntries.add(entrie);
                        break;
                    }
                }
            
                for (String compareTopic : entrie.getTopics()) {
                    if (searchItem.matches("(?i)" + compareTopic)) {
                        includedEntries.add(entrie);
                        break;
                    }
                }
            }
        }
        
        return includedEntries;
    }
    
    /**
     * 
     * @param content
     * @param theDate
     * @return 
     */
    private Entry parseText(String content, Date theDate) {
        ArrayList<String> tempTopics = new ArrayList<>();
        ArrayList<Scripture> tempScriptures = new ArrayList<>();
        
        //parse the topics out of the file
        for (Topic compareTopic : topicsList) {
            String topic = compareTopic.getTopic();
            if (content.contains(topic)) {
            tempTopics.add(topic);
            } else {
                List<String> synonyms = compareTopic.getSynonyms();
                for (String synms : synonyms) {
                    if (content.contains(synms)) {
                        tempTopics.add(topic);
                    }
                }
            }
            
        }                
                
        // parse the scriptures out of the file
        for (int i = 0; i < bookList.size(); i++) {
            String word = bookList.get(i).getBookName();
            String regexChapterVerse = "(?i)" + word + " \\d{1,3}:?\\d{0,2}-?\\d{0,3}";
            String regexBookChapter = "(?i)" + word + " chapter \\d{1,3}:?\\d{0,3}-?\\d{0,3}";
                    
            Pattern chapterVersePattern = Pattern.compile(regexChapterVerse);
            Pattern bookChapterPattern = Pattern.compile(regexBookChapter);
               
            Matcher chapterVerse = chapterVersePattern.matcher(content);
            Matcher bookChapter = bookChapterPattern.matcher(content);
                    
            while (chapterVerse.find()) {
                tempScriptures.add(new Scripture(chapterVerse.group()));
            }
                    
            while (bookChapter.find()) {
                tempScriptures.add(new Scripture(bookChapter.group()));
            }
        }
        return (new Entry(content,tempScriptures,tempTopics,theDate));
    }
}
