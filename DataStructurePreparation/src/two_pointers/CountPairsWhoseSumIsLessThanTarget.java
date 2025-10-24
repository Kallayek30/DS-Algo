package two_pointers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountPairsWhoseSumIsLessThanTarget {

    public static void main(String[] args) {

        System.out.println("no of pairs : " + getNoOfPairs(new int[]{10, 1, 6, 2, 3, 8}, 9));
    }

    public static int getNoOfPairs(int [] numbers, int target){

        if(numbers == null || numbers.length == 0) return -1;

        List<Integer> integers = new java.util.ArrayList<>(Arrays.stream(numbers).boxed().toList());
        Collections.sort(integers);

        int low = 0;
        int high = integers.size() -1;
        int count = 0;
        while(low < high){
            if(integers.get(low) + integers.get(high) < target){
                count+=high-low;
                low++;
            }else {
                high--;
            }
        }
        return count;
    }

}
