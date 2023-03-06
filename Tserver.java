package tp2tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class Tserver {

public static int nbclient;
public Tserver() throws Exception
{
	int port = 9000;
	ServerSocket server_socket = new ServerSocket(port);
	System.out.println("The server port=" + port + "is open.");
	
	while(true)
	{
		System.out.println("Waiting for connection: ");
		Socket service_socket = server_socket.accept();
		Server2 server_thread  = new Server2(service_socket, nbclient);
		Thread thread = new Thread(server_thread);
		thread.start();
		nbclient++;
		System.out.println("the client n°= " + nbclient + "is connected.");
	
	}
}

public static void main(String[] args)
{
	try {
	new Tserver();
	} catch (Exception e) {
		e.printStackTrace();
		nbclient--;
	}
}

}