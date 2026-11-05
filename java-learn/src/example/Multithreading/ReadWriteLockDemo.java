package example.Multithreading;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ConfigurationCache<K, V> {
    private final Map<K, V> cache = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // Extract individual read and write lock
    private final Lock readLock = readWriteLock.readLock();
    private final Lock  writeLock = readWriteLock.writeLock();

    public V get(K key) {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is Reading key: " + key);
            Thread.sleep(1000);
            return cache.get(key);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            readLock.unlock();
        }
        return null;
    }

    public void put(K key, V value) {
        writeLock.lock();
        // Exclusive lock: blocks all other readers and writers
        try {
            System.out.println(Thread.currentThread().getName() + " is WRITING key: " + key);
            Thread.sleep(1000);
            cache.put(key, value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ConfigurationCache<String, String> configCache = new ConfigurationCache<>();

        // Pre-populate some data
        configCache.put("TIMEOUT", "30s");
        configCache.put("MAX_CONNECTIONS", "100");

        // Simulate multiple threads reading simultaneously
        Runnable readerTask = () -> {
            System.out.println("Value of TIMEOUT: " + configCache.get("TIMEOUT"));
        };

        // Simulate a thread updating configuration
        Runnable writeTask = () -> {
            configCache.put("TIMEOUT", "60s");
        };

        Thread t1 = new Thread(readerTask, "Reader-1");
        Thread t2 = new Thread(readerTask, "Reader-2");
        Thread t3 = new Thread(writeTask, "Writer-1");
        Thread t4 = new Thread(readerTask, "Reader-3");

        t1.start();
        t2.start();
        t3.start(); // Will block until readers finish, then block subsequent readers until write is completed
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}
