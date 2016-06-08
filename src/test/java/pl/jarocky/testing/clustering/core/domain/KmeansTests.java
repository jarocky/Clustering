package pl.jarocky.testing.clustering.core.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pl.jarocky.clustering.core.domain.Kmeans;
import pl.jarocky.clustering.utils.IRandomizer;
import pl.jarocky.testing.clustering.core.mocks.RandomizerMock;

public class KmeansTests
{
  IRandomizer _randomizer = new RandomizerMock();

  @Test(expected = IllegalArgumentException.class)
  public void Proceed_RandomizerIsNull_ThrowException()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 1, 2, 3 });
    data.add(new double[]
    { 4, 5, 6 });
    data.add(new double[]
    { 7, 8, 9 });

    new Kmeans(data, 2, null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Proceed_ClustersCountEqualOne_ThrowException()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 1, 2, 3 });
    data.add(new double[]
    { 4, 5, 6 });
    data.add(new double[]
    { 7, 8, 9 });

    new Kmeans(data, 1, _randomizer, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Proceed_ClustersCountEqualZero_ThrowException()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 1, 2, 3 });
    data.add(new double[]
    { 4, 5, 6 });
    data.add(new double[]
    { 7, 8, 9 });

    new Kmeans(data, 0, _randomizer, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Proceed_ClustersCountLessThanZero_ThrowException()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 1, 2, 3 });
    data.add(new double[]
    { 4, 5, 6 });
    data.add(new double[]
    { 7, 8, 9 });

    new Kmeans(data, -1, _randomizer, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Proceed_DataSizeLessThanClustersCount_ThrowException()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 1, 2, 3 });
    data.add(new double[]
    { 4, 5, 6 });
    data.add(new double[]
    { 7, 8, 9 });

    new Kmeans(data, 4, _randomizer, null);
  }

  @Test
  public void Proceed_TwoItemsAndTwoClusters_ClusterZeroExists()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 1, 2, 3 });
    data.add(new double[]
    { 4, 5, 6 });
    Kmeans domain = new Kmeans(data, 2, _randomizer, null);

    int[] clustering = domain.proceed();

    Assert.assertTrue(clustering[0] == 0);
  }

  @Test
  public void Proceed_TwoItemsAndTwoClusters_ClusterOneExists()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 1, 2, 3 });
    data.add(new double[]
    { 4, 5, 6 });
    Kmeans domain = new Kmeans(data, 2, _randomizer, null);

    int[] clustering = domain.proceed();

    Assert.assertTrue(clustering[1] == 1);
  }

  @Test
  public void Proceed_ThreeItemsAndTwoClusters_ClusterZeroExists()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 2, 0, 0 });
    data.add(new double[]
    { 1, 10, 2 });
    data.add(new double[]
    { 2, 2, 3 });
    Kmeans domain = new Kmeans(data, 2, _randomizer, null);

    int[] clustering = domain.proceed();

    Assert.assertTrue(clustering[0] == 0);
  }

  @Test
  public void Proceed_ThreeItemsAndTwoClusters_ClusterOneExists()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 2, 0, 0 });
    data.add(new double[]
    { 1, 10, 2 });
    data.add(new double[]
    { 2, 2, 3 });
    Kmeans domain = new Kmeans(data, 2, _randomizer, null);

    int[] clustering = domain.proceed();

    Assert.assertTrue(clustering[1] == 1);
  }

  @Test
  public void Proceed_ThreeItemsAndTwoClusters_ClusterOneSecondElementExists()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 2, 0, 0 });
    data.add(new double[]
    { 1, 10, 2 });
    data.add(new double[]
    { 2, 2, 3 });
    Kmeans domain = new Kmeans(data, 2, _randomizer, null);

    int[] clustering = domain.proceed();

    Assert.assertTrue(clustering[2] == 0);
  }

  @Test
  public void Proceed_FourItemsAndTwoClusters_ClusterZeroExists()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 2, 0, 0 });
    data.add(new double[]
    { 1, 10, 2 });
    data.add(new double[]
    { 2, 2, 3 });
    data.add(new double[]
    { 3, 5, 6 });
    Kmeans domain = new Kmeans(data, 2, _randomizer, null);

    int[] clustering = domain.proceed();

    Assert.assertTrue(clustering[0] == 0);
  }

  @Test
  public void Proceed_FourItemsAndTwoClusters_ClusterZeroSecondElementExists()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 2, 0, 0 });
    data.add(new double[]
    { 1, 10, 2 });
    data.add(new double[]
    { 2, 2, 3 });
    data.add(new double[]
    { 3, 5, 6 });
    Kmeans domain = new Kmeans(data, 2, _randomizer, null);

    int[] clustering = domain.proceed();

    Assert.assertTrue(clustering[1] == 1);
  }

  @Test
  public void Proceed_FourItemsAndTwoClusters_ClusterOneExists()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 2, 0, 0 });
    data.add(new double[]
    { 1, 10, 2 });
    data.add(new double[]
    { 2, 2, 3 });
    data.add(new double[]
    { 3, 5, 6 });
    Kmeans domain = new Kmeans(data, 2, _randomizer, null);

    int[] clustering = domain.proceed();

    Assert.assertTrue(clustering[2] == 0);
  }

  @Test
  public void Proceed_FourItemsAndTwoClusters_ClusterOneSecondElementExists()
  {
    List<double[]> data = new ArrayList<double[]>();
    data.add(new double[]
    { 2, 0, 0 });
    data.add(new double[]
    { 1, 10, 2 });
    data.add(new double[]
    { 2, 2, 3 });
    data.add(new double[]
    { 3, 5, 6 });
    Kmeans domain = new Kmeans(data, 2, _randomizer, null);

    int[] clustering = domain.proceed();

    Assert.assertTrue(clustering[3] == 1);
  }
}
