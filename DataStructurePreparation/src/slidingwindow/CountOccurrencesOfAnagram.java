package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class CountOccurrencesOfAnagram {

    public static void main(String[] args) {
        String text = "aababaadcabaa";
        String pattern = "abaa";
        System.out.println(countAnagramInaString(text, pattern));
    }

    public static int countAnagramInaString(String text, String pattern) {

        if (pattern == null || pattern.isEmpty() || text == null || text.isEmpty()) {
            return 0;
        }
        Map<Integer, Integer> charFrequency = new HashMap<>();

        for (char character : pattern.toLowerCase().toCharArray()) {
            charFrequency.put(character - 'a', charFrequency.getOrDefault(character - 'a', 0) + 1);
        }

        int i = 0, j = 0;
        int anagramCounter = 0;
        int uniqueCharCounter = charFrequency.size();
        while (j < text.length()) {
            if (charFrequency.get(text.charAt(j) - 'a') != null) {
                charFrequency.put(text.charAt(j) - 'a', charFrequency.get(text.charAt(j) - 'a') - 1);
                if (charFrequency.get(text.charAt(j) - 'a') == 0) uniqueCharCounter--;
            }
            if (j - i + 1 == pattern.length()) {
                if (uniqueCharCounter == 0) {
                    anagramCounter++;
                }
                if (charFrequency.get(text.charAt(i) - 'a') != null) {
                    charFrequency.put(text.charAt(i) - 'a', charFrequency.get(text.charAt(i) - 'a') + 1);
                    if (charFrequency.get(text.charAt(i) - 'a') == 1) {
                        uniqueCharCounter++;
                    }
                }
                i++;
            }
            j++;
        }
        return anagramCounter;

    }


}
