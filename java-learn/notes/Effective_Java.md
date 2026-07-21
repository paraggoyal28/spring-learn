## Effective Java



### Overriding equals method

High quality equals method:
1. Use the == operator to check if the argument is a reference to this object.
If so, return True. This is just a performance optimization but one that is worth doing if the 
comparison is potentially expensive.
2. Use the instanceof operator to check if the argument has the correct type.
If not return false.
3. Case the argument to correct type.
4. For each "significant" field in the class, check if that field of the argument matches the corresponding field of this 
object. If all these tests passes, then only return true, else return false.

For primitive fields whose type is not float or double, use the == operator for comparisons, for object reference fields, 
call the equals method recursively; for float fields, use the static Float.compare(float, float) method; and for double field
, use the Double.compare(double, double). While you could compare float and double fields with static methods Float.equals and 
Double.equals, this would entail autoboxing on every comparison, which would have poor performance. For array fields, apply 
these guidelines to each element. If every element in an array field is significant, use one of the Arrays.equals method.

Eg.

#### class with typical equals method
public final class PhoneNumber {
  private final short areaCode, prefix, lineNum;

  public PhoneNumber(int areaCode, int prefix, int lineNum) {
    this.areaCode = rangeCheck(areaCode, 999, "area code");
    this.prefix = rangeCheck(prefix, 999, "prefix");
    this.lineNum = rangeCheck(lineNum, 9999, "line num");
  }

  @Override public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof PhoneNumber)) {
      return false;
    }
    PhoneNumber pn = (PhoneNumber) o;
    return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode = areaCode;
  }
}

* Always override hashcode when overriding equals method
* Don't substitute another type for Object in the equals declaration.
* Don't try to be too clever. If you simply test fields for equality, it’s not hard to adhere to the equals contract

### Always override hashcode when overriding equals method
We must override hashcode in every class that overrides equals. If you fail to do so, your class will 
violate the general contract for hashcode which will prevent it from functioning properly in collections such 
as HashMaps and HashSets.
* When the hashcode method is invoked on an object repeatedly during an execution of an application, it must 
consistently return the same value, provided no information used in equals comparison is modified. This value need not
remain consistent from one execution of an application to another.
* If two objects are equal according to the equals(Object) method, then calling hashCode on the two objects must produce the 
same integer results.
* If two objects are unequal according to the equals(Object) method, it is not required that calling hashcode on each of 
the objects must produce distinct results. However, the programmer should be aware that producing distinct results for 
unequal objects may improve the performance of the hash tables.

The key provision that is violated when you fail to override hashcode is equal objects must have equal hash codes.
A good hash function tends to produce unequal hash codes for unequal instances. 

// Typical hashCode method
@Override public int hashCode() {
    int result = Short.hashCode(areaCode);
    result = 31 * result + Short.hashCode(prefix);
    result = 31 * result + Short.hashCode(lineNum);
    return result;
}

If you have a bona fide need for hash functions less likely to produce collisions, see Guava’s com.google.common.hash.Hashing
Unequal objects should have unequal hash codes.
The AutoValue framework provides a fine alternative to writing equals and hashcode methods manually, and IDEs also 
provide the same functionality.

### Always override toString

It is recommended that all subclasses override this method. Providing a good toString implementation makes your class much 
more pleasant to use and make systems using the class easier to debug. The toString method is automatically called when
an object is passed to println, printf. 
If you’ve provided a good toString method for PhoneNumber, generating a useful diagnostic message is as easy as this:
System.out.println("Failed to connect to " + phoneNumber);
The benefits of providing a good toString method extend beyond instances of the class to objects containing references to these instances, especially collections. Which would you rather see when printing a map, 
{Jenny=PhoneNumber@adbbd} or {Jenny=707-867-5309}?
When practical, the toString method should return all of the interesting information contained in the object, The string should be self-explanatory. 
A particularly annoying penalty for failing to include all of an object’s interesting information in its string representation is test failure reports that look like this:
Assertion failure: expected {abc, 123}, but was {abc, 123}.
Whether or not you decide to specify the format, you should clearly document your intentions. If you specify the format, you should do so precisely. For example, here’s a toString method to go with the PhoneNumber class 
/**
 * Returns the string representation of this phone number.
 * The string consists of twelve characters whose format is
 * "XXX-YYY-ZZZZ", where XXX is the area code, YYY is the
 * prefix, and ZZZZ is the line number. Each of the capital
 * letters represents a single decimal digit.
 *
 * If any of the three parts of this phone number is too small
 * to fill up its field, the field is padded with leading zeros.
 * For example, if the value of the line number is 123, the last
 * four characters of the string representation will be "0123".
 */
