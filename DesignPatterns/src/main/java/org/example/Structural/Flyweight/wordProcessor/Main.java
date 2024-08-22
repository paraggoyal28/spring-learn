package org.example.Structural.Flyweight.wordProcessor;

public class Main {
    public static void main(String[] args) {
        ILetter object1  =LetterFactory.createLetter('t');
        object1.display(0, 0);

        ILetter object2 = LetterFactory.createLetter('t');
        object2.display(0, 6);
    }
}
