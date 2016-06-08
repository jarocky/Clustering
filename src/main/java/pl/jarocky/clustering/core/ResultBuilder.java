package pl.jarocky.clustering.core;

import java.util.List;

public class ResultBuilder implements IResultBuilder
{

  @Override
  public ClusteringResult Build(List<double[]> data, int[] clustering, int clustersCount)
  {
    int[] clusters = new int[clustersCount];
    for (int i = 0; i < clustering.length; i++)
    {
      clusters[clustering[i]]++;
    }

    return new ClusteringResult(clusters);
  }
}
