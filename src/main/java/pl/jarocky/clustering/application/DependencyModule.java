package pl.jarocky.clustering.application;

import com.google.inject.AbstractModule;

import pl.jarocky.clustering.core.ClusteringService;
import pl.jarocky.clustering.core.DataAccess;
import pl.jarocky.clustering.core.IDataAccess;
import pl.jarocky.clustering.utils.FileService;
import pl.jarocky.clustering.utils.IFileService;
import pl.jarocky.clustering.utils.IRandomizer;
import pl.jarocky.clustering.utils.Randomizer;

public class DependencyModule extends AbstractModule
{
  private final String _clusteringFileName;
  
  public DependencyModule(String clusteringFileName)
  {
    _clusteringFileName = clusteringFileName;
  }
      
  @Override
  protected void configure()
  {
    bind(ClusteringService.class);
    bind(IDataAccess.class).to(DataAccess.class);
    bind(IFileService.class).to(FileService.class);
    bind(IRandomizer.class).to(Randomizer.class);
    bindConstant().annotatedWith(FileService.ClusteringFileName.class).to(_clusteringFileName);
  }
}
