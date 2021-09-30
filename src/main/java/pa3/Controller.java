package pa3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;


public class Controller {
  @FXML
  public Pane pane;
  @FXML
  private GridPane grid;

  @FXML
  private TextField xCoord;

  @FXML
  private TextField yCoord;

  @FXML
  private Label atmDisplayTitle;

  @FXML
  private Label atmLocation;

  @FXML
  private Label atmPostalCode;

  @FXML
  private Label atmCoords;

  @FXML
  private Label atmDistance;

  @FXML
  private Button submitButton;

  @FXML
  private Button aboutProgrammer;

  private Circle selectedCircle;
  private ATM nearestATM;
  private final HashMap<ATM, Circle> atmCircleMap = new HashMap<>();
  private boolean hasSelectedCircle;

  private Circle userCircle;

  private KDTree kdtree;

  private final double scale = 20;
  private final int radius = 8;


  // Initialization functions
  private void addATMCircle(double x, double y, ATM atm) {
    Circle circle = new Circle(x * scale, y * scale, radius, Color.BLACK);
    pane.getChildren().add(circle);
    circle.toFront();
    circle.setOnMouseClicked(value -> handleSelect(circle, atm));
    atmCircleMap.put(atm, circle);
  }

  private void addRowNumber(int num) {
    Text text = new Text(String.format("%2d", num));
    GridPane.setHalignment(text, HPos.LEFT);
    GridPane.setValignment(text, VPos.TOP);
    GridPane.setMargin(text, new Insets(-10, 0, 0, -15));
    grid.add(text, 0, num);
  }

  private void addColumnNumber(int num) {
    Text text = new Text(String.format("%2d", num));
    GridPane.setHalignment(text, HPos.LEFT);
    GridPane.setValignment(text, VPos.TOP);
    GridPane.setMargin(text, new Insets(-15, 0, 0, -10));
    grid.add(text, num, 0);
  }

  // Event handlers
  private void handleSelect(Circle circle, ATM atm) {
    this.hasSelectedCircle = true;
    if (selectedCircle != null) {
      circle.setStrokeWidth(0);
      selectedCircle.setStrokeWidth(0);
    }
    selectedCircle = circle;
    circle.setStrokeWidth(2);
    circle.setStroke(Color.RED);
    displayATMInfo(atm);
  }

  // Helper functions
  private void displayATMInfo(ATM atm) {
    if (atm.equals(nearestATM)) {
      atmDisplayTitle.setText("Nearest ATM:");
    } else {
      atmDisplayTitle.setText("Selected ATM:");
    }
    atmLocation.setText(atm.getLocation());
    atmPostalCode.setText(atm.getPostal());
    atmCoords.setText(String.format("(%.2f, %.2f)", atm.getX(), atm.getY()));

    Point2D userPoint = getUserPoint(false);
    if (userPoint == null) {
      atmDistance.setText("-");
    } else {
      Point2D atmPoint = new Point2D(atm.getX(), atm.getY());
      atmDistance.setText(String.format("%.3f", atmPoint.distance(userPoint)));
    }
  }

  private void displayUserPoint(Point2D userPoint) {
    if (userCircle != null) {
      userCircle.setCenterX(userPoint.getX() * scale);
      userCircle.setCenterY(userPoint.getY() * scale);
    } else {
      userCircle = new Circle(userPoint.getX() * scale, userPoint.getY() * scale, radius, Color.RED);
      pane.getChildren().add(userCircle);
    }
  }

  private void displayNearestATM(ATM atm) {
    if (nearestATM != null) {
      atmCircleMap.get(nearestATM).setFill(Color.BLACK);
    }
    atmCircleMap.get(atm).setFill(Color.rgb(0x00, 0xff, 0x22));
    nearestATM = atm;
  }

  private Point2D getUserPoint(boolean error) {
    double x, y;
    try {
      x = Double.parseDouble(xCoord.getText());
      y = Double.parseDouble(yCoord.getText());
    } catch (NumberFormatException e) {
      if (error)
        new Alert(Alert.AlertType.ERROR, "Please enter a number for X and Y coordinates.", ButtonType.OK).showAndWait();
      return null;
    }
    if (x > 10 || x < 0) {
      if (error)
        new Alert(Alert.AlertType.ERROR, " X coordinate must be between 0 and 10.", ButtonType.OK).showAndWait();
      return null;
    }
    if (y > 10 || y < 0) {
      if (error)
        new Alert(Alert.AlertType.ERROR, "Y coordinate must be between 0 and 10.", ButtonType.OK).showAndWait();
      return null;
    }
    return new Point2D(x, y);
  }

  // Sets up event handlers
  private void initHandlers() {
    submitButton.setOnMouseClicked(event -> {
      Point2D userPoint = getUserPoint(true);
      if (userPoint == null) return;
      displayUserPoint(userPoint);
      kdtree.numVisited = 0;
      ATM nearest = kdtree.nearestNeighbour(userPoint.getX(), userPoint.getY(), kdtree.getRoot());
      System.out.println(kdtree.numVisited);
      displayNearestATM(nearest);
      displayATMInfo(nearest);
    });


    // User can also click on grid to set current location
    pane.setOnMouseClicked(event -> {
      // Pane click event already handled by ATM selection
      if (this.hasSelectedCircle) {
        this.hasSelectedCircle = false;
        return;
      }
      double userX = event.getX() / scale;
      double userY = event.getY() / scale;
      if (userX < 0 || userY < 0) return;
      if (userX > 10 || userY > 10) return;

      Point2D userPoint = new Point2D(userX, userY);

      xCoord.setText(String.format("%.2f", userX));
      yCoord.setText(String.format("%.2f", userY));

      displayUserPoint(userPoint);
      ATM nearest = kdtree.nearestNeighbour(userPoint.getX(), userPoint.getY(), kdtree.getRoot());
      displayNearestATM(nearest);
      displayATMInfo(nearest);
    });

    aboutProgrammer.setOnMouseClicked(event -> {
      Stage dialog = new Stage();
      String resourcePath = "/about.fxml";
      URL location = getClass().getResource(resourcePath);
      FXMLLoader fxmlLoader = new FXMLLoader(location);
      Scene about;
      try {
        about = new Scene(fxmlLoader.load(), 600, 400);
      } catch (IOException e) {
        e.printStackTrace();
        return;
      }
      dialog.setResizable(false);
      dialog.setTitle("About programmer");
      dialog.setScene(about);
      dialog.show();
    });
  }

  @FXML
  public void initialize() {
    try {
      this.kdtree = new KDTree("ATMLocations.csv");
    } catch (IllegalArgumentException e) {
      new Alert(Alert.AlertType.ERROR, "ATM location file could not be found.", ButtonType.OK).showAndWait();
      System.exit(-1);
    }

    for (int i = 1; i < 11; i++) {
      addRowNumber(i);
      addColumnNumber(i);
    }
    Text text = new Text("(0, 0)");
    GridPane.setHalignment(text, HPos.LEFT);
    GridPane.setValignment(text, VPos.TOP);
    GridPane.setMargin(text, new Insets(-15, 0, 0, -30));
    grid.add(text, 0, 0);

    // Add ATM locations on grid
    for (ATM atm : kdtree.nodesList()) {
      addATMCircle(atm.getX(), atm.getY(), atm);
    }
    grid.toBack();

    initHandlers();
  }

}
