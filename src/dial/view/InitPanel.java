/**
 * This class is used to Init panel ui like load an image
 * as background image
 */

package dial.view;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InitPanel {
    @FXML
    private static ImageView View_Background;

    public static void InitPanel(Scene scene){
        loadBackgroundImage(scene);
    }

    private static void loadBackgroundImage(Scene scene){
        try{
            Image backGroundImage = new Image("file:resources\\images\\BackGround.png");
            View_Background.setImage(backGroundImage);
            View_Background.setCache(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
