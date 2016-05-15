package pl.jarocky.clustering.application;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import pl.jarocky.clustering.core.ClusteringService;

public class Main extends Application
{
  private final Injector _injector = Guice.createInjector(new DependencyModule(".\\ClusteringFileName.txt"));
  
  @Override
  public void start(Stage primaryStage)
  {
    try
    {
      final ClusteringViewModel model = new ClusteringViewModel();
      final ClusteringService service = (ClusteringService) _injector.getInstance(ClusteringService.class);
      
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ClusteringView.fxml"));
      loader.setController(new ClusteringController(model, service));
      BorderPane root = (BorderPane) loader.load();
      Scene scene = new Scene(root, 400, 400);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
