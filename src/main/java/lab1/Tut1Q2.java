package lab1;

import java.util.ArrayList;
import java.util.Arrays;

public class Tut1Q2 {

  // Brute force
  // Generating permutations takes O(n!) time while checking if each string matches takes O(n) time
  public static boolean m1(char[] s1, char[] s2) {
    for (String s : permute(new String(s1))) {
      boolean found = true;
      for (int i = 0; i < s2.length; i++) {
        if (s.charAt(i) != s2[i]) {
          found = false;
          break;
        }
      }
      if (found) {
        return true;
      }
    }
    return false;
  }

  // Find permutations of string in O(n!) time
  // At each stage, n recursive calls are made with input length n-1 
  // Thus the time complexity is O(n * (n-1) * (n-2) * ... * 2 * 1) = O(n!)
  private static ArrayList<String> permute(String s1) {
    ArrayList<String> out = new ArrayList<>();
    if (s1.length() == 0) {
      out.add("");
      return out;
    }
    // Pick first character of string
    // then recurse for rest
    for (int i = 0; i < s1.length(); i++) {
      ArrayList<String> permuted = permute(s1.substring(0, i) + s1.substring(i + 1));
      for (int j = 0; j < permuted.size(); j++) {
        permuted.set(j, s1.charAt(i) + permuted.get(j));
      }
      out.addAll(permuted);
    }
    return out;
  }

  // For each char in s1, we check if it exists in s2 (an O(n) operation)
  // If it exists, we set the corresponding char in s2 to 0 (which is not an alphabet)
  // If all the chars in s1 exist in s2, it is an anagram
  public static boolean m2(char[] s1, char[] s2) {
    for (char c : s1) {
      boolean found = false;
      for (int j = 0; j < s2.length; j++) {
        if (c == s2[j]) {
          s2[j] = '0';
          found = true;
          break;
        }
      }
      if (!found) {
        return false;
      }
    }
    return true;
  }

  // We first sort both arrays (an O(n log n) operation)
  // Then we check if the sorted arrays are equal
  public static boolean m3(char[] s1, char[] s2) {
    Arrays.sort(s1);
    Arrays.sort(s2);
    return Arrays.equals(s1, s2);
  }

  // We first count the number of times each character appears in s1 (O(n))
  // and store it in a new array a1.
  // Then we iterate through s2 and subtract 1 from the count at corresponding a1 index
  // whenever a character appears 
  // Finally, we iterate through a1 and ensure all elements are zero
  public static boolean m4(char[] s1, char[] s2) {
    int[] a1 = new int[26];
    for (char c : s1) {
      a1[(int) c - 97]++;
    }
    for (char c : s2) {
      a1[(int) c - 97]--;
    }
    for (int a : a1) {
      if (a != 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // char[] s1 = "earth".toCharArray();
    // char[] s2 = "heart".toCharArray();
    // char[] s3 = "burntz".toCharArray();
    // System.out.println(m1(s1, s2));
    // System.out.println(m1(s1, s3));
    int n = 4;
    for (int i = 0; i < n; i++)
      // loop 2				 
      for (int j = i + 1; j > i; j--)
        // loop 3		      
        for (int k = n; k > j; k--) System.out.println("*");

  }
}
