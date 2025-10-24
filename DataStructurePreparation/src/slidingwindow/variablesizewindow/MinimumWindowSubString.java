package slidingwindow.variablesizewindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubString {

    public static void main(String[] args) {

        String[] s = {"PATTERN", "LIFE", "ABRACADABRA", "STRIKER", "DFFDFDFVD"};
        String[] t = {"TN", "I", "ABC", "RK", "VDD"};

        for (int i = 0; i < s.length; i++) {
            System.out.printf("%d.\ts: %s\n\tt: %s\n\tThe minimum substring containing %s is: %s\n",
                    i + 1, s[i], t[i], t[i], getMinimumWindowString(s[i], t[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static String getMinimumWindowString(String text, String pattern){

        if(text == null || text.isEmpty() || pattern == null || pattern.isEmpty()) return "";

        int i = 0,  j = 0;
        String minimumWindowString = "";

        Map<Character, Integer> patternFreqMap = new HashMap<>();
        Map<Character, Integer> textFreqMap = new HashMap<>();
        for(char character : pattern.toCharArray()){
            patternFreqMap.put(character, patternFreqMap.getOrDefault(character, 0)+ 1);
        }
        int uniqueCharacters = patternFreqMap.size();
        int current = 0;

        while(j < text.length()){
            char currentChar = text.charAt(j);
            if(patternFreqMap.containsKey(currentChar)){
              textFreqMap.put(currentChar, textFreqMap.getOrDefault(currentChar, 0)+ 1);
              if(textFreqMap.get(currentChar).equals(patternFreqMap.get(currentChar))){
                  current++;
              }
            }
            while(current == uniqueCharacters){
                if(minimumWindowString.isEmpty()){
                    minimumWindowString = text.substring(i, j + 1);
                }else {
                    minimumWindowString = j - i + 1 < minimumWindowString.length() ?
                            text.substring(i , j + 1) : minimumWindowString;
                }
                char leftChar = text.charAt(i);
                if(patternFreqMap.containsKey(leftChar)){
                    textFreqMap.put(leftChar, textFreqMap.get(leftChar) - 1);
                    if(textFreqMap.get(leftChar) < patternFreqMap.get(leftChar)){
                        current--;
                    }
                }
                i++;
            }
            j++;
        }
        return minimumWindowString;
    }
}
