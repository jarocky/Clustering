package pl.jarocky.clustering.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import pl.jarocky.clustering.utils.IFileService;

public class DataAccess
{
  private IFileService _fileService;

  public DataAccess(IFileService fileService)
  {
    _fileService = fileService;
  }

  public List<BigDecimal[]> GetData() throws Exception
  {
    _fileService.init();

    List<BigDecimal[]> data = new ArrayList<BigDecimal[]>();
    String line;
    while ((line = _fileService.getNextLine()) != null)
    {
      String[] stringValues = line.split(";");
      BigDecimal[] values = new BigDecimal[stringValues.length];
      for (int i = 0; i < stringValues.length; i++)
      {
        values[i] = new BigDecimal(stringValues[i]);
      }
      data.add(values);
    }

    return data;
  }
}
