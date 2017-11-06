import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import sun.applet.Main;
import views.CalculatorController;

import java.io.IOException;
import java.sql.SQLException;
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
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
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
     *
     * Using this approach allows you to use loader.getController() to get a
     * reference to the fxml's controller should you need to pass data to it.
     * Not used in this archetype.
     */
    private void configureStage() {
        try {
            // Instantiate the FXMLLoader
            FXMLLoader loader = new FXMLLoader();

            // Set the location of the fxml file in the FXMLLoader
            loader.setLocation(MainApp.class.getResource("/fxml/CalculatorLayout.fxml"));

            // Localize the loader with its bundle
            // Uses the default locale and if a matching bundle is not found
            // will then use MessagesBundle.properties
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
