public class HuffmanNode {
        char data;
        int frequency;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }
    }
