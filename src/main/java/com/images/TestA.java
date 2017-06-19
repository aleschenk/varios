package com.images;

public class TestA {

  public static int solution(final int[] numbers) {
    int left = 0, right;

    for(int i = 0; i < numbers.length; i++) {
      right = 0;

      for(int j = numbers.length - 1; j > i; j--) {
        right += numbers[j];
      }

      if(left == right) {
        return i;
      }

      left += numbers[i];
    }

    return -1;
  }

  public static void main(String[] args) {
    int numbers[] = {-1, 3, -4, 5, 1,-6, 2, 1};
    System.out.println(solution(numbers));
  }

}
