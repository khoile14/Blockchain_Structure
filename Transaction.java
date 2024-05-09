/**
 * This class represents a single transaction.
 */
public class Transaction implements Comparable<Transaction> {
    /**
     * The sender.
     */
    private String sender;
    /**
     * The receiver.
     */
    private String receiver;
    /**
     * The amount.
     */
    private int amount;
    /**
     * The fee.
     */
    private int fee;

    /**
     * Constructor.
     *
     * @param sender   sender.
     * @param receiver receiver.
     * @param amount   amount.
     * @param fee      fee.
     */
    public Transaction(String sender, String receiver, int amount, int fee) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.fee = fee;
    }

    /**
     * toString method.
     *
     * @return String representation of the transaction.
     */
    public String toString() {
        return String.format("%s %s %d %d", sender, receiver, amount, fee);
    }

    /**
     * Getter for fee.
     *
     * @return fee.
     */
    public int getFee() {
        return fee;
    }

    /**
     * Compares this transaction with the other transaction.
     *
     * @param o the object to be compared.
     * @return 1 if this transaction has a higher fee than the other transaction, -1
     *         if this transaction has a lower fee than the other transaction, 0 if
     *         they have the same fee.
     */
    @Override
    public int compareTo(Transaction o) {
        if (this.fee > o.fee) {
            return 1;
        } else if (this.fee < o.fee) {
            return -1;
        } else {
            return 0;
        }
    }
}
