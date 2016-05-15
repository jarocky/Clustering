package pl.jarocky.clustering.core;

import java.util.List;

public interface IDataAccess
{
  List<double[]> getData() throws Exception;
}