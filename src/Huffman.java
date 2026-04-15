import java.util.*;

public class Huffman {
    private Map<Character, Integer> freqMap;
    private Map<Character, String> charToCode;
    private HuffmanNode root;
    private String encodedString;

    public Huffman() {
        freqMap = new HashMap<>();
        charToCode = new HashMap<>();
    }

    public void frequencyCount(String text) {
        freqMap.clear();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) + 1);
            } else {
                freqMap.put(c, 1);
            }
        }
    }

    public void buildHuffman() {
        List<HuffmanNode> nodeList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            nodeList.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (nodeList.size() > 1) {
            int firstMinIndex = findMinIndex(nodeList);
            HuffmanNode left = nodeList.remove(firstMinIndex);

            int secondMinIndex = findMinIndex(nodeList);
            HuffmanNode right = nodeList.remove(secondMinIndex);

            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            nodeList.add(parent);
        }

        if (!nodeList.isEmpty()) {
            root = nodeList.get(0);
        }
    }

    private int findMinIndex(List<HuffmanNode> list) {
        int minIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).frequency < list.get(minIndex).frequency) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void genCode() {
        charToCode.clear();
        if (root != null) {
            generateRecursive(root, "");
        }
    }

    private void generateRecursive(HuffmanNode node, String code) {
        if (node.left == null && node.right == null) {
            charToCode.put(node.data, code);
            return;
        }
        if (node.left != null) generateRecursive(node.left, code + "0");
        if (node.right != null) generateRecursive(node.right, code + "1");
    }

    public String encode(String text) {
        StringBuilder sb = new StringBuilder();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append(charToCode.get(chars[i]));
        }
        this.encodedString = sb.toString();
        return this.encodedString;
    }

    public void printStats() {
        System.out.println("Frequencies:");
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            String label = entry.getKey() == ' ' ? "(space)" : entry.getKey().toString();
            System.out.println(label + " : " + entry.getValue());
        }
        System.out.println("\nHuffman Codes:");
        for (Map.Entry<Character, String> entry : charToCode.entrySet()) {
            String label = entry.getKey() == ' ' ? "(space)" : entry.getKey().toString();
            System.out.println(label + ": " + entry.getValue());
        }
        System.out.println("\nEncoded:");
        System.out.println(encodedString);
    }
}