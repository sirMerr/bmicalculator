import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import sun.applet.Main;

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

        primaryStage.show();
    }
}
