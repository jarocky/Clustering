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
  private final IProcessProgress _processProgress;

  @Inject
  public ClusteringService(IDataAccess dataAccess, IRandomizer randomizer, IProcessProgress processProgress)
  {
    _dataAccess = dataAccess;
    _randomizer = randomizer;
    _processProgress = processProgress;
  }

  public void Proceed(String clusetersCount) throws Exception
  {
    new Thread()
    {
      @Override
      public void run()
      {
        try
        {

          for (int i = 0; i <= 100; i += 1)
          {
            _processProgress.CalculateProgress(i, 100);
            Thread.sleep(100);
          }

        }
        catch (InterruptedException ex)
        {
          System.err.println("Error on Thread Sleep");
        }
      }

    }.start();

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
    }
    catch (NumberFormatException ex)
    {
      throw new ValidationException("Niepoprawna liczba klastrów");
    }
  }
}
