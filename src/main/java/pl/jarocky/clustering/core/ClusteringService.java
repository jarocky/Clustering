package pl.jarocky.clustering.core;

import com.google.inject.Inject;

public class ClusteringService
{
  private final IDataAccess _dataAccess;
  
  @Inject public ClusteringService(IDataAccess dataAccess)
  {
    _dataAccess = dataAccess;
  }
  
  public void Proceed()
  {    
  }
}
