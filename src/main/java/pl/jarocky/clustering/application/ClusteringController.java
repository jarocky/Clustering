package pl.jarocky.clustering.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import pl.jarocky.clustering.core.ClusteringService;

public class ClusteringController implements Initializable
{
  @FXML private TextField _tbNumberOfClusters;
  @FXML private Button _btProceed;
  
  final private ClusteringViewModel _model;
  final private ClusteringService _service;
  
  public ClusteringController(ClusteringViewModel model, ClusteringService service)
  {
    _model = model;
    _service = service;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1)
  {
    _tbNumberOfClusters.textProperty().bindBidirectional(_model.NumberOfClustersProperty());
  
    _btProceed.setOnAction((event) -> 
    { 
      _service.Proceed();
    });
  }
}
