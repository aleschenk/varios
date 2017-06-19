package com.algo;

import org.junit.Test;

import static com.images.Task3.solution;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class Task3Test {

  @Test
  public void test() {
    int numbers[] = {1, 1, 2, 3, 3};
    assertTrue(solution(numbers, 3));
  }

  @Test
  public void testA() {
    int numbers[] = {1, 1, 3};
    assertFalse(solution(numbers, 2));
  }

  @Test
  public void testB() {
    int numbers[] = {1, 2, 3, 4};
    assertFalse(solution(numbers, 5));
  }

  @Test
  public void testC() {
    int numbers[] = {1, 2, 3, 4};
    assertFalse(solution(numbers, -1));
  }

  @Test
  public void testD() {
    int numbers[] = {-1, 0, 1, 2, 3, 4};
    assertFalse(solution(numbers, 2));
  }

  @Test
  public void testE() {
    int numbers[] = {1, 1, 1, 1, 3};
    assertFalse(solution(numbers, 2));
  }


}
