package data_structures;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {
        // Hash maps are not ordered.
        HashMap<String, Double> productAndValue = new HashMap<>();

        // Adding elements to HashMap object
        productAndValue.put("pencil", 0.99);
        productAndValue.put("notepad", 2.50);
        productAndValue.put("printer", 199.99);
        productAndValue.put("chair", 74.99);
        productAndValue.put("lotto ticket", 3.99);

        // Checking if HashMap contains a mapping for a particular key and a particular value.
        boolean hasThisKey = productAndValue.containsKey("computer");
        boolean hasThisValue = productAndValue.containsValue(2.50);

        System.out.println("The hashmap contains a mapping for key \"computer\": " + hasThisKey);
        System.out.println("The hashmap contains a key for value \"2.50\": " + hasThisValue);

        // Iterating through the HashMap and displaying the results.
        for (Map.Entry<String, Double> entry : productAndValue.entrySet()) {
            System.out.println("Key:\t" + entry.getKey() + "\nValue:\t" + entry.getValue());
        }

        // Remove an entry with specified key.
        productAndValue.remove("lotto ticket");

        // Remove all entries.
        productAndValue.clear();

        // Demonstrate that it's been cleared.
        for (Map.Entry<String, Double> entry : productAndValue.entrySet()) {
            System.out.println("Key:\t" + entry.getKey() + "\nValue:\t" + entry.getValue());
        }
    }
}
