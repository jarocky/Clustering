package pl.jarocky.clustering.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;;

public class ClusteringViewModel 
{
	private final StringProperty _numberOfClusters;
	
	public ClusteringViewModel()
	{
	  _numberOfClusters = new SimpleStringProperty("10");
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
}
