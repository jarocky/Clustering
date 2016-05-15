package pl.jarocky.clustering.core.domain;

import java.util.Arrays;
import java.util.List;

import pl.jarocky.clustering.utils.IRandomizer;

public class Kmeans
{
  private List<double[]> _data;
  private int[] _clustering;
  private double[][] _centroids;
  private IRandomizer _randomizer;

  public Kmeans(List<double[]> data, int clustersCount, IRandomizer randomizer)
  {
    if (clustersCount < 2)
    {
      throw new IllegalArgumentException("Clusters count must be grater than 1");
    }

    if (data.size() < clustersCount)
    {
      throw new IllegalArgumentException("Data is not enough");
    }

    if (randomizer == null)
    {
      throw new IllegalArgumentException("Randomizer can not be null");
    }

    _data = data;
    _clustering = new int[data.size()];
    _centroids = new double[clustersCount][data.get(0).length];
    _randomizer = randomizer;
  }

  public int[] proceed()
  {
    return proceed(_data.size() * 10);
  }

  public int[] proceed(int sanity)
  {
    initRandom();

    boolean changed = true;
    int i = 0;
    while (changed == true && i <= sanity)
    {
      i++;
      updateCentroids();
      changed = updateClustering();
    }

    return Arrays.copyOf(_clustering, _clustering.length);
  }

  public void initRandom()
  {
    int clusterId = 0;
    for (int i = 0; i < _data.size(); i++)
    {
      _clustering[i] = clusterId++;
      if (clusterId == _centroids.length)
      {
        clusterId = 0;
      }
    }

    for (int i = 0; i < _data.size(); i++)
    {
      int r = _randomizer.nextInt(i, _data.size());
      int temp = _clustering[r];
      _clustering[r] = _clustering[i];
      _clustering[i] = temp;
    }
  }

  private void updateCentroids()
  {
    int[] clusterCounts = getClusterCounts();

    for (int y = 0; y < _centroids.length; y++)
    {
      for (int x = 0; x < _centroids[1].length; x++)
      {
        _centroids[y][x] = 0.0;
      }
    }

    for (int i = 0; i < _data.size(); i++)
    {
      int clusterId = _clustering[i];
      for (int k = 0; k < _centroids[1].length; k++)
      {
        _centroids[clusterId][k] += _data.get(i)[k];
      }
    }

    for (int i = 0; i < _centroids.length; i++)
    {
      for (int k = 0; k < _centroids[i].length; k++)
      {
        _centroids[i][k] /= clusterCounts[i];
      }
    }
  }

  private boolean updateClustering()
  {
    boolean changed = false;
    double[] distances = new double[_centroids.length];

    for (int i = 0; i < _data.size(); i++)
    {
      for (int k = 0; k < _centroids.length; k++)
      {
        distances[k] = distance(_data.get(i), _centroids[k]);
      }

      int clusterId = indexOfMinDistance(distances);
      if (_clustering[i] != clusterId)
      {
        changed = true;
        _clustering[i] = clusterId;
      }
    }

    if (changed == false)
    {
      return false;
    }

    int[] clusterCounts = getClusterCounts();
    for (int i = 0; i < clusterCounts.length; i++)
    {
      if (clusterCounts[i] == 0)
      {
        return false;
      }
    }

    return true;
  }

  private double distance(double[] a, double[] b)
  {
    double sumSquaredDiffs = 0.0;
    for (int i = 0; i < a.length; i++)
    {
      sumSquaredDiffs += (a[i] - b[i]) * (a[i] - b[i]);
    }
    return Math.sqrt(sumSquaredDiffs);
  }

  private int indexOfMinDistance(double[] distances)
  {
    int indexIfMin = 0;
    double smallDist = distances[0];
    for (int i = 1; i < distances.length; i++)
    {
      if (distances[i] < smallDist)
      {
        smallDist = distances[i];
        indexIfMin = i;
      }
    }
    return indexIfMin;
  }

  private int[] getClusterCounts()
  {
    int[] clusterCounts = new int[_centroids.length];
    for (int i = 0; i < _data.size(); i++)
    {
      int clusterId = _clustering[i];
      ++clusterCounts[clusterId];
    }

    return clusterCounts;
  }
}
