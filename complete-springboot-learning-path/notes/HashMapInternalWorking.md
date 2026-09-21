# HashMap Internal Working

1. HashMap is Node<K, V> buckets. 
Each node contains a hash, key, value and next node

2. put(K key, V value) operation
* Step 1(Hashing): Java calls key.hashcode() to find the relevant bucket
* Step 2(Index Calculation): from the hash it calculates the bucket index 
index = (n-1) & hash, where n is the size of the table
* Step 3 (Collision Handling): In case already elements present in that bucket
it compares the keys using key.equals(existingKey), if existing key is equal to the 
new key, the existing value is replaced with new value, else the new key, value pair
is inserted
* Resizing happens when the number of elements exceeds Capacity * Load Factor
Default load factor - 0.75
Default capacity - 16
So if the number of elements exceeds 12
* In resizing, the size of the bucket is doubled, all existing elements are rehashed
and redistributed into new bucket positions. This is costly O(n) operation, thus
in case of larger dataset a high initial capacity is required.

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class StudentKey {
    // Make all fields private final
    private final int id;
    private final String department;

    public StudentKey(int id, String department) {
        this.id = id;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    // HashCode Contract: Must use the same fields as equals()
    @Override
    public int hashCode() {
        return Objects.hash(id, department);
    }

    // Equals contract: Reflexive, Symmetric, transitive, and consistent
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StudentKey that = (StudentKey) obj;
        return id == that.id && Objects.equals(department, that.department);
    }

    @Override
    public String toString() {
        return "StudentKey{Id=" + id + ", dept='" + department + "'}"; 
    }

    public static void main(String[] args) {

        Map<StudentKey, String> map = new HashMap<>();
        
        StudentKey key1 = new StudentKey(101, "HR");
        StudentKey key2 = new StudentKey(101, "HR");

        map.put(key1, "Alice");

        System.out.println(map.get(key2)); // Alice

    }

}