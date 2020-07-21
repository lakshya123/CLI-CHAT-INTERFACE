import java.net.Socket;
import java.io.*;


public class ClientRead extends Thread
{
	private Socket Myself;
	private Client client;
	private BufferedReader read;

	public ClientRead(Socket Myself, Client User)
	{
		this.Myself = Myself;
		this.client = User;

		try
		{
			InputStream input = Myself.getInputStream();
			read = new BufferedReader(new InputStreamReader(input));
		}
		catch(IOException e)
		{
			System.out.println("I/O Error: " +  e.getMessage());
			e.printStackTrace();
		}
	}

	public void run()
	{
		while(true)
		{
			try
			{
				String response = read.readLine();
				if(response != null)
					System.out.println(response);	
			}
			catch(IOException e)
			{
				System.out.println("--------CONNECTION CLOSED-----------");
				break;
			}
		}
	}

}