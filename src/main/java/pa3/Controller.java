package pa3;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

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

  private Circle selectedCircle;
  private ATM nearestATM;
  private final HashMap<ATM, Circle> atmCircleMap = new HashMap<>();

  private Circle userCircle;

  private final KDTree kdtree;

  private final double scale = 20;
  private final int radius = 8;


  public Controller() {
    this.kdtree = new KDTree("ATMLocations.csv");
  }


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
    if (selectedCircle != null) {
      circle.setStrokeWidth(0);
      selectedCircle.setStroke(Color.BLACK);
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

  @FXML
  public void initialize() {
    for (int i = 1; i < 11; i++) {
      addRowNumber(i);
      addColumnNumber(i);
    }
    Text text = new Text("(0, 0)");
    GridPane.setHalignment(text, HPos.LEFT);
    GridPane.setValignment(text, VPos.TOP);
    GridPane.setMargin(text, new Insets(-15, 0, 0, -30));
    grid.add(text, 0, 0);

    for (ATM atm : kdtree.nodesList()) {
      addATMCircle(atm.getX(), atm.getY(), atm);
    }
    grid.toBack();


    submitButton.setOnMouseClicked(event -> {
      Point2D userPoint = getUserPoint(true);
      if (userPoint == null) return;
      displayUserPoint(userPoint);
      ATM nearest = kdtree.nearestNeighbour(userPoint.getX(), userPoint.getY(), kdtree.getRoot());
      displayNearestATM(nearest);
      displayATMInfo(nearest);
    });
  }

}
