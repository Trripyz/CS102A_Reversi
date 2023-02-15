import controller.User;
import view.ChessBoardPanel;
import java.awt.Robot;
import java.util.Date;
import java.io.*;
import java.util.ArrayList;
//
//public class test {
//    public static void main(String[] args){
//        ChessBoardPanel a = new ChessBoardPanel(1,1);
//        a.updatecb();
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.printf("%d ",a.cb[i][j]);
//            }
//            System.out.println();
//        }
//    }
//}
//
//class test1{
//    public static void main(String[] args){
//        int[][] a = new int[8][8];
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                a[i][j] = 0;
//            }
//        }
//        for (int[] aa:a
//             ) {
//            for (int aaa:aa
//                 ) {
//                System.out.println(aaa);
//            }
//
//        }
//    }
//}
//
//class test2{
//    public static void main(String[] args){
//        ArrayList<User> uuu = new ArrayList<User>();
//        File file = new File("C:\\Users\\zyz'\\IdeaProjects\\Reversi\\user\\user.txt");
//        String filePath = "C:\\Users\\zyz'\\IdeaProjects\\Reversi\\user\\user.txt";
//        FileReader fr = null;
//        try {
//            fr = new FileReader(filePath);
//        } catch (FileNotFoundException fileNotFoundException) {
//            fileNotFoundException.printStackTrace();
//        }
//
//        BufferedReader br = new BufferedReader(fr);
//
//        String str = null;
//        try {
//            str = br.readLine();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//
//        System.out.println(str);
//
//        char[] b =str.toCharArray();
//
//
//        try {
//            br.close();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//
//        try {
//            fr.close();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//        int space1 = str.indexOf(" ");
//        String sizeString = "";
//        for (int i = 0; i < space1; i++) {
//            sizeString = sizeString + String.valueOf(b[i]);
//        }
//        System.out.println(sizeString);
//        int size = Integer.parseInt(sizeString);
//        ArrayList<String> name = new ArrayList<>();
//        String name0 = "";
//        int times0 = 0;String timesStr = "";
//        User user0 = null;
//        int[] space = new int[99999];space[0] = space1;
//        for (int i = 0; i < size; i+=2) {
//            space[i+1] = str.indexOf(" ",space[i]+1);
//            for (int j = 1; j < space[i+1]-space[i]; j++) {
//                name0 = name0 + String.valueOf(b[space[i]+j]);
//            }
//            name.add(name0);
//            space[i+2] = str.indexOf(" ",space[i+1]+1);
//            for (int j = 1; j < space[i+2]-space[i+1]; j++) {
//                timesStr = timesStr + String.valueOf(b[space[i+1]+j]);
//            }
//
//            times0 = Integer.parseInt(timesStr);
//            user0 = new User(name0,times0);
//            uuu.add(user0);
//        }
//
//    }
//}
//
//class test3{
//    public   static   void   main(String[]   args)   throws   Exception{
//        Robot  r   =   new   Robot();
//        System.out.println( "延时前:"+new Date().toString()  );
//        r.delay(   500   );
//        System.out.println(   "延时后:"+new Date().toString()   );
//    }
//
//}