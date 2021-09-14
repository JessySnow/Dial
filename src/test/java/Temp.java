import java.io.*;
import java.net.*;
import java.util.*;

public class Temp {
    public static void main(String[] args) {
        try{
            int i = 1;
            ServerSocket s = new ServerSocket(8189);

            while (true) {
                Socket inComing = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadEchoHandler(inComing);
                Thread t = new Thread(r);
                t.start();
                i ++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class ThreadEchoHandler implements Runnable{

    private Socket inComing;

    public ThreadEchoHandler(Socket i){
        inComing = i;
    }

    @Override
    public void run() {
        try{
            InputStream inStream = inComing.getInputStream();
            OutputStream outStream = inComing.getOutputStream();

            Scanner in = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);

            out.println("Hello! Enter BYE to exit!");

            inComing.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}