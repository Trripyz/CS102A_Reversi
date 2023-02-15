package components;

import controller.GameController;
import model.*;
import view.ChessBoardPanel;
import view.GameFrame;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(255, 150, 50);


    private ChessPiece chessPiece;
    private int row;
    private int col;
    public boolean isnext = false;


    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }

    @Override
    public void onMouseClicked() {
        System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
        //todo: complete mouse click method
        int color;
        if(GameFrame.controller.getCurrentPlayer()==ChessPiece.BLACK){
            color=-1;
        }else {
            color=1;
        }
        if (GameFrame.controller.canClick(row, col)) {
            if (this.chessPiece == null) {
                this.chessPiece = GameFrame.controller.getCurrentPlayer();
                GameFrame.controller.swapPlayer();
            }
            GameFrame.controller.getfanzhuan(color,row,col);
            GameFrame.controller.useCheckNext();
            GameFrame.controller.getGamePanel().repaint();
            if(GameFrame.controller.GameOver()){
                String winner = "";
                if(GameFrame.controller.whiteScore-GameFrame.controller.blackScore>0){
                    winner = "White is win.";
                    GameFrame.controller.userWintimesadd(GameFrame.controller.whitePlayer);
                }
                if(GameFrame.controller.whiteScore-GameFrame.controller.blackScore<0){
                    winner = "Black is win.";
                    GameFrame.controller.userWintimesadd(GameFrame.controller.blackPlayer);
                }
                if(GameFrame.controller.whiteScore-GameFrame.controller.blackScore==0){
                    winner = "Both of you lose.:)";
                }
                JOptionPane.showMessageDialog(null,winner);
                GameFrame.controller.save(GameFrame.controller.uuu);
            }
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
            GameFrame.controller.checkAI();
        }
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void drawPiece(Graphics g) {
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
        if(GameFrame.controller.getGameMode()){
            if(isnext){
                g.setColor(Color.GRAY);
                g.fillOval((gridSize - chessSize)*2, (gridSize - chessSize)*2, chessSize/5, chessSize/5);
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        drawPiece(g);
    }
}
