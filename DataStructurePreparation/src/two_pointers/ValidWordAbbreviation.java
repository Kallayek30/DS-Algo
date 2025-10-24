package two_pointers;

public class ValidWordAbbreviation {

    public static void main(String[] args) {
        System.out.println(validWordAbbreviation("minimum", "min2um"));
        System.out.println(validWordAbbreviation("Kubernetes", "K8s"));
    }

    public static boolean validWordAbbreviation(String word, String abbr) {

        // Replace the following return statement with your code
        if (word == null || word.isEmpty() || abbr == null || abbr.isEmpty()) return false;
        int i = 0, j = 0;
        while (i < abbr.length()) {
            if (Character.isDigit(abbr.charAt(i))) {
                if (Character.getNumericValue(abbr.charAt(i)) == 0) return false;
                int num = 0;
                while (Character.isDigit(abbr.charAt(i))) {
                    num = num * i + Character.getNumericValue(abbr.charAt(i));
                    i++;
                }
                j += num;
            } else {
                if (j > word.length() || abbr.charAt(i) != word.charAt(j)) {
                    return false;
                }
                j++;
                i++;
            }

        }
        return j == word.length() && i == abbr.length();
    }
}
