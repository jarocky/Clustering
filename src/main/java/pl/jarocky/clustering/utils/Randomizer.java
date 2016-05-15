package pl.jarocky.clustering.utils;

import java.util.Random;

public class Randomizer implements IRandomizer
{
  @Override
  public int nextInt(int from, int to)
  {
    Random random = new Random();
    int r = random.nextInt(to - from) + from;
    return r;
  }
}
