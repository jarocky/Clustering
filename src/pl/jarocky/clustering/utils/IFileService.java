package pl.jarocky.clustering.utils;

import java.io.FileNotFoundException;

public interface IFileService
{
  void init() throws FileNotFoundException;

  String getNextLine() throws Exception;
}