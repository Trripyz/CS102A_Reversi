import view.GameFrame;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//public class Main {
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//
//            GameFrame mainFrame = new GameFrame(800);
//            mainFrame.setVisible(true);
////            helloLabel hl = new helloLabel();
////            hl.setVisible(true);
////            ActionListener bt1_ls = new ActionListener() {
////                @Override
////                public void actionPerformed(ActionEvent arg0) {
////                    // TODO Auto-generated method stub
////                    String whiteuser = hl.jtext1.getText();
////                    String blackuser = hl.jtext2.getText();
////                    System.out.println(whiteuser);
////                    System.out.println(blackuser);
////                    GameFrame ml = new GameFrame(800);//为跳转的界面
////                    ml.setVisible(true);
////                    hl.jf_1.dispose();//销毁当前界面
////
////                }
////            };
////            hl.bt1.addActionListener(bt1_ls);
////            //退出事件的处理
////            ActionListener bt2_ls = new ActionListener() {
////                @Override
////                public void actionPerformed(ActionEvent e) {
////                    // TODO Auto-generated method stub
////                    System.exit(0);//终止当前程序
////                }
////            };
////            hl.bt2.addActionListener(bt2_ls);
//        });
//    }
//}


class helloLabel extends JFrame {
    public static int count = 0;
    public static JButton bt1;//登陆按钮
    public static JButton bt2;//忘记密码按钮
    public static JLabel jl_1;//登录的版面
    public static JFrame jf_1;//登陆的框架
    public static JTextField jtext1;//用户名
    public static JTextField jtext2;//密码
    public static JLabel jl_admin;
    public static JLabel jl_password;
    static ArrayList<user> uuu = new ArrayList<user>();

    public helloLabel() {//初始化登陆界面
        Font font = new Font("黑体", Font.PLAIN, 20);//设置字体
        jf_1 = new JFrame("登陆界面");
        jf_1.setSize(450, 400);
        //给登陆界面添加背景图片
//        ImageIcon bgim = new ImageIcon(helloLabel.class.getResource("baozou.PNG"));//背景图案
//        bgim.setImage(bgim.getImage().
//                getScaledInstance(bgim.getIconWidth(),
//                        bgim.getIconHeight(),
//                        Image.SCALE_DEFAULT));
//        ImageIcon bg=new ImageIcon("C:\\Users\\zyz'\\IdeaProjects\\Reversi\\background.jpg");
//        JLabel label=new JLabel(bg);
//        label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
//        jf_1.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
//        JPanel jp=(JPanel)jf_1.getContentPane();
//        jp.setOpaque(false);//设置透明
        jl_1 = new JLabel();
//        jl_1.setIcon(bg);

        jl_admin = new JLabel("White User");
        jl_admin.setBounds(20, 50, 200, 50);
        jl_admin.setFont(font);

        jl_password = new JLabel("Black User");
        jl_password.setBounds(20, 120, 200, 50);
        jl_password.setFont(font);

        bt1 = new JButton("进入");         //更改成loginButton
        bt1.setBounds(90, 250, 100, 50);
        bt1.setFont(font);

        bt2 = new JButton("退出");
        bt2.setBounds(250, 250, 100, 50);
        bt2.setFont(font);

        //加入文本框
        jtext1 = new JTextField("");
        jtext1.setBounds(150, 50, 250, 50);
        jtext1.setFont(font);

        jtext2 = new JTextField("");//密码输入框
        jtext2.setBounds(150, 120, 250, 50);
        jtext2.setFont(font);

        jl_1.add(jtext1);
        jl_1.add(jtext2);

        jl_1.add(jl_admin);
        jl_1.add(jl_password);
        jl_1.add(bt1);
        jl_1.add(bt2);

        jf_1.add(jl_1);
        jf_1.setVisible(true);
        jf_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf_1.setLocation(300, 400);
    }

