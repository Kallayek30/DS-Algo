package two_pointers;

public class MinimumNumberOfMovesToMakePalindrome {


    public static void main(String[] args) {

        System.out.println(noOfMovesRequired("axax"));

    }

    public static int noOfMovesRequired(String text) {

        if (text.isEmpty()) return -1;

        int i = 0;
        int j = text.length() - 1;
        int k = 0;
        int moves = 0;
        char[] characters = text.toCharArray();
        while (i < j) {
            k = j;
            while (k > i) {
                if (characters[k] == characters[i]) {
                     while (k < j) {
                        char temp = characters[k];
                        characters[k] = characters[k + 1];
                        characters[k + 1] = temp;
                        moves++;
                        k++;
                    }
                    j--;
                    break;
                }
                --k;
            }
            if (k == i) {
                moves += (text.length() / 2) - i;
            }
            i++;
        }
        return moves;
    }


}
