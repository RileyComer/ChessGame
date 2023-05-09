package Chess;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Gui extends JFrame{ 
	private DisplayChessBoard CB;
	private String[][] board;
	private int selected=-1;
	private char player,AIPlayer;
	private boolean AI,AIOnly,RRW=false,LRW=false, KW=false,RRB=false,LRB=false,KB=false;
	
	public Gui(String[][] b,char p,boolean AI,char AIP,boolean AIO) {
		super("Title");
		board=b;
		player=p;
		this.AI=AI;
		AIPlayer=AIP;
		AIOnly=AIO;
		
		CB =new DisplayChessBoard(this,board);
		add(CB,BorderLayout.CENTER);
		
		//Mouse Handler
		HandlerClass handler=new HandlerClass();
		CB.addMouseListener(handler);
		
		//Exit key
		addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent ke) {  // handler
						if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
							System.exit(0);
						} else if(ke.getKeyCode() == KeyEvent.VK_H) {
							CB.toggleH();
							redraw();
						}
					} 
				}
		);
	}
	
	public void redraw() {
		CB.redraw();
	}
	
	public void move(int x, int y) {
		board[x][y]=board[selected%8][selected/8];
		board[selected%8][selected/8]=null;
		if(board[x][y].charAt(1)=='P') {
			if(y==7||y==0) {
				board[x][y]=player+"Q";
			}
		}
		select(-1,-1);
		CB.move(board);
		if(player=='B') {
			player='W';
		}else {
			player='B';
		}
		if(AIPlayer=='W'||AIOnly) {
			Chess.move(board);
		}
	}
	
	public void guiFinishMove() {
		Chess.move(board);
	}
	
	public void ChangeBoard(String[][]newBoard) {
		board=newBoard;
		select(-1,-1);
		CB.move(board);
		if(player=='W') {
			player='B';
		}else {
			player='W';
		}
		if(AIPlayer=='B'||AIOnly) {
			Chess.move(board);
		}
	}
	
	private String[][] copy(String[][] board){
		String[][] out=new String[8][8];
		for(int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				out[x][y]=board[x][y];
			}
		}
		return out;
	}
	
	private void setMoveLocations(int x, int y) {
		char p=board[x][y].charAt(0);
		char c=board[x][y].charAt(1);
		//Pawn
		if(c=='P') {
			if(p=='W'){
				//White
				//move
				if(y>=1&&board[x][y-1]==null) {
					CB.highlight(x, y-1);
					if(y==6) {
						if(board[x][y-2]==null) {
							CB.highlight(x, y-2);
						}
					}
				}
				//Attack
				if(x>=1&&y>=1&&board[x-1][y-1]!=null&&board[x-1][y-1].charAt(0)!=p) {
					CB.highlight(x-1, y-1);
				}
				if(x<=6&&y>=1&&board[x+1][y-1]!=null&&board[x+1][y-1].charAt(0)!=p) {
					CB.highlight(x+1, y-1);
				}
			
			}else {
				//Black
				if(y<=6&&board[x][y+1]==null) {
					CB.highlight(x, y+1);
					if(y==1) {
						if(board[x][y+2]==null) {
							CB.highlight(x, y+2);
						}
					}
				}
				//Attack
				if(x>=1&&y<=6&&board[x-1][y+1]!=null&&board[x-1][y+1].charAt(0)!=p) {
					CB.highlight(x-1, y+1);
				}
				if(x<=6&&y<=6&&board[x+1][y+1]!=null&&board[x+1][y+1].charAt(0)!=p) {
					CB.highlight(x+1, y+1);
				}
			}
		}else if(c=='K'&&board[x][y].length()==3) {
			//Knight
			if(y>=2&&x>=1) {
				if(board[x-1][y-2]!=null) {
					if(board[x-1][y-2].charAt(0)!=p) {
						CB.highlight(x-1, y-2);
					}
				}else {
					CB.highlight(x-1, y-2);
				}
			}
			if(y>=2&&x<=6) {
				if(board[x+1][y-2]!=null) {
					if(board[x+1][y-2].charAt(0)!=p) {
						CB.highlight(x+1, y-2);
					}
				}else {
						CB.highlight(x+1, y-2);
				}
			}
			if(y>=1&&x>=2) {
				if(board[x-2][y-1]!=null) {
					if(board[x-2][y-1].charAt(0)!=p) {
						CB.highlight(x-2, y-1);
					}
				}else {
					CB.highlight(x-2, y-1);
				}
			}
			if(y>=1&&x<=5) {
				if(board[x+2][y-1]!=null) {
					if(board[x+2][y-1].charAt(0)!=p) {
						CB.highlight(x+2, y-1);
					}
				}else {
					CB.highlight(x+2, y-1);
				}
			}
			if(y<=6&&x>=2) {
				if(board[x-2][y+1]!=null) {
					if(board[x-2][y+1].charAt(0)!=p) {
						CB.highlight(x-2, y+1);
					}
				}else {
					CB.highlight(x-2, y+1);
				}
			}
			if(y<=6&&x<=5) {
				if(board[x+2][y+1]!=null) {
					if(board[x+2][y+1].charAt(0)!=p) {
						CB.highlight(x+2, y+1);
					}
				}else {
					CB.highlight(x+2, y+1);
				}
			}
			if(y<=5&&x>=1) {
				if(board[x-1][y+2]!=null) {
					if(board[x-1][y+2].charAt(0)!=p) {
						CB.highlight(x-1, y+2);
					}
				}else {
					CB.highlight(x-1, y+2);
				}
			}
			if(y<=5&&x<=6) {
				if(board[x+1][y+2]!=null) {
					if(board[x+1][y+2].charAt(0)!=p) {
						CB.highlight(x+1, y+2);
					}
				}else {
					CB.highlight(x+1, y+2);
				}
			}
		}else if(c=='K') {
			//King
			if(y>=1&&x>=1) {
				if(board[x-1][y-1]!=null) {
					if(board[x-1][y-1].charAt(0)!=p) {
						CB.highlight(x-1, y-1);
					}
				}else {
					CB.highlight(x-1, y-1);
				}
			}
			if(y>=1) {
				if(board[x][y-1]!=null) {
					if(board[x][y-1].charAt(0)!=p) {
						CB.highlight(x, y-1);
					}
				}else {
					CB.highlight(x, y-1);
				}
			}
			if(y>=1&&x<=6) {
				if(board[x+1][y-1]!=null) {
					if(board[x+1][y-1].charAt(0)!=p) {
						CB.highlight(x+1, y-1);
					}
				}else {
					CB.highlight(x+1, y-1);
				}
			}
			if(x>=1) {
				if(board[x-1][y]!=null) {
					if(board[x-1][y].charAt(0)!=p) {
						CB.highlight(x-1, y);
					}
				}else {
					CB.highlight(x-1, y);
				}
			}
			if(x<=6) {
				if(board[x+1][y]!=null) {
					if(board[x+1][y].charAt(0)!=p) {
						CB.highlight(x+1, y);
					}
				}else {
					CB.highlight(x+1, y);
				}
			}
			if(y<=6&&x>=1) {
				if(board[x-1][y+1]!=null) {
					if(board[x-1][y+1].charAt(0)!=p) {
						CB.highlight(x-1, y+1);
					}
				}else {
					CB.highlight(x-1, y+1);
				}
			}
			if(y<=6&&x<=6) {
				if(board[x+1][y+1]!=null) {
					if(board[x+1][y+1].charAt(0)!=p) {
						CB.highlight(x+1, y+1);
					}
				}else {
					CB.highlight(x+1, y+1);
				}
			}
			if(y<=6) {
				if(board[x][y+1]!=null) {
					if(board[x][y+1].charAt(0)!=p) {
						CB.highlight(x, y+1);
					}
				}else {
					CB.highlight(x, y+1);
				}
			}
		}else if(c=='Q') {
			//Queen
			for(int iy=y;iy>=1;iy--){
				if(board[x][iy-1]!=null) {
					if(board[x][iy-1].charAt(0)!=p) {
						CB.highlight(x, iy-1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(x, iy-1);
				}
			}
			for(int iy=y;iy<=6;iy++){
				if(board[x][iy+1]!=null) {
					if(board[x][iy+1].charAt(0)!=p) {
						CB.highlight(x, iy+1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(x, iy+1);
				}
			}
			for(int ix=x;ix<=6;ix++){
				if(board[ix+1][y]!=null) {
					if(board[ix+1][y].charAt(0)!=p) {
						CB.highlight(ix+1, y);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix+1, y);
				}
			}
			for(int ix=x;ix>=1;ix--){
				if(board[ix-1][y]!=null) {
					if(board[ix-1][y].charAt(0)!=p) {
						CB.highlight(ix-1, y);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix-1, y);
				}
			}
			for(int ix=x,iy=y;ix>=1&&iy>=1;ix--,iy--){
				if(board[ix-1][iy-1]!=null) {
					if(board[ix-1][iy-1].charAt(0)!=p) {
						CB.highlight(ix-1, iy-1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix-1, iy-1);
				}
			}
			for(int ix=x,iy=y;ix<=6&&iy>=1;ix++,iy--){
				if(board[ix+1][iy-1]!=null) {
					if(board[ix+1][iy-1].charAt(0)!=p) {
						CB.highlight(ix+1, iy-1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix+1, iy-1);
				}
			}
			for(int ix=x,iy=y;ix<=6&&iy<=6;ix++,iy++){
				if(board[ix+1][iy+1]!=null) {
					if(board[ix+1][iy+1].charAt(0)!=p) {
						CB.highlight(ix+1, iy+1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix+1, iy+1);
				}
			}
			for(int ix=x,iy=y;ix>=1&&iy<=6;ix--,iy++){
				if(board[ix-1][iy+1]!=null) {
					if(board[ix-1][iy+1].charAt(0)!=p) {
						CB.highlight(ix-1, iy+1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix-1, iy+1);
				}
			}
		}else if(c=='C') {
			//Castle
			for(int iy=y;iy>=1;iy--){
				if(board[x][iy-1]!=null) {
					if(board[x][iy-1].charAt(0)!=p) {
						CB.highlight(x, iy-1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(x, iy-1);
				}
			}
			for(int iy=y;iy<=6;iy++){
				if(board[x][iy+1]!=null) {
					if(board[x][iy+1].charAt(0)!=p) {
						CB.highlight(x, iy+1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(x, iy+1);
				}
			}
			for(int ix=x;ix<=6;ix++){
				if(board[ix+1][y]!=null) {
					if(board[ix+1][y].charAt(0)!=p) {
						CB.highlight(ix+1, y);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix+1, y);
				}
			}
			for(int ix=x;ix>=1;ix--){
				if(board[ix-1][y]!=null) {
					if(board[ix-1][y].charAt(0)!=p) {
						CB.highlight(ix-1, y);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix-1, y);
				}
			}
		}else if(c=='R') {
			//Rook
			for(int ix=x,iy=y;ix>=1&&iy>=1;ix--,iy--){
				if(board[ix-1][iy-1]!=null) {
					if(board[ix-1][iy-1].charAt(0)!=p) {
						CB.highlight(ix-1, iy-1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix-1, iy-1);
				}
			}
			for(int ix=x,iy=y;ix<=6&&iy>=1;ix++,iy--){
				if(board[ix+1][iy-1]!=null) {
					if(board[ix+1][iy-1].charAt(0)!=p) {
						CB.highlight(ix+1, iy-1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix+1, iy-1);
				}
			}
			for(int ix=x,iy=y;ix<=6&&iy<=6;ix++,iy++){
				if(board[ix+1][iy+1]!=null) {
					if(board[ix+1][iy+1].charAt(0)!=p) {
						CB.highlight(ix+1, iy+1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix+1, iy+1);
				}
			}
			for(int ix=x,iy=y;ix>=1&&iy<=6;ix--,iy++){
				if(board[ix-1][iy+1]!=null) {
					if(board[ix-1][iy+1].charAt(0)!=p) {
						CB.highlight(ix-1, iy+1);
						break;
					}else {
						break;
					}
				}else {
					CB.highlight(ix-1, iy+1);
				}
			}
		}
	}
	
	public void select(int x,int y) {
		if(x!=-1) {
			selected=x+y*8;
			CB.unHighlight();
			CB.highlight(x, y);
			setMoveLocations(x,y);
		}else {
			selected=-1;
			CB.unHighlight();
		}
		redraw();
	}
	
	public void setCon(int c) {
		CB.setCon(c);
	}
	
	private class HandlerClass implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			if((!AI||player!=AIPlayer)&&!AIOnly) {
				double x=e.getX();
				double y=e.getY(); 
				int out=CB.getLocation(x, y);
				int outX=CB.getLocation(x, y)%8;
				int outY=CB.getLocation(x, y)/8;
				if(out!=-1 && board[outX][outY]!=null&& board[outX][outY].charAt(0)==player) {
					select(outX,outY);
				}else if(out!=-1 && CB.checkMoveLocation(outX, outY)&&out!=selected){
					move(outX,outY);
				}else {
					select(-1,-1);
				}
			}
		}
	}		
}
