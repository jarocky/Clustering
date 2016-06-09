package pl.jarocky.clustering.core;

import java.util.List;

public class DataNormalizer implements IDataNormalizer
{
  private double minValue;
  private double maxValue;

  @Override
  public void Normalize(List<double[]> data)
  {
    FindMinMaxValue(data);
    double range = maxValue - minValue;
    for (double[] line : data)
    {
      for (int i = 0; i < line.length; i++)
      {
        line[i] = (line[i] - minValue) / range;
      }
    }
  }

  private void FindMinMaxValue(List<double[]> data)
  {
    minValue = maxValue = data.get(0)[0];
    for (double[] line : data)
    {
      for (int i = 0; i < line.length; i++)
      {
        if (line[i] < minValue)
        {
          minValue = line[i];
        }
        if (line[i] > maxValue)
        {
          maxValue = line[i];
        }
      }
    }
  }
}
