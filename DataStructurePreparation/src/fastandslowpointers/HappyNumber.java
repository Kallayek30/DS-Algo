package fastandslowpointers;

public class HappyNumber {

    public static void main(String[] args) {
        int a[] = {1, 5, 19, 25, 7};
        for (int i = 0; i < a.length; i++) {
            System.out.println((i + 1) + ".\tInput Number: " + a[i]);
            String output = isHappyNumber(a[i]) ? "True" : "False";

            System.out.println("\n\tIs it a happy number? " + output);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int sumOfSquareDigits(int number) {
        int totalSum = 0;
        while (number > 0) {
            int digit = number % 10;
            number = number / 10;
            totalSum += (int) Math.pow(digit, 2);
        }
        return totalSum;
    }

    public static boolean isHappyNumber(int number){

        int slowPointer = number;
        int fastPointer = sumOfSquareDigits(number);

        while(fastPointer!= 1 && fastPointer!=slowPointer ){
            slowPointer = sumOfSquareDigits(slowPointer);
            fastPointer = sumOfSquareDigits(sumOfSquareDigits(fastPointer));
            System.out.println("slow pointer : "+ slowPointer + " fast pointer : "+ fastPointer);
        }

        return fastPointer == 1;
    }
}
