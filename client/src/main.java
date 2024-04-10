import java.awt.*;
import java.io.*;
import java.util.*;

import components.windows;

public class main {
	public final static String SERVER_IP = "127.0.0.1";
	public final static int SERVER_PORT = 7;
	public static void main(String[] args) throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		FuntionClient.check(SERVER_IP, SERVER_PORT);
		int opt=0;
		Scanner sc= new Scanner(System.in);
        DataOutputStream dataOutput= new DataOutputStream(FuntionClient.getClient().getOutputStream());
		while(!FuntionClient.getClient().isClosed())
		{
			System.out.println("Press number for funtion: ");
			System.out.println("1. Send message: ");
			System.out.println("2. ScreenShot: ");
			System.out.println("3. Shutdown: ");
			System.out.println("4. Task Manager: ");
			opt = sc.nextInt();
			sc.nextLine();
			dataOutput.writeInt(opt);
			switch(opt)
			{
			case 1://Message
				FuntionClient.message(sc, dataOutput);
				break;
			case 2: // Capture
				FuntionClient.capture();
				break;
				
			case 3:	//Close
				// 
				sc.close();
				dataOutput.close();
				break;
			case 4: //hien task manager
				FuntionClient.taskManager(sc);
				break;

			}
		}

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                windows.createAndShowGUI();
            }
        });
	}
}