import java.util.Arrays;

public class AnagramChecker {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] sCharArr = s.toCharArray();
        char[] tCharArr = t.toCharArray();

        Arrays.sort(sCharArr);
        Arrays.sort(tCharArr);

        return Arrays.equals(sCharArr, tCharArr);
    }

    /*
        Time complexity: O(n)
        Space complexity: O(1)
        Explanation: n = length of s (and t); freq is a constant-size array (26).
    */
    public boolean isAnagramOptimised(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        for (int f : freq) if (f != 0) return false;
        return true;
    }

    /*
        Time complexity: O(n)
        Space complexity: O(1)
        Explanation: n = length of s (and t); freq is a constant-size array (26), it contains sorted style comparison.
    */
    public boolean isAnagramCountingSortStyle(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] a = new int[26], b = new int[26];
        for (int i = 0; i < s.length(); i++) a[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) b[t.charAt(i) - 'a']++;

        for (int i = 0; i < 26; i++) if (a[i] != b[i]) return false;
        return true;
    }


    public static void main(String[] args) {
        AnagramChecker checker = new AnagramChecker();
        System.out.println(checker.isAnagram("listen", "silent")); // true
        System.out.println(checker.isAnagram("hello", "world"));   // false

        System.out.println(checker.isAnagramOptimised("listen", "silent")); // true
        System.out.println(checker.isAnagramOptimised("hello", "world"));   // false

        System.out.println(checker.isAnagramCountingSortStyle("listen", "silent")); // true
        System.out.println(checker.isAnagramCountingSortStyle("hello", "world"));   // false 
    }
    
}
