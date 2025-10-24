package slidingwindow.variablesizewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.educative.io/courses/grokking-coding-interview/solution-fruit-into-baskets
 *
 * While visiting a farm of fruits, you have been given a row of fruits represented by an integer array, fruits, where fruits[i] is the type of fruit the
 * i
 * t
 * h
 * i
 * th
 *
 *  tree produces. You have to collect fruits, but there are some rules that you must follow while collecting fruits:
 *
 * You are given only two baskets, each capable of holding an unlimited quantity of a single type of fruit.
 *
 * You can start collecting from any tree but must collect exactly one fruit from each tree (including the starting tree) while moving to the right.
 *
 * You must stop while encountering a tree with a fruit type that cannot fit into any of your baskets.
 *
 * Return the maximum number of fruits you can collect following the above rules for the given array of fruits.
 *
 */
public class FruitsIntoBaskets {

    public static void main(String[] args) {

        int[][] fruits = {{3,4,2,1,3,2},
                {2,2,2,3,1,2,4,4,4,4},
                {1,1,1,1,1,1,1,1,1,1},
                {2,3,4,1,3,3,1,2,3,4,1,5,2,5,7,7},
                {5,4,3,2,1,1}};
        for (int i = 0; i < fruits.length; i++) {
            System.out.print((i + 1) + ".\tFruits: [");
            for (int j = 0; j < fruits[i].length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(fruits[i][j]);
            }
            System.out.println("]");
            System.out.println("\n\tMaximum number of fruit(s) collected: " + getMaxNoOfFruits(fruits[i], 2));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int getMaxNoOfFruits(int[] fruits, int k) {

        if (fruits == null || fruits.length == 0 || k <= 0) return -1;

        int start = 0, end = 0;
        int collected = Integer.MIN_VALUE;

        Map<Integer, Integer> baskets = new HashMap<>();

        while (end < fruits.length) {

            baskets.put(fruits[end], baskets.getOrDefault(fruits[end], 0) + 1);

            while (baskets.size() > k) {
                baskets.put(fruits[start], baskets.get(fruits[start]) - 1);
                if (baskets.get(fruits[start]) == 0) {
                    baskets.remove(fruits[start]);
                }
                start++;
            }
            collected = Math.max(collected, end - start + 1);
            end++;
        }

        return collected;
    }
}
