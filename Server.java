import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class Server
{

	private static int port = 9090;
	private static ArrayList<ClientHandler> ClientList = new ArrayList<>();


	public static void main (String args[])
	{
	
		Server server = new Server();
		try(ServerSocket Myserver = new ServerSocket(port)){

			System.out.println("SERVER RUNNING ON PORT : " + port);

			while(true){

				Socket MyWorker = Myserver.accept();
				ClientHandler clientThread  = new ClientHandler(MyWorker,server);
				ClientList.add(clientThread);
				clientThread.start();

			}

		}
		catch(Exception e)
		{
			System.out.println("Error Found "  + e.getMessage());
			e.printStackTrace();
		}

	}

	void removeUser(ClientHandler user)
	{
		ClientList.remove(user);
	}

	void broadcast(String msg, ClientHandler sender)
	{
		for(ClientHandler user : ClientList)
		{
			if(user != sender)
			{
				user.sendMessage(msg);	
			}
		}
	}
}