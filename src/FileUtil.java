import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cq on 14-8-26.
 */
public class FileUtil {

    public static void searchFile(String reg, String filePath) {

    }

    public static ArrayList searchFileByLine(String reg, String filePath) throws IOException {       //txt文件的查询
        ArrayList result = new ArrayList();
        InputStream inStream = FileUtil.class.getResourceAsStream("num.data");
        InputStreamReader inStreamR = new InputStreamReader(inStream);
        BufferedReader br = new BufferedReader(inStreamR);
        String Line = br.readLine();//从文件读取一行字符串
        //  依据读取到的整行字符串是否不为空，来判断是够到达文件结尾
        for (int i = 0; Line != null; i++) {
            if (Line.indexOf(reg) > -1) {         // 使用indexof来实现模糊查询
                System.out.println(Line);
                result.add("Line:" + (i + 1) + Line);
            }
            Line = br.readLine();
        }
        br.close();//关闭BufferedReader对象
        inStreamR.close();
        inStream.close();//关闭文件
        return result;
    }


    public static HashMap<String, BallData> bulidFromTxt(String filePath) throws IOException{
        HashMap<String, BallData> andes = new HashMap<String, BallData>();
        InputStream inStream = FileUtil.class.getResourceAsStream(filePath);
        InputStreamReader inStreamR = new InputStreamReader(inStream);
        BufferedReader br = new BufferedReader(inStreamR);
        String line = br.readLine();//从文件读取一行字符串
        for (int i = 0; line != null; i++) {
            BallData gd = BallData.getGlobalData(line);
            andes.put(gd.getId(), gd);
            line = br.readLine();
        }
        br.close();
        inStreamR.close();
        inStream.close();
        return andes;
    }

    private void buildData(String line) {

    }

    public static void main(String[] args) {
        try {
            FileUtil.searchFileByLine("2014091", "num.data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
