package example.accessModifiers;

public class PublicDemo {
	public String getVisibilityDescription() {
		return "Public classes and public methods can be accessed from any package.";
	}

	public static void main(String[] args) {
		System.out.println(new PublicDemo().getVisibilityDescription());
	}
}
