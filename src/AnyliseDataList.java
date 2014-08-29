import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by cq on 14-8-29.
 */
public class AnyliseDataList {
    private ArrayList<AnyliseData> list;

    public AnyliseDataList(){
        list = new ArrayList<AnyliseData>();
    }

    public void addData(AnyliseData ad) {
        list.add(ad);
    }

    static void display(ArrayList<AnyliseData> lst){
        for(AnyliseData s:lst)
            s.print();
    }

    public void sortByID() {

    }

    public void sortByRate() {
        Collections.sort(list, comparator);
        display(list);
    }

    Comparator<AnyliseData> comparator = new Comparator<AnyliseData>() {
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


}
