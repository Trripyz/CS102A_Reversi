package controller;

import model.ChessPiece;
import view.*;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class GameController {


    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    public int blackScore;
    public int whiteScore;
    private boolean GameMode = true;
    public ArrayList<User> uuu = new ArrayList<>();
    public boolean Ai = false;
    public String whitePlayer = "";
    public String blackPlayer = "";

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 2;
        whiteScore = 2;
        uuu = load();
    }

    public void swapPlayer() {

        currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore,whiteScore);

    }


    public void countScore() {
        //todo: modify the countScore method
        whiteScore=gamePanel.getWhiteScore();
        blackScore=gamePanel.getBlackScore();
//        if (currentPlayer == ChessPiece.BLACK) {
//            blackScore++;
//        } else {
//            whiteScore++;
//        }
    }


    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void readFileData(String fileName) {
        //todo: read date from file
        List<String> fileData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
            fileData.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToFile(String fileName) {
        //todo: write data into file
    }

    public boolean canClick(int row, int col) {
        if(GameMode){
            return gamePanel.canClickGrid(row, col, currentPlayer);
        }
        
        return gamePanel.canClickGridForModeFalse(row,col,currentPlayer);
    }

    public void getfanzhuan(int c,int x,int y){
        gamePanel.fanzhuan(c,x,y);
        countScore();
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public boolean GameOver(){
        int a = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(gamePanel.canClickGrid(i,j,currentPlayer)){
                    a++;
                }
            }
        }
        if(a==0){
            return true;
        }else {
            return false;
        }
    }

    public void changeMode(){
        if(GameMode){
            GameMode = false;
        }else {
            GameMode = true;
        }
    }

    public void useCheckNext(){
        gamePanel.refreshnext(currentPlayer);
    }

    public boolean getGameMode(){
        return GameMode;
    }

    public void useRemake(){
        gamePanel.remake();
    }

    public void checkCurrentPlayer(){
        countScore();
        if((blackScore+whiteScore)%2==0){
            currentPlayer = ChessPiece.BLACK;
        }else{
            currentPlayer = ChessPiece.WHITE;
        }
    }

    public void openAi(){
        if(Ai){
            Ai = false;
        }else {
            Ai = true;
        }
    }

    public void checkAI(){
        int c = 0;int a = 0;
        if(Ai){
            if(currentPlayer.equals(ChessPiece.WHITE)){
                c=1;
            }else{
                c=-1;
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(canClick(i,j)){
                        a++;
                        getfanzhuan(c,i,j);
                        swapPlayer();
                        useCheckNext();
                        getGamePanel().repaint();
                        break;
                    }
                    if(a!=0){
                        break;
                    }
                }
            }
            if(GameFrame.controller.GameOver()){
                String winner = "";
                if(GameFrame.controller.whiteScore-GameFrame.controller.blackScore>0){
                    winner = "White is win.";
                }
                if(GameFrame.controller.whiteScore-GameFrame.controller.blackScore<0){
                    winner = "Black is win.";
                }
                if(GameFrame.controller.whiteScore-GameFrame.controller.blackScore==0){
                    winner = "Both of you lose.   :)";
                }
                JOptionPane.showMessageDialog(null,winner);

            }
        }
    }

    public ArrayList<User> load(){
        ArrayList<User> uuu = new ArrayList<User>();
        File file = new File("user\\user.txt");
        String filePath = "user\\user.txt";
        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);

        String str = null;
        try {
            str = br.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        System.out.println(str);

        char[] b =str.toCharArray();


        try {
            br.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            fr.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        int space1 = str.indexOf(" ");
        String sizeString = "";
        for (int i = 0; i < space1; i++) {
            sizeString = sizeString + String.valueOf(b[i]);
        }
        int size = Integer.parseInt(sizeString);
        ArrayList<String> name = new ArrayList<>();
        String name0 = "";
        int times0 = 0;String timesStr = "";
        User user0 = new User("",0);
        int[] space = new int[99999];space[0] = space1;
        for (int i = 0; i < size*2-1; i+=2) {
            space[i+1] = str.indexOf(" ",space[i]+1);
            for (int j = 1; j < space[i+1]-space[i]; j++) {
                name0 = name0 + String.valueOf(b[space[i]+j]);
            }
            space[i+2] = str.indexOf(" ",space[i+1]+1);
            for (int j = 1; j < space[i+2]-space[i+1]; j++) {
                timesStr = timesStr + String.valueOf(b[space[i+1]+j]);
            }
            times0 = Integer.parseInt(timesStr);//user文件里第一个是人数，记得最后面要加空格
            user0 = new User(name0,times0);
            name0 = "";
            timesStr = "";
            uuu.add(user0);
        }
//        for (int i = 0; i < 3; i++) {
//            System.out.println(uuu.get(i).name);
//            System.out.println(uuu.get(i).wintimes);
//        }
        return uuu;
    }

    public void save(ArrayList<User> uuu){
        toBlack();
        String content = "";
        int size = uuu.size();
        content = content + size + " ";
        for (int i = 0; i < uuu.size(); i++) {
            content = content + uuu.get(i).name + " " + uuu.get(i).wintimes + " ";
        }
        String path = "user\\user.txt";
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(content);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            bw.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            fw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }

    public void toBlack(){
        String path = "user\\user.txt";
        File file = new File(path);
        file.delete();
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(file, true);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//
//        BufferedWriter bw = new BufferedWriter(fw);
//
//        try {
//            bw.write("");
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//
//        try {
//            bw.flush();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//
//        try {
//            bw.close();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//
//        try {
//            fw.close();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }

    }

    public void checkUser(String player){
        int a = 0;
        User uu = new User(player,0);
        for (int i = 0; i < uuu.size(); i++) {
            if(uuu.get(i).name.equals(player)){
                a++;
            }
        }
        if(a==0){
            uuu.add(uu);
        }
    }

    public void userWintimesadd(String player){
        for (int i = 0; i < uuu.size(); i++) {
            if(uuu.get(i).name.equals(player)){
                uuu.get(i).wintimes++;
            }
        }
    }

    public void rankUUU(){
        Collections.sort(uuu,new Comparator<User>(){
            @Override
            public int compare(User f1,User f2){
                return f2.wintimes-f1.wintimes;
            }

        });
    }

    public String getUUUname(int i){
        return uuu.get(i).name;
    }

    public int getUUUWintime(int i){
        return uuu.get(i).wintimes;
    }
}
