# Nested Classes

Define a class within another class. 

Static Nested Class
Non-Static Nested Class
| - Local Class
| - Anonymous Class

Static nested class can be instantiated without the outer class object.
An object of static nested class is not strongly associated with the outer class object
A static nested class cannot refer directly to instance variables and instance methods of
outer class.

| Normal/Regular Inner Class | Statis nested class | 
| ---- | ---- |
| Without an outer class object, there cannot be an inner class object. That is,
inner class is always associated with the outer class object | Without an outer class object existing, there may be a static nested class object. That is, a static nested 
class object is not associated with the outer class object |
| Regular nested class cannot be invoked directly | Static nested class can be invoked directly | 
| Both static and non static members of outer class can be accessed directly | 
Only the static members of an outer class can be accessed directly |

Normal Inner class can be of two types
1. Local Inner Class
2. Anonymous Inner Class


Static Nested Class Uses
1. Less memory overhead 
2. Preventing memory leaks - outer class can be safely garbage collected
3. Can be instantiated without other class object
4. Controlled access - Static nested class can access the instance methods and variables
of outer class if we pass an outer instance explicitly to the static nested class

# Direct Comparison
| Feature | Static Nested Class | Non-Static Nested Class | 
| --- | ---- | ---- |
| Enclosing Reference | None (Independent) | Implicit pointer to outer class OuterClass.this | 
| Instantiation | new Outer.staticClass() | outerInstance.new InnerClass(); |
| Memory Footprint | Lightweight | Heavyweight (hidden reference pointer) | 
| Risk of memory leaks | Low | High (keeps the parent instance alive indefinitely) |
| Best Used For | Independent helpers, builder patterns | logically linked subgroups |


 
Ref: https://www.geeksforgeeks.org/java/nested-classes-java/
https://www.geeksforgeeks.org/java/local-inner-class-java/
https://www.geeksforgeeks.org/java/anonymous-inner-class-java/