package Chess;

public class AI {
	public char AIPlayer;
	private int P,C,KT,B,Q,BP,RP,KTP,lastValue=0,AIlevel;
	private boolean passive,ECL,ECR,AICL,AICR;
	
	public AI(int Pawn,int Castle,int Kight, int Bishop, int Queen, int BishopPair, int RookPair,int KnightPair,boolean passive,int l) {
		P=Pawn;
		C=Castle;
		KT=Kight;
		B=Bishop;
		Q=Queen;
		BP=BishopPair;
		RP=RookPair;
		KTP=KnightPair;
		this.passive=passive;
		AIlevel=l;
		
		AICL=true;
		AICR=true;
		ECL=true;
		ECR=true;
	}
	
	public int getLevel() {
		return AIlevel;
	}
	
	public String[][] AIMove(String[][] board, char player){
		int level=AIlevel;
		String[][] out=new String[8][8];
		AIPlayer=player;
		int value=2000,t;
		char e='B';
		String[][] testBoard=checkMove(board);
		if(testBoard!=null) {
			return testBoard;
		}
		if(player=='B') {
			e='W';
		}
		for(int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				if(board[x][y]!=null&&board[x][y].charAt(0)==player){
					char p=player;
					char c=board[x][y].charAt(1);
					//Pawn
					if(c=='P') {
						if(p=='W'){
							//White
							//move
							if(y>=1&&board[x][y-1]==null) {
								t=value(move(x,y,x,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR)-1;
								if(t<value) {
									value=t;
									out=move(x,y,x,y-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x,y-1,board);
								}
								if(y==6) {
									if(board[x][y-2]==null) {
										t=value(move(x,y,x,y-2,board), level-1, 2000,e,ECL,ECR,AICL,AICR)-1;
										if(t<value) {
											value=t;
											out=move(x,y,x,y-2,board);
										}else if(t==value&&(Math.random()*3)<1) {
											out=move(x,y,x,y-2,board);
										}
									}
								}
							}
							//Attack
							if(x>=1&&y>=1&&board[x-1][y-1]!=null&&board[x-1][y-1].charAt(0)!=p) {
								t=value(move(x,y,x-1,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR)-1;
								if(t<value) {
									value=t;
									out=move(x,y,x-1,y-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x-1,y-1,board);
								}
							}
							if(x<=6&&y>=1&&board[x+1][y-1]!=null&&board[x+1][y-1].charAt(0)!=p) {
								t=value(move(x,y,x+1,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR)-1;
								if(t<value) {
									value=t;
									out=move(x,y,x+1,y-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x+1,y-1,board);
								}
							}
						
						}else {
							//Black
							if(y<=6&&board[x][y+1]==null) {
								t=value(move(x,y,x,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR)-1;
								if(t<value) {
									value=t;
									out=move(x,y,x,y+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x,y+1,board);
								}
								if(y==1) {
									if(board[x][y+2]==null) {
										t=value(move(x,y,x,y+2,board), level-1, 2000,e,ECL,ECR,AICL,AICR)-1;
										if(t<value) {
											value=t;
											out=move(x,y,x,y+2,board);
										}else if(t==value&&(Math.random()*3)<1) {
											out=move(x,y,x,y+2,board);
										}
									}
								}
							}
							//Attack
							if(x>=1&&y<=6&&board[x-1][y+1]!=null&&board[x-1][y+1].charAt(0)!=p) {
								t=value(move(x,y,x-1,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR)-1;
								if(t<value) {
									value=t;
									out=move(x,y,x-1,y+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x-1,y+1,board);
								}
							}
							if(x<=6&&y<=6&&board[x+1][y+1]!=null&&board[x+1][y+1].charAt(0)!=p) {
								t=value(move(x,y,x+1,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR)-1;
								if(t<value) {
									value=t;
									out=move(x,y,x+1,y+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x+1,y+1,board);
								}
							}
						}
					}else if(c=='K'&&board[x][y].length()==3) {
						//Knight
						if(y>=2&&x>=1) {
							if(board[x-1][y-2]!=null) {
								if(board[x-1][y-2].charAt(0)!=p) {
									t=value(move(x,y,x-1,y-2,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x-1,y-2,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x-1,y-2,board);
									}
								}
							}else {
								t=value(move(x,y,x-1,y-2,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x-1,y-2,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x-1,y-2,board);
								}
							}
						}
						if(y>=2&&x<=6) {
							if(board[x+1][y-2]!=null) {
								if(board[x+1][y-2].charAt(0)!=p) {
									t=value(move(x,y,x+1,y-2,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x+1,y-2,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x+1,y-2,board);
									}
								}
							}else {
								t=value(move(x,y,x+1,y-2,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x+1,y-2,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x+1,y-2,board);
								}
							}
						}
						if(y>=1&&x>=2) {
							if(board[x-2][y-1]!=null) {
								if(board[x-2][y-1].charAt(0)!=p) {
									t=value(move(x,y,x-2,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x-2,y-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x-2,y-1,board);
									}
								}
							}else {
								t=value(move(x,y,x-2,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x-2,y-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x-2,y-1,board);
								}
							}
						}
						if(y>=1&&x<=5) {
							if(board[x+2][y-1]!=null) {
								if(board[x+2][y-1].charAt(0)!=p) {
									t=value(move(x,y,x+2,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x+2,y-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x+2,y-1,board);
									}
								}
							}else {
								t=value(move(x,y,x+2,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x+2,y-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x+2,y-1,board);
								}
							}
						}
						if(y<=6&&x>=2) {
							if(board[x-2][y+1]!=null) {
								if(board[x-2][y+1].charAt(0)!=p) {
									t=value(move(x,y,x-2,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x-2,y+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x-2,y+1,board);
									}
								}
							}else {
								t=value(move(x,y,x-2,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x-2,y+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x-2,y+1,board);
								}
							}
						}
						if(y<=6&&x<=5) {
							if(board[x+2][y+1]!=null) {
								if(board[x+2][y+1].charAt(0)!=p) {
									t=value(move(x,y,x+2,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x+2,y+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x+2,y+1,board);
									}
								}
							}else {
								t=value(move(x,y,x+2,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x+2,y+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x+2,y+1,board);
								}
							}
						}
						if(y<=5&&x>=1) {
							if(board[x-1][y+2]!=null) {
								if(board[x-1][y+2].charAt(0)!=p) {
									t=value(move(x,y,x-1,y+2,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x-1,y+2,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x-1,y+2,board);
									}
								}
							}else {
								t=value(move(x,y,x-1,y+2,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x-1,y+2,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x-1,y+2,board);
								}
							}
						}
						if(y<=5&&x<=6) {
							if(board[x+1][y+2]!=null) {
								if(board[x+1][y+2].charAt(0)!=p) {
									t=value(move(x,y,x+1,y+2,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x+1,y+2,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x+1,y+2,board);
									}
								}
							}else {
								t=value(move(x,y,x+1,y+2,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x+1,y+2,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x+1,y+2,board);
								}
							}
						}
					}else if(c=='K') {
						//King
						if(y>=1&&x>=1) {
							if(board[x-1][y-1]!=null) {
								if(board[x-1][y-1].charAt(0)!=p) {
									t=value(move(x,y,x-1,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x-1,y-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x-1,y-1,board);
									}
								}
							}else {
								t=value(move(x,y,x-1,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x-1,y-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x-1,y-1,board);
								}
							}
						}
						if(y>=1) {
							if(board[x][y-1]!=null) {
								if(board[x][y-1].charAt(0)!=p) {
									t=value(move(x,y,x,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x,y-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x,y-1,board);
									}
								}
							}else {
								t=value(move(x,y,x,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x,y-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x,y-1,board);
								}
							}
						}
						if(y>=1&&x<=6) {
							if(board[x+1][y-1]!=null) {
								if(board[x+1][y-1].charAt(0)!=p) {
									t=value(move(x,y,x+1,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x+1,y-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x+1,y-1,board);
									}
								}
							}else {
								t=value(move(x,y,x+1,y-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x+1,y-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x+1,y-1,board);
								}
							}
						}
						if(x>=1) {
							if(board[x-1][y]!=null) {
								if(board[x-1][y].charAt(0)!=p) {
									t=value(move(x,y,x-1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x-1,y,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x-1,y,board);
									}
								}
							}else {
								t=value(move(x,y,x-1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x-1,y,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x-1,y,board);
								}
							}
						}
						if(x<=6) {
							if(board[x+1][y]!=null) {
								if(board[x+1][y].charAt(0)!=p) {
									t=value(move(x,y,x+1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x+1,y,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x+1,y,board);
									}
								}
							}else {
								t=value(move(x,y,x+1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x+1,y,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x+1,y,board);
								}
							}
						}
						if(y<=6&&x>=1) {
							if(board[x-1][y+1]!=null) {
								if(board[x-1][y+1].charAt(0)!=p) {
									t=value(move(x,y,x-1,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x-1,y+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x-1,y+1,board);
									}
								}
							}else {
								t=value(move(x,y,x-1,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x-1,y+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x-1,y+1,board);
								}
							}
						}
						if(y<=6&&x<=6) {
							if(board[x+1][y+1]!=null) {
								if(board[x+1][y+1].charAt(0)!=p) {
									t=value(move(x,y,x+1,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x+1,y+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x+1,y+1,board);
									}
								}
							}else {
								t=value(move(x,y,x+1,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x+1,y+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x+1,y+1,board);
								}
							}
						}
						if(y<=6) {
							if(board[x][y+1]!=null) {
								if(board[x][y+1].charAt(0)!=p) {
									t=value(move(x,y,x,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x,y+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x,y+1,board);
									}
								}
							}else {
								t=value(move(x,y,x,y+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x,y+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x,y+1,board);
								}
							}
						}
					}else if(c=='Q') {
						//Queen
						for(int iy=y;iy>=1;iy--){
							if(board[x][iy-1]!=null) {
								if(board[x][iy-1].charAt(0)!=p) {
									t=value(move(x,y,x,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x,iy-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x,iy-1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,x,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x,iy-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x,iy-1,board);
								}
							}
						}
						for(int iy=y;iy<=6;iy++){
							if(board[x][iy+1]!=null) {
								if(board[x][iy+1].charAt(0)!=p) {
									t=value(move(x,y,x,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x,iy+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x,iy+1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,x,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x,iy+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x,iy+1,board);
								}
							}
						}
						for(int ix=x;ix<=6;ix++){
							if(board[ix+1][y]!=null) {
								if(board[ix+1][y].charAt(0)!=p) {
									t=value(move(x,y,ix+1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix+1,y,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix+1,y,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix+1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix+1,y,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix+1,y,board);
								}
							}
						}
						for(int ix=x;ix>=1;ix--){
							if(board[ix-1][y]!=null) {
								if(board[ix-1][y].charAt(0)!=p) {
									t=value(move(x,y,ix-1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix-1,y,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix-1,y,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix-1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix-1,y,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix-1,y,board);
								}
							}
						}
						for(int ix=x,iy=y;ix>=1&&iy>=1;ix--,iy--){
							if(board[ix-1][iy-1]!=null) {
								if(board[ix-1][iy-1].charAt(0)!=p) {
									t=value(move(x,y,ix-1,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix-1,iy-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix-1,iy-1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix-1,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix-1,iy-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix-1,iy-1,board);
								}
							}
						}
						for(int ix=x,iy=y;ix<=6&&iy>=1;ix++,iy--){
							if(board[ix+1][iy-1]!=null) {
								if(board[ix+1][iy-1].charAt(0)!=p) {
									t=value(move(x,y,ix+1,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix+1,iy-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix+1,iy-1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix+1,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix+1,iy-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix+1,iy-1,board);
								}
							}
						}
						for(int ix=x,iy=y;ix<=6&&iy<=6;ix++,iy++){
							if(board[ix+1][iy+1]!=null) {
								if(board[ix+1][iy+1].charAt(0)!=p) {
									t=value(move(x,y,ix+1,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix+1,iy+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix+1,iy+1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix+1,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix+1,iy+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix+1,iy+1,board);
								}
							}
						}
						for(int ix=x,iy=y;ix>=1&&iy<=6;ix--,iy++){
							if(board[ix-1][iy+1]!=null) {
								if(board[ix-1][iy+1].charAt(0)!=p) {
									t=value(move(x,y,ix-1,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix-1,iy+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix-1,iy+1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix-1,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix-1,iy+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix-1,iy+1,board);
								}
							}
						}
					}else if(c=='C') {
						//Castle
						for(int iy=y;iy>=1;iy--){
							if(board[x][iy-1]!=null) {
								if(board[x][iy-1].charAt(0)!=p) {
									t=value(move(x,y,x,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x,iy-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x,iy-1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,x,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x,iy-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x,iy-1,board);
								}
							}
						}
						for(int iy=y;iy<=6;iy++){
							if(board[x][iy+1]!=null) {
								if(board[x][iy+1].charAt(0)!=p) {
									t=value(move(x,y,x,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,x,iy+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,x,iy+1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,x,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,x,iy+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,x,iy+1,board);
								}
							}
						}
						for(int ix=x;ix<=6;ix++){
							if(board[ix+1][y]!=null) {
								if(board[ix+1][y].charAt(0)!=p) {
									t=value(move(x,y,ix+1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix+1,y,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix+1,y,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix+1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix+1,y,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix+1,y,board);
								}
							}
						}
						for(int ix=x;ix>=1;ix--){
							if(board[ix-1][y]!=null) {
								if(board[ix-1][y].charAt(0)!=p) {
									t=value(move(x,y,ix-1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix-1,y,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix-1,y,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix-1,y,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix-1,y,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix-1,y,board);
								}
							}
						}
					}else if(c=='R') {
						//Rook
						for(int ix=x,iy=y;ix>=1&&iy>=1;ix--,iy--){
							if(board[ix-1][iy-1]!=null) {
								if(board[ix-1][iy-1].charAt(0)!=p) {
									t=value(move(x,y,ix-1,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix-1,iy-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix-1,iy-1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix-1,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix-1,iy-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix-1,iy-1,board);
								}
							}
						}
						for(int ix=x,iy=y;ix<=6&&iy>=1;ix++,iy--){
							if(board[ix+1][iy-1]!=null) {
								if(board[ix+1][iy-1].charAt(0)!=p) {
									t=value(move(x,y,ix+1,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix+1,iy-1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix+1,iy-1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix+1,iy-1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix+1,iy-1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix+1,iy-1,board);
								}
							}
						}
						for(int ix=x,iy=y;ix<=6&&iy<=6;ix++,iy++){
							if(board[ix+1][iy+1]!=null) {
								if(board[ix+1][iy+1].charAt(0)!=p) {
									t=value(move(x,y,ix+1,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix+1,iy+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix+1,iy+1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix+1,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix+1,iy+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix+1,iy+1,board);
								}
							}
						}
						for(int ix=x,iy=y;ix>=1&&iy<=6;ix--,iy++){
							if(board[ix-1][iy+1]!=null) {
								if(board[ix-1][iy+1].charAt(0)!=p) {
									t=value(move(x,y,ix-1,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
									if(t<value) {
										value=t;
										out=move(x,y,ix-1,iy+1,board);
									}else if(t==value&&(Math.random()*3)<1) {
										out=move(x,y,ix-1,iy+1,board);
									}
									break;
								}else {
									break;
								}
							}else {
								t=value(move(x,y,ix-1,iy+1,board), level-1, 2000,e,ECL,ECR,AICL,AICR);
								if(t<value) {
									value=t;
									out=move(x,y,ix-1,iy+1,board);
								}else if(t==value&&(Math.random()*3)<1) {
									out=move(x,y,ix-1,iy+1,board);
								}
							}
						}
					}
				}
			}
		}
		lastValue=value;
		return out;
		
	}
	
	public int value(String[][] board, int level, int Min,char player,boolean ecl,boolean ecr,boolean aicl,boolean aicr) {
		int value=-2000,nm=2000;
		if(level<=0) {
			value=Evaluate(board,player);
		}else {
			if(8500<=Evaluate(board,player)||-8500>=Evaluate(board,player)) {
				return Evaluate(board,player);
			}
			//AI thinking
			
			int t;
			char e='B';
			if(player=='B') {
				e='W';
			}
			for(int y=0;y<8;y++) {
				for(int x=0;x<8;x++) {
					if(x+y>=1) {
						nm=-value;
					}
					if(board[x][y]!=null&&board[x][y].charAt(0)==player){
						char p=player;
						char c=board[x][y].charAt(1);
						//Pawn
						if(c=='P') {
							if(p=='W'){
								//White
								//move
								if(y>=1&&board[x][y-1]==null) {
									t=-value(move(x,y,x,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(player==AIPlayer) {
										t+=1;
									}
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
									if(y==6) {
										if(board[x][y-2]==null) {
											t=-value(move(x,y,x,y-2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
											if(player==AIPlayer) {
												t+=1;
											}
											if(t>value) {
												value=t;
												if(value>Min) {
													return value;
												}
											}
										}
									}
								}
								//Attack
								if(x>=1&&y>=1&&board[x-1][y-1]!=null&&board[x-1][y-1].charAt(0)!=p) {
									t=-value(move(x,y,x-1,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(player==AIPlayer) {
										t+=1;
									}
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
								if(x<=6&&y>=1&&board[x+1][y-1]!=null&&board[x+1][y-1].charAt(0)!=p) {
									t=-value(move(x,y,x+1,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(player==AIPlayer) {
										t+=1;
									}
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							
							}else {
								//Black
								if(y<=6&&board[x][y+1]==null) {
									t=-value(move(x,y,x,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(player==AIPlayer) {
										t+=1;
									}
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
									if(y==1) {
										if(board[x][y+2]==null) {
											t=-value(move(x,y,x,y+2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
											if(player==AIPlayer) {
												t+=1;
											}
											if(t>value) {
												value=t;
												if(value>Min) {
													return value;
												}
											}
										}
									}
								}
								//Attack
								if(x>=1&&y<=6&&board[x-1][y+1]!=null&&board[x-1][y+1].charAt(0)!=p) {
									t=-value(move(x,y,x-1,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(player==AIPlayer) {
										t+=1;
									}
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
								if(x<=6&&y<=6&&board[x+1][y+1]!=null&&board[x+1][y+1].charAt(0)!=p) {
									t=-value(move(x,y,x+1,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(player==AIPlayer) {
										t+=1;
									}
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
						}else if(c=='K'&&board[x][y].length()==3) {
							//Knight
							if(y>=2&&x>=1) {
								if(board[x-1][y-2]!=null) {
									if(board[x-1][y-2].charAt(0)!=p) {
										t=-value(move(x,y,x-1,y-2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x-1,y-2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y>=2&&x<=6) {
								if(board[x+1][y-2]!=null) {
									if(board[x+1][y-2].charAt(0)!=p) {
										t=-value(move(x,y,x+1,y-2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x+1,y-2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y>=1&&x>=2) {
								if(board[x-2][y-1]!=null) {
									if(board[x-2][y-1].charAt(0)!=p) {
										t=-value(move(x,y,x-2,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x-2,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y>=1&&x<=5) {
								if(board[x+2][y-1]!=null) {
									if(board[x+2][y-1].charAt(0)!=p) {
										t=-value(move(x,y,x+2,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x+2,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y<=6&&x>=2) {
								if(board[x-2][y+1]!=null) {
									if(board[x-2][y+1].charAt(0)!=p) {
										t=-value(move(x,y,x-2,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x-2,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y<=6&&x<=5) {
								if(board[x+2][y+1]!=null) {
									if(board[x+2][y+1].charAt(0)!=p) {
										t=-value(move(x,y,x+2,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x+2,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y<=5&&x>=1) {
								if(board[x-1][y+2]!=null) {
									if(board[x-1][y+2].charAt(0)!=p) {
										t=-value(move(x,y,x-1,y+2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x-1,y+2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y<=5&&x<=6) {
								if(board[x+1][y+2]!=null) {
									if(board[x+1][y+2].charAt(0)!=p) {
										t=-value(move(x,y,x+1,y+2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x+1,y+2,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
						}else if(c=='K') {
							//King
							if(y>=1&&x>=1) {
								if(board[x-1][y-1]!=null) {
									if(board[x-1][y-1].charAt(0)!=p) {
										t=-value(move(x,y,x-1,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x-1,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y>=1) {
								if(board[x][y-1]!=null) {
									if(board[x][y-1].charAt(0)!=p) {
										t=-value(move(x,y,x,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y>=1&&x<=6) {
								if(board[x+1][y-1]!=null) {
									if(board[x+1][y-1].charAt(0)!=p) {
										t=-value(move(x,y,x+1,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x+1,y-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(x>=1) {
								if(board[x-1][y]!=null) {
									if(board[x-1][y].charAt(0)!=p) {
										t=-value(move(x,y,x-1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x-1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(x<=6) {
								if(board[x+1][y]!=null) {
									if(board[x+1][y].charAt(0)!=p) {
										t=-value(move(x,y,x+1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x+1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y<=6&&x>=1) {
								if(board[x-1][y+1]!=null) {
									if(board[x-1][y+1].charAt(0)!=p) {
										t=-value(move(x,y,x-1,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x-1,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y<=6&&x<=6) {
								if(board[x+1][y+1]!=null) {
									if(board[x+1][y+1].charAt(0)!=p) {
										t=-value(move(x,y,x+1,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x+1,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							if(y<=6) {
								if(board[x][y+1]!=null) {
									if(board[x][y+1].charAt(0)!=p) {
										t=-value(move(x,y,x,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
									}
								}else {
									t=-value(move(x,y,x,y+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
						}else if(c=='Q') {
							//Queen
							for(int iy=y;iy>=1;iy--){
								if(board[x][iy-1]!=null) {
									if(board[x][iy-1].charAt(0)!=p) {
										t=-value(move(x,y,x,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,x,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int iy=y;iy<=6;iy++){
								if(board[x][iy+1]!=null) {
									if(board[x][iy+1].charAt(0)!=p) {
										t=-value(move(x,y,x,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,x,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x;ix<=6;ix++){
								if(board[ix+1][y]!=null) {
									if(board[ix+1][y].charAt(0)!=p) {
										t=-value(move(x,y,ix+1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix+1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x;ix>=1;ix--){
								if(board[ix-1][y]!=null) {
									if(board[ix-1][y].charAt(0)!=p) {
										t=-value(move(x,y,ix-1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix-1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x,iy=y;ix>=1&&iy>=1;ix--,iy--){
								if(board[ix-1][iy-1]!=null) {
									if(board[ix-1][iy-1].charAt(0)!=p) {
										t=-value(move(x,y,ix-1,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix-1,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x,iy=y;ix<=6&&iy>=1;ix++,iy--){
								if(board[ix+1][iy-1]!=null) {
									if(board[ix+1][iy-1].charAt(0)!=p) {
										t=-value(move(x,y,ix+1,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix+1,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x,iy=y;ix<=6&&iy<=6;ix++,iy++){
								if(board[ix+1][iy+1]!=null) {
									if(board[ix+1][iy+1].charAt(0)!=p) {
										t=-value(move(x,y,ix+1,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix+1,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x,iy=y;ix>=1&&iy<=6;ix--,iy++){
								if(board[ix-1][iy+1]!=null) {
									if(board[ix-1][iy+1].charAt(0)!=p) {
										t=-value(move(x,y,ix-1,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix-1,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
						}else if(c=='C') {
							//Castle
							for(int iy=y;iy>=1;iy--){
								if(board[x][iy-1]!=null) {
									if(board[x][iy-1].charAt(0)!=p) {
										t=-value(move(x,y,x,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,x,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int iy=y;iy<=6;iy++){
								if(board[x][iy+1]!=null) {
									if(board[x][iy+1].charAt(0)!=p) {
										t=-value(move(x,y,x,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,x,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x;ix<=6;ix++){
								if(board[ix+1][y]!=null) {
									if(board[ix+1][y].charAt(0)!=p) {
										t=-value(move(x,y,ix+1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix+1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x;ix>=1;ix--){
								if(board[ix-1][y]!=null) {
									if(board[ix-1][y].charAt(0)!=p) {
										t=-value(move(x,y,ix-1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix-1,y,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
						}else if(c=='R') {
							//Rook
							for(int ix=x,iy=y;ix>=1&&iy>=1;ix--,iy--){
								if(board[ix-1][iy-1]!=null) {
									if(board[ix-1][iy-1].charAt(0)!=p) {
										t=-value(move(x,y,ix-1,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix-1,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x,iy=y;ix<=6&&iy>=1;ix++,iy--){
								if(board[ix+1][iy-1]!=null) {
									if(board[ix+1][iy-1].charAt(0)!=p) {
										t=-value(move(x,y,ix+1,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix+1,iy-1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x,iy=y;ix<=6&&iy<=6;ix++,iy++){
								if(board[ix+1][iy+1]!=null) {
									if(board[ix+1][iy+1].charAt(0)!=p) {
										t=-value(move(x,y,ix+1,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix+1,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
							for(int ix=x,iy=y;ix>=1&&iy<=6;ix--,iy++){
								if(board[ix-1][iy+1]!=null) {
									if(board[ix-1][iy+1].charAt(0)!=p) {
										t=-value(move(x,y,ix-1,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
										if(t>value) {
											value=t;
											if(value>Min) {
												return value;
											}
										}
										break;
									}else {
										break;
									}
								}else {
									t=-value(move(x,y,ix-1,iy+1,board), level-1, nm,e,ecl,ecr,aicl,aicr);
									if(t>value) {
										value=t;
										if(value>Min) {
											return value;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return value;
		
	}
	
	public int Evaluate(String[][]board,char player) {
		int m=-1,out=0,rps=0,rpo=0,ktps=0,ktpo=0,bps=0,bpo=0,extra=0;
		boolean dead=true;
		for(int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				if(board[x][y]!=null) {
					if(player==board[x][y].charAt(0)) {
						m=1;
					}else {
						m=-1;
					}
					
					if(passive) {
						if(AIPlayer==player) {
							if(AIPlayer==board[x][y].charAt(0)) {
								extra++;
							}
						}else {
							if(AIPlayer==board[x][y].charAt(0)) {
								extra--;
							}
						}
					}
					if(board[x][y].substring(1).equals("K")) {
						out+=m*10000;
					}else if(board[x][y].substring(1).equals("Q")) {
						out+=m*Q;
					}else if(board[x][y].substring(1).equals("R")) {
						out+=m*B;
						if(m==-1) {
							bpo++;
						}else {
							bps++;
						}
					}else if(board[x][y].substring(1).equals("KT")) {
						out+=m*KT;
						if(m==-1) {
							ktpo++;
						}else {
							ktps++;
						}
					}else if(board[x][y].substring(1).equals("C")) {
						out+=m*C;
						if(m==-1) {
							rpo++;
						}else {
							rps++;
						}
					}else if(board[x][y].substring(1).equals("P")) {
						out+=m*P;
					}
				}
			}
		}
		if(bpo==2) {
			out+=-(((-B)*2)+BP*2);
		}
		if(bps==2) {
			out+=(((-B)*2)+BP*2);
		}
		if(ktpo==2) {
			out+=-(((-KT)*2)+KTP*2);
		}
		if(ktps==2) {
			out+=(((-KT)*2)+KTP*2);
		}
		if(rpo==2) {
			out+=-(((-C)*2)+RP*2);
		}
		if(rps==2) {
			out+=(((-C)*2)+RP*2);
		}
		out+=extra;
		return out;
	}
	
	private String[][] move(int x,int y, int nx, int ny, String[][] board){
		String[][] out=copy(board);
		if(out[x][y].charAt(1)=='P') {
			if(out[x][y].charAt(0)=='W') {
				if(ny==0) {
					out[nx][ny]="BQ";
				}else {
					out[nx][ny]=board[x][y];
				}
			}else {
				if(ny==7) {
					out[nx][ny]="BQ";
				}else {
					out[nx][ny]=board[x][y];
				}
			}
		}else {
			out[nx][ny]=board[x][y];
		}
		out[x][y]=null;
		return out;
	}
	
	public int getCon() {
		return lastValue;
	}
	
	private static String[][] copy(String[][]board){
		String[][] out=new String[8][8];
		for(int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				out[x][y]=board[x][y];
			}
		}
		return out;
		
	}
	
	public boolean getCheck(String[][] b,char p,boolean ecl,boolean ecr,boolean aicl,boolean aicr) {
		int i=value(b, 0, 2000, p,ecl,ecr,aicl,aicr);
		if(8500<=i) {
			return true;
		}
		return false;
	}
	
	public boolean getLCastle(String[][] b,char p,boolean ecl,boolean ecr,boolean aicl,boolean aicr) {
		String[][] b2=copy(b);
		if(p=='W') {
			if(b2[3][7]==null&&b2[2][7]==null&&b2[1][7]==null) {
				if(!getCheck(b2,'B',ecl,ecr,aicl,aicr)) {
					b2[4][7]=null;
					b2[3][7]="WK";
					if(!getCheck(b2,'B',ecl,ecr,aicl,aicr)){
						b2[3][7]=null;
						b2[2][7]="WK";
						if(!getCheck(b2,'B',ecl,ecr,aicl,aicr)) {
							return true;
						}
					}
				}
			}
		}else {
			if(b2[3][0]==null&&b2[2][0]==null&&b2[1][0]==null) {
				if(!getCheck(b2,'W',ecl,ecr,aicl,aicr)) {
					b2[4][0]=null;
					b2[3][0]="BK";
					if(!getCheck(b2,'W',ecl,ecr,aicl,aicr)){
						b2[3][0]=null;
						b2[2][0]="BK";
						if(!getCheck(b2,'W',ecl,ecr,aicl,aicr)) {
							return true;
						}
					}
				}
			}	
		}
		return false;
	}
	
	public boolean getRCastle(String[][] b,char p,boolean ecl,boolean ecr,boolean aicl,boolean aicr) {
		String[][] b2=copy(b);
		if(p=='W') {
			if(b2[5][7]==null&&b2[6][7]==null) {
				if(!getCheck(b2,'B',ecl,ecr,aicl,aicr)) {
					b2[4][7]=null;
					b2[5][7]="WK";
					if(!getCheck(b2,'B',ecl,ecr,aicl,aicr)){
						b2[5][7]=null;
						b2[6][7]="WK";
						if(!getCheck(b2,'B',ecl,ecr,aicl,aicr)) {
							return true;
						}
					}
				}
			}
		}else {
			if(b2[5][0]==null&&b2[6][0]==null) {
				if(!getCheck(b2,'W',ecl,ecr,aicl,aicr)) {
					b2[4][0]=null;
					b2[5][0]="BK";
					if(!getCheck(b2,'W',ecl,ecr,aicl,aicr)){
						b2[5][0]=null;
						b2[6][0]="BK";
						if(!getCheck(b2,'W',ecl,ecr,aicl,aicr)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public String[][] checkMove(String[][] b){
		String[][] out=new String[8][8];
		return null;
		
	}
}
