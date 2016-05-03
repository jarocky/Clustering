package pl.jarocky.clustering.testing.core;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.jarocky.clustering.core.DataAccess;
import pl.jarocky.clustering.utils.IFileService;

public class DataAccessTests
{
  private DataAccess _dataAccess;

  @Before
  public void setUp() throws Exception
  {
    IFileService fileService = createNiceMock(IFileService.class);
    expect(fileService.getNextLine()).andReturn("123.32;43.62");
    replay(fileService);
    _dataAccess = new DataAccess(fileService);
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnListWithOneItem() throws Exception
  {
    List<BigDecimal[]> data = _dataAccess.GetData();

    Assert.assertEquals(1, data.size());
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnArrayWithTwoValues() throws Exception
  {
    List<BigDecimal[]> data = _dataAccess.GetData();

    Assert.assertEquals(2, data.get(0).length);
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnExpectedFirstValue() throws Exception
  {
    List<BigDecimal[]> data = _dataAccess.GetData();

    Assert.assertTrue((new BigDecimal("123.32")).equals(data.get(0)[0]));
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnExpectedSecondValue() throws Exception
  {
    List<BigDecimal[]> data = _dataAccess.GetData();

    Assert.assertTrue((new BigDecimal("43.62")).equals(data.get(0)[1]));
  }

  @Test
  public void GetData_FileServiceReturnLineWithTwoValues_ReturnListWithTwoItems() throws Exception
  {
    IFileService fileService = createNiceMock(IFileService.class);
    expect(fileService.getNextLine()).andReturn("123.32;43.62").andReturn("656.65;24.79");
    replay(fileService);
    _dataAccess = new DataAccess(fileService);

    List<BigDecimal[]> data = _dataAccess.GetData();

    Assert.assertEquals(2, data.size());
  }
}
