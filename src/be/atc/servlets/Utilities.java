package be.atc.servlets;

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
}
