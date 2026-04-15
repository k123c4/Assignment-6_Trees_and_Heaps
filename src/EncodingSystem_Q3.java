import java.util.*;

public class EncodingSystem_Q3 {
    private Huffman_Q3 huffman;
    private List<String> originalTexts;
    private List<String> encodedStrings;

    public EncodingSystem_Q3() {
        this.huffman = new Huffman_Q3();
        this.originalTexts = new ArrayList<>();
        this.encodedStrings = new ArrayList<>();
    }

    public void initialize(String[] texts) {
        for (int i = 0; i < texts.length; i++) {
            originalTexts.add(texts[i]);
            huffman.frequencyCount(texts[i]);
            huffman.buildHuffman();
            huffman.genCode();
            encodedStrings.add(huffman.encode(texts[i]));
        }
    }

    public String highestCode() {
        if (encodedStrings.isEmpty()) return "";
        String longest = encodedStrings.get(0);
        for (int i = 1; i < encodedStrings.size(); i++) {
            if (encodedStrings.get(i).length() > longest.length()) {
                longest = encodedStrings.get(i);
            }
        }
        return longest;
    }

    public void shuffleCodes() {
        Collections.shuffle(encodedStrings);
    }

    public void stats() {
        for (int i = 0; i < originalTexts.size(); i++) {
            System.out.println("Stats for \"" + originalTexts.get(i) + "\"");
            huffman.frequencyCount(originalTexts.get(i));
            huffman.buildHuffman();
            huffman.genCode();
            huffman.encode(originalTexts.get(i));
            huffman.printStats();
            System.out.println("-------------------------");
        }
    }

    public static void main(String[] args) {
        EncodingSystem_Q3 system = new EncodingSystem_Q3();
        String[] texts = {"marcus fenix is a gear"};
        system.initialize(texts);

        System.out.println("Highest Code Result: " + system.highestCode());
        system.stats();
    }
}