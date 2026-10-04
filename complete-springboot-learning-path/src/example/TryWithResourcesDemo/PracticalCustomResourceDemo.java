package example.TryWithResourcesDemo;

class DatabaseConnection implements AutoCloseable {
    public void executeQuery(String sql) {
        System.out.println("Executing " + sql);
    }

    @Override
    public void close() throws Exception {
        throw new IllegalStateException("Error occurred while trying to close the resource");
    }
}

public class PracticalCustomResourceDemo {
    public static void main(String[] args) {
        // Instantiate custom resource within try block
        try (DatabaseConnection databaseConnection = new DatabaseConnection()) {
            databaseConnection.executeQuery("Select * From Users");
            throw new RuntimeException("Main error");
        } catch (Exception ex) {
            System.out.println("Main Exception " + ex.getMessage());

           Throwable[] suppressed = ex.getSuppressed();

           for (Throwable t: suppressed) {
            System.out.println(t.getMessage());
           }
        }

        System.out.println("code continuously running outside the try block");
    }
}
