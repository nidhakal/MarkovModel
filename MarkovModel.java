

public class MarkovModel {

    private ST<String, Integer> markovModel;
    private static final int ASCII = 128;
    private ST<String, int[]> nChar;
    private String key;


    // creates a Markov model of order k for the specified text
    public MarkovModel(String text, int k) {

        markovModel = new ST<String, Integer>();
        nChar = new ST<String, int[]>();
        String circ = text + text.substring(0, k);
        int length = text.length();
        for (int i = 0; i < length; i++) {
            key = circ.substring(i, i + k);
            char val = circ.charAt(i + k);

            if (markovModel.contains(key)) {

                markovModel.put(key, markovModel.get(key) + 1);
                int[] charFreq = nChar.get(key);
                charFreq[val]++;
                nChar.put(key, charFreq);

            }
            else {

                markovModel.put(key, 1);
                int[] charFreq = new int[ASCII];
                charFreq[val]++;
                nChar.put(key, charFreq);
            }
        }
    }

    // returns the order k of this Markov model
    public int order() {

        int order = key.length();
        return order;

    }

    // returns a string representation of the Markov model (as described below)
    public String toString() {

        String input = "";

        for (String kgram : nChar) {

            input += kgram + ": ";
            int[] freq = nChar.get(kgram);

            for (int i = 0; i < ASCII; i++) {

                if (freq[i] != 0) {

                    input += ((char) i) + " " + freq[i] + " ";
                }
            }
            input += "\n";
        }

        return input;
    }

    // returns the number of times the specified kgram appears in the text
    public int freq(String kgram) {

        if (kgram.length() != key.length())

            throw new IllegalArgumentException("Kgram is not the correct length");

        if (markovModel.contains(kgram)) {

            int count = markovModel.get(kgram);
            return count;
        }

        else return 0;
    }


    // returns the number of times the character c follows the specified
    // kgram in the text
    public int freq(String kgram, char c) {

        if (kgram.length() != key.length())

            throw new IllegalArgumentException("Kgram is not the correct length");

        if (nChar.contains(kgram)) {

            int[] freq = nChar.get(kgram);
            int charFreq = freq[c];
            return charFreq;
        }

        else return 0;
    }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram) {

        if (kgram.length() != key.length()) {

            throw new IllegalArgumentException("Kgram is not the correct length");
        }
        int[] freq = nChar.get(kgram);
        int rand = StdRandom.discrete(freq);
        char randChar = (char) rand;
        return randChar;
    }


    // unit tests all methods in this class
    public static void main(String[] args) {

        String text1 = "banana";

        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text3 = "one fish two fish red fish blue fish";

        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));

    }
}


