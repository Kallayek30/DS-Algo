package slidingwindow.variablesizewindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        List<String> inputStrings = Arrays.asList("AABCCBB", "ABBCB", "ABCCDE", "ABBCAB", "BBBBBBBBB");
        List<Integer> k = Arrays.asList(2, 1, 1, 2, 4);

        for (int i = 0; i < inputStrings.size(); ++i) {
            System.out.println((i + 1) + ".\tInput String: '" + inputStrings.get(i) + "'");
            System.out.println("\tk: " + k.get(i));
            System.out.println("\tLength of the longest substring with repeating characters: "
                    + getLongestRepeatingCharacterReplacement(inputStrings.get(i), k.get(i)));
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }

    public static int getLongestRepeatingCharacterReplacement(String text, int k){

        if(text == null || text.isEmpty() || k <= 0) return 0;

        int start = 0;
        int end = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        int maxFreq= Integer.MIN_VALUE;
        int lengthOfMaxSubString = Integer.MIN_VALUE;

        for(end = 0; end < text.length(); end++){
            freqMap.put(text.charAt(end), freqMap.getOrDefault(text.charAt(end), 0) + 1);

            maxFreq = Math.max(maxFreq, freqMap.get(text.charAt(end)));

            if(end - start + 1 - maxFreq > k){
                freqMap.put(text.charAt(start), freqMap.get(text.charAt(start)) - 1);
                start++;
            }
            lengthOfMaxSubString= Math.max(lengthOfMaxSubString, end - start + 1);
        }
     return lengthOfMaxSubString;
    }

}
