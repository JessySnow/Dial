/*
 *  ---------------  JavaFX Controller ---------------- *
 * This class is used to Init panel ui like load an image
 * as background image or add handler of button and label
 */

package dial.view;
import dial.model.Cmd;
import dial.model.Config;
import dial.model.Noti;
import dial.model.User;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InitPanel {

    private Stage primStage = null;
    private double x_offset = 0, y_offset = 0;

    public InitPanel(){}    /* Called before initialize() */

    @FXML
    private ImageView View_Quit;
    @FXML
    private Pane rootPane;
    @FXML
    private ToggleGroup ISP;
    @FXML
    private RadioButton CTC;
    @FXML
    private RadioButton CMC;
    @FXML
    private RadioButton CUC;
    @FXML
    private TextField studentID;
    @FXML
    private PasswordField passWord;

    /* Logic part */
    private User user;
    private Cmd cmd;
    private Noti noti;
    private Config config;


    /*
     * Automatically called after the fxml file
     * has been loaded, it's a necessary part of
     * JavaFX application's controller and the init
     * method will be called automatically too.
     */
    @FXML
    private void initialize(){
        initQuitImage();
        initISPGroup();
    }

    /*********************************** @FXML --> View_Quit_Handler *********************************************/
    @FXML
    void initQuitImage(){
        View_Quit.setImage(new Image("file:resources\\images\\Quit_Unhover.png"));
    }
    @FXML
    void View_Quit_Click_Handler(){
        Platform.exit();
    }
    @FXML
    void View_Quit_MoveIn_Handler(){
        View_Quit.setImage(new Image("file:resources\\images\\Quit_Hover.png"));
    }
    @FXML
    void View_Quit_Exit_Handler(){
        View_Quit.setImage(new Image("file:resources\\images\\Quit_Unhover.png"));
    }
    /*************************************************************************************************************/



    /*********************************** @FXML --> Label_Header *********************************************/
    @FXML
    private void initStage(){
        this.primStage = (Stage)rootPane.getScene().getWindow();
    }

    @FXML
    void Label_Header_Moved_Handler(){
        if(primStage == null){
            initStage();
        }
    }

    @FXML
    void Label_Header_Drag_Handler(MouseEvent event){
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
            x_offset = event.getSceneX();
            y_offset = event.getSceneY();
        }
        if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
            primStage.setX(event.getScreenX() - x_offset);
            if(event.getScreenX() - y_offset < 0){
                primStage.setY(0);
            }else{
                primStage.setY(event.getScreenY() - y_offset);
            }
        }
    }
    /********************************************************************************************************/



    /************************************************** Functions ******************************************************/

    private void initISPGroup(){
        ISP = new ToggleGroup();
        CMC.setToggleGroup(ISP);
        CUC.setToggleGroup(ISP);
        CTC.setToggleGroup(ISP);
    }

    private boolean isInfoFilled(){
        if(studentID.getText().trim().isEmpty()) return false;
        if(passWord.getText().trim().isEmpty())  return false;
        if((RadioButton) ISP.getSelectedToggle() == null) return false;
       return true;
    }

    /* Entrance of dial */
    private void Dial(){
        
    }

    /********************************************************************************************************************/





}