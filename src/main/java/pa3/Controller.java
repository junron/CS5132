package pa3;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


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

  private Circle selectedCircle;
  private ATM nearestATM;

  private final KDTree kdtree;

  public Controller() {
    this.kdtree = new KDTree("ATMLocations.csv");
  }


  private void addATMCircle(double x, double y, ATM atm) {
    double size = 20;
    Circle circle = new Circle(x * size, y * size, 8, Color.BLACK);
    pane.getChildren().add(circle);
    circle.toFront();
    circle.setOnMouseClicked(value -> handleSelect(circle, atm));
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

    for (ATM atm : kdtree.getAtmArr()) {
      addATMCircle(atm.getX(), atm.getY(), atm);
    }
    grid.toBack();

  }

}
