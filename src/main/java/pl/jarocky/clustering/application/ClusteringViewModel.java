package pl.jarocky.clustering.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;;

public class ClusteringViewModel
{
  private final StringProperty _numberOfClusters;
  private final StringProperty _status;

  public ClusteringViewModel()
  {
    _numberOfClusters = new SimpleStringProperty("10");
    _status = new SimpleStringProperty("");
  }

  public String getNumberOfClusters()
  {
    return _numberOfClusters.get();
  }

  public void setNumberOfClusters(String numberOfClusters)
  {
    _numberOfClusters.set(numberOfClusters);
  }

  public StringProperty NumberOfClustersProperty()
  {
    return _numberOfClusters;
  }

  public String getStatus()
  {
    return _status.get();
  }

  public void setStatus(String status)
  {
    _status.set(status);
  }

  public StringProperty StatusProperty()
  {
    return _status;
  }
}
