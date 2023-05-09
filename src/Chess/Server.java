package Chess;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private Socket socket=null;
	private ServerSocket server=null;
	private DataInputStream input=null;
	private DataOutputStream out=null;
	
	public Server(int port) throws IOException {
		server=new ServerSocket(port);
		socket=server.accept();
		
		out=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		input=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		
		out.writeInt(8888);
		System.out.println(input.available());
	}
	
	public void writeInt(int i) throws IOException {
		//out
		out.writeInt(i);
	}
	
	public int readInt() throws IOException {
		//in
		while(input.available()==0) {
			
		}
		return input.readInt();
		
	}
	public void close() throws IOException {
		input.close();
		out.close();
		socket.close();
	}
}
