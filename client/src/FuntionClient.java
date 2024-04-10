import java.net.Socket;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FuntionClient {

    public static Socket client=null;
    public static Scanner sc = new Scanner(System.in);
    public static Socket getClient()
    {
        return client;
    }
    public static void check(String IP, int Port) throws IOException
    {
        try{
            client = new Socket(IP,Port);
            System.out.println("Connected: ");
            System.out.println("Address: " + client.getInetAddress());
            System.out.println("Port: " + client.getPort());
        }catch (Exception e){
            System.out.println("Can't connect to server");
            System.exit(1);
        }
    }
    public static void message(Scanner sc, DataOutputStream dataOutput) throws IOException
    {
        System.out.println("Message from client: ");
        String s=sc.nextLine();
        dataOutput.writeUTF(s);	//Write string
        dataOutput.flush();	//Send output
    }
    public static void capture() throws ClassNotFoundException
    {
        try {
            DataInputStream in = new DataInputStream(FuntionClient.getClient().getInputStream());
            BufferedImage capture=ImageIO.read(in);
            ImageIO.write(capture, "png", new File("screen.png"));
        System.out.println("A full screenshot saved!");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    public static void closeClient() throws IOException
    {
        //  client.close();
        //  System.out.println("Close socket successfully");
    }
    public static void taskManager (Scanner sc) throws IOException
    {
        // String temp = "XEM";
        // System.out.println(temp);System.out.flush();
        // String s1 = "name application";
        // String s2 = "ID";
        // String s3 = "count";
        // temp = sc.nextLine();
        // int soprocess = 
        try{String line;
        Process p = Runtime.getRuntime().exec("tasklist.exe /nh");//("ps -e");
        BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null)
         {
            System.out.println(line);
        // if (!line.trim().equals("")) {
        // // keep only the process name
        // //processes.add(line.substring(0, line.indexOf(" ")));
        // }
        }
    }catch (Exception err) {
        err.printStackTrace();
    }
    }
}
