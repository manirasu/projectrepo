import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by OManimaran on 20-02-2018.
 */
public class IteratorExample {
    public static void main(String s[]) {
        List<String> ss = new CopyOnWriteArrayList<String>();

        //List<String> ss = new ArrayList<>();
        ss.add("mani");
        ss.add("chennai");
        ss.add("mm");

        /*if(ss != null && ss.size() > 0) {
            Iterator iterator = ss.iterator();
            while(iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }*/
        for(String sss: ss) {
            System.out.println(sss);
            if(sss.equals("mm")) {
                ss.remove(sss);
            }
        }

        /*for(String s1 :ss) {
            System.out.println(s1);
        }*/
    }
}
