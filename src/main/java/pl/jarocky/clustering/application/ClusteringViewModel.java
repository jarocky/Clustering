package pl.jarocky.clustering.application;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.jarocky.clustering.core.ClusteringResult;
import pl.jarocky.clustering.core.IResultUpdate;;

public class ClusteringViewModel implements IResultUpdate
{
  private final int DefaultClusterCount = 10;
  private final int MaxIterations = 1000;
  private int CurrentIteration = 0;
  private boolean _isGoodClustering = false;

  private final StringProperty _numberOfClusters;
  private final SimpleBooleanProperty _dataNormalize;
  private final StringProperty _status;
  private final StringProperty _description;
  private final SimpleDoubleProperty _progress;

  public ClusteringViewModel()
  {
    _numberOfClusters = new SimpleStringProperty("10");
    _dataNormalize = new SimpleBooleanProperty(false);
    _status = new SimpleStringProperty("");
    _description = new SimpleStringProperty("");
    _progress = new SimpleDoubleProperty(0);
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

  public boolean getDataNormalize()
  {
    return _dataNormalize.get();
  }

  public void setDataNormalize(boolean dataNormalize)
  {
    _dataNormalize.set(dataNormalize);
  }

  public SimpleBooleanProperty DataNormalizeProperty()
  {
    return _dataNormalize;
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

  public void setProgress(double progres)
  {
    _progress.set(progres);
  }

  public SimpleDoubleProperty ProgressProperty()
  {
    return _progress;
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
    if (result != null && result.Clusters != null && result.IsGoodClustering())
    {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < result.Clusters.length; i++)
      {
        sb.append("Numer klastra: " + (i + 1) + "; Liczba elementów: " + result.Clusters[i] + "\n");
      }
      Platform.runLater(() -> setDescription(sb.toString()));
      _isGoodClustering = true;
      CurrentIteration = 0;
    }
    else
    {
      _isGoodClustering = false;
      CurrentIteration++;
      if (CurrentIteration == MaxIterations)
      {
        CurrentIteration = 0;
        Platform.runLater(() -> setStatus("Clustering process failed!"));
        _isGoodClustering = true;
      }
    }
  }

  @Override
  public boolean IsGoodClustering()
  {
    return _isGoodClustering;
  }
}
