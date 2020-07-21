import java.net.Socket;
import java.io.*;

public class ClientWrite extends Thread
{

	private PrintWriter writer;
	private Socket MySelf;
	private Client client;

	public ClientWrite(Socket MySelf, Client User)
	{
		this.MySelf = MySelf;
		this.client = User;	

		try
		{
			OutputStream output = MySelf.getOutputStream();
			writer = new PrintWriter(output,true);
		}
		catch(IOException ex)
		{
			System.out.println("Error getting OutputStream.." + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void run()
	{
		Console console = System.console();

		String userName = console.readLine("\nEnter your UserName: ");
		writer.println(userName);
		client.setUsername(userName);

		String text = null;
		String choice = null;

		do
		{

			choice = console.readLine("--- 1. Message--- \n---2. Run Command---\n");

			if(choice.equals("1"))
			{
				text = console.readLine("[" + userName + "]");
				writer.println(text);
			}
			else if(choice.equals("2"))
			{
				String cmd = null;
				String loc = null;

				cmd = console.readLine("---Input Command :---");
				loc = console.readLine("---File Location :---");

				text  = "-rc " + cmd + " " + loc;
				writer.println(text);
			}
			else
			{
				System.out.println("Please Select (1/2).");
			}

		} while(text == null || !text.equals("bye"));

		try
		{
			MySelf.close();
		}
		catch(Exception e)
		{
			System.out.println("Error...." + e.getMessage());
			e.printStackTrace();
		}
	}


}