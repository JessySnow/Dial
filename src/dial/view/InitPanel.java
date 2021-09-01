/**
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

    public void testInit(){
        System.out.println("Hello World!");
    }

    @FXML
    public void loadBackgroundImage(){
        try{
            Image backGroundImage = new Image("file:resources\\images\\BackGround.png");
            View_Background.setImage(backGroundImage);
            View_Background.setCache(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
