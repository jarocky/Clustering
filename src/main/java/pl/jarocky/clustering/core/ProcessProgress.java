package pl.jarocky.clustering.core;

public class ProcessProgress implements IProcessProgress
{
  private IProcessProgressController _processProgressController;

  @Override
  public void Init(IProcessProgressController processProgresssController)
  {
    _processProgressController = processProgresssController;
  }

  @Override
  public void CalculateProgress(int finishedElements, int allElements)
  {
    _processProgressController.SetProgress((double) finishedElements / allElements);
  }
}
