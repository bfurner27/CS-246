/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insightjournal;

import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Benjamin
 */
public class Date {
    private int year;
    private int month;
    private int day;
    private Calendar date;

    public Calendar getDate() {
        return date;
    }
    
    public Date() {
        year = 0;
        month = 0;
        day = 0;
    }
    
    public Date(int theYear, int theMonth, int theDay) {
        year = theYear;
        month = theMonth;
        day = theDay;
    }
    
    public Date(String theDate) {
        try {
            String[] tempStrings = theDate.split("-");
            year = Integer.parseInt(tempStrings[0]);
            month = Integer.parseInt(tempStrings[1]);
            day = Integer.parseInt(tempStrings[2]);
        }
        catch (Exception e) {
            System.out.println("This is an inalid string for date. Expected yyyy-mm-dd"
                    + "setting to default value");
            year = 0;
            month = 0;
            day = 0;
        }
    }
    
    public void dateFromComputer() {
        DateFormat compDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar nDate = Calendar.getInstance();
        
        String temp = compDateFormat.format(nDate.getTime());
        String split[] = temp.split("-");
        year = parseInt(split[0]);
        month = parseInt(split[1]);
        day = parseInt(split[2]);
        System.out.println(temp);
    }
    
    /**
     * 
     * @return 
     */
    public String stringDate() {
        String formattedDate = "";
        formattedDate += Integer.toString(year) + "-";
        formattedDate += Integer.toString(month) + "-";
        formattedDate += Integer.toString(day);
        
        return formattedDate;
    }
    
    public int getYear() {
        return year;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getDay() {
        return day;
    }
    
    public void setYear(int theYear) {
    }
    
    public void setMonth(int theMonth) {
        
    }
    
    public void setDay(int theDay) {
        
    }
    
    private void convertToIntForm(String date) {
        
    }
    
    private void correctValue() {
        
    }
}
