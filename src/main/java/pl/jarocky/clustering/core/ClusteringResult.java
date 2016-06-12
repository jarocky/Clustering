package pl.jarocky.clustering.core;

import java.util.List;

public class ClusteringResult
{
  public ClusteringResult(int[] clusters, int[] clustering)
  {
    Clusters = clusters;
    Clustering = clustering;
  }

  public final int[] Clusters;
  public final int[] Clustering;

  public boolean IsGoodClustering()
  {
    boolean isgoodClustering = true;
    for (int i = 0; i < Clusters.length; i++)
    {
      if (Clusters[i] == 0)
      {
        isgoodClustering = false;
        break;
      }
    }

    return isgoodClustering;
  }
}
