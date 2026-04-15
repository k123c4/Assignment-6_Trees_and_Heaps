public class HuffmanNode_Q3 {
        char data;
        int frequency;
        HuffmanNode_Q3 left;
        HuffmanNode_Q3 right;

        public HuffmanNode_Q3(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }
    }