@Override public String toString() {
    return String.format("%03d-%03d-%04d",
            areaCode, prefix, lineNum);
}

If you decide not to specify a format, the documentation comment should read something like this
/**
 * Returns a brief description of this potion. The exact details
 * of the representation are unspecified and subject to change,
 * but the following may be regarded as typical:
 *
 * "[Potion #9: type=love, smell=turpentine, look=india ink]"
 */
@Override public String toString() { ... }

Whether or not you specify the format, provide programmatic access to the information contained in the value returned by toString. For example, the PhoneNumber class should contain accessors for the area code, prefix, and line number. If you fail to do this, you force programmers who need this information to parse the string. Besides reducing performance and making unnecessary work for programmers, this process is error-prone and results in fragile systems that break if you change the format. By failing to provide accessors, you turn the string format into a de facto API, even if you’ve specified that it’s subject to change.

Google’s open source AutoValue facility, will generate a toString method for you, as will most IDEs. These methods are great for telling you the contents of each field but aren’t specialized to the meaning of the class. So, for example, it would be inappropriate to use an automatically generated toString method for our PhoneNumber class (as phone numbers have a standard string representation), but it would be perfectly acceptable for our Potion class. That said, an automatically generated toString method is far preferable to the one inherited from Object, which tells you nothing about an object’s value.

To recap, override Object’s toString implementation in every instantiable class you write, unless a superclass has already done so. It makes classes much more pleasant to use and aids in debugging. The toString method should return a concise, useful description of the object, in an aesthetically pleasing format.

### Override clone judiciously

* What does Cloneable interface do, given that it contains no methods ? If a class implements Cloneable, Object's clone method 
returns a field-by-field copy of the object; otherwise it throws CloneNotSupportedException. 
* A class implementing Cloneable is expected to provide functioning public clone method. 

/ Must implement the Cloneable marker interface
class Student implements Cloneable {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Overriding clone() and changing access modifier from protected to public
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); 
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            Student s1 = new Student(101, "Alex");
            // Typecasting is required since clone() returns an Object type
            Student s2 = (Student) s1.clone(); 
            
            System.out.println(s2.id + " " + s2.name); // Outputs: 101 Alex
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

General contract for clone method:
Creates and returns a copy of the object.
x.clone() != x and
x.clone().getClass() == x.getClass() 

* immutable classes should never provide a clone method because it would merely encourage wasteful copying

// Clone method for class with no references to mutable state
@Override public PhoneNumber clone() {
    try {
        return (PhoneNumber) super.clone();
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();  // Can't happen
    }
}

What is covariant return type
In other words, an overriding method’s return type can be a subclass of the overridden method’s return type

If an object contains fields that refer to mutable objects, the simple clone implementation shown earlier can be disastrous. For example, consider the Stack class

public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    // Ensure space for at least one more element.
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}

Suppose you want to make this class cloneable. If the clone method merely returns super.clone(), the resulting Stack instance will have the correct value in its size field, but its elements field will refer to the same array as the original Stack instance. Modifying the original will destroy the invariants in the clone and vice versa.

* In effect, the clone method functions as a constructor; you must ensure that it does no harm to the original object and that it properly establishes invariants on the clone.

In order for the clone method on Stack to work properly, it must copy the internals of the stack. The easiest way to do this is to call clone recursively on the elements array:

