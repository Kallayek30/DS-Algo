package slidingwindow;

public class MaximumSumSubArrayOfSizeK {

    public static void main(String[] args) {

        System.out.println(new MaximumSumSubArrayOfSizeK().maximumSumSubArray(new int[] {100, 200, 300, 400,200,600}, 2));

    }


    public int maximumSumSubArray(int[] numbers, int k) {

        int i = 0, j = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;

        while (j < numbers.length) {
            sum += numbers[j];
            if (j - i + 1 == k) {
                max = Math.max(sum, max);
                sum -= numbers[i];
                i++;
            }
            j++;
        }
        return max;

    }


}
