package views;

import beans.Person;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

import java.util.ResourceBundle;

public class CalculatorController {
    @FXML TextField personBMI;
    @FXML RadioButton radioMetric;
    @FXML RadioButton radioEnglish;
    @FXML CheckBox checkboxPregnant;
    @FXML TextField tfHeight;
    @FXML TextField tfWeight;
    private ToggleGroup group;
    private Alert dialog;
    private final Person person;

    public CalculatorController() {
        super();
        person = new Person();
        group = new ToggleGroup();
        dialog = new Alert(Alert.AlertType.ERROR);
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
    }

    /**
     * Calculates BMI
     * @param actionEvent
     */
    public void onCalculate(ActionEvent actionEvent) {
        if (validateHeight() && validateNonPregnant() && validateWeight()) {

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
     * Ensures that the person's height is valid
     *
     * @return true if height is above 3 feet and below 6.11 feet
     */
    private boolean validateHeight() {
        if (person.getEnglishMeasurements()) {
            if (person.getHeight() > 3 && person.getHeight() < 6.11) {
                return true;
            } else {
                dialog.setContentText(ResourceBundle.getBundle("MessagesBundle").getString("ERROR_HEIGHT_ENGLISH"));
                dialog.show();
                return false;
            }
        } else {
            if (person.getHeight() > 0.914 && person.getHeight() < 2.108) {
                return true;
            } else {
                dialog.setContentText(ResourceBundle.getBundle("MessagesBundle").getString("ERROR_HEIGHT_METRIC"));
                dialog.show();
                return false;
            }
        }
    }

    /**
     * Ensures that the person's weight is valid
     *
     * @return true if weight is between 51 and 499, false if not
     */
    private boolean validateWeight() {
        if (person.getWeight() > 50 && person.getWeight() < 500) {
            return true;
        } else {
            // This alert is not necessary in the specs but I wanted to add it anyway
            // for consistency sake
            dialog.setContentText(ResourceBundle.getBundle("MessagesBundle").getString("ERROR_WEIGHT"));
            dialog.show();

           return false;
        }
    }

    private boolean validateNonPregnant() {
        if (person.isPregnant()) {
            dialog.setContentText(ResourceBundle.getBundle("MessagesBundle").getString("ERROR_YOU_ARE_PREGNANT"));
            dialog.show();
            return false;
        } else {
            return true;
        }
    }
}
