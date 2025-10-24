package slidingwindow.variablesizewindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubSequence {

    public static void main(String[] args) {

        System.out.println("Minimum window substring is " + getMinimumWindow("abcxxxxdddeabcdasdasdabscdasdasdabcd", "abcd"));

    }

    public static String getMinimumWindow(String text, String pattern) {

        if (text == null || text.isEmpty() || pattern == null || pattern.isEmpty()) return "";

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char character : pattern.toCharArray()) {
            frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
        }
        int uniqueCharSize = frequencyMap.size();
        int i = 0, j = 0;
        String minWindow = "";
        int windowSize = Integer.MAX_VALUE;

        while (j < text.length()) {

            if (frequencyMap.get(text.charAt(j)) != null) {
                frequencyMap.put(text.charAt(j), frequencyMap.get(text.charAt(j)) - 1);
                if (frequencyMap.get(text.charAt(j)) == 0) {
                    uniqueCharSize--;
                }
            }

            if (uniqueCharSize == 0) {

                while (uniqueCharSize == 0) {
                    windowSize = Math.min(windowSize, j - i + 1);
                    String currMinimumWindow = text.substring(i, j + 1);
                    minWindow = windowSize < currMinimumWindow.length() ? minWindow : currMinimumWindow;
                    if (frequencyMap.containsKey(text.charAt(i))) {
                        if (frequencyMap.get(text.charAt(i)) == 0) {
                            uniqueCharSize++;
                        }
                        frequencyMap.put(text.charAt(i), frequencyMap.getOrDefault(text.charAt(i), 0) + 1);
                    }
                    i++;
                }
            }
            j++;
        }
        return minWindow;
    }
}
