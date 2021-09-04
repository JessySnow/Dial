package modelEx;
import model.PbkOperation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class pbkOp implements PbkOperation{

    public pbkOp(){}

    @Override
    public void createPbk() {
        File file = new File(target_path);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.exit(5);
            }
        }

        file = null;
    }

    @Override
    public boolean scanPbk() {
        try{
            File file = new File(target_path);
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                if(line.equals("[PPPOE]")){
                    file = null;
                    return  true;
                }
            }

            bufferedReader.close();
            read.close();
            file = null;
        }catch (IOException e){
            System.exit(6);
        }
        return false;
    }


    /* The entrance of pbk operation */
    @Override
    public void copyPbk() {
        createPbk();
        if(!scanPbk()){
            try{
                File file = new File(source_path);
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    Files.write(Paths.get(target_path), (line + '\n').getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                }
                bufferedReader.close();
                read.close();
                file = null;
            }catch (IOException e){
                System.exit(4);
            }
        }
    }

}
