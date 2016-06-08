package pl.jarocky.clustering.application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.jarocky.clustering.core.ClusteringResult;
import pl.jarocky.clustering.core.IResultUpdate;;

public class ClusteringViewModel implements IResultUpdate
{
  private final StringProperty _numberOfClusters;
  private final StringProperty _status;
  private final StringProperty _description;
  private final SimpleDoubleProperty _progres;

  public ClusteringViewModel()
  {
    _numberOfClusters = new SimpleStringProperty("10");
    _status = new SimpleStringProperty("");
    _description = new SimpleStringProperty("");
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

  public String getDescription()
  {
    return _description.get();
  }

  public void setDescription(String description)
  {
    _description.set(description);
  }

  public StringProperty DescriptionProperty()
  {
    return _description;
  }

  @Override
  public void Update(ClusteringResult result)
  {
    if (result != null && result.Clusters != null)
    {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < result.Clusters.length; i++)
      {
        sb.append("Numer klastra: " + (i + 1) + "; Liczba elementów: " + result.Clusters[i] + "\n");
      }
      setDescription(sb.toString());
    }
  }
}
