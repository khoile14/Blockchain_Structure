import java.util.Iterator;

/**
 * The class represents the entire blockchain.
 */

public class Blockchain implements Iterable<Block> {
    /**
     * The blockchain is represented as a singly linked list of blocks.
     */
    private SinglyLinkedList<Block> blockchain;

    /**
     * The constructor takes a priority queue and creates the linked list of blocks.
     * If the queue is not empty then keep adding transactions into a block until
     * the total fees of the block is greater than or equal to the threshold.
     * Reset the total fees and create a new block.
     *
     * @param threshold is the minimum amount of cumulative fees that is required to
     *                  create a new block.
     * @param queue     is the priority queue of transactions.
     */
    public Blockchain(PriorityLine<Transaction> queue, int threshold) {
        blockchain = new SinglyLinkedList<>();
        Block block = new Block();
        int totalBlockFees = 0;
        while (!queue.isEmpty()) {
            Transaction transaction = queue.dequeue();
            block.addTransaction(transaction);
            totalBlockFees += transaction.getFee();
            if (totalBlockFees >= threshold) {
                blockchain.add(block);
                block = new Block();
                totalBlockFees = 0;
            }
        }
        if (totalBlockFees > 0) {
            String hash = block.getRootHash();
            block.setRootHash(hash);
            blockchain.add(block);
        }

    }

    /**
     * Iterator of the blockchain.
     *
     * @return an iterator of the blockchain.
     */
    public Iterator<Block> iterator() {
        return blockchain.iterator();
    }
}
