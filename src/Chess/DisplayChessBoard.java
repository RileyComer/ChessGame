package Chess;

import java.awt.*;

import javax.swing.*;

public class DisplayChessBoard extends JPanel{
	Toolkit t=Toolkit.getDefaultToolkit();
	private Gui gui;
	private String[][] board, oldBoard=new String[8][8];
	private String[] graveW=new String[16];
	private String[] graveB=new String[16];
	private boolean[][] move = new boolean[8][8];
	private int size=100,startX,startY,turnEnd=0,con=0,dx=-1,dy=-1;
	private Color color;
	private boolean H=true;
	
	public DisplayChessBoard(Gui gui1,String[][] b){
		gui=gui1;
		board=b;
		oldBoard=copy(board);
	}
	
	public void paintComponent(Graphics g) {
		//window.getHeight(); window.getWidth();
		startX=(int)((gui.getWidth()/2.0)-((size*8)/2.0));
		startY=(int)((gui.getHeight()/2.0)-((size*8)/2.0));
		// Board(Black and white),Highlighted piece, movable spaces
		super.paintComponent(g);
		
		this.setBackground(Color.BLACK);
		color=new Color(100, 50, 10);
		g.setColor(color);
		g.fillRect(startX-2*size, startY-size, size*12, size*10);
		for(int y=1;y<8;y+=2) {
			for(int x=0;x<8;x+=2) {
				if(move[x][y]!=false&&H) {
					color=new Color(255, 230, 28);
					g.setColor(color);
				}else {
					color=new Color(221, 167, 68);
					g.setColor(color);
				}
				if(dx==x&&dy==y) {
					color=new Color(221, 20, 20);
					g.setColor(color);
				}
				g.fillRect(startX+x*size, startY+y*size, size, size);
			}
			
			for(int x=1;x<8;x+=2) {
				if(move[x][y]!=false&&H) {
					color=new Color(255, 255, 149);
					g.setColor(color);
				}else {
					color=new Color(253, 230, 189);
					g.setColor(color);
				}
				if(dx==x&&dy==y) {
					color=new Color(221, 20, 20);
					g.setColor(color);
				}
				g.fillRect(startX+x*size, startY+y*size, size, size);
			}
		}
		
		for(int y=0;y<8;y+=2) {
			for(int x=1;x<8;x+=2) {
				if(move[x][y]!=false&&H) {
					color=new Color(255, 230, 28);
					g.setColor(color);
				}else {
					color=new Color(221, 167, 68);
					g.setColor(color);
				}
				if(dx==x&&dy==y) {
					color=new Color(221, 20, 20);
					g.setColor(color);
				}
				g.fillRect(startX+x*size, startY+y*size, size, size);
			}
			
			for(int x=0;x<8;x+=2) {
				if(move[x][y]!=false&&H) {
					color=new Color(255, 255, 149);
					g.setColor(color);
				}else {
					color=new Color(253, 230, 189);
					g.setColor(color);
				}
				if(dx==x&&dy==y) {
					color=new Color(221, 20, 20);
					g.setColor(color);
				}
				g.fillRect(startX+x*size, startY+y*size, size, size);
			}
		}
		
		//Draw chess pieces
		for(int y=0; y<8;y++){
			for(int x=0;x<8;x++) {
				if(board[x][y]!=null) {
					drawPiece(g,board[x][y],(int)((size/2.0)-30)+startX+x*size,(int)((size/2.0)-30)+startY+y*size);
				}
			}
		}
		//Draw Grave
		for(int i=0;i<16;i++) {
			if(graveW[i]!=null) {
				drawPiece(g,graveW[i],(int)((size/2.0)-30)+startX+8*size+((i)/8)*size,(int)((size/2.0)-30)+startY+(((i)%8))*size);
			}else {
				break;
			}
		}
		for(int i=0;i<16;i++) {
			if(graveB[i]!=null) {
				drawPiece(g,graveB[i],(int)((size/2.0)-30)+startX-((i)/8)*size-size,(int)((size/2.0)-30)+startY+(((i)%8))*size);
			}else {
				break;
			}
		}
		//AI INT
		if(con==0) {
			color=new Color(0,0,0);
		}else if(con==-1) {
			color=new Color(0,100,0);
		}else if(con==+1) {
			color=new Color(100,0,0);
		}else if(con==-2) {
			color=new Color(0,255,0);
		}else if(con==+2) {
			color=new Color(255,0,0);
		}
		g.setColor(color);
		g.fillRect(0, 0, size, size);
		
		//AI turn settings
		if(turnEnd==2){
			gui.guiFinishMove();
			turnEnd=0;
		}else if(turnEnd==1) {
			turnEnd=2;
		}
	}
	public void redraw() {
		this.repaint();
	}
	private void drawPiece(Graphics g, String piece,int x,int y) {
		Image i=t.getImage("src/Images/"+piece+".png");
		g.drawImage(i, x,y,this);
	}
	public int getLocation(double x,double y) {
		if(x>startX&&x<startX+(size*8)) {
			if(y>startY&&y<startY+(size*8)) {
				return ((int)((x-startX)/size))+((int)((y-startY)/size)*8);
			}
		}
		return-1;
		
	}
	public void toggleH() {
		if(H) {
			H=false;
		}else {
			H=true;
		}
	}
	public void highlight(int x,int y,char c) {
		if(c=='R') {
			dx=x;
			dy=y;
		}else {
			move[x][y]=true;
		}
	}
	public void highlight(int x,int y) {
		move[x][y]=true;
	}
	
	public void unHighlight() {
		dx=-1;
		dy=-1;
		for(int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				move[x][y] = false;
			}
		}
	}
	public boolean checkMoveLocation(int x, int y) {
		if(move[x][y]==true) {
			return true;
		}
		return false;
		
	}
	public void move(String[][] b) {
		board=b;
		checkGrave();
		oldBoard=copy(board);
		turnEnd=1;
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
	public void checkGrave() {
		boolean died;
		for(int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				if(oldBoard[x][y]!=null&&board[x][y]!=null) {
					if(oldBoard[x][y].charAt(0)!=board[x][y].charAt(0)) {
						if(oldBoard[x][y].charAt(0)=='W') {
							for(int i=0;i<16;i++) {
								if(graveW[i]==null) {
									graveW[i]=oldBoard[x][y];
									highlight(x,y,'R');
									break;
								}
							}
						}else {
							for(int i=0;i<16;i++) {
								if(graveB[i]==null) {
									graveB[i]=oldBoard[x][y];
									highlight(x,y,'R');
									break;
								}
							}
						}
					}
				}else {
					if(board[x][y]!=oldBoard[x][y]) {
						highlight(x,y);
					} 
				}
			}
		}
	}
	
	public void setCon(int c) {
		con=c;
	}
}
