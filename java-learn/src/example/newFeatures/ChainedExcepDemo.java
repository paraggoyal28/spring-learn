package example.newFeatures;

public class ChainedExcepDemo {
    static void demoproc() {
        // create an exception
        NullPointerException e = 
            new NullPointerException("Top layer");
        
        e.initCause(new ArithmeticException("cause"));
        throw e;
    }

    public static void main(String[] args) {
        try {
            demoproc();
        } catch (NullPointerException ex) {
            // display top level exception
            System.out.println("Caught: " + ex.getMessage());

            // display cause exception
            System.out.println("Original Cause: " + ex.getCause());
        }
    }
}
