/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Partner Name:    Ada Lovelace
 *  Partner NetID:   alovelace
 *  Partner Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

public class TextGenerator {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        String first;

        String text = StdIn.readAll();

        MarkovModel model = new MarkovModel(text, k);
        if (k == 0) {
            k = 1;
        }

        first = text.substring(0, k);

        StdOut.print(first);
        for (int i = 0; i < t - k; i++) {
            char nextChar = model.random(first);
            first = first.substring(1) + nextChar;
            StdOut.print(nextChar);
        }
        StdOut.println();

    }
}
