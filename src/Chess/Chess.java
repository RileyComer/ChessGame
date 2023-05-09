package Chess;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class Chess{
	
	private static String[][] board;
	private static String[] randomPeices= {"R","KT","C","Q"};
	private static char player='W',AIPlayer='B';
	private static boolean AIINT=false,AI=true,AIOnly=false,randomGame=false,semiRandomGame=false;
							//(Pawn,Rook,Knight,Bishop,Queen,BishopPair,RookPair,KnightPair,defensive?,level)
	private static AI MainAI=new AI(10,50,30,30,90,40,50,30,true,5);
	private static AI TestAI=new AI(10,10,10,10,10,10,10,10,true,5);
	private static AI Tracy=new AI(10,70,31,31,100,31,70,30,true,5);
	private static AI Levi=new AI(5,35,15,20,100,35,35,15,false,3);
	//private static AI Daylon=new AI(10,60,50,);
	private static AI Riley=new AI(10,40,30,45,90,50,40,30,false,4);
	private static AI Bop=new AI(10,50,45,50,100,50,50,45,true,4);
	private static AI AIWGame=Riley, AIBGame=Bop;
	private static Gui window;
	private static File turnEnd=new File ("src/Images/turnEnd.WAV");
	private static int Value=0;
	
	public static void main(String[] args)  {
		//String of board pieces
		if(!randomGame) {
			board = new String[8][8];{
				board[0][0]="BC";
				board[1][0]="BKT";
				board[2][0]="BR";
				board[3][0]="BQ";
				board[4][0]="BK";
				board[5][0]="BR";
				board[6][0]="BKT";
				board[7][0]="BC";
				for(int i=0;i<8;i++) {
					board[i][1]="BP";
				}
				board[0][7]="WC";
				board[1][7]="WKT";
				board[2][7]="WR";
				board[3][7]="WQ";
				board[4][7]="WK";
				board[5][7]="WR";
				board[6][7]="WKT";
				board[7][7]="WC";
				for(int i=0;i<8;i++) {
					board[i][6]="WP";
				}
			}
		}else if(!semiRandomGame) {
			board = new String[8][8];{
				board[0][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[1][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[2][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[3][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[4][0]="BK";
				board[5][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[6][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[7][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				
				board[0][1]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[1][1]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[2][1]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[3][1]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[4][1]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[5][1]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[6][1]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[7][1]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				
				board[0][7]="W"+board[0][0].substring(1);
				board[1][7]="W"+board[1][0].substring(1);
				board[2][7]="W"+board[2][0].substring(1);
				board[3][7]="W"+board[3][0].substring(1);
				board[4][7]="WK";
				board[5][7]="W"+board[5][0].substring(1);
				board[6][7]="W"+board[6][0].substring(1);
				board[7][7]="W"+board[7][0].substring(1);
				
				board[0][6]="W"+board[0][1].substring(1);
				board[1][6]="W"+board[1][1].substring(1);
				board[2][6]="W"+board[2][1].substring(1);
				board[3][6]="W"+board[3][1].substring(1);
				board[4][6]="W"+board[4][1].substring(1);
				board[5][6]="W"+board[5][1].substring(1);
				board[6][6]="W"+board[6][1].substring(1);
				board[7][6]="W"+board[7][1].substring(1);
			}
		}else {
			board = new String[8][8];{
				board[0][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[1][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[2][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[3][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[4][0]="BK";
				board[5][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[6][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				board[7][0]="B"+randomPeices[(int) (Math.random()*randomPeices.length)];
				for(int i=0;i<8;i++) {
					board[i][1]="BP";
				}
				board[0][7]="W"+board[0][0].substring(1);
				board[1][7]="W"+board[1][0].substring(1);
				board[2][7]="W"+board[2][0].substring(1);
				board[3][7]="W"+board[3][0].substring(1);
				board[4][7]="WK";
				board[5][7]="W"+board[5][0].substring(1);
				board[6][7]="W"+board[6][0].substring(1);
				board[7][7]="W"+board[7][0].substring(1);
				for(int i=0;i<8;i++) {
					board[i][6]="WP";
				}
			}
		}
		// Creates Window
		window= new Gui (board,player,AI,AIPlayer,AIOnly);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); window.setUndecorated(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		if(AIPlayer=='W') {
			Value=AIWGame.Evaluate(board, player);
			board=AIWGame.AIMove(board,player);
			if(AIINT) {
				if(Value+40<AIWGame.getCon()) {
					window.setCon(-2);
				}else if(Value+20<AIWGame.getCon()) {
					window.setCon(-1);
				}else if(Value-20>AIWGame.getCon()) {
					window.setCon(+1);
				}else if(Value-40>AIWGame.getCon()) {
					window.setCon(+2);
				}else {
					window.setCon(0);
				}
			}
			if(AIBGame.getLevel()<5) {
				try {
					Thread.sleep(10);
				}catch(Exception e){
					
				}
			}
			playSound(turnEnd);
			window.ChangeBoard(board);
		}
		while(true) {
			int i=0;
			if (i==1){
				try {
			    	Thread.sleep(1);
			    } catch (InterruptedException ie){
			    	System.out.println("Scanning...");
			    }

			}
			window.redraw();
		}
	}
	
	public static void move(String[][] b) {
		board=b;
		//System.out.println(AIBGame.getCheck(board, 'B', false, false, false, false));
		if(player=='B') {
			player='W';
		}else {
			player='B';
		}
		if((player==AIPlayer||AIOnly)&&AI) {
			if(player=='W') {
				Value=AIWGame.Evaluate(board, player);
				board=AIWGame.AIMove(board,player);
			}else {
				Value=AIBGame.Evaluate(board, player);
				board=AIBGame.AIMove(board,player);
			}
			if(AIINT) {
				if(player=='W') {
					if(Value+40<AIWGame.getCon()) {
						window.setCon(-2);
					}else if(Value+20<AIWGame.getCon()) {
						window.setCon(-1);
					}else if(Value-20>AIWGame.getCon()) {
						window.setCon(+1);
					}else if(Value-40>AIWGame.getCon()) {
						window.setCon(+2);
					}else {
						window.setCon(0);
					}
				}else {
					if(Value+40<AIBGame.getCon()) {
						window.setCon(-2);
					}else if(Value+20<AIBGame.getCon()) {
						window.setCon(-1);
					}else if(Value-20>AIBGame.getCon()) {
						window.setCon(+1);
					}else if(Value-40>AIBGame.getCon()) {
						window.setCon(+2);
					}else {
						window.setCon(0);
					}
				}
			}
			if(AIBGame.getLevel()<5) {
				try {
					Thread.sleep(10);
				}catch(Exception e){
					
				}
			}
			playSound(turnEnd);
			window.ChangeBoard(board);
		}
	}
	
	private static void playSound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		}catch(Exception e) {
			
		}
	}
}
