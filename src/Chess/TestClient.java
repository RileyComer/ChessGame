package Chess;

import java.io.IOException;

public class TestClient {

	public static void main(String[] args) throws IOException {
		Client c =new Client("192.168.1.71", 1345);
		//System.out.println(c.readInt());
		//c.writeInt(4);
		c.close();
	}

}
