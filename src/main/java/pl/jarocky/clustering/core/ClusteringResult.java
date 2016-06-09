package pl.jarocky.clustering.core;

public class ClusteringResult
{
  public ClusteringResult(int[] clusters)
  {
    Clusters = clusters;
  }

  public final int[] Clusters;

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
