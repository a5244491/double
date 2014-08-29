import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Created by cq on 14-7-28.
 */
public class BallData {
    private String id;
    private ArrayList pool1 = new ArrayList(); // 红色球
    private ArrayList pool2 = new ArrayList(); // 蓝色球

    public BallData(String id, String s) {
        this.id = id;
        if(s != null) {
            String[] temp = s.split(" ");
            for(int i = 0; i < temp.length -1; i++) {
                pool1.add(temp[i]);
            }
            pool2.add(temp[temp.length-1]);
        }
    }

    public String getId() {
        return id;
    }

    public static BallData getGlobalData(String line) {
        if (line == null) return null;
        Pattern pattern = Pattern.compile("[0-9]{7}");
        Matcher matcher = pattern.matcher(line);
        String id = "";
        if(matcher.find()) {
            id = matcher.group();
        }
        Pattern pattern2 = Pattern.compile("([0-9][0-9] ){6}[0-9][0-9]");
        Matcher matcher2 = pattern2.matcher(line);
        String nums = "";
        if(matcher2.find()) {
            nums = matcher2.group();
        }
//        System.out.println(id);
//        System.out.println(nums);
        return new BallData(id, nums);
    }

    public ArrayList getPool1() {
        return this.pool1;
    }

    public ArrayList getPool2() {
        return this.pool2;
    }

    public void remove(BallData temp) {
        this.pool1.removeAll(temp.getPool1());
    }

    public void print() {
        System.out.println("期号" + this.id + " 红色球号码: " + this.getPool1() + " 蓝色球号码:" + this.getPool2());
    }

    public static void main(String[] args) {
        BallData gd = BallData.getGlobalData("2014-08-10 	2014091 	01 05 12 19 27 29 14 	395,683,536");
        gd.print();
    }
}
