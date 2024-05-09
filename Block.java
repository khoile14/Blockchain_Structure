import java.util.Iterator;

/**
 * Represents a single block in the blockchain.
 */

public class Block implements Comparable<Block>, Iterable<Transaction> {
    /**
     * A list of transactions in the block.
     */
    private SinglyLinkedList<Transaction> transactions;
    /**
     * The hash code of the root of the Merkle tree of transactions in the block.
     */
    private String rootHash;

    /**
     * Creates a new block with an empty list of transactions.
     */
    public Block() {
        transactions = new SinglyLinkedList<>();
    }

    /**
     * Adds a transaction to the block.
     *
     * @param t the transaction to be added.
     */
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    /**
     * return the number of transactions in the block.
     *
     * @return the number of transactions in the block.
     */
    public int numOfTransactions() {
        return transactions.size();
    }

    /**
     * return the hash code of the root of the Merkle tree of transactions in the
     * block.
     *
     * @return the hash code of the root of the Merkle tree of transactions in the
     * block.
     */
    public String getRootHash() {
        return rootHash;
    }

    /**
     * Sets the hash code of the root of the Merkle tree of transactions in the
     * block.
     *
     * @param hashCode the hash code of the root of the Merkle tree of transactions
     *                 in the block.
     */
    public void setRootHash(String hashCode) {
        rootHash = hashCode;
    }

    /**
     * Returns an iterator over the transactions in the block.
     *
     * @return an iterator over the transactions in the block.
     */
    public Iterator<Transaction> iterator() {
        return transactions.iterator();
    }

    /**
     * Compares this block with the specified block for order.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this block is less
     * than, equal to, or greater than the specified block.
     */
    @Override
    public int compareTo(Block o) {
        return this.numOfTransactions() - o.numOfTransactions();
    }

}
