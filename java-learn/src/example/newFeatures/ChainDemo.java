

// Custom High level exception
class ApplicationException extends RuntimeException {
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class ChainDemo {
    public static void main(String[] args) {
        try {
            doLowLevelWork();
        } catch (ApplicationException e) {
            System.out.println("Caught High-Level: " + e.getMessage());

            // Retrieve and print the root cause
            System.out.println("Root Cause: " + e.getCause().getMessage());

            // We can also print the full chained stack trace
            // e.printStackTrace();
        }
    }

    public static void doLowLevelWork() {
        try {

            // Simulating a low level failure (e.g division by zero or file error)
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            // Wrapping the low-level exception inside a high-level custom exception
            throw new ApplicationException("Operation failed due to arithmetic error", e);
        }
    }
}

