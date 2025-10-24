package two_pointers;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Statement
 * Given a string num representing an integer, determine whether it is a strobogrammatic number. Return TRUE if the number is strobogrammatic or FALSE if it is not.
 *
 * Note: A strobogrammatic number appears the same when rotated
 * 180
 * 180
 *  degrees (viewed upside down). For example, “69” is strobogrammatic because it looks the same when flipped upside down, while “962” is not.
 *
 * Constraints:
 *
 * 1
 * <
 * =
 * 1<=
 *  num.length
 * <
 * =
 * 50
 * <=50
 *
 * num contains only digits.
 *
 * num has no leading zeros except when the number itself is zero.
 *
 *
 */
public class StroboGrammaticNumber {

    public static Map<String, String> dict = new HashMap<>();

    static {

        dict.put("0", "0");
        dict.put("6", "9");
        dict.put("8", "8");
        dict.put("9", "6");
    }

    public static void main(String[] args) {
        System.out.println(isStrobGrammaticNumber("9888"));
    }



    public static boolean isStrobGrammaticNumber(String text){
        if(text== null || text.isEmpty()) return false;

        int i = 0, j = text.length() - 1;

        while(i < j){
           if(!dict.containsKey(String.valueOf(text.charAt(i))) || !Objects.equals(dict.get(String.valueOf(text.charAt(i))), String.valueOf(text.charAt(j)))){
               return false;
           }

           i++; j--;
        }
        return true;
    }



}
