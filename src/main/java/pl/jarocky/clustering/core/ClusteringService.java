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
  private final IResultBuilder _resultBuilder;
  private final IDataNormalizer _dataNormalizer;
  private boolean _inProgress;

  @Inject
  public ClusteringService(IDataAccess dataAccess, IRandomizer randomizer, IProcessProgress processProgress,
      IResultBuilder resultBuilder, IDataNormalizer dataNormalizer)
  {
    _dataAccess = dataAccess;
    _randomizer = randomizer;
    _processProgress = processProgress;
    _resultBuilder = resultBuilder;
    _dataNormalizer = dataNormalizer;
    _inProgress = false;
  }

  public void Proceed(String clusetersCount, boolean dataNormalize, IResultUpdate resultUpdate) throws Exception
  {
    if (!_inProgress)
    {
      _inProgress = true;
      int clusterCountInt = ConvertStringToInt(clusetersCount);
      List<double[]> data = _dataAccess.getData();

      if (dataNormalize)
      {
        _dataNormalizer.Normalize(data);
      }

      new Thread()
      {
        @Override
        public void run()
        {
          do
          {
            Kmeans kmeans = new Kmeans(data, clusterCountInt, _randomizer, _processProgress);
            int[] clustering = kmeans.proceed();
            resultUpdate.Update(_resultBuilder.Build(data, clustering, clusterCountInt));
            _inProgress = false;
          }
          while (!resultUpdate.IsGoodClustering());
        }
      }.start();
    }
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
