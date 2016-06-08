package pl.jarocky.clustering.application;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.jarocky.clustering.core.ClusteringService;
import pl.jarocky.clustering.core.IProcessProgress;

public class Main extends Application
{
  private final Injector _injector = Guice.createInjector(new DependencyModule("D:\\ClusteringFileName.txt"));

  @Override
  public void start(Stage primaryStage)
  {
    try
    {
      final ClusteringViewModel model = new ClusteringViewModel();
      final ClusteringService service = (ClusteringService) _injector.getInstance(ClusteringService.class);
      final IProcessProgress processProgress = (IProcessProgress) _injector.getInstance(IProcessProgress.class);

      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ClusteringView.fxml"));
      loader.setController(new ClusteringController(model, service, processProgress));
      BorderPane root = (BorderPane) loader.load();
      Scene scene = new Scene(root, 800, 600);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
