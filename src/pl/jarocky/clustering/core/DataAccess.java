package pl.jarocky.clustering.core;

import java.util.ArrayList;
import java.util.List;

import pl.jarocky.clustering.utils.IFileService;

public class DataAccess implements IDataAccess
{
  private IFileService _fileService;

  public DataAccess(IFileService fileService)
  {
    _fileService = fileService;
  }

  @Override
  public List<double[]> getData() throws Exception
  {
    _fileService.init();

    List<double[]> data = new ArrayList<double[]>();
    String line;
    while ((line = _fileService.getNextLine()) != null)
    {
      String[] stringValues = line.split(";");
      double[] values = new double[stringValues.length];
      for (int i = 0; i < stringValues.length; i++)
      {
        values[i] = Double.parseDouble(stringValues[i].replaceAll(",", "."));
      }
      data.add(values);
    }

    return data;
  }
}
