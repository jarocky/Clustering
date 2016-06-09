package pl.jarocky.clustering.application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.ValidationException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.jarocky.clustering.core.ClusteringService;
import pl.jarocky.clustering.core.IProcessProgress;
import pl.jarocky.clustering.core.IProcessProgressController;

public class ClusteringController implements Initializable, IProcessProgressController
{
  @FXML
  private TextField _tbNumberOfClusters;
  @FXML
  private Button _btProceed;
  @FXML
  private CheckBox _cbDataNormalize;
  @FXML
  private Label _lbStatus;
  @FXML
  private TextArea _taData;
  @FXML
  private ProgressBar _pbProcess;

  final private ClusteringViewModel _model;
  final private ClusteringService _service;
  final private IProcessProgress _processProgress;

  public ClusteringController(ClusteringViewModel model, ClusteringService service, IProcessProgress processProgress)
  {
    _model = model;
    _service = service;
    _processProgress = processProgress;
    _processProgress.Init(this);
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1)
  {
    _tbNumberOfClusters.textProperty().bindBidirectional(_model.NumberOfClustersProperty());
    _cbDataNormalize.selectedProperty().bindBidirectional(_model.DataNormalizeProperty());
    _lbStatus.textProperty().bind(_model.StatusProperty());
    _taData.textProperty().bind(_model.DescriptionProperty());
    _pbProcess.progressProperty().bind(_model.ProgresProperty());

    _btProceed.setOnAction((event) ->
    {
      try
      {
        _service.Proceed(_model.getNumberOfClusters(), _model.getDataNormalize(), _model);
      }
      catch (ValidationException ex)
      {
        // TODO: show communicate
        _model.setStatus(ex.getMessage());
      }
      catch (Exception ex)
      {
        // TODO: exception to log
        _model.setStatus(ex.getMessage());
      }
    });
  }

  @Override
  public void SetProgress(double progress)
  {
    _model.setProgress(progress);
  }
}