    public static void main(String[] args) {
        //初始化登陆界面
//        ArrayList<user> uuu = new ArrayList<user>();
//        uuu = load();
        helloLabel hl = new helloLabel();
        /**
         * 处理点击事件
         * 1.登陆按钮点击事件，判断账号密码是否正确，若正确，弹出监测信息界面
         * 否则，无响应（暂时无响应）
         * ：后可在登陆界面添加一个logLabel提示用户是否用户密码正确
         * 2.退出按钮，直接退出程序
         */
        //登陆点击事件
        ActionListener bt1_ls = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                String whiteuser = jtext1.getText();
                String blackuser = jtext2.getText();
                System.out.println(whiteuser);
                System.out.println(blackuser);
//                GameFrame ml = new GameFrame(800);//为跳转的界面
                SwingUtilities.invokeLater(() -> {

                    GameFrame ml = new GameFrame(800);
                    ml.setVisible(true);
                    ml.getController().whitePlayer = whiteuser;
                    ml.getController().blackPlayer = blackuser;
                    ml.getController().checkUser(whiteuser);
                    ml.getController().checkUser(blackuser);
                    ml.setVisible(true);
//            helloLabel hl = new helloLabel();
//            hl.setVisible(true);
//            ActionListener bt1_ls = new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent arg0) {
//                    // TODO Auto-generated method stub
//                    String whiteuser = hl.jtext1.getText();
//                    String blackuser = hl.jtext2.getText();
//                    System.out.println(whiteuser);
//                    System.out.println(blackuser);
//                    GameFrame ml = new GameFrame(800);//为跳转的界面
//                    ml.setVisible(true);
//                    hl.jf_1.dispose();//销毁当前界面
//
//                }
//            };
//            hl.bt1.addActionListener(bt1_ls);
//            //退出事件的处理
//            ActionListener bt2_ls = new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    // TODO Auto-generated method stub
//                    System.exit(0);//终止当前程序
//                }
//            };
//            hl.bt2.addActionListener(bt2_ls);
                });

                hl.jf_1.dispose();//销毁当前界面
                File f;
                URI uri;
                URL url;
                try {
                    f = new File("music\\jinru.wav" );
                    uri = f.toURI();
                    url = uri.toURL();  //解析地址
                    AudioClip aau;
                    aau = Applet.newAudioClip(url);
                    aau.play();  //循环播放
                } catch (Exception E)
                { E.printStackTrace();
                }

            }
        };
        bt1.addActionListener(bt1_ls);
        //退出事件的处理
        ActionListener bt2_ls = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);//终止当前程序
            }
        };
        bt2.addActionListener(bt2_ls);
    }

    public static ArrayList<user> load(){
        ArrayList<user> uuu = new ArrayList<user>();
        File file = new File("C:\\Users\\zyz'\\IdeaProjects\\Reversi\\user\\user.txt");
        String filePath = "C:\\Users\\zyz'\\IdeaProjects\\Reversi\\user\\user.txt";
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
        char[] b = str.toCharArray();

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
        String sizeString = null;
        for (int i = 0; i < space1; i++) {
            sizeString = sizeString + String.valueOf(b[i]);
        }
        int size = Integer.parseInt(sizeString);
        ArrayList<String> name = new ArrayList<>();
        String name0 = null;
        int times0 = 0;String timesStr = null;user user0 = null;
        int[] space = new int[99999];space[0] = space1;
        for (int i = 0; i < size; i+=2) {
            space[i+1] = str.indexOf(" ",space[i]+1);
            for (int j = 1; j < space[i+1]-space[i]; j++) {
                name0 = name0 + String.valueOf(b[space[i]+j]);
            }
            name.add(name0);
            space[i+2] = str.indexOf(" ",space[i+1]+1);
            for (int j = 1; j < space[i+2]-space[i+1]; j++) {
                timesStr = timesStr + String.valueOf(b[space[i+1]+j]);
            }
            times0 = Integer.parseInt(timesStr);
            user0 = new user(name0,times0);
            uuu.add(user0);
        }
        return uuu;
    }

    public static void save(ArrayList<user> uuu){
        String content = "";
        int size = uuu.size();
        content = content + size + " ";
        for (int i = 0; i < uuu.size(); i++) {
            content = content + uuu.get(i).name + " " + uuu.get(i).wintimes + " ";
        }
        String path = "C:\\Users\\zyz'\\IdeaProjects\\Reversi\\user\\user.txt";
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

    public static class user{
        String name;
        int wintimes;

        public user(String name,int wintimes){
            this.name = name;
            this.wintimes = wintimes;
        }
    }

    public ArrayList<user> getuuu(){
        return uuu;
    }
}

