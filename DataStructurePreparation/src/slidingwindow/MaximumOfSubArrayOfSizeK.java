package slidingwindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaximumOfSubArrayOfSizeK {

    public static void main(String[] args) {

        System.out.println(getMaximumOfSubArraySizeK(new int[]{10,7,8,11}, 2));

    }

    public static List<Integer> getMaximumOfSubArraySizeK(int[] numbers, int k) {

        int i = 0, j = 0, x = 0;
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> numberQueue = new ArrayDeque<>();

        while (j < numbers.length) {
            while (!numberQueue.isEmpty() && numberQueue.peekLast() < numbers[j]) {
                numberQueue.removeLast();
            }
            numberQueue.addLast(numbers[j]);
            if (j - i + 1 == k) {
                ans.add(numberQueue.peekFirst()); // when the sliding window is hit add the max item in the list
                if (!numberQueue.isEmpty() && numbers[x] == numberQueue.peek()) {
                    numberQueue.pollFirst();
                }
                x++;
                while (!numberQueue.isEmpty() && numberQueue.peekLast() < numbers[j]) {
                    numberQueue.removeLast();
                }

                i++;
            }
            j++;
        }
        return ans;
    }



}

