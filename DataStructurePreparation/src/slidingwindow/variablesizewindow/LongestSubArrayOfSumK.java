package slidingwindow.variablesizewindow;

public class LongestSubArrayOfSumK {

    public static void main(String[] args) {

        int [] arr = {4,1,1,1,2,3,5};
        System.out.println(getLongestSubArraySize(arr, 5));

    }

    public static int getLongestSubArraySize(int [] positiveNumbers, int target){

        if(positiveNumbers == null || positiveNumbers.length == 0) return -1;

        int i = 0, j= 0;
        int sum = 0;
        int maxLen = 0;

        while(j < positiveNumbers.length){

            sum+=positiveNumbers[j]; // add the number

            if(sum > target){ // when the sum is greater than the target sum we need to reduce it
                while(sum > target && i < j){
                    sum-=positiveNumbers[i];
                    i++;
                }
            }

            if(sum == target){ // when the sum is hit do calculate the max length
                maxLen = Math.max(maxLen, j-i+1);
            }
            j++;

        }
        return maxLen;
    }
}
