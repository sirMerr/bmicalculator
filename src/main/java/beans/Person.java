package beans;

import javafx.beans.property.*;

/**
 * JavaFX bean for a person
 *
 * @author Tiffany Le-Nguyen
 */
public class Person {

    private IntegerProperty height;
    private IntegerProperty weight;
    private BooleanProperty pregnant;
    private BooleanProperty englishMeasurements;

    /**
     * Non-default constructor
     *
     * @param height
     * @param weight
     * @param pregnant
     * @param englishMeasurements
     */
    public Person(int height, int weight, boolean pregnant, boolean englishMeasurements) {
        this.height = new SimpleIntegerProperty(height);
        this.weight = new SimpleIntegerProperty(weight);
        this.pregnant = new SimpleBooleanProperty(pregnant);
        this.englishMeasurements = new SimpleBooleanProperty(englishMeasurements);
    }

    public Person() {
        this(-1,-1,false, false);
    }

    // Getters and Setters
    public int getHeight() {
        return height.get();
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    public int getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public boolean isPregnant() {
        return pregnant.get();
    }

    public BooleanProperty pregnantProperty() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant.set(pregnant);
    }

    public boolean getEnglishMeasurements() {
        return englishMeasurements.get();
    }

    public BooleanProperty englishMeasurementsProperty() {
        return englishMeasurements;
    }

    public void setEnglishMeasurements(boolean englishMeasurements) {
        this.englishMeasurements.set(englishMeasurements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (height != null ? !height.equals(person.height) : person.height != null) return false;
        if (weight != null ? !weight.equals(person.weight) : person.weight != null) return false;
        if (pregnant != null ? !pregnant.equals(person.pregnant) : person.pregnant != null) return false;
        return englishMeasurements != null ? englishMeasurements.equals(person.englishMeasurements) : person.englishMeasurements == null;
    }

    @Override
    public int hashCode() {
        int result = height != null ? height.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (pregnant != null ? pregnant.hashCode() : 0);
        result = 31 * result + (englishMeasurements != null ? englishMeasurements.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "height=" + height +
                ", weight=" + weight +
                ", pregnant=" + pregnant +
                ", englishMeasurements=" + englishMeasurements +
                '}';
    }
}
