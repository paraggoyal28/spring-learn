package example.nestedClasses;


public class UserProfile {
    private final String username;
    private final String email;
    private final int age; // optional

    // Private constructor so that it can only be called by Builder
    private UserProfile(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.age = builder.age;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAge() {
        return this.age;
    }

    public static class Builder {
        private final String username; // required
        private final String email; // required
        private int age = 0;

        public Builder(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserProfile build() {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("Username cannot be null or empty");
            }

            return new UserProfile(this);
        }
    }

    public static void main(String args[]) {
        UserProfile profile = new UserProfile.Builder("parag_g", "abc@example.com").setAge(29).build();
        System.out.println("Profile name: " + profile.getUsername());
        System.out.println("Profile email: " + profile.getEmail());
        System.out.println("Profile age: " + profile.getAge());
    }
}