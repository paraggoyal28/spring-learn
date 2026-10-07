package example.newFeatures;

import java.util.Scanner;
import java.math.BigInteger;


public class BigIntegerTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("How many numbers you would like to draw ? ");
        int k = in.nextInt();

        System.out.println("What is the highest number you can draw ? ");
        BigInteger n = in.nextBigInteger();

        /*
        compute binomial coefficient n*(n-1)*(n-2)*(n-3)...(n-k+1)/1*2*3.,k
        */

        BigInteger lotteryOdds = BigInteger.ONE;

        for (int i = 1; i <= k; ++i) {
            lotteryOdds = lotteryOdds
                            .multiply(n.subtract(BigInteger.valueOf(i-1)))
                            .divide(BigInteger.valueOf(i));
        }
            
        System.out.printf("Your odds are 1 in %s, Good luck!%n", lotteryOdds);
    }    
}
