import java.util.ArrayList;
import java.util.List;

public class HeapLengthFinder_Q5 {

    public void findAllLess(String[] s, int x) {
        if (s == null || s.length == 0) {
            System.out.println("[]");
            return;
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            String current = s[i];
            if (current != null) {
                if (current.length() < x) {
                    result.add(current);
                }
            } else {
                if (0 < x) {
                    result.add(null);
                }
            }
        }

        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print("\"" + result.get(i) + "\"");
            if (i < result.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }


    public static void main(String[] args) {
        HeapLengthFinder_Q5 finder = new HeapLengthFinder_Q5();

        String[] s = {"zero", "size", "nutella", "jojo", "luna", "isse", "astor", "as", "entretien", "", "cal"};
        int x = 3;

        finder.findAllLess(s, x);
    }
}