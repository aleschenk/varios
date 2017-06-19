package com.images;

import static java.lang.Integer.toBinaryString;

public class BinaryGap {

  static class Solution {

    static int solution(int n) {
      return solution(n, 0, 0, 0);
    }

    static int solution(int n, int max, int current, int index) {
      if (n == 0)
        return max;
      else if (n % 2 == 0 && index == 0)
        return 0;
      else if (n % 2 == 0 && index > 0)
        return solution(n / 2, max, current + 1, index + 1);
      else
        return solution(n / 2, Math.max(max, current), 0, index + 1);
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution(1041));
  }

}
