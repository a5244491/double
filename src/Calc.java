import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cq on 14-7-28.
 * 思路
 * 1 从前5期中，删除已经出现的，剩余的是待选的号码。
 * 2 从前5期中选取2个号码，这两个号码也将加入待选号码
 * 3 从待选区里面 生成号码组合。
 */
public class Calc {
    private HashMap<String, GlobalData> andes;
    private StringBuilder fullPool = new StringBuilder("01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33");
    private StringBuilder rest = new StringBuilder(fullPool);

    public Calc() {
        init();
    }

    private void init() {
        try {
            andes = FileUtil.bulidFromTxt("num.data");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
//        andes = new HashMap<String, GlobalData>();
//        andes.put("2014097", new GlobalData("2014097", "07 13 24 25 27 32 15"));
//        andes.put("2014096", new GlobalData("2014096", "12 14 17 19 22 24 08"));
//        andes.put("2014095", new GlobalData("2014095", "05 06 08 14 22 31 08"));
//        andes.put("2014094", new GlobalData("2014094", "01 10 18 20 23 29 01"));
//        andes.put("2014093", new GlobalData("2014093", "02 08 09 10 20 29 05"));
//        andes.put("2014092", new GlobalData("2014092", "03 13 18 19 22 26 07"));
    }



    /*
     * 获取前5期重号 这个思路最男。
     */
    private void getRepetNum(String number) {
        Integer target = Integer.parseInt(number);
        int num = target.intValue();
        GlobalData next = new GlobalData(target.toString(), fullPool.toString());

        for(int i = 1; i < 5; i++) {
            GlobalData first = getNumDate(num - i + "");
            for(int j = i; j < 5; j++ ) {
                GlobalData temp = getNumDate(num - j + "");
                first.remove(temp);
            }
        }

        for (int i = 1; i <= 5; i++) {
            GlobalData temp = andes.get((num - i) + "");
        }
        next.print();
    }


    /*
     * 粗略删除之前5期已经出现的号码
     */
    private void removeOld(String number) {
        Integer target = Integer.parseInt(number);
        GlobalData next = new GlobalData(target.toString(), fullPool.toString());
        for (int i = 1; i <= 5; i++) {
            GlobalData temp = andes.get((target.intValue() - i) + "");
            next.remove(temp);
        }

        next.print();
    }


    public void printAll() {
        Iterator iter = andes.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            ((GlobalData)val).print();
        }

    }

    /*
     * 获取指定期号的数据
     */
    private GlobalData getNumDate(String number){
        GlobalData temp = andes.get(number);
        return temp;
    }

    /*
     * 获取指定期号的数据
     */
    private GlobalData getNumDate(int number){
        GlobalData temp = andes.get(number + "");
        return temp;
    }


    public static void main(String[] arg) {
        Calc c = new Calc();
//        c.removeOld("2014098");
        c.printAll();
    }
}
