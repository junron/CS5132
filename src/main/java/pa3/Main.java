package pa3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      String resourcePath = "/main.fxml";
      URL location = getClass().getResource(resourcePath);
      FXMLLoader fxmlLoader = new FXMLLoader(location);
      Scene mainScene = new Scene(fxmlLoader.load(), 640, 480);
      primaryStage.setTitle("ATM Finder");
      primaryStage.setResizable(false);
      primaryStage.setScene(mainScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
