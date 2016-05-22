package pl.jarocky.clustering.core;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.google.inject.Inject;

import pl.jarocky.clustering.core.domain.Kmeans;
import pl.jarocky.clustering.utils.IRandomizer;

public class ClusteringService
{
  private final IDataAccess _dataAccess;
  private final IRandomizer _randomizer;

  @Inject
  public ClusteringService(IDataAccess dataAccess, IRandomizer randomizer)
  {
    _dataAccess = dataAccess;
    _randomizer = randomizer;
  }

  public void Proceed(String clusetersCount) throws Exception
  {
    int clusterCountInt = ConvertStringToInt(clusetersCount);
    List<double[]> data = _dataAccess.getData();
    Kmeans kmeans = new Kmeans(data, clusterCountInt, _randomizer);
    kmeans.proceed();
  }

  private int ConvertStringToInt(String clustersCount) throws ValidationException
  {
    try
    {
      return Integer.parseInt(clustersCount);
    } catch (NumberFormatException ex)
    {
      throw new ValidationException("Niepoprawna liczba klastrów");
    }
  }
}
