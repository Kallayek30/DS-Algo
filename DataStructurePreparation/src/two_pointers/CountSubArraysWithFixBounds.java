package two_pointers;

public class CountSubArraysWithFixBounds {

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 3, 5, 2, 7, 5},
                {1, 5},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4},
                {1, 5, 1, 5, 1, 5}
        };
        int[] minKs = {1, 1, 1, 2, 1};
        int[] maxKs = {5, 5, 1, 5, 5};

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int minK = minKs[i];
            int maxK = maxKs[i];
            System.out.println((i + 1) + ".\tnums = " + java.util.Arrays.toString(nums));
            System.out.println("\tminK = " + minK);
            System.out.println("\tmaxK = " + maxK);

            long result = getNoOfSubArrays(nums, minK, maxK);
            System.out.println("\n\tOutput: " + result);
            System.out.println("-".repeat(100));
    }
    }

    public static int getNoOfSubArrays(int [] arr, int minBound, int maxBound){

        int minPos = -1, maxPos = -1, leftBound = -1;
        int count = 0;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] < minBound || arr[i] > maxBound){
                minBound = maxBound = -1;
                leftBound = i;
            }else if (arr[i] == minBound){
                minPos = i;
            } else if (arr[i] == maxBound) {
                maxPos = i;
            }

            if(minPos !=-1 && maxPos!= -1){
                count+= Math.max(0, Math.min(minPos,maxPos) - leftBound);
            }
        }
        return count;

    }
}
