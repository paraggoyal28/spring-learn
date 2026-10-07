package example.newFeatures;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class BigIntegerMath {
    public static void main(String[] args) {
        BigInteger num1 = new BigInteger("50000000000000000000");
        BigInteger num2 = new BigInteger("25000000000000000000");

        // Addition (+)
        BigInteger sum = num1.add(num2);
        System.out.println("Sum: " + sum);

        BigInteger diff = num1.subtract(num2);
        System.out.println("Difference: " + diff);

        BigInteger product = num1.multiply(num2);
        System.out.println("Product: " + product);

        BigInteger quotient = num1.divide(num2);
        System.out.println("Quotient: " + quotient);

        BigInteger remainder = num1.remainder(num2);
        System.out.println("Remainder: " + remainder);

        BigInteger totalGold = new BigInteger("10000000000000000003");
        BigInteger players = new BigInteger("4");

        BigInteger[] result = totalGold.divideAndRemainder(players);

        System.out.println("Gold per player: " + result[0]);
        System.out.println("Leftover gold: " + result[1]);

        // Generate a random, massive 512 bit prime number
        BigInteger massivePrime = BigInteger.probablePrime(512, new Random());
        System.out.println("Generated Prime: " + massivePrime);

        // check if a number if prime
        boolean isPrime = massivePrime.isProbablePrime(100);
        System.out.println("Is it prime? " + isPrime);

        //BigInteger value1 = BigInteger.ZERO;
        //BigInteger value2 = BigInteger.ONE;
        BigInteger value3 = BigInteger.TWO;

        BigInteger myNum = new BigInteger("50");

        // Comparing numbers
        // Comparing returns -1 (less than), 0 (equals), 1 (greater than)
        if (myNum.compareTo(value3) > 0) {
            System.out.println("myNum is greater than 10");
        } 

        if (myNum.equals(new BigInteger("50"))) {
            System.out.println("Numbers are equal");
        }

        BigInteger b1 = new BigInteger("12"); // Binary: 1100
        BigInteger b2 = new BigInteger("10"); // Binary: 1010

        BigInteger andResult = b1.and(b2);
        BigInteger shiftLeftResult = b1.shiftLeft(2);

        System.out.println("AndResult: " + andResult);
        System.out.println("Shift left result: " + shiftLeftResult);

        BigInteger value1 = new BigInteger("1");
        BigInteger value2 = new BigInteger("1");

        System.out.println(value1 == value2);
        System.out.println(value1.equals(value2));

        BigDecimal payment = new BigDecimal("1234567.89");

        NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("US: " + usFormat.format(payment));

        BigDecimal cashInHand = new BigDecimal("50.0");
        BigDecimal itemCost = new BigDecimal("50.00");

        System.out.println("Using equals(): " + cashInHand.equals(itemCost)); 

        if (cashInHand.compareTo(itemCost) == 0) {
            System.out.println("You have the exact amount of money required!");
        }

        BigDecimal itemPrice = new BigDecimal("19.99");
        BigDecimal shipping = new BigDecimal("4.99");
        BigDecimal discount = new BigDecimal("2.50");
        BigDecimal taxRate = new BigDecimal("0.08");

        // Addition
        BigDecimal subTotal = itemPrice.add(shipping);

        BigDecimal discountedSubTotal = subTotal.subtract(discount);

        System.out.println("Discounted SubTotal: " + discountedSubTotal);

        BigDecimal tax = discountedSubTotal.multiply(taxRate);

        System.out.println("Tax: " + tax);

        BigDecimal finalTax = tax.setScale(2, RoundingMode.HALF_UP);

        System.out.println("Final Tax: " + finalTax);

        int[] emptyArray = new int[0];

        int[] emptyArray2 = {};

        System.out.println(emptyArray.length);

        System.out.println(emptyArray2.length);

    }
}
