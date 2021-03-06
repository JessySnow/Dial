import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Objects;

public class Main extends Application{

    /* Set up for javafx ev */
    public static void main(String[] args) {
        launch(args);
    }


    /* The main javafx entry of javafx application */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Panel.fxml")));

        Scene scene = new Scene(root);

        /* Edit the stage */
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/images/icons.png"));
        primaryStage.setResizable(false);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Panel.css")).toExternalForm());
        primaryStage.show();
    }
}