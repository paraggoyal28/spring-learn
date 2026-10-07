package example.newFeatures;


import java.sql.SQLException;

public class ServiceLayerDemo {
    // Custom business exceptions
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class UserRepository {
        public void findUserById(long id) throws SQLException {
            // Simulate a raw database error
            throw new SQLException("Connection timeout to MySQL cluster on port 3306");
        }
    }

    public static class UserService {
        private final UserRepository repository = new UserRepository();

        public void getUserDetails(long id) {
            try {
                repository.findUserById(id);
            } catch (SQLException sqlException) {
                // Wrap the unchecked/raw SQL exception into an unchecked business exceptiun
                throw new UserNotFoundException("Unable to fetch user profile for ID: " + id, sqlException);
            }
        }
    }

    public static void main(String args[]) {
        UserService userService = new UserService();

        try {
            userService.getUserDetails(42L);
        } catch (UserNotFoundException e) {
            System.err.println("[ERROR]: " + e.getMessage());
            System.err.println("[CAUSED BY]: " + e.getCause().getMessage());
        }
    } 
}
