/*
 *  ---------------  JavaFX Controller ---------------- *
 * This class is used to Init panel ui like load an image
 * as background image or add handler of button and label
 */

package view;

import model.User;
import modelEx.PropertiesConfig;
import modelEx.DialThread;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import java.util.Timer;
import java.util.TimerTask;

public class InitPanel {

    private Stage primStage = null;
    private double x_offset = 0, y_offset = 0;
    private Timer timer;

    public InitPanel(){}    /* Called before initialize() */

    @FXML
    private ImageView View_Quit;
    @FXML
    private ImageView View_BackGround;
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
    @FXML
    private Label Label_Quit;
    @FXML
    private Circle Circle_Status;
    @FXML
    private Button Button_Login;

    /* Logic part */
    private User user;
    private PropertiesConfig config;


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
        View_BackGround_Init();

        /* Init a user and init user_info's text_field */
        fillUserInfo();

        updateStatus();
    }

    /*********************************** @FXML --> View_Quit_Handler/Label_Quit_Handler/View_BackGround_Init *********************************************/
    @FXML
    void initQuitImage(){
        View_Quit.setImage(new Image("images/Quit_Unhover.png"));
    }
    @FXML
    void View_Quit_Click_Handler(){
        timer.cancel();
        Platform.exit();
    }
    @FXML
    void View_Quit_MoveIn_Handler(){
        Label_Quit.setStyle("-fx-background-color: #d50000;");
    }

    @FXML
    void Label_Quit_Exit_Handler(){
        Label_Quit.setStyle("-fx-background-color: #707070");
    }
    @FXML
    void Label_Quit_MoveIn_Handler(){
        Label_Quit.setStyle("-fx-background-color: #d50000");
    }

    /* Load a Image as background */
    private void View_BackGround_Init(){
        View_BackGround.setImage(new Image("images/BackGround.png"));
    }
    /*********************************************************************************************************************************/



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
        /* Sync the color */
        Label_Quit.setStyle("-fx-background-color: #424242");
    }

    @FXML
    void Label_Header_Exit_Handler(){
        /* Sync the color */
        Label_Quit.setStyle("-fx-background-color: #707070");
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

    /* Entrance of dial (important) */
    @FXML
    private void Dial(){
        if(!user.getStatus().equals("DIAING")) {
            if (isInfoFilled()) {
                /* Get info from text_field */
                updateUserInfo();
                /* Create a new thread to dial */
                dial_Thread("#Dial_Thread");
            }else {
                highLight_Unfilled();
            }
        }
    }

    /* Highlight the controller if not filled or selected */
    private void highLight_Unfilled(){
        if(passWord.getText().trim().equals("")){
            passWord.requestFocus();
        }
        if(studentID.getText().trim().equals("")){
            studentID.requestFocus();
        }
    }

    private void initISPGroup(){
        ISP = new ToggleGroup();
        CMC.setToggleGroup(ISP);
        CUC.setToggleGroup(ISP);
        CTC.setToggleGroup(ISP);
    }

    private boolean isInfoFilled(){
        if(studentID.getText().trim().isEmpty()) return false;
        if(passWord.getText().trim().isEmpty())  return false;
        if(ISP.getSelectedToggle() == null) return false;
       return true;
    }

    /* Init a user and get the info from config if user's info is not null */
    private void initUser(){
        user = new User();
        config = new PropertiesConfig(user);
        config.loadFromDisk();
        config.initUserWithConfig();
    }

    /* Fill the test field if user's info is not null */
    private void fillUserInfo(){
        initUser();
        if(!user.getUserName().equals("NULL")){
            studentID.setText(user.getUserName());
            passWord.setText(user.getPassWord());

            switch (user.getRule()){
                case ("@cmcc"):
                    CMC.setSelected(true);
                    break;
                case("@cuc"):
                    CUC.setSelected(true);
                    break;
                case("@ctc"):
                    CTC.setSelected(true);
                    break;
            }
        }
    }

    /* Get user info from text_field
    *  this function is called by dial function
    * */
    private void updateUserInfo(){
        user.setUserName(studentID.getText());
        user.setPassWord(passWord.getText());

        if(CUC.isSelected()){
            user.setRule("@cuc");
        }else if(CMC.isSelected()){
            user.setRule("@cmcc");
        }else {
            user.setRule("@ctc");
        }
    }


    public void dial_Thread(String thread_name){
        DialThread dial_thread = new DialThread(thread_name, user);
        dial_thread.start();
    }
    /********************************************************************************************************************/


    /*************************************************** Schedule Works(UI)  ***************************************************************/

    /* Show status --> Change the color of circle */
    private void updateStatus(){
        if(timer == null) {
            timer = new Timer();
        }
        updateStatus updateStatus = new updateStatus(Circle_Status, user, Button_Login);
        timer.schedule(updateStatus, 1000, 1000);
    }
    /***************************************************************************************************************************************/
}

/* Timer class */
class updateStatus extends TimerTask{
    Circle circle;
    User user;
    Button Button_Login;
    int status_Count = 0;

    public updateStatus(Circle circle, User user, Button Button_Login){
        this.circle = circle;
        this.user = user;
        this.Button_Login = Button_Login;
    }

    @Override
    public void run() {
        if(user.getStatus().equals("DIAING")) {
            Button_Login.setDisable(true);
            if (status_Count % 2 == 0) {
                circle.setFill(Color.valueOf("#fbc02d"));
            } else {
                circle.setFill(Color.valueOf("#388e3c"));
            }
            status_Count = (status_Count + 1) % 10;
        }
        if(user.getStatus().equals("OFFLINE")){
            Button_Login.setDisable(false);
            circle.setFill(Color.valueOf("#9b0000"));
        }
        if(user.getStatus().equals("INLINE")){
            Button_Login.setDisable(false);
            circle.setFill(Color.valueOf("#388e3c"));
        }
    }
}