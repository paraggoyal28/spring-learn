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