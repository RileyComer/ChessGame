package Chess;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	private Socket socket=null;
	private DataInputStream input=null;
	private DataOutputStream out=null;
	
	
	
	public Client(String address,int port) throws IOException {
		
		socket= new Socket(address,port);
		
		input=new DataInputStream(socket.getInputStream());
		out=new DataOutputStream(socket.getOutputStream());
		
		out.writeInt(8888);
		System.out.println("Read");
		System.out.println(input.read());
		System.out.println("Read");
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
