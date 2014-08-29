import java.util.ArrayList;

/**
 * Created by cq on 14-8-29.
 */
public class AnyliseData {
    private String id;
    private BallData anyliData;
    private BallData trueData;
    private ArrayList include = new ArrayList();
    private ArrayList exclude = new ArrayList();
    private ArrayList repeat = new ArrayList();

    public AnyliseData(String id, BallData anyData, BallData trueData, ArrayList include, ArrayList exclude, ArrayList repeat) {
        this.id = id;
        this.anyliData = anyData;
        this.trueData = trueData;
        this.include = include;
        this.exclude = exclude;
        this.repeat = repeat;
    }

    public String getId() {
        return id;
    }

    public BallData getAnyliData() {
        return anyliData;
    }

    public BallData getTrueData() {
        return trueData;
    }

    public ArrayList getInclude() {
        return include;
    }

    public ArrayList getExclude() {
        return exclude;
    }

    public ArrayList getRepeat() {
        return repeat;
    }

    public double getRate1() {
        return include.size() * 1.0 / anyliData.getPool1().size();
    }

    public double getRate2() {
        return include.size() * 1.0 / 6;
    }

    public void print() {
        System.out.printf("%8s:预%64s 预测命中率%5.2f%% 命中率%5.2f%%  命中%20s 未命中%20s 本期重号%20s %n",
                id, anyliData.getPool1(), getRate1() * 100, getRate2() * 100, include, exclude, repeat);
    }

    public String toString() {
        return this.id;
    }
}
