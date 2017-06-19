package com.images;

public class Task3 {
  public static boolean solution(int[] numbers, int K) {
    int numbersLength = numbers.length;

    for (int i = 0; i < numbersLength - 1; i++) {
      if (numbers[i] + 1 < numbers[i + 1])
        return false;
    }

    if (numbers[0] != 1 || numbers[numbersLength - 1] != K)
      return false;
    else
      return true;

  }

  public static void main(String[] args) {
    int numbers[] = {1, 1, 2, 3, 3};

    solution(numbers, 0);
  }
}