// Clone method for class with references to mutable state
@Override public Stack clone() {
    try {
        Stack result = (Stack) super.clone();
        result.elements = elements.clone();
        return result;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}

* the earlier solution would not work if the elements field were final because clone would be prohibited from assigning a new value to the field. This is a fundamental problem: like serialization, the Cloneable architecture is incompatible with normal use of final fields referring to mutable objects, except in cases where the mutable objects may be safely shared between an object and its clone. In order to make a class cloneable, it may be necessary to remove final modifiers from some fields.

* It is not always sufficient merely to call clone recursively. For example, suppose you are writing a clone method for a hash table whose internals consist of an array of buckets, each of which references the first entry in a linked list of key-value pairs. For performance, the class implements its own lightweight singly linked list instead of using java.util.LinkedList internally:

public class HashTable implements Cloneable {
    private Entry[] buckets = ...;

    private static class Entry {
        final Object key;
        Object value;
        Entry  next;

        Entry(Object key, Object value, Entry next) {
            this.key   = key;
            this.value = value;
            this.next  = next;  
        }
    }
    ... // Remainder omitted
}

Suppose you merely clone the bucket array recursively, as we did for Stack:


// Broken clone method - results in shared mutable state!
@Override public HashTable clone() {
    try {
        HashTable result = (HashTable) super.clone();
        result.buckets = buckets.clone();
        return result;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}

Though the clone has its own bucket array, this array references the same linked lists as the original, which can easily cause nondeterministic behavior in both the clone and the original. To fix this problem, you’ll have to copy the linked list that comprises each bucket. Here is one common approach:

// Recursive clone method for class with complex mutable state
public class HashTable implements Cloneable {
    private Entry[] buckets = ...;

    private static class Entry {
        final Object key;
        Object value;
        Entry  next;

        Entry(Object key, Object value, Entry next) {
            this.key   = key;
            this.value = value;
            this.next  = next;  
        }


        // Recursively copy the linked list headed by this Entry
        Entry deepCopy() {
            return new Entry(key, value,
                next == null ? null : next.deepCopy());
        }
    }

    @Override public HashTable clone() {
        try {
            HashTable result = (HashTable) super.clone();
            result.buckets = new Entry[buckets.length];
            for (int i = 0; i < buckets.length; i++)
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    ... // Remainder omitted
}

The private class HashTable.Entry has been augmented to support a “deep copy” method. The clone method on HashTable allocates a new buckets array of the proper size and iterates over the original buckets array, deep-copying each nonempty bucket.

// Iteratively copy the linked list headed by this Entry
Entry deepCopy() {
   Entry result = new Entry(key, value, next);
   for (Entry p = result; p.next != null; p = p.next)
      p.next = new Entry(p.next.key, p.next.value, p.next.next);
   return result;
}

 If you write a thread-safe class that implements Cloneable, remember that its clone method must be properly synchronized, just like any other method. 

 To recap, all classes that implement Cloneable should override clone with a public method whose return type is the class itself. This method should first call super.clone, then fix any fields that need fixing. Typically, this means copying any mutable objects that comprise the internal “deep structure” of the object and replacing the clone’s references to these objects with references to their copies. While these internal copies can usually be made by calling clone recursively, this is not always the best approach. If the class contains only primitive fields or references to immutable objects, then it is likely the case that no fields need to be fixed. There are exceptions to this rule. For example, a field representing a serial number or other unique ID will need to be fixed even if it is primitive or immutable.

  A better approach to object copying is to provide a copy constructor or copy factory. A copy constructor is simply a constructor that takes a single argument whose type is the class containing the constructor, for example,

  // Copy constructor
public Yum(Yum yum) { ... };


// Copy factory
public static Yum newInstance(Yum yum) { ... };

While it’s less harmful for final classes to implement Cloneable, this should be viewed as a performance optimization, reserved for the rare cases where it is justified

As a rule, copy functionality is best provided by constructors or factories. A notable exception to this rule is arrays, which are best copied with the clone method.

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ConversionConstructorExample {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("banana");
        hashSet.add("apple");
        hashSet.add("cherry");

        // Conversion constructor: accepts the Collection interface.
        // The caller chooses TreeSet as the copied implementation.
        Set<String> sortedCopy = new TreeSet<>(hashSet);

        System.out.println(hashSet);     // unordered HashSet
        System.out.println(sortedCopy);  // [apple, banana, cherry]
    }
}

public TreeSet(Collection<? extends E> source) {
    this();
    addAll(source);
}

Example of Deep Copy using clone

class Address implements Cloneable {
    String city;

    Address (String city) {
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Clone the Address object
        return super.clone();
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Create a shallow copy first
        Person cloned = (Person) super.clone();

        // Now clone the nested Address object manually
        cloned.address = (Address) address.clone();

        return cloned;
    }
}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Address a1 = new Address("London");
        Person p1 = new Person("Alice", a1);

        // Create deep copy
        Person p2 = (Person) p1.clone();

        // Display original and copied before change
        System.out.println("Original city: " + p1.address.city);

        System.out.println("Copied city: " + p2.address.city);

        // Modify copied object's address
        p2.address.city = "Paris";

        // Display original and copied before change
        System.out.println("Original city: " + p1.address.city);

        System.out.println("Copied city: " + p2.address.city);

    }
}

* Use Copy constructor

class Address {
    String city;

    Address(String city) {
        this.city = city;
    }

    // Copy Constructor for deep copy
    Address (Address other) {
        this.city = other.city;
    }
}

class Person {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    Person(Person other) {
        this.name = other.name;
        this.address = new Address(other.address);
    }
}

public class Main {
    public static void main(String[] args) {

        Address a1 = new Address("London");
        Person p1 = new Person("Bob", a1);

        // Deep copy using copy constructor
        Person p2 = new Person(p1);

        // Display original and modified city
        System.out.println("Original City: " + p1.address.city);
        System.out.println("Copied City: " + p2.address.city);

        p2.address.city = "Paris";

        // Display original and modified city
        System.out.println("Original City: " + p1.address.city);
        System.out.println("Copied City: " + p2.address.city);

    }
}

### Consider implementing Comparable

Virtually all of the value classes in Java platform libraries, as well as enum types implements Comparable. If we are 
writing a value class with an obvious natural ordering, such as alphabetical order, numerical order, or chronological order,
we should implement the Comparable interface.
public interface Comparable<T> {
    int compareTo(T t);
}

Compares this object with the specified object for order. Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object. Throws ClassCastException if the specified object’s type prevents it from being compared to this object.

If a class has multiple significant fields, the order in which you compare them is critical. Start with the most significant field and work your way down. If a comparison results in anything other than zero (which represents equality), you’re done; just return the result. If the most significant field is equal, compare the next-most-significant field, and so on, until you find an unequal field or compare the least significant field.

// Multiple-field Comparable with primitive fields
public int compareTo(PhoneNumber pn) {
    int result = Short.compare(areaCode, pn.areaCode);
    if (result == 0)  {
        result = Short.compare(prefix, pn.prefix);
        if (result == 0)
            result = Short.compare(lineNum, pn.lineNum);
    }
    return result;
}

In Java 8, the Comparator interface was outfitted with a set of comparator construction methods, which enable fluent construction of comparators.

// Comparable with comparator construction methods
private static final Comparator<PhoneNumber> COMPARATOR =
        comparingInt((PhoneNumber pn) -> pn.areaCode)
          .thenComparingInt(pn -> pn.prefix)
          .thenComparingInt(pn -> pn.lineNum);

public int compareTo(PhoneNumber pn) {
    return COMPARATOR.compare(this, pn);
}

// BROKEN difference-based comparator - violates transitivity!
static Comparator<Object> hashCodeOrder = new Comparator<>() {
    public int compare(Object o1, Object o2) {
        return o1.hashCode() - o2.hashCode();
    }
};

Do not use this technique. It is fraught with danger from integer overflow and IEEE 754 floating point arithmetic artifacts

// Comparator based on static compare method
static Comparator<Object> hashCodeOrder = new Comparator<>() {
    public int compare(Object o1, Object o2) {
        return Integer.compare(o1.hashCode(), o2.hashCode());
    }
};

Or

// Comparator based on Comparator construction method
static Comparator<Object> hashCodeOrder =
        Comparator.comparingInt(o -> o.hashCode());

In summary, whenever you implement a value class that has a sensible ordering, you should have the class implement the Comparable interface so that its instances can be easily sorted, searched, and used in comparison-based collections. When comparing field values in the implementations of the compareTo methods, avoid the use of the < and > operators. Instead, use the static compare methods in the boxed primitive classes or the comparator construction methods in the Comparator interface.
