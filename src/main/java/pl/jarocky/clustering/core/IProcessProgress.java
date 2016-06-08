package pl.jarocky.clustering.core;

public interface IProcessProgress
{
  void Init(IProcessProgressController processProgresssController);

  void CalculateProgress(int finishedElements, int allElements);
}
