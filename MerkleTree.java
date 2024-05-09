import java.util.Iterator;

/**
 * This class represents the Merkle Tree of a single Block.
 */

public class MerkleTree {
    /**
     * Private variable height for the height of the tree.
     */
    private int height;
    /**
     * Private variable innerNodes for the number of inner nodes in the tree.
     */
    private int innerNodes;
    /**
     * Private variable root serves as pointer to the root of the tree.
     */
    private Node root;

    /**
     * Get the list of transactions from the block and construct the Merkle Tree.
     * Create a list of Node to contain the hash of each transaction.
     * Loop through the list of transactions and generate the hash of each
     * transaction then create a Node for each hash in the list of Node.
     * If the size of the list of Node is not a power of 2, add dummy nodes to the
     * list of Node until the size of the list of Node is a power of 2.
     * Call the makeTree method to construct the Merkle Tree.
     * Set the root hash of the block to the hash of the root of the Merkle Tree.
     *
     * @param block is the block for which the Merkle Tree is being constructed.
     */
    public MerkleTree(Block block) {
        Iterator<Transaction> transactions = block.iterator();
        if (!transactions.hasNext()) {
            root = null;
        } else {
            SinglyLinkedList<Node> hashes = new SinglyLinkedList<>();
            while (transactions.hasNext()) {
                Transaction transaction = transactions.next();
                String hash = Utilities.cryptographicHashFunction(transaction.toString());
                Node node = new Node(hash, null, null);
                hashes.add(node);
            }
            while (!powerOfTwo(hashes.size())) {
                String hashDummy = Utilities.cryptographicHashFunction("DUMMY");
                Node node = new Node(hashDummy, null, null);
                hashes.add(node);
            }
            root = makeTree(hashes);
            block.setRootHash(root.hash);
        }
    }

    /**
     * Check if the size of the list of Node is 1.
     * If the size of the list of Node is 1, set the height of the tree to the
     * current height of the tree.
     * Create 2 children nodes for the parent node.
     * Generate the hash for parent and create a Node for the parent.
     * Add the parent Node to the end of the list of Node.
     * Increment the number of inner nodes in the tree.
     * Remove the first two Node in the list of Node since they have been used.
     * Call the makeTree method recursively and increment the height of the tree.
     *
     * @param hashes is the list of Node containing the hash of each
     *               transaction.
     * @return the root of the Merkle Tree.
     */
    private Node makeTree(SinglyLinkedList<Node> hashes) {
        if (hashes.size() == 1) {
            return hashes.get(0);
        }

        Node kid1Left = hashes.get(0);
        Node kid2Right = hashes.get(1);
        String hash = Utilities.cryptographicHashFunction(kid1Left.hash, kid2Right.hash);
        Node parent = new Node(hash, kid1Left, kid2Right);

        hashes.add(parent);
        innerNodes++;
        hashes.remove(0);
        hashes.remove(0);

        return makeTree(hashes);
    }

