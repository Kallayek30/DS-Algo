package slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

public class FirstNegativeNumberInEveryWindowSizeOfK {

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 2, 3, 4, -5, -6, 7, 8, -9};
        int k = 3;
        getNegativeNumber(arr, k);
    }

    public static void getNegativeNumber(int[] numbers, int k) {

        int i = 0, j = 0;
        Queue<Integer> negativeNumberTracker = new LinkedList<>();
        while (j < numbers.length) {
            if (numbers[j] < 0) {
                negativeNumberTracker.add(numbers[j]);
            }
            if (j - i + 1 == k) {
                if (!negativeNumberTracker.isEmpty()) {
                    if (negativeNumberTracker.peek() == numbers[i]) {
                        System.out.print(negativeNumberTracker.poll() + "\t");
                    } else {
                        System.out.print(negativeNumberTracker.peek() + "\t");
                    }
                }else {
                    System.out.print(0 + "\t");
                }
                i++;
            }
            j++;


        }


    }


}
