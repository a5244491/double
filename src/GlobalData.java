import java.util.ArrayList;
/**
 * Created by cq on 14-7-28.
 */
public class GlobalData {
    private String id;
    private ArrayList pool1 = new ArrayList();
    private ArrayList pool2 = new ArrayList();

    public GlobalData(String id, String s) {
        this.id = id;
        if(s != null) {
            String[] temp = s.split(" ");
            for(int i = 0; i < temp.length -1; i++) {
                pool1.add(temp[i]);
            }
            pool2.add(temp[temp.length-1]);
        }
    }

    public ArrayList getPool1() {
        return this.pool1;
    }

    public ArrayList getPool2() {
        return this.pool2;
    }

    public void remove(GlobalData temp) {
        this.pool1.removeAll(temp.getPool1());
    }

    public void print() {
        System.out.println("期号" + this.id + " 红色球号码: " + this.getPool1() + " 蓝色球号码:" + this.getPool2());
    }
}
