package Chess;

import java.io.IOException;
import java.util.Scanner;

public class ChessServer {
	
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner(System.in);
		Server s=new Server(1345);
		while(true) {
			if(in.nextLine().equals("")) {
				break;
			}
		}
		System.out.println("Closed");
		s.close();
	}
}
