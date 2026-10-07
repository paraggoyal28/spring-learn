package example.newFeatures;

public class RecordInterfaceDemo {
    
    // Define an interface
    public interface Loggable {
        String logMessage();
    }

    // Record implements the interface
    public record UserLoginEvent(String username, long timestamp) implements Loggable {
        @Override
        public String logMessage() {
            return "LOGIN: User " + username + " at epoch " + timestamp;
        }
    }

    public record SystemAlertEvent(int errorCode, String details) implements Loggable {
        @Override
        public String logMessage() {
            return "ALERT [" + errorCode + "]: " + details;
        }
    }

    public static void main(String[] args) {
        Loggable event1 = new UserLoginEvent("parag_g", System.currentTimeMillis());
        Loggable event2 = new SystemAlertEvent(500, "Database timeout");

        processLog(event1);
        processLog(event2);
    }

    public static void processLog(Loggable log) {
        System.out.println(log.logMessage());
    }
}
