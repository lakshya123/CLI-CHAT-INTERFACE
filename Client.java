import java.net.Socket;
import java.io.*;
import java.net.*;

public class Client
{
	private static String ip = "localhost";
	private static int port = 9090 ;
	private String Username;

	public String getUsername()
	{
		return this.Username;
	}

	public void setUsername(String Username)
	{
		this.Username = Username;
	}
	
	public void execute()
	{
		try
		{
			Socket Myself = new Socket(ip,port);
			System.out.println("Connected to the Chat Server:");

			new ClientRead(Myself,this).start();
			new ClientWrite(Myself,this).start();
		}
		catch(UnknownHostException e)
		{
			System.out.println("Server Not Found" + e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println("I/O error : " + e.getMessage());
		}
		
	}

	public static void main(String args[])
	{
		Client User = new Client();
		User.execute();

	}
}