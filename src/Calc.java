import java.util.HashMap;

/**
 * Created by cq on 14-7-28.
 */
public class Calc {
    private HashMap<String, GlobalData> andes;
    private StringBuilder pool = new StringBuilder("01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33");
    private StringBuilder rest = new StringBuilder(pool);

    public Calc() {
        init();
    }

    private void init() {
        andes = new HashMap<String, GlobalData>();
        andes.put("2014085", new GlobalData("2014085", "01 02 11 19 23 29 08"));
        andes.put("2014084", new GlobalData("2014084", "01 06 09 10 13 25 08"));
        andes.put("2014083", new GlobalData("2014083", "05 06 19 21 23 33 12"));
        andes.put("2014082", new GlobalData("2014082", "02 04 20 25 26 29 11"));
        andes.put("2014081", new GlobalData("2014081", "08 14 22 24 27 29 10"));
        andes.put("2014080", new GlobalData("2014081", "05 14 16 17 25 30 07"));
    }

    private void getRepetNum(String number) {
        Integer target = Integer.parseInt(number);
        GlobalData next = new GlobalData(target.toString(), pool.toString());
        for (int i = 1; i <= 5; i++) {
            GlobalData temp = andes.get((target.intValue() - i) + "");
        }

        next.print();
    }

    private void computer(String number) {
        Integer target = Integer.parseInt(number);
        GlobalData next = new GlobalData(target.toString(), pool.toString());
        for (int i = 1; i <= 5; i++) {
            GlobalData temp = andes.get((target.intValue() - i) + "");
            next.remove(temp);
        }

        next.print();
    }


    public static void main(String[] arg) {
        Calc c = new Calc();
        c.computer("2014085");
    }
}
