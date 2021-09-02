/*
 * This class is used to Init panel ui like load an image
 * as background image or add handler of button and label
 */

package dial.view;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InitPanel {

    @FXML
    private ImageView View_Background;
    @FXML
    private ImageView View_Quit;

    /*
     * Automatically called after the fxml file
     * has been loaded, it's a necessary part of
     * JavaFX application's controller and the init
     * method will be called automatically too.
     */
    public InitPanel(){}    /* Called before initialize() */
    @FXML
    private void initialize(){
        loadQuitImage();
    }


    @FXML
    private void loadBackgroundImage(){
        Image backGroundImage = new Image("file:resources\\images\\BackGround.png");
        View_Background.setImage(backGroundImage);
        View_Background.setCache(true);
    }

    @FXML
    private void loadQuitImage(){
        Image quitImage = new Image("file:resources\\images\\Quit_Unhover.png");
        View_Quit.setImage(quitImage);
        View_Quit.setCache(true);
    }
}