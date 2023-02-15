package view;

import components.ChessGridComponent;
import model.ChessPiece;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Robot;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Date;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;
    public int[][] cb = new int[8][8];//white=1,black=-1
    int[][][] recordcb = new int[64][8][8];
    int steps = 0;
    int[][] newcb = new int[8][8];//white=1,black=2
    int[][][] newrecordcb = new int[64][8][8];

    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);//可聚焦，选择
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);

        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess

        repaint();
    }

    /**
     * set an empty chessboard
     */
    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
        refreshnext(ChessPiece.BLACK);//初始化next
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        //todo: complete this method
        int color;
        if(currentPlayer==ChessPiece.BLACK){
            color=-1;
        }else {
            color=1;
        }
        if(check(color,row,col)){
            return true;
        }else{
            return false;
        }
    }

    public boolean canClickGridForModeFalse(int row,int col,ChessPiece currentPlayer){
        if(cb[row][col]==0){
            return true;
        }
        return false;
    }

    public void updatecb(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(chessGrids[i][j].getChessPiece()==null){
                    cb[i][j]=0;
                }
                if(chessGrids[i][j].getChessPiece()==ChessPiece.WHITE){
                    cb[i][j]=1;
                }
                if(chessGrids[i][j].getChessPiece()==ChessPiece.BLACK){
                    cb[i][j]=-1;
                }
            }
        }
    }

    public boolean check(int c,int a,int b) {
        updatecb();
        int x = a,y = b;
        if(cb[x][y]==1||cb[x][y]==-1){return false;}
        int q=0,w=0,e=0,r=0,t=0,o=0,u=0,p=0;
        if(c==1){
            for (int i = 1; i <=8 ; i++) {
                if(checkin(x+i,y)){
                    if(cb[x+i][y]==-1){
                        q++;
                        continue;
                    }
                    if(q==0){break;}
                    if(cb[x+i][y]==0){
                        break;
                    }
                    if(cb[x+i][y]==1&&q!=0){
                        return true;
                    }
                }else{break;}
            }
            for (int i = 1; i <=9 ; i++) {
                if(checkin(x,y+i)){if(cb[x][y+i]==-1){
                    w++;
                    continue;
                }if(w==0){break;}
                    if(cb[x][y+i]==0){
                        break;
                    }if(cb[x][y+i]==1&&w!=0){
                        return true;
                    }}else{break;}
            }
            for (int i = 1; i <=9 ; i++) {
                if(checkin(x-i,y)){
                    if(cb[x-i][y]==-1){e++;continue;}if(e==0){break;}
                    if(cb[x-i][y]==0){break;}
                    if(cb[x-i][y]==1&&e!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <=9 ; i++) {
                if(checkin(x,y-i)){
                    if(cb[x][y-i]==-1){r++;continue;}if(r==0){break;}
                    if(cb[x][y-i]==0){break;}
                    if(cb[x][y-i]==1&&r!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <= 9; i++) {
                if(checkin(x+i,y+i)){
                    if(cb[x+i][y+i]==-1){t++;continue;}if(t==0){break;}
                    if(cb[x+i][y+i]==0){break;}
                    if(cb[x+i][y+i]==1&&t!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <= 9; i++) {
                if(checkin(x+i,y-i)){
                    if(cb[x+i][y-i]==-1){u++;continue;}if(u==0){break;}
                    if(cb[x+i][y-i]==0){break;}
                    if(cb[x+i][y-i]==1&&u!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <= 9; i++) {
                if(checkin(x-i,y+i)){
                    if(cb[x-i][y+i]==-1){o++;continue;}if(o==0){break;}
                    if(cb[x-i][y+i]==0){break;}
                    if(cb[x-i][y+i]==1&&o!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <= 9; i++) {
                if(checkin(x-i,y-i)){
                    if(cb[x-i][y-i]==-1){p++;continue;}if(p==0){break;}
                    if(cb[x-i][y-i]==0){break;}
                    if(cb[x-i][y-i]==1&&p!=0){return true;}
                }else{break;}
            }
        }else{
            for (int i = 1; i <=9 ; i++) {
                if(checkin(x+i,y)){if(cb[x+i][y]==1){
                    q++;
                    continue;
                }if(q==0){break;}
                    if(cb[x+i][y]==0){
                        break;
                    }if(cb[x+i][y]==-1&&q!=0){
                        return true;
                    }}else{break;}
            }
            for (int i = 1; i <=9 ; i++) {
                if(checkin(x,y+i)){if(cb[x][y+i]==1){
                    w++;
                    continue;
                }if(w==0){break;}
                    if(cb[x][y+i]==0){
                        break;
                    }if(cb[x][y+i]==-1&&w!=0){
                        return true;
                    }}else{break;}
            }
            for (int i = 1; i <=9 ; i++) {
                if(checkin(x-i,y)){
                    if(cb[x-i][y]==1){e++;continue;}if(e==0){break;}
                    if(cb[x-i][y]==0){break;}
                    if(cb[x-i][y]==-1&&e!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <=9 ; i++) {
                if(checkin(x,y-i)){
                    if(cb[x][y-i]==1){r++;continue;}if(r==0){break;}
                    if(cb[x][y-i]==0){break;}
                    if(cb[x][y-i]==-1&&r!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <= 9; i++) {
                if(checkin(x+i,y+i)){
                    if(cb[x+i][y+i]==1){t++;continue;}if(t==0){break;}
                    if(cb[x+i][y+i]==0){break;}
                    if(cb[x+i][y+i]==-1&&t!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <= 9; i++) {
                if(checkin(x+i,y-i)){
                    if(cb[x+i][y-i]==1){u++;continue;}if(u==0){break;}
                    if(cb[x+i][y-i]==0){break;}
                    if(cb[x+i][y-i]==-1&&u!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <= 9; i++) {
                if(checkin(x-i,y+i)){
                    if(cb[x-i][y+i]==1){o++;continue;}if(o==0){break;}
                    if(cb[x-i][y+i]==0){break;}
                    if(cb[x-i][y+i]==-1&&o!=0){return true;}
                }else{break;}
            }
            for (int i = 1; i <= 9; i++) {
                if(checkin(x-i,y-i)){
                    if(cb[x-i][y-i]==1){p++;continue;}if(p==0){break;}
                    if(cb[x-i][y-i]==0){break;}
                    if(cb[x-i][y-i]==-1&&p!=0){return true;}
                }else{break;}
            }
        }return false;//be careful
    }

    public boolean checkin(int x,int y){
        return !(x<0||y<0||x>=8||y>=8);
    }

    public void fanzhuan(int c,int x,int y){
        updatecb();
        int q=0,w=0,e=0,r=0,t=0,o=0,u=0,p=0;
        if(c==1){
            for (int i = 1; i < 9; i++) {
                if(checkin(x+i,y)){
                    if(cb[x+i][y]==-1){
                        q++;
                        continue;
                    }
                    if(cb[x+i][y]==0){
                        break;
                    }
                    if(cb[x+i][y]==1){
                        for (int j = 0; j <= q; j++) {
                            cb[x+j][y]=1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x-i,y)){
                    if(cb[x-i][y]==-1){
                        w++;
                        continue;
                    }
                    if(cb[x-i][y]==0){
                        break;
                    }
                    if(cb[x-i][y]==1){
                        for (int j = 0; j <= w; j++) {
                            cb[x-j][y]=1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x,y+i)){
                    if(cb[x][y+i]==-1){
                        e++;
                        continue;
                    }
                    if(cb[x][y+i]==0){
                        break;
                    }
                    if(cb[x][y+i]==1){
                        for (int j = 0; j <= e; j++) {
                            cb[x][y+j]=1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x,y-i)){
                    if(cb[x][y-i]==-1){
                        r++;
                        continue;
                    }
                    if(cb[x][y-i]==0){
                        break;
                    }
                    if(cb[x][y-i]==1){
                        for (int j = 0; j <= r; j++) {
                            cb[x][y-j]=1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x+i,y+i)){
                    if(cb[x+i][y+i]==-1){
                        t++;
                        continue;
                    }
                    if(cb[x+i][y+i]==0){
                        break;
                    }
                    if(cb[x+i][y+i]==1){
                        for (int j = 0; j <= t; j++) {
                            cb[x+j][y+j]=1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x+i,y-i)){
                    if(cb[x+i][y-i]==-1){
                        u++;
                        continue;
                    }
                    if(cb[x+i][y-i]==0){
                        break;
                    }
                    if(cb[x+i][y-i]==1){
                        for (int j = 0; j <= u; j++) {
                            cb[x+j][y-j]=1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x-i,y+i)){
                    if(cb[x-i][y+i]==-1){
                        o++;
                        continue;
                    }
                    if(cb[x-i][y+i]==0){
                        break;
                    }
                    if(cb[x-i][y+i]==1){
                        for (int j = 0; j <= o; j++) {
                            cb[x-j][y+j]=1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x-i,y-i)){
                    if(cb[x-i][y-i]==-1){
                        p++;
                        continue;
                    }
                    if(cb[x-i][y-i]==0){
                        break;
                    }
                    if(cb[x-i][y-i]==1){
                        for (int j = 0; j <= p; j++) {
                            cb[x-j][y-j]=1;
                        }
                        break;
                    }
                }}
        }else{
            for (int i = 1; i < 9; i++) {
                if(checkin(x+i,y)){
                    if(cb[x+i][y]==1){
                        q++;
                        continue;
                    }
                    if(cb[x+i][y]==0){
                        break;
                    }
                    if(cb[x+i][y]==-1){
                        for (int j = 0; j <= q; j++) {
                            cb[x+j][y]=-1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x-i,y)){
                    if(cb[x-i][y]==1){
                        w++;
                        continue;
                    }
                    if(cb[x-i][y]==0){
                        break;
                    }
                    if(cb[x-i][y]==-1){
                        for (int j = 0; j <= w; j++) {
                            cb[x-j][y]=-1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x,y+i)){
                    if(cb[x][y+i]==1){
                        e++;
                        continue;
                    }
                    if(cb[x][y+i]==0){
                        break;
                    }
                    if(cb[x][y+i]==-1){
                        for (int j = 0; j <= e; j++) {
                            cb[x][y+j]=-1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x,y-i)){
                    if(cb[x][y-i]==1){
                        r++;
                        continue;
                    }
                    if(cb[x][y-i]==0){
                        break;
                    }
                    if(cb[x][y-i]==-1){
                        for (int j = 0; j <= r; j++) {
                            cb[x][y-j]=-1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x+i,y+i)){
                    if(cb[x+i][y+i]==1){
                        t++;
                        continue;
                    }
                    if(cb[x+i][y+i]==0){
                        break;
                    }
                    if(cb[x+i][y+i]==-1){
                        for (int j = 0; j <= t; j++) {
                            cb[x+j][y+j]=-1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x+i,y-i)){
                    if(cb[x+i][y-i]==1){
                        u++;
                        continue;
                    }
                    if(cb[x+i][y-i]==0){
                        break;
                    }
                    if(cb[x+i][y-i]==-1){
                        for (int j = 0; j <= u; j++) {
                            cb[x+j][y-j]=-1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x-i,y+i)){
                    if(cb[x-i][y+i]==1){
                        o++;
                        continue;
                    }
                    if(cb[x-i][y+i]==0){
                        break;
                    }
                    if(cb[x-i][y+i]==-1){
                        for (int j = 0; j <= o; j++) {
                            cb[x-j][y+j]=-1;
                        }
                        break;
                    }
                }}
            for (int i = 1; i < 9; i++) {
                if(checkin(x-i,y-i)){
                    if(cb[x-i][y-i]==1){
                        p++;
                        continue;
                    }
                    if(cb[x-i][y-i]==0){
                        break;
                    }
                    if(cb[x-i][y-i]==-1){
                        for (int j = 0; j <= p; j++) {
                            cb[x-j][y-j]=-1;
                        }
                        break;
                    }
                }}
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cb[i][j]==1){
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                }
                if(cb[i][j]==-1){
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                }
                if(cb[i][j]==0){
                    chessGrids[i][j].setChessPiece(null);
                }
            }
        }
        steps++;record();
        refresh();
    }

    public void refresh() {
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                chessGrids[i][j].paintComponent(chessGrids[i][j].getGraphics());
            }
        }

        if(isEnd()){
            //弹出窗口
        }
    }

    public void reStart(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessGrids[i][j].setChessPiece(null);
            }
        }
        initialGame();
        refresh();
        refreshrecord();
    }

    public boolean isEnd(){
        int white = 0,black = 0,kong = 0;
        updatecb();
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                if(cb[i][j]==1){
                    white++;
                }
                if(cb[i][j]==-1){
                    black++;
                }
                if(cb[i][j]==0){
                    kong++;
                }
            }
        }
        if(black==0||white==0||kong==0){
            return true;
        }
        return false;
    }

    public int getWhiteScore(){
        int white = 0;
        updatecb();
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                if(cb[i][j]==1){
                    white++;
                }
            }
        }
        return white;
    }

    public int getBlackScore(){
        int black = 0;
        updatecb();
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                if(cb[i][j]==-1){
                    black++;
                }
            }
        }
        return black;
    }

    public void refreshnext(ChessPiece currentPlayer){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(canClickGrid(i,j,currentPlayer)){
                    chessGrids[i][j].isnext = true;
                }else {
                    chessGrids[i][j].isnext = false;
                }
            }
        }
        if(currentPlayer.equals(ChessPiece.RED)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    chessGrids[i][j].isnext = false;
                }
            }
        }
    }

    public void record(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                recordcb[steps][i][j] = cb[i][j];
            }
        }
    }

    public void remake(){
        if(steps==1){
            reStart();refreshrecord();
        }else{
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cb[i][j] = recordcb[steps-1][i][j];
            }
        }
        if(steps!=0){
            steps--;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cb[i][j]==1){
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                }
                if(cb[i][j]==-1){
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                }
                if(cb[i][j]==0){
                    chessGrids[i][j].setChessPiece(null);
                }
            }
        }
        refreshnext(GameFrame.controller.getCurrentPlayer());

        }
        refresh();
        repaint();

    }

    public void refreshrecord(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                recordcb[0][i][j]=0;
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                recordcb[1][i][j]=0;
            }
        }
        steps=0;
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                recordcb[2][i][j]=0;
//            }
//        }
    }
    
    public String toString(){
        updatecb();
        setNewcb();
        setNewRecordcb();
        String answer = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                answer = answer + String.valueOf(newcb[i][j]);
            }
        }
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    answer = answer + String.valueOf(newrecordcb[i][j][k]);
                }
            }
        }
        answer = answer + String.valueOf(steps);
        return answer;
    }

    public void setcbAndRecord(String str){
        char[] a = str.toCharArray();
        int c = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newcb[i][j] = a[c] - '0';
                c++;
            }
        }
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    newrecordcb[i][j][k] = a[c] - '0';
                    c++;
                }
            }
        }
        steps = a[c] - '0';
//        System.out.println(steps);
        setcbFromNewcb();
        setRecordcbFromNewRecordcb();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cb[i][j]==1){
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                }
                if(cb[i][j]==-1){
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                }
                if(cb[i][j]==0){
                    chessGrids[i][j].setChessPiece(null);
                }
            }
        }
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print(recordcb[1][i][j]);
//            }
//            System.out.println();
//        }
    }

    public void setNewcb(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cb[i][j]==0){
                    newcb[i][j]=0;
                }
                if(cb[i][j]==1){
                    newcb[i][j]=1;
                }
                if(cb[i][j]==-1){
                    newcb[i][j]=2;
                }
            }
        }
    }

    public void setcbFromNewcb(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(newcb[i][j]==0){
                    cb[i][j]=0;
                }
                if(newcb[i][j]==1){
                    cb[i][j]=1;
                }
                if(newcb[i][j]==2){
                    cb[i][j]=-1;
                }
            }
        }
    }

    public void setNewRecordcb(){
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    if(recordcb[i][j][k]==0){
                        newrecordcb[i][j][k]=0;
                    }
                    if(recordcb[i][j][k]==1){
                        newrecordcb[i][j][k]=1;
                    }
                    if(recordcb[i][j][k]==-1){
                        newrecordcb[i][j][k]=2;
                    }
                }
            }
        }
    }

    public void setRecordcbFromNewRecordcb(){
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    if(newrecordcb[i][j][k]==0){
                        recordcb[i][j][k]=0;
                    }
                    if(newrecordcb[i][j][k]==1){
                        recordcb[i][j][k]=1;
                    }
                    if(newrecordcb[i][j][k]==2){
                        recordcb[i][j][k]=-1;
                    }
                }
            }
        }
    }

    public void playback(int time){
        Robot  r   = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        refreshnext(ChessPiece.RED);
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    cb[j][k] = recordcb[i][j][k];
                }
            }
//            for (int j = 0; j < 8; j++) {//////////////////////////////////all correct        why?????????
//                for (int k = 0; k < 8; k++) {
//                    System.out.print(cb[j][k]);
//                }
//                System.out.println();
//            }
//            System.out.println();
            for (int q = 0; q < 8; q++) {
                for (int j = 0; j < 8; j++) {
                    if(cb[q][j]==1){
                        chessGrids[q][j].setChessPiece(ChessPiece.WHITE);
                    }
                    if(cb[q][j]==-1){
                        chessGrids[q][j].setChessPiece(ChessPiece.BLACK);
                    }
                    if(cb[q][j]==0){
                        chessGrids[q][j].setChessPiece(null);
                    }
                }
            }
            refresh();
            GameFrame.controller.getStatusPanel().setScoreText(GameFrame.controller.blackScore,GameFrame.controller.whiteScore);
            refresh();
            File f;
            URI uri;
            URL url;
            try {
                f = new File("music\\xiaqi.wav" );
                uri = f.toURI();
                url = uri.toURL();  //解析地址
                AudioClip aau;
                aau = Applet.newAudioClip(url);
                aau.play();  //循环播放
            } catch (Exception E)
            { E.printStackTrace();
            }
//            GameFrame.controller.getStatusPanel().repaint();
//            GameFrame.controller.getGamePanel().repaint();

//            System.out.println( "延时前:"+new Date().toString()  );
            r.delay(   time   );
//            System.out.println(   "延时后:"+new Date().toString()   );
        }
        GameFrame.controller.checkCurrentPlayer();
        refreshnext(GameFrame.controller.getCurrentPlayer());
        GameFrame.controller.getStatusPanel().setScoreText(GameFrame.controller.blackScore,GameFrame.controller.whiteScore);
        GameFrame.controller.getStatusPanel().repaint();
        GameFrame.controller.getGamePanel().repaint();
    }
}
