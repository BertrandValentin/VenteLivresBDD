package be.atc.servlets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    private static Utilities INSTANCE;
    /**
     * Create private constructor
     */
    private Utilities(){
         
    }
    /**
     * Create a static method to get instance.
     */
    public static Utilities getInstance(){
        if(INSTANCE == null){
        	INSTANCE = new Utilities();
        }
        return INSTANCE;
    }
    
    /**
     * take a String in parameters and returns a integer
     * if the String is not a number it returns -1
     * @return
     */
    public int convertStringRequestParameterToInt(String s){
    	if(s == null || s.isEmpty()){
    		return -1;
    	}
    	else {
    		try{
    			return Integer.parseInt(s);
    		}
    		catch (NumberFormatException e){
    			e.getMessage();
    			return -1;
    		}
    	}
    }
    
    public double convertStringRequestParameterToDouble(String s){
    	if(s == null || s.isEmpty()){
    		return -1;
    	}
    	else {
    		try{
    			return Double.parseDouble(s);
    		}
    		catch (NumberFormatException e){
    			e.getMessage();
    			return -1.0;
    		}
    	}
    }
    
    public Date stringToDate(String dateToConvert){
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    	Date date = null;

        try {
            date = formatter.parse(dateToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return date;
    }
}
