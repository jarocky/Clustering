package pl.jarocky.testing.clustering.core.mocks;

import pl.jarocky.clustering.utils.IRandomizer;

public class RandomizerMock implements IRandomizer
{
  @Override
  public int nextInt(int from, int to)
  {
    return from;
  }
}
