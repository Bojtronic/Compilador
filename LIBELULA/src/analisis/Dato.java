package analisis;


public class Dato {
    
    public String lineInfo;
    public int lineNumber; 
    public boolean hasError;
     

    
    public Dato(int lineNumber, boolean hasError, String lineInfo) {
        this.lineInfo = lineInfo;
        this.lineNumber = lineNumber;
        this.hasError = hasError;
        
    }

    public boolean hasError() {
        return hasError;
    }
   
    public int getLineNumber() {
        return lineNumber;
    }

    
    public String getLineInfo() {
        return lineInfo;
    }
}
