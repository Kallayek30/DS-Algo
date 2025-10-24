package slidingwindow.variablesizewindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String[] inputs = {
                "abcabcbb",
                "pwwkew",
                "bbbbb",
                "ababababa",
                "",
                "ABCDEFGHI",
                "ABCDEDCBA",
                "AAAABBBBCCCCDDDD"
        };
        for (int i = 0; i < inputs.length; i++) {
            String longestSubString = getLongestSubString(inputs[i]);
            System.out.print(i + 1);
            System.out.println("\tInput string: " + inputs[i]);
            System.out.println("\n\tLength of longest substring: " + longestSubString);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static String getLongestSubString(String text) {

        if (text == null || text.isEmpty() ) return "";

        int i = 0, j = 0;

        Map<Character, Integer> freqMap = new HashMap<>();
        String longestSubString = "";
        while (j < text.length()) {
            char currentChar = text.charAt(j);
            freqMap.put(currentChar, freqMap.getOrDefault(currentChar, 0) + 1);

            while (j - i + 1 > freqMap.size()) {
                char leftChar = text.charAt(i);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                if (freqMap.get(leftChar) == 0) {
                    freqMap.remove(leftChar);
                }
                i++;
            }

            if (j - i + 1 == freqMap.size()) {
                if (longestSubString.isEmpty()) {
                    longestSubString = text.substring(i, j + 1);
                } else {
                    longestSubString = j - i + 1 > longestSubString.length() ?
                            text.substring(i, j + 1) : longestSubString;
                }
            }
            j++;
        }
        return longestSubString;
    }
}
