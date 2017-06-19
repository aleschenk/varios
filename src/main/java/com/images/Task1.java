package com.images;

public class Task1 {


//  int solution(int A[],int n){
//    int i,length=0;
//
//    while(arr[i]>0 && arr[i] < n){
//      length++;
//      i = arr[i];
//    }
//
//    return length + 1;
//  }

//  public static int solution(final int index, int count, final int[] numbers) {
//
//    int value = numbers[index];
//
//    if(value != -1) {
//      solution(value, count++, numbers);
//    }
//
//    return count;
//  }

  public static int solution(final int numbers[]) {
    int k = 0, lenght = 1;

    while(numbers[k] != -1) {
      lenght++;
      k = numbers[k];
    }

    return lenght;
  }

  public static void main(String[] args) {
    int numbers[] = {1,4,-1,3, 2};
    System.out.println(solution(numbers));
  }
}
