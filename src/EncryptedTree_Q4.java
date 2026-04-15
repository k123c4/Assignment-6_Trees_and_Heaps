public class EncryptedTree_Q4 {

    public boolean findElement(int[] bt, int t) {
        if (bt == null || bt.length == 0) {
            return false;
        }


        int[] unencrypted = new int[bt.length];

        if (bt[0] == -2) {
            unencrypted[0] = 1;
        } else {
            return false;
        }

        if (unencrypted[0] == t) {
            return true;
        }

        for (int i = 0; i < bt.length; i++) {
            if (bt[i] == -2) {
                int currentVal = unencrypted[i];


                int leftIndex = 2 * i + 1;
                if (leftIndex < bt.length && bt[leftIndex] == -2) {
                    unencrypted[leftIndex] = 3 * currentVal + 1;
                    if (unencrypted[leftIndex] == t) return true;
                }

                int rightIndex = 2 * i + 2;
                if (rightIndex < bt.length && bt[rightIndex] == -2) {
                    unencrypted[rightIndex] = 2 * currentVal + 5;
                    if (unencrypted[rightIndex] == t) return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        EncryptedTree_Q4 solver = new EncryptedTree_Q4();


        int[] bt = {-2, -1};
        int t = 1;

        System.out.println("Target " + t + " found: " + solver.findElement(bt, t));
    }
}
