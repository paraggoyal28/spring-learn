package org.example.Structural.Flyweight.wordProcessor;

public class DocumentCharacter implements ILetter{

    private final char character;
    private final String fontType;
    private final int size;

    DocumentCharacter(char character, String fontType, int size) {
        this.character = character;
        this.fontType = fontType;
        this.size = size;
    }

    public char getCharacter() {
        return character;
    }

    public String getFontType() {
        return fontType;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void display(int row, int column) {
        System.out.println("Character " + character + " displayed at " + row + " and " + column + " and the object is " + this);
    }
}
