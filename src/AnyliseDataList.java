import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by cq on 14-8-29.
 */
public class AnyliseDataList {
    private ArrayList<AnyliseData> list;
    private ArrayList<AnyliseData> list_sortted_by_rate;

    public AnyliseDataList(){
        list = new ArrayList<AnyliseData>();
    }

    public void addData(AnyliseData ad) {
        list.add(ad);
    }

    /**
     * 打印全部分析的期号
     * @param lst
     */
    public void display(ArrayList<AnyliseData> lst){
        for(AnyliseData s:lst)
            s.print();
    }

    /**
     * 打印指定期号的数据
     * @param lst
     * @param start
     * @param end
     */
    public void display(ArrayList<AnyliseData> lst, String start, String end){
        int i_start = calcIdx(start);
        int i_end = calcIdx(end);
        for(int i = i_start; i <= i_end; i++) {
            try {
                AnyliseData s = list.get(i);
                s.print();
            }catch (Exception e) {
                System.out.print("超出期号");
            }
        }
    }

    /**
     * 按照预测的数目进行排序
     */
    public void sortByRate() {
        list_sortted_by_rate = (ArrayList)list.clone();
        Collections.sort(list_sortted_by_rate, comparator_rate);
//        display(list);
//        System.out.println("---------------------------------------------------");
//        display(list_sortted_by_rate);
    }

    /**
     * 计算目标期号在List中的下标位置
     * @param ID
     * @return
     */
    private int calcIdx(String ID) {
        AnyliseData result = null;
        AnyliseData bd = list.get(0);
        int first = Integer.parseInt(bd.getId());
        int tar = Integer.parseInt(ID);
        if(tar - first < list.size()) {
            return tar - first;
        }
        return -1;
    }


    /**
     * 根据制定的目标期号，获取分析中的结果
     * @param ID
     * @return
     */
    public AnyliseData getBallDataById(String ID) {
        AnyliseData result = null;
        int idx = calcIdx(ID);
        if(idx < list.size()) {
            result = list.get(idx);
            result.print();
        }
        return result;
    }

    Comparator<AnyliseData> comparator_rate = new Comparator<AnyliseData>() {
        public int compare(AnyliseData ad1, AnyliseData ad2) {
            //先排年龄
            if (ad1.getAnyliData().getPool1().size() != ad2.getAnyliData().getPool1().size()) {
                return ad1.getAnyliData().getPool1().size() - ad2.getAnyliData().getPool1().size();
            } else {
                //年龄相同则按姓名排序
                if (ad1.getRate1() != ad2.getRate1()) {
                    return (int)(ad1.getRate1() * 100 - ad2.getRate1() * 100);
                } else {
                    //姓名也相同则按学号排序
                    return (int)(ad1.getRate2() * 100 - ad2.getRate2() * 100);
                }
            }
        }
    };

    Comparator<AnyliseData> comparator_id = new Comparator<AnyliseData>() {
        public int compare(AnyliseData ad1, AnyliseData ad2) {
            return Integer.parseInt(ad1.getId()) - Integer.parseInt(ad2.getId());

        }
    };
}
