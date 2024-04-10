

import java.io.*;
import java.awt.*;


public class App {

	public final static int SERVER_PORT = 7;
	public final static String SERVER_IP = "127.0.0.1";


	public static void main(String[] args) throws IOException, AWTException {

		//Start app of server
		FuntionServer.checkClient(SERVER_IP, SERVER_PORT);
		DataInputStream in = new DataInputStream(FuntionServer.getServer().getInputStream());
		while(!FuntionServer.getServer().isClosed())
		{
			int message=in.readInt();
			switch(message)
			{
			case 1:
				FuntionServer.receiveMessage(in);
				break;
			case 2:
				FuntionServer.Screenshot();
				break;
			case 3:
				FuntionServer.checkClose();
				break;
			}
		}
	}
}