package pl.jarocky.clustering.application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;;

public class ClusteringViewModel
{
  private final StringProperty _numberOfClusters;
  private final StringProperty _status;
  private final SimpleDoubleProperty _progres;

  public ClusteringViewModel()
  {
    _numberOfClusters = new SimpleStringProperty("10");
    _status = new SimpleStringProperty("");
    _progres = new SimpleDoubleProperty(0);
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
  
  public String getProgres()
  {
    return _status.get();
  }

  public void setProgres(double progres)
  {
    _progres.set(progres);
  }

  public SimpleDoubleProperty ProgresProperty()
  {
    return _progres;
  }
}
