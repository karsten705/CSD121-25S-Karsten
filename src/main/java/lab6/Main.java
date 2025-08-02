package lab6;

import javafx.application.Application; // base class
import javafx.event.ActionEvent; // represents an event related to user actions
import javafx.event.EventHandler; // interface for handling events
import javafx.geometry.Insets; // padding and spacing for UI elements
import javafx.geometry.Pos; // alignment options
import javafx.scene.Scene; // represents a scene containing UI elements
import javafx.scene.control.*;
import javafx.scene.layout.GridPane; // grid-based layout
import javafx.stage.Stage; // main window of the javaFX application

public class Main extends Application {

    private ComboBox<String> unitSelector; // drop-down list selection
    private TextField inputField, outputField; // to take input and show output

    /**
     * Main method: launches the JavaFX application by calling start.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args); // internally calls the abstract start() method
    }

    /**
     * Start method - Entry point for JavaFX applications.
     * Initializes the UI elements and implements event handling.
     * @param primaryStage The stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Unit Converter");

        // main window (Stage) setup with grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20)); // padding around the grid
        grid.setHgap(10); // inline gap
        grid.setVgap(10); // block gap
        grid.setAlignment(Pos.CENTER); // aligns the grid CENTER
        grid.setStyle("-fx-background-color: #27E0F5;");

        // Initializing labels
        Label inputLabel = new Label("Enter Value:");
        Label unitLabel = new Label("Select Unit:");
        Label outputLabel = new Label("Converted Value:");

        // Input text field
        inputField = new TextField();
        inputField.setPromptText("Enter value..."); // hint text

        // ComboBox for selecting conversion units
        unitSelector = new ComboBox<>();
        unitSelector.getItems().addAll(
                "Metres to Feet",
                "Feet to Metres",
                "Kilograms to Pounds",
                "Pounds to Kilograms",
                "Celsius to Fahrenheit",
                "Fahrenheit to Celsius"
        );
        unitSelector.setValue("Metres to Feet"); // setting default

        // Output text field
        outputField = new TextField();
        outputField.setEditable(false); // un-editable

        // Action Button
        Button convertButton = new Button("Convert");
        convertButton.setOnAction(new ConvertHandler()); // assigns event handler
        convertButton.setStyle("-fx-background-color: #293E47; -fx-text-fill: white;");

        // adding components to grid layout (control, col, row)
        grid.add(inputLabel, 0, 1);
        grid.add(inputField, 1, 1);
        grid.add(unitLabel, 0, 2);
        grid.add(unitSelector, 1, 2);
        grid.add(outputLabel, 0, 3);
        grid.add(outputField, 1, 3);
        grid.add(convertButton, 1, 4);

        // Initializing the scene and set it in the primary stage
        Scene scene = new Scene(grid, 350, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Event handler for the conversion button.
     * Reads input (TextField), converts based on selected unit(ComboBox), and displays the result(TextField).
     */
    private class ConvertHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                double inputValue = Double.parseDouble(inputField.getText()); // parses input value to dbl, by default String
                String selectedUnit = unitSelector.getValue(); // .getValue() returns string, <>type is String here
                double convertedValue = 0;
                String outputUnit = "";

                switch (selectedUnit) {
                    case "Metres to Feet" -> {
                        convertedValue = inputValue * 3.28084;
                        outputUnit = "feet";
                    }
                    case "Feet to Metres" -> {
                        convertedValue = inputValue / 3.28084;
                        outputUnit = "metres";
                    }
                    case "Kilograms to Pounds" -> {
                        convertedValue = inputValue * 2.20462;
                        outputUnit = "pounds";
                    }
                    case "Pounds to Kilograms" -> {
                        convertedValue = inputValue / 2.20462;
                        outputUnit = "kilograms";
                    }
                    case "Celsius to Fahrenheit" -> {
                        convertedValue = (inputValue * 9 / 5) + 32;
                        outputUnit = "°F";
                    }
                    case "Fahrenheit to Celsius" -> {
                        convertedValue = (inputValue - 32) * 5 / 9;
                        outputUnit = "°C";
                    }
                }
                outputField.setText(String.format("%.2f %s", convertedValue, outputUnit)); // Display result
                outputField.setStyle("-fx-border-color: none; -fx-font-weight: normal;");
            } catch (NumberFormatException e) { // if input != number
                outputField.setText("Invalid Input"); // show error in output's TextField
                outputField.setStyle("-fx-border-color: red; -fx-font-weight: bold;");
            }
        }
    }
}

