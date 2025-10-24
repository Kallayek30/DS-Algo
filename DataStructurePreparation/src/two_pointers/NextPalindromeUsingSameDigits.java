package two_pointers;

public class NextPalindromeUsingSameDigits {

    public static void main(String[] args) {

    }

    public static String getNextpalindromeString(String text){

        if(text.isEmpty()) return "";

        if(text.length() == 1) return "";

        int leftHalfLen = text.length()/2;
        String leftHalf = text.substring(0, leftHalfLen);
        String nextPalindrome = findNextPalindrome(leftHalf);
        if(nextPalindrome.isEmpty()){
            return "";
        }
        StringBuilder palindrome = new StringBuilder();
        palindrome.append(nextPalindrome);
        if(text.length()%2 == 0){
            return nextPalindrome + palindrome.reverse();
        }else{
            return palindrome.append(text.charAt(leftHalfLen)).append(palindrome.reverse()).toString();
        }

    }

    private static String findNextPalindrome(String leftHalf) {

        int i = leftHalf.length() -2;
        char[] charArray = leftHalf.toCharArray();
        while(i >=0 && charArray[i] - '0' <= charArray[i+1] - '0'){
            i--;
        }
        if(i == -1){
            return "";
        }
        int j = leftHalf.length() -1;
        while(charArray[j] - '0' <= charArray[i] - '0'){
            j--;
        }
        swapValues(charArray, i, j);
        return reverseCharArray(charArray, i+1, j);

    }

    private static String reverseCharArray(char[] charArray, int i, int j) {
        while(i < j){
            swapValues(charArray, i, j);
            i++;
            j--;
        }
        return new String(charArray);
    }

    private static void swapValues(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }

}
