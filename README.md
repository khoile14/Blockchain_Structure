# P3 - Blockchain and Merkle Tree Implementation

This project represents a sophisticated and professional implementation of Blockchain and Merkle Tree data structures, meticulously designed in Java. It is engineered to efficiently read transactions from a designated file, strategically add them to a priority queue based on predefined criteria, and subsequently incorporate them into a robust blockchain.

Each block within the blockchain is ingeniously represented as a Merkle Tree, a testament to the project’s commitment to data integrity and security. This design choice not only enhances the overall performance but also provides a reliable and secure method for verification and validation of transactions within each block.

🚀 Getting Started

To get started with this project, you'll need to compile and run a few Java files. Here's a step-by-step guide:

Compile the Java Files: Start by compiling the provided Java files. These include:

- `Utilities.java`: Contains auxiliary methods including a cryptographic hash function and methods for loading and verifying transactions.
- `Transaction.java`: Represents an individual financial transaction, with most code provided and a requirement to complete missing parts.
- `SinglyLinkedList.java`: Implements a singly-linked list data structure with basic operations and iterability.
- `PriorityLine.java`: Implements a priority queue using SinglyLinkedList as the underlying structure.
- `Block.java`: Represents an individual block in the blockchain, storing transactions and the root of the Merkle tree for efficient verification.
- `Blockchain.java`: Represents the entire blockchain structure, holding a list of Block objects and building the chain from transactions in the priority queue.
- `MerkleTree.java`: Represents a Merkle tree data structure, stored as a linked structure without using arrays.
- `Order.java`: An enumeration for depth-first traversal types, provided with no need for edits.
- `transaction.txt`: A text files that will be read from the `Main.java`

Run the Example: Once you've compiled the Java files, run the provided `Main.java` file. This file contains a small example that demonstrates how to use the classes implemented in this project.
