package ch.heigvd.pro.b04.utils;

import org.junit.jupiter.api.Test;

public class IdGeneratorTest {

  @Test
  public void testGeneratorDoesNotHaltAfterManyGenerations() {
    int tries = 10_000;
    for (int i = 0; i < tries; i++) {
      IdGenerator.newId();
    }
  }
}
