package views;

import beans.BMI;
import beans.Person;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CalculatorController {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass()
            .getName());
    @FXML TableColumn colRisk;
    @FXML TableColumn colBMI;
    @FXML TableColumn colCat;
    @FXML TableView tableview;
    @FXML TextField personBMI;
    @FXML RadioButton radioMetric;
    @FXML RadioButton radioEnglish;
    @FXML CheckBox checkboxPregnant;
    @FXML TextField tfHeight;
    @FXML TextField tfWeight;
    private ToggleGroup group;
    private Alert dialog;
    private final Person person;
    private final ObservableList<BMI> bmiStats;

    /**
     * Default constructor, initiates the global variables
     */
    public CalculatorController() {
        super();
        person = new Person();
        group = new ToggleGroup();
        dialog = new Alert(Alert.AlertType.ERROR);
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        bmiStats = FXCollections.observableArrayList(
                new BMI("Underweight", "less than 18.5", "Increased health risk"),
                new BMI("Normal weight", "18.5 to 24.9", "Least health risk"),
                new BMI("Overweight", "18.5 to 24.9", "Increased health risk"),
                new BMI("Obese class I", "30.0 to 34.9", "High health risk"),
                new BMI("Obese class II", "35.0 to 39.9", "Very high health risk"),
                new BMI("Obese class III", "40.0 or more", "Extremely high health risk")
        );
    }
    /**
     * This method is automatically called after the fxml file has been loaded.
     * This code binds the properties of the data bean to the JavaFX controls.
     * Changes to a control is immediately written to the bean and a change to
     * the bean is immediately shown in the control.
     */
    @FXML
    private void initialize() {
        // Set radio buttons to groups
        radioEnglish.setToggleGroup(group);
        radioMetric.setToggleGroup(group);
        radioMetric.setSelected(true);

        // Two way binding
        Bindings.bindBidirectional(tfHeight.textProperty(), person.heightProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(tfWeight.textProperty(), person.weightProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(checkboxPregnant.selectedProperty(), person.pregnantProperty());
        Bindings.bindBidirectional(radioEnglish.selectedProperty(), person.englishMeasurementsProperty());

        // Set bmi stats properties to columns
        colCat.setCellValueFactory(
                new PropertyValueFactory<BMI,String>("category")
        );

        colBMI.setCellValueFactory(
                new PropertyValueFactory<BMI,String>("values")
        );

        colRisk.setCellValueFactory(
                new PropertyValueFactory<BMI,String>("risk")
        );

        tableview.setItems(bmiStats);
    }

    /**
     * Calculates BMI
     * @param actionEvent
     */
    public void onCalculate(ActionEvent actionEvent) {
        if (validateHeight() && validateNonPregnant() && validateWeight()) {
            double bmi;
            if (person.isEnglishMeasurements()) {
                bmi = person.getWeight()*703/ Math.pow(person.getHeight() * 12,2);
            } else {
                bmi = person.getWeight()/ Math.pow(person.getHeight(),2);
            }

            personBMI.setText(new DecimalFormat("##.##").format(bmi));
        }
    }



    /**
     * Exit event handler
     *
     * @param actionEvent
     */
    public void onExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * Ensures that the person's height is valid, otherwise
     * sends an alert
     *
     * @return true if height is above 3 feet and below 6.11 feet
     */
    private boolean validateHeight() {
        log.debug("English: " + person.isEnglishMeasurements());

        if (person.isEnglishMeasurements()) {
            if (person.getHeight() >= 3 && person.getHeight() <= 6.08333) {
                return true;
            } else {
                dialog.setContentText(ResourceBundle.getBundle("Messages").getString("ERROR_HEIGHT_ENGLISH"));
                dialog.show();
                return false;
            }
        } else {
            if (person.getHeight() > 0.914 && person.getHeight() < 2.108) {
                return true;
            } else {
                dialog.setContentText(ResourceBundle.getBundle("Messages").getString("ERROR_HEIGHT_METRIC"));
                dialog.show();
                return false;
            }
        }
    }

    /**
     * Ensures that the person's weight is valid, otherwise
     * sends an alert
     *
     * @return true if weight is between 51 and 499, false if not
     */
    private boolean validateWeight() {
        if (person.getWeight() > 50 && person.getWeight() < 500) {
            return true;
        } else {
            // This alert is not necessary in the specs but I wanted to add it anyway
            // for consistency sake
            dialog.setContentText(ResourceBundle.getBundle("Messages").getString("ERROR_WEIGHT"));
            dialog.show();

           return false;
        }
    }

    /**
     * Ensures that the person is not pregnant, otherwise sends
     * an alert telling them that this measure is not for them
     *
     * @return true if not pregnant, false if not
     */
    private boolean validateNonPregnant() {
        if (person.isPregnant()) {
            dialog.setContentText(ResourceBundle.getBundle("Messages").getString("ERROR_YOU_ARE_PREGNANT"));
            dialog.show();
            return false;
        } else {
            return true;
        }
    }
}
