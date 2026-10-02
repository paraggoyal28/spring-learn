package example.accessModifierDemo;

import example.accessModifiers.PublicDemo;

public class PublicClassDemo {
	public static void main(String[] args) {
		PublicDemo demo = new PublicDemo();
		System.out.println("Cross-package call: " + demo.getVisibilityDescription());
	}
}
