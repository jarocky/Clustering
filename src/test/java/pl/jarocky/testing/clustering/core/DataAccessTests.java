package pl.jarocky.testing.clustering.core;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.jarocky.clustering.core.DataAccess;
import pl.jarocky.clustering.core.IDataAccess;
import pl.jarocky.clustering.utils.IFileService;

public class DataAccessTests
{
  private IDataAccess _dataAccess;

  @Before
  public void setUp() throws Exception
  {
    IFileService fileService = EasyMock.createNiceMock(IFileService.class);
    expect(fileService.getNextLine()).andReturn("123.32;43.62");
    replay(fileService);
    _dataAccess = new DataAccess(fileService);
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnListWithOneItem() throws Exception
  {
    List<double[]> data = _dataAccess.getData();

    Assert.assertEquals(1, data.size());
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnArrayWithTwoValues() throws Exception
  {
    List<double[]> data = _dataAccess.getData();

    Assert.assertEquals(2, data.get(0).length);
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnExpectedFirstValue() throws Exception
  {
    List<double[]> data = _dataAccess.getData();

    Assert.assertEquals(123.32, data.get(0)[0], 0);
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnExpectedSecondValue() throws Exception
  {
    List<double[]> data = _dataAccess.getData();

    Assert.assertEquals(43.62, data.get(0)[1], 0);
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnListWithTwoItems() throws Exception
  {
    IFileService fileService = EasyMock.createNiceMock(IFileService.class);
    expect(fileService.getNextLine()).andReturn("123.32;43.62").andReturn("656.65;24.79");
    replay(fileService);
    _dataAccess = new DataAccess(fileService);

    List<double[]> data = _dataAccess.getData();

    Assert.assertEquals(2, data.size());
  }
}
