package pl.jarocky.clustering.core;

import java.util.List;

public interface IResultBuilder
{
  ClusteringResult Build(List<double[]> data, int[] clustering, int clustersCount);
}
