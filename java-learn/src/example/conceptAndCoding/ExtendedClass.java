public class ExtendedClass extends OuterInheritedClass.InnerClass {
    ExtendedClass(){
        new OuterInheritedClass().super();
    }

    public static void main(String[] args) {
        ExtendedClass extendedClassObj = new ExtendedClass();
        extendedClassObj.display();
    }
}