package pl.jarocky.clustering.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pl.jarocky.clustering.utils.exceptions.InvalidOperationException;

public class FileService implements IFileService
{
  private String _fileFullPath;
  private BufferedReader _reader;

  private boolean _initialized = false;

  public FileService(String fileFullPath)
  {
    _fileFullPath = fileFullPath;
  }

  @Override
  public void init() throws FileNotFoundException
  {
    _reader = new BufferedReader(new FileReader(_fileFullPath));
    _initialized = true;
  }

  @Override
  public String getNextLine() throws InvalidOperationException, IOException
  {
    if (!_initialized)
    {
      throw new InvalidOperationException("Use init to initialize");
    }

    String line = _reader.readLine();

    if (line == null)
    {
      _reader.close();
      _reader = null;
      _initialized = false;
    }

    return line;
  }
}
