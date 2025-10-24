package two_pointers;

public class GetMaxScore {

    public static void main(String[] args) {

    }

    public static int getMaxScore(int[] numArray1, int[] numArray2) {

        if (numArray1 == null || numArray1.length == 0 || numArray2 == null || numArray2.length == 0) return -1;

        int sumPath1 = 0, sumPath2 = 0;
        int pointer1 = 0, pointer2 = 0;
        int MOD = 1_000_000_007;

        while (pointer1 < numArray1.length || pointer2 < numArray2.length) {
            if (pointer1 < numArray1.length && (pointer2 == numArray2.length || numArray1[pointer1] < numArray2[pointer2])) {
                sumPath1 += numArray1[pointer1];
                pointer1++;
            } else if (pointer2 < numArray2.length && (pointer1 == numArray1.length || numArray1[pointer1] > numArray2[pointer2])) {
                sumPath2 += numArray2[pointer2];
                pointer2++;
            } else {
                sumPath1 = sumPath2 = Math.max(sumPath1, sumPath2) + numArray1[pointer1];
                pointer1++;
                pointer2++;
            }

        }

        return (int)(Math.max(sumPath1, sumPath2)%MOD);
    }
}
