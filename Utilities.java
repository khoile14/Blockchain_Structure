import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

/**
 * Utilities class.
 */

public final class Utilities {
    /**
     * Reads the transactions from a text file and adds them to a priority queue.
     *
     * @param pgmFile is the filename of the text file.
     * @return a priority queue containing the transactions.
     */

    public static PriorityLine<Transaction> loadTransactions(String pgmFile) {
        try {
            File file = new File(pgmFile);
            Scanner sc = new Scanner(file);
            PriorityLine<Transaction> pq = new PriorityLine<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.isEmpty())
                    continue;
                String[] arr = line.split(" ");
                String sender = arr[0];
                String receiver = arr[1];
                int amount = Integer.parseInt(arr[2]);
                int fee = Integer.parseInt(arr[3]);
                Transaction t = new Transaction(sender, receiver, amount, fee);
                pq.enqueue(t);
            }
            sc.close();
            return pq;
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Verifies if a transaction is contained in a block.
     *
     * @param t             is the transaction that we want to verify it's contained
     *                      in a certain block.
     * @param blockRootHash is the root hash code stored in the respective block.
     * @param proof         is the list of hashes extracted with the method
     *                      extractProof.
     * @return true if the transaction is verified, false otherwise.
     */
    public static boolean verifyTransaction(Transaction t, SinglyLinkedList<String> proof, String blockRootHash) {
        if (proof == null || proof.isEmpty() || blockRootHash == null || blockRootHash.isEmpty()) {
            return false;
        }

        if (proof.size() < 2 || (proof.size() & (proof.size() - 1)) != 0) {
            return false;
        }

        String a, b, parent = cryptographicHashFunction(t.toString());
        while (proof.size() > 0) {
            a = parent;
            b = proof.get(0);

            parent = cryptographicHashFunction(a, b);
            proof.remove(0);
        }
        return parent.equals(blockRootHash);
    }

    /**
     * SHA-256 cryptographic hash function for a single input.
     * 
     * @param input is the input string.
     * @return the hash code of the input string.
     */
    public static String cryptographicHashFunction(String input) {
        StringBuilder hexString = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            hexString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return hexString.toString();
    }

    /**
     * SHA-256 cryptographic hash function for a pair of inputs.
     * It uses the XOR bitwise operator to merge the two hash codes.
     * 
     * @param input1 is the first input string.
     * @param input2 is the second input string.
     * @return the hash code of the two input strings.
     */
    public static String cryptographicHashFunction(String input1, String input2) {
        StringBuilder hexString = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash1 = digest.digest(input1.getBytes(StandardCharsets.UTF_8));
            byte[] encodedhash2 = digest.digest(input2.getBytes(StandardCharsets.UTF_8));
            hexString = new StringBuilder(2 * encodedhash1.length);
            for (int i = 0; i < encodedhash1.length; i++) {
                String hex = Integer.toHexString(0xff & (encodedhash1[i] ^ encodedhash2[i]));
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return hexString.toString();
    }

}
