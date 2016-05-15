package pl.jarocky.clustering.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

import pl.jarocky.clustering.utils.exceptions.InvalidOperationException;

public class FileService implements IFileService
{
  private String _fileFullPath;
  private BufferedReader _reader;

  private boolean _initialized = false;

  @Inject public FileService(@ClusteringFileName String fileFullPath)
  {
    _fileFullPath = fileFullPath;
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  @BindingAnnotation
  public @interface ClusteringFileName {}

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
