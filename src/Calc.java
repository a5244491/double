import java.io.IOException;
import java.util.*;

/**
 * Created by cq on 14-7-28.
 * 思路
 * 1 从前5期中，删除已经出现的，剩余的是待选的号码。
 * 2 从前5期中选取2个号码，这两个号码也将加入待选号码
 * 3 从待选区里面 生成号码组合。
 */
public class Calc {
    private AnyliseDataList anyliseDatalist;
    private HashMap<String, BallData> andes;
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


    /**
     * 从指定的期号开始，分析上期出现的号，本期再次出现的概率有多大
     */
    private void any_repeat(int numBegin, int numEnd) {
        for(int i = numBegin; i < numEnd; i++) {
            BallData first = getNumDate(i);
            BallData second = getNumDate(i + 1);

            ArrayList firstPool1 = first.getPool1();
            ArrayList secondPool1 = second.getPool1();
            ArrayList include= repeat(i, i + 1);
            System.out.printf("%8s: 本期重复的%20s 非重复的%20s%n",
                    second.getId(), include.toString(), secondPool1.toString());
        }
    }


    /**
     * 计算指定期号号码在另一期中出现的重号。
     * @param target
     * @param old
     */
    private ArrayList repeat(int target, int old){
        BallData first = getNumDate(target);
        BallData second = getNumDate(old);

        ArrayList firstPool1 = first.getPool1();
        ArrayList secondPool1 = second.getPool1();
        ArrayList include = new ArrayList();
        ArrayList exclude = new ArrayList();
        for (int j = 0; j < firstPool1.size(); j++) {
            String temp = (String) firstPool1.get(j);
            if (secondPool1.contains(temp)) {
                include.add(temp);
            } else {
                exclude.add(temp);
            }
        }
        return include;
    }

    /*
     * 根据自己的算法 用历史数据进行判断，看看成功的概率有多大。
     */
    public void justed(int numbegin, int numend) {
        anyliseDatalist = new AnyliseDataList();
        for(int i = numbegin; i <= numend; i++) {
            BallData calced = removeOld(i + "");
            BallData truely = getNumDate(i);
            anylisy(calced, truely);
        }
//        anyliseDatalist.sortByRate();
//        anyliseDatalist.sortByID();
        anyliseDatalist.getBallDataById("2014072");
    }


    public void menos(BallData calced, String menosList){
        String[] temp = menosList.split(" ");
        calced.getPool1().removeAll(Arrays.asList(temp));
        calced.print();
    }

    /**
     * 分析
     * @param calced
     * @param truely
     */
    private void anylisy(BallData calced, BallData truely) {
        ArrayList listCalced = calced.getPool1();
        ArrayList listTruely = truely.getPool1();
        ArrayList include = new ArrayList();
        ArrayList exclude = new ArrayList();
        for(int i = 0; i < listTruely.size(); i++) {
            String temp = (String) listTruely.get(i);
            if(listCalced.contains(temp)) {
                include.add(temp);
            } else {
                exclude.add(temp);
            }
        }
        int id = Integer.parseInt(calced.getId());
        AnyliseData ad = new AnyliseData(calced.getId(), calced, truely, include, exclude, repeat(id, id - 1));
        //ad.print();
        anyliseDatalist.addData(ad);
//        System.out.printf("%8s:预%64s 预测命中率%5.2f%% 命中率%5.2f%%  命中%20s 未命中%20s 本期重号%20s %n",
//                calced.getId(),listCalced.toString(), include.size()*100.0/listCalced.size(), include.size()*100.0/6, include.toString(), exclude.toString(), repeat(id, id - 1));
    }


    /*
     * 粗略删除之前5期已经出现的号码
     */
    private BallData removeOld(String number) {
        Integer target = Integer.parseInt(number);
        BallData next = new BallData(target.toString(), fullPool.toString());
        for (int i = 1; i <= 5; i++) {
            BallData temp = andes.get((target.intValue() - i) + "");
            next.remove(temp);
        }
        return next;
    }


    /*
     * 将所有的往期数据 全部打印出来
     */
    public void printAll() {
        Iterator iter = andes.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            ((BallData)val).print();
        }

    }

    /*
     * 获取指定期号的数据
     */
    private BallData getNumDate(String number){
        BallData temp = andes.get(number);
        return temp;
    }

    /*
     * 获取指定期号的数据
     */
    private BallData getNumDate(int number){
        BallData temp = andes.get(number + "");
        return temp;
    }


    public static void main(String[] arg) {
        Calc c = new Calc();
        int numbegin = 2014006;
        int numend = 2014100;
//        BallData bd = c.removeOld("2014101");
//        bd.print();
//        c.menos(bd, "03 04 15 26 28 30 31");
//        bd.print();

      c.justed(numbegin, numend);

//        c.any_repeat(numbegin, numend);
    }
}