    /**
     * Check if the size of the list of Node is a power of 2.
     *
     * @param size is the size of the list of Node.
     * @return true if the size of the list of Node is a power of 2, false
     * otherwise.
     */
    private boolean powerOfTwo(int size) {
        if (size == 0) {
            return false;
        } else {
            int a = (int) Math.max(Math.ceil(Math.log(size) / Math.log(2)), 1);
            this.height = a;
            if (Math.pow(2, a) == size) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * return the height of the tree.
     *
     * @return the height of the tree.
     */
    public int height() {
        return height;
    }

    /**
     * return the number of inner nodes in the tree.
     *
     * @return the number of inner nodes in the tree.
     */
    public int innerNodes() {
        return innerNodes;
    }

    /**
     * Create an empty queue and an empty list to store hash codes.
     * Enqueue the root of the tree to the queue.
     * While the queue is not empty and the front of the queue is not null, dequeue
     * the front and add the hash code of the front to the list of hash codes.
     * If the left child of the front is not null, enqueue the left child to the
     * queue.
     * If the right child of the front is not null, enqueue the right child to the
     * queue.
     *
     * @return a list of the hash codes contained in the tree by walking the tree in
     * a level-order.
     */
    public SinglyLinkedList<String> breadthFirstTraversal() {
        SinglyLinkedList<Node> temp = new SinglyLinkedList<>();
        SinglyLinkedList<String> result = new SinglyLinkedList<>();
        temp.add(root);

        while (!temp.isEmpty()) {
            Node node = temp.remove(0);
            result.add(node.hash);

            if (node.left != null) {
                temp.add(node.left);
            }
            if (node.right != null) {
                temp.add(node.right);
            }
        }

        return result;
    }

    /**
     * Create an empty list to store hash codes.
     * Call the helper function to traverse the tree in a certain order.
     *
     * @param order is an enumeration representing the three possible depth-first
     *              traversals.
     * @return a list of the hash codes contained in the tree by walking the tree in
     * a certain order.
     */
    public SinglyLinkedList<String> depthFirstTraversal(Order order) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        helperFunction(root, order, list);
        return list;
    }

    /**
     * Helper function that invokes the recursive traversal method based on the
     * Order.
     *
     * @param node  is the root of the tree.
     * @param order is an enumeration representing the three possible depth-first
     *              traversals.
     * @param list  is the list of hash codes.
     */
    private void helperFunction(Node node, Order order, SinglyLinkedList<String> list) {

        if (order == Order.PREORDER) {
            helperPreOrder(node, list);
        } else if (order == Order.INORDER) {
            helperInOrder(node, list);
        } else if (order == Order.POSTORDER) {
            helperPostOrder(node, list);
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * Recursive method that traverses the tree in pre-order.
     *
     * @param node is the root of the tree.
     * @param list is the list of hash codes.
     */
    private void helperPreOrder(Node node, SinglyLinkedList<String> list) {
        if (node == null) {
            return;
        }
        list.add(node.hash);
        helperPreOrder(node.left, list);
        helperPreOrder(node.right, list);
    }

    /**
     * Recursive method that traverses the tree in in-order.
     *
     * @param node is the root of the tree.
     * @param list is the list of hash codes.
     */
    private void helperInOrder(Node node, SinglyLinkedList<String> list) {
        if (node == null) {
            return;
        }
        helperInOrder(node.left, list);
        list.add(node.hash);
        helperInOrder(node.right, list);
    }

    /**
     * Recursive method that traverses the tree in post-order.
     *
     * @param node is the root of the tree.
     * @param list is the list of hash codes.
     */
    private void helperPostOrder(Node node, SinglyLinkedList<String> list) {
        if (node == null) {
            return;
        }
        helperPostOrder(node.left, list);
        helperPostOrder(node.right, list);
        list.add(node.hash);
    }

    /**
     * Create an empty list to store hash codes and call the helper function.
     *
     * @param t is the transaction that we want to verify it's contained
     * @return a list of the hash codes that are required to prove that a
     * transaction is contained in the block that this Merkle Tree encodes.
     */
    public SinglyLinkedList<String> extractProof(Transaction t) {
        SinglyLinkedList<String> proof = findProof(root, t);
        if (proof != null) {
            return proof;
        } else {
            return new SinglyLinkedList<>();
        }
    }

    /**
     * Check if the node is null.
     * Check the left side of the input node, if matches the hash code of the
     * transaction, add the hash code of the right child to the list of hash codes.
     * Check the right side of the input node, if matches the hash code of the
     * transaction, add the hash code of the left child to the list of hash codes.
     * Call the helper function recursively.
     *
     * @param node        is the root of the tree.
     * @param transaction is the transaction that we want to verify it's contained in a
     *                    certain block.
     * @return a list of the hash codes that are required to prove that a
     * transaction is contained in the block that this Merkle Tree encodes.
     */
    private SinglyLinkedList<String> findProof(Node node, Transaction transaction) {
        if (node == null) {
            return null;
        }

        if (node.left != null && node.left.hash.equals(Utilities.cryptographicHashFunction(transaction.toString()))) {
            SinglyLinkedList<String> proof = new SinglyLinkedList<>();
            proof.add(node.right.hash);
            return proof;
        }

        if (node.right != null && node.right.hash.equals(Utilities.cryptographicHashFunction(transaction.toString()))) {
            SinglyLinkedList<String> proof = new SinglyLinkedList<>();
            proof.add(node.left.hash);
            return proof;
        }

        SinglyLinkedList<String> leftProof = findProof(node.left, transaction);
        if (leftProof != null) {
            leftProof.add(node.right.hash);
            return leftProof;
        }

        SinglyLinkedList<String> rightProof = findProof(node.right, transaction);
        if (rightProof != null) {
            rightProof.add(node.left.hash);
            return rightProof;
        }

        return null;
    }

    /**
     * Private nested class Node that represents a node in the Merkle Tree.
     */
    private static class Node implements Comparable<Node> {
        /**
         * Private variable hash for the hash code of the node.
         */
        private String hash;
        /**
         * Private variable left for the left child of the node.
         */
        private Node left;
        /**
         * Private variable right for the right child of the node.
         */
        private Node right;

        /**
         * Constructor for the Node class.
         *
         * @param hash  is the hash code of the node.
         * @param left  is the left child of the node.
         * @param right is the right child of the node.
         */
        public Node(String hash, Node left, Node right) {
            this.hash = hash;
            this.left = left;
            this.right = right;
        }

        /**
         * return the result of comparing the 2 hash codes.
         *
         * @return the result of comparing the 2 hash codes.
         */
        @Override
        public int compareTo(Node o) {
            return this.hash.compareTo(o.hash);
        }
    }

}
