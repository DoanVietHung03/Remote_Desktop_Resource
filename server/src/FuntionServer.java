import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.net.*;
import java.io.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;

public class FuntionServer {
    static ServerSocket serverSocket;
    static Socket server=null;
	private static final float JPG_QUALITY = 0.3f;

	
    public static Socket getServer()
    {
        return server;
    }
    public static ServerSocket getServerSocket()
    {
        return serverSocket;
    }
    public static void checkClient(String SERVER_IP,int SERVER_PORT)
    {
        try {
			System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server started: " + serverSocket);
			System.out.println("Waiting for a client ...");
			while (true) {
				try {
					server = serverSocket.accept();
					System.out.println("Client accepted: " + server);
					break;
				} catch (IOException e) {
					System.err.println(" Connection Error: " + e);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    public static void receiveMessage(DataInputStream dataInput)
    {
			try{
				String message = dataInput.readUTF();
				System.out.println(message);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	public static void Screenshot() throws IOException
	{
		try{
			Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screen =new Robot().createScreenCapture(rect);
			screen.flush();
		}catch (AWTException ex) {
			System.out.println(ex);
		}
	}
	private static byte[] convertToJPG(BufferedImage img) throws IOException {
    		ImageWriter writer =
        	ImageIO.getImageWritersByFormatName("jpg").next();
    		ImageWriteParam iwp = writer.getDefaultWriteParam();
    		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    		iwp.setCompressionQuality(JPG_QUALITY);

    		ByteArrayOutputStream bout = new ByteArrayOutputStream();
    		writer.setOutput(new MemoryCacheImageOutputStream(bout));
    		writer.write(null, new IIOImage(img, null, null), iwp);
    		writer.dispose();
    		bout.flush();
    		return bout.toByteArray();
  		}
	
    public static void checkClose() throws IOException
    {
		if(server!=null)
		{
			server.close();
			System.out.println("Close socket successfully");
		}
    }
}
