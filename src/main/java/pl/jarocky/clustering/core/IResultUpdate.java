package pl.jarocky.clustering.core;

public interface IResultUpdate
{
  void Update(ClusteringResult result);

  boolean IsGoodClustering();
}
