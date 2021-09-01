package dial.modelEx;
import dial.model.Cmd;
import dial.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WinCMD extends Cmd{
    private Process Rasdial;

    public WinCMD(User user){
        super(user);
        Rasdial = null;
    }

    @Override
    public void rasdialRun(){
        try{
            Rasdial = super.WindowsEV.exec("cmd /c" + super.Command);
            Rasdial.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }

        updateUserStatusCode();
    }

    private void updateUserStatusCode(){
        try {
            BufferedReader BR = new BufferedReader(new InputStreamReader(Rasdial.getInputStream(), "GB2312"));
            String Line;
            while ((Line = BR.readLine()) != null){
                if(Line.contains("已连接") || Line.contains("Connected")){
                    user.setStaCode(0);
                    break;
                }else if(Line.contains("623")){
                    user.setStaCode(623);
                    break;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            Rasdial = null;
        }
    }
}