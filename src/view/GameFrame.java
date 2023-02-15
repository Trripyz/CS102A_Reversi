package view;


import controller.GameController;
import controller.User;
import model.ChessPiece;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameFrame extends JFrame {
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    private JLabel remakeTimesLabel;
    private int remakeTimes = 0;

    public GameFrame(int frameSize) {

        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize+150 + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);


        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        controller = new GameController(chessBoardPanel, statusPanel);
        controller.setGamePanel(chessBoardPanel);

        this.add(chessBoardPanel);
        this.add(statusPanel);


        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(120, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addActionListener(e -> {
            System.out.println("click restart Btn");//点击后的功能
            chessBoardPanel.reStart();
            if(controller.getCurrentPlayer().equals(ChessPiece.WHITE)){
                controller.swapPlayer();
            }
            controller.countScore();
            statusPanel.setScoreText(GameFrame.controller.blackScore,GameFrame.controller.whiteScore);
        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(120, 50);
        loadGameBtn.setLocation(restartBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            File file = new File("gamefile\\");
            String[] fileList = file.list();
            String filename = JOptionPane.showInputDialog(this, fileList);//"input the name here"
            String filePath = "gamefile\\" + filename +".txt";
            FileReader fr = null;
            try {
                fr = new FileReader(filePath);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            BufferedReader br = new BufferedReader(fr);

            String str = null;
            try {
                str = br.readLine();/////////输出内容
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            System.out.println(str);

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
            chessBoardPanel.setcbAndRecord(str);
            controller.checkCurrentPlayer();
            chessBoardPanel.refresh();
            chessBoardPanel.refreshnext(GameFrame.controller.getCurrentPlayer());
            statusPanel.setScoreText(GameFrame.controller.blackScore,GameFrame.controller.whiteScore);
            GameFrame.controller.getStatusPanel().repaint();
            GameFrame.controller.getGamePanel().repaint();
//            System.out.println("clicked Load Btn");

//            controller.readFileData(filePath);
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(loadGameBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(this, "input the name here");
            String path = String.format("gamefile\\%s.txt",filename);
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
                bw.write(chessBoardPanel.toString());/////////////
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

//            JFileChooser fc = new JFileChooser();//实例化一个filechooser*/
//            fc.setDialogTitle("选择文件...");//设置Filechooser的标签
//            fc.setApproveButtonText("选择");
//            int result=fc.showOpenDialog(fc);
//            if(result==JFileChooser.APPROVE_OPTION)//定义确认按钮的行为
//            {
//                String fileName=fc.getCurrentDirectory()+"\\"+fc.getSelectedFile().getName();//设置已获取文件的路径及文件名
//
////                filepath.setText(fileName);
//            }
//            System.out.println("clicked Save Btn");
//            String filePath = JOptionPane.showInputDialog(this, "input the path here");
//            controller.writeDataToFile(filePath);

        });

        JButton ModeBtn = new JButton("Remake");
        ModeBtn.setSize(120, 50);
        ModeBtn.setLocation(loadGameBtn.getX()+2*restartBtn.getWidth()+200, restartBtn.getY()-520);
        add(ModeBtn);
        ModeBtn.addActionListener(e -> {
            remakeTimes++;
            GameFrame.controller.useRemake();
            chessBoardPanel.refresh();
            GameFrame.controller.swapPlayer();
            chessBoardPanel.refreshnext(GameFrame.controller.getCurrentPlayer());
            controller.countScore();
            statusPanel.setScoreText(GameFrame.controller.blackScore,GameFrame.controller.whiteScore);
            GameFrame.controller.getStatusPanel().repaint();
            GameFrame.controller.getGamePanel().repaint();
        });

//        this.remakeTimesLabel = new JLabel();
//        this.remakeTimesLabel.setLocation(loadGameBtn.getX()+2*restartBtn.getWidth()+200, restartBtn.getY()-500);
//        this.remakeTimesLabel.setSize(loadGameBtn.getX()+2*restartBtn.getWidth()+220,100);
//        this.remakeTimesLabel.setFont(new Font("Calibri", Font.BOLD, 30));
//        this.setRemakeTimesText(remakeTimes);
//        add(remakeTimesLabel);

        JButton RemakeBtn = new JButton("Mode");
        RemakeBtn.setSize(120, 50);
        RemakeBtn.setLocation(loadGameBtn.getX()+2*restartBtn.getWidth()+200, restartBtn.getY()-600);
        add(RemakeBtn);
        RemakeBtn.addActionListener(e -> {
            GameFrame.controller.changeMode();
            chessBoardPanel.refresh();
            statusPanel.setModeText(GameFrame.controller.getGameMode());

        });

        JButton musicBtn = new JButton("Music");
        musicBtn.setSize(120, 50);
        musicBtn.setLocation(loadGameBtn.getX()+2*restartBtn.getWidth()+200, restartBtn.getY()-440);
        add(musicBtn);
        musicBtn.addActionListener(e -> {
//            URL musicURL   =   null;
//            try {
//                musicURL   =   new   URL( "C:\\Users\\zyz'\\IdeaProjects\\Reversi\\music\\Cantina Band");//得到要播放音乐的url
//            } catch (MalformedURLException malformedURLException) {
//                malformedURLException.printStackTrace();
//            }
//            AudioClip ac   =   Applet.newAudioClip(musicURL);   //得到一个播放音频的实例

//            try {
//                URL cb;
//                File f = new File("C:\\Users\\zyz'\\IdeaProjects\\Reversi\\music\\Cantina Band"); //引号里面的是音乐文件所在的绝对鹿筋
//                cb = f.toURL();
//                AudioClip aau;
//                aau = Applet.newAudioClip(cb);
//aau.play();
//                aau.loop();
////循环播放 aau.play() 单曲 aau.stop()停止播放
//                JavaClip frame=new JavaClip();
//
//frame.setBounds(0, 0, 300, 200);
//frame.setVisible(true);
//
//            } catch (MalformedURLException E) {
//                E.printStackTrace();
//            }
            File f;
            URI uri;
            URL url;
            try {
                f = new File("music\\juk4l-459qb.wav" );
                uri = f.toURI();
                url = uri.toURL();  //解析地址
                AudioClip aau;
                aau = Applet.newAudioClip(url);
                aau.loop();  //循环播放
            } catch (Exception E)
            { E.printStackTrace();
            }
        });

        JButton RankBtn = new JButton("Rank");
        RankBtn.setSize(120, 50);
        RankBtn.setLocation(loadGameBtn.getX()+2*restartBtn.getWidth()+200, restartBtn.getY()-360);
        add(RankBtn);
        RankBtn.addActionListener(e -> {
            controller.rankUUU();
            ArrayList<String> a = new ArrayList<>();
            a.add(String.format("1st : %6s    %d场",controller.getUUUname(0),controller.getUUUWintime(0)));
            a.add(String.format("2nd : %6s    %d场",controller.getUUUname(1),controller.getUUUWintime(1)));
            a.add(String.format("3rd : %6s    %d场",controller.getUUUname(2),controller.getUUUWintime(2)));
            a.add(String.format("4th : %6s    %d场",controller.getUUUname(3),controller.getUUUWintime(3)));
            a.add(String.format("5th : %6s    %d场",controller.getUUUname(4),controller.getUUUWintime(4)));
            JOptionPane.showMessageDialog(this,a);


        });

        JButton PveBtn = new JButton("PVE");
        PveBtn.setSize(120, 50);
        PveBtn.setLocation(loadGameBtn.getX()+2*restartBtn.getWidth()+200, restartBtn.getY()-280);
        add(PveBtn);
        PveBtn.addActionListener(e -> {
            controller.openAi();
            if(controller.Ai){
                JOptionPane.showMessageDialog(null,"AI is opened.");
            }else{
                JOptionPane.showMessageDialog(null,"AI is closed.");
            }
            controller.useCheckNext();
        });

        JButton rewiseBtn = new JButton("Playback");
        rewiseBtn.setSize(120, 50);
        rewiseBtn.setLocation(loadGameBtn.getX()+2*restartBtn.getWidth()+200, restartBtn.getY()-200);
        add(rewiseBtn);
        rewiseBtn.addActionListener(e -> {
            File file = new File("gamefile\\");
            String[] fileList = file.list();
            String filename = JOptionPane.showInputDialog(this, fileList);//"input the name here"
            String filePath = "gamefile\\" + filename +".txt";
            FileReader fr = null;
            try {
                fr = new FileReader(filePath);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            BufferedReader br = new BufferedReader(fr);

            String str = null;
            try {
                str = br.readLine();/////////输出内容
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            System.out.println(str);

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
            int time = Integer.parseInt(JOptionPane.showInputDialog(this,"Please input the time between each two steps(ms)"));
            chessBoardPanel.setcbAndRecord(str);
//            controller.checkCurrentPlayer();
//            chessBoardPanel.refresh();
//            chessBoardPanel.refreshnext(GameFrame.controller.getCurrentPlayer());
//            statusPanel.setScoreText(GameFrame.controller.blackScore,GameFrame.controller.whiteScore);
//            GameFrame.controller.getStatusPanel().repaint();
//            GameFrame.controller.getGamePanel().repaint();
            chessBoardPanel.playback(time);
        });


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void setRemakeTimesText(int remakeTimes){
        this.remakeTimesLabel.setText(String.format("%d times",remakeTimes));
    }

    public GameController getController(){
        return controller;
    }

    public String newDateTime(){
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String datetime = tempDate.format(new java.util.Date());
        return datetime;
    }

}
//class JavaClip extends Frame{
//
//    public JavaClip(){
//        super();
//    } }

//class Music extends JFrame{
// File f;
//  URI uri;
//     URL url;
////  Music(){
////             bgMusic();
////          }
//         Music(){
//           try {
//                   f = new File("C:\\Users\\zyz'\\IdeaProjects\\Reversi\\music\\Cantina Band");
//                   uri = f.toURI();
//                   url = uri.toURL();  //解析地址
//                   AudioClip aau;
//                   aau = Applet.newAudioClip(url);
//                   aau.loop();  //循环播放
//               } catch (Exception e)
//           { e.printStackTrace();
//               }
//         }}