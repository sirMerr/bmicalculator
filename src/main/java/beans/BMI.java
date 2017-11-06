package beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * JavaFX bean for a BMI stats. For use in
 * the TableView to display information about BMI
 * and their associated risks/benefits
 *
 * @author Tiffany Le-Nguyen
 */
public class BMI {
    StringProperty category;
    StringProperty values;
    StringProperty risk;

    /**
     * Constructor
     *
     * @param category
     * @param values
     * @param risk
     */
    public BMI(String category, String values, String risk) {
        this.category = new SimpleStringProperty(category);
        this.values = new SimpleStringProperty(values);
        this.risk = new SimpleStringProperty(risk);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getValues() {
        return values.get();
    }

    public StringProperty valuesProperty() {
        return values;
    }

    public void setValues(String values) {
        this.values.set(values);
    }

    public String getRisk() {
        return risk.get();
    }

    public StringProperty riskProperty() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk.set(risk);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BMI bmi = (BMI) o;

        if (category != null ? !category.equals(bmi.category) : bmi.category != null) return false;
        if (values != null ? !values.equals(bmi.values) : bmi.values != null) return false;
        return risk != null ? risk.equals(bmi.risk) : bmi.risk == null;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (values != null ? values.hashCode() : 0);
        result = 31 * result + (risk != null ? risk.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BMI{" +
                "category=" + category +
                ", values=" + values +
                ", risk=" + risk +
                '}';
    }
}
