import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * This class in which the JavaFX application begins
 * that uses FXML/Controller architecture.
 *
 * @author Tiffany Le-Nguyen
 */
public class MainApp extends Application {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass()
            .getName());
    private Stage primaryStage;
    private Parent rootPane;

    public MainApp() {
        super();
    }
    /**
     * The main entry point for the JavaFX application.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        configureStage();

        // Set the window title
        primaryStage.setTitle(ResourceBundle.getBundle("Messages").getString("TITLE"));

        // Show stage
        primaryStage.show();
    }

    /**
     * Load the FXML and bundle, create a Scene and put the Scene on Stage.
     */
    private void configureStage() {
        try {
            // Instantiate the FXMLLoader
            FXMLLoader loader = new FXMLLoader();

            // Set the location of the fxml file in the FXMLLoader
            loader.setLocation(MainApp.class.getResource("/fxml/CalculatorLayout.fxml"));

            // Set resources
            loader.setResources(ResourceBundle.getBundle("Messages"));

            // Parent is the base class for all nodes that have children in the
            // scene graph such as AnchorPane and most other containers
            Parent parent = (GridPane) loader.load();

            // Load the parent into a Scene
            Scene scene = new Scene(parent);

            // Put the Scene on Stage
            primaryStage.setScene(scene);


        } catch (IOException ex) { // getting resources or files
            // could fail
            log.error(null, ex);
            System.exit(1);
        }
    }
}
