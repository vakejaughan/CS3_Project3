class Node {
    int data; Node prev; Node next;

    public Node(int val) {
        this.data = val;
        this.prev = null;
        this.next = null;
    }
}

class DLinkedList{
    Node head; Node tail; int n = 0;
    DLinkedList() {this.head = null; this.tail = null;}

    private boolean isEmpty() {return this.head == null;}

    void insert(int val) { //to enter a node to the list, no matter if it is empty or has pre-existing nodes
        Node node = new Node(val);
        if (isEmpty()) { //if the list is empty
            head = node;
            tail = node;
            ++n;
            System.out.println("Node added: " + node.data);
        }
        else { // if there is one or more node in the list
            tail.next = node;
            node.prev = tail;
            tail = node;
            ++n;
            System.out.println("Node added: " + node.data);
        }
    }

    void findNode(int data) { //searches to find node with given int value
        if (isEmpty()) {
            System.out.println("The linked list is empty.");
        }
        else { //if list is not empty
            Node node = returnNode(data);
            if (node != null) { //if node is found
                System.out.println("Found it! Data found: " + data + ".");
            }
            else { //node is not found
                System.out.println("Node not found: " + data + ".");
            }
        }
    }

    void updateNode(int oldData, int newData) { //takes old data associated with a node, searches for it and if found updates with new data
        if (isEmpty()) { //if the list is empty
            System.out.println("You must add a Node to the list before being able to edit it.");
        }
        else { // if list is not empty
            Node node = returnNode(oldData);
            if (node != null) { //if the node is found, change data
                node.data = newData;
                System.out.println("Node saved with new data: " + node.data + ". Old data: " + oldData);
            }
            else {
                System.out.println("Node with data " + oldData + " does not exist");
            }
        }
    }

    void deleteNode(int val) { //deletes a node with given val
        if (!isEmpty()) { // if the linked list is not empty
            Node temp = head;
            boolean found = false;
            while (temp != null) {
                if (temp.data == val) { //if node found matches data that we want to delete
                    if (temp == head) { //if the node found is the only node in the list
                        deleteHead(); //let this method handle it from here
                    }
                    else if (temp == tail) { //if the node found is only the tail
                        deleteTail(); //let this method handle it from here
                    }
                    else { // if the node found is in between the head and tail - the only case this method will handle on its own
                        temp.prev.next = temp.next;
                        temp.next.prev = temp.prev;
                        n--;
                        System.out.println("Node deleted with value: " + val);
                    }
                    found = true;
                    break;
                }
                temp = temp.next;
            }
            if (!found) {
                System.out.println("No node with " + val + " value found.");
            }
        }
        else {
            System.out.println("The list is empty.");
        }
    }

    void deleteHead() { //deletes the head of the list
        if (isEmpty()) { //if the list is empty
            System.out.println("The list is empty. Nothing to delete.");
        }
        if (head == tail) { // if the head is the only node in the list
            head = null;
            tail = null;
        }
        else { // if there is more than one node in the list
            head = head.next;
            head.prev = null;
        }
        n--; // increment n
        System.out.println("Head node deleted!");

        if (head != null && tail != null) { //updated info
            System.out.println("Updated head: " + head.data);
            System.out.println("Updated tail: " + tail.data);
        }
    }

    void deleteTail() { //deletes the tail node
        if (isEmpty()) { //if the list is empty
            System.out.println("The list is empty. Nothing to delete.");
        }
        if (head == tail) { // if there is only one node in the list
            head = null;
            tail = null;
        }
        else { // if there is more than one node in the list
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
        }
        n--; //increment
        System.out.println("Tail node deleted!");

        if (head != null && tail != null) { //updated info
            System.out.println("Updated head: " + head.data);
            System.out.println("Updated tail: " + tail.data);
        }
    }

    void size() {  //prints total number of nodes
        System.out.println("Size of linked list: " + n);
    }

    void displayAll() { //print all data
        if (!isEmpty()) {
            Node temp = head;
            System.out.println("Display all: ");
            System.out.println();
            while (temp != null) {
                System.out.print(" <- (" + temp.data + ") -> ");
                temp = temp.next;
            }
            System.out.println();
        }
        else {
            System.out.println("Nothing to display. List is empty.");
        }
    }

    private Node returnNode (int data) { //a utility method to find nodes because I got tired of writing while loops
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) {
                return temp;
            } else if (temp.data != data && temp.next != null) {
                temp = temp.next;
            } else {
                break;
            }
        }
        return null;
    }
}

public class DoublyLinkedList {
    public static void main(String[]  args) {
        DLinkedList d = new DLinkedList();
        System.out.println("Initial list:");
        d.displayAll();

        System.out.println("Adding nodes 1 to 10:");
        for (int i = 1; i <= 10; i++) {
            d.insert(i);
        }
        d.displayAll();
        d.size();

        System.out.println("Searching for nodes:");
        d.findNode(10);
        d.findNode(15);
        d.findNode(25);
        d.findNode(30);

        System.out.println("Updating nodes:");
        d.updateNode(10, 11);
        d.updateNode(17, 20);
        d.displayAll();
        d.size();

        System.out.println("Deleting nodes:");
        d.deleteNode(11);
        d.displayAll();
        d.size();
        d.deleteNode(15);
        d.deleteNode(20);
        d.deleteNode(5);
        d.deleteNode(25);
        d.size();
        d.displayAll();

        System.out.println("Adding new nodes:");
        d.insert(5);
        d.insert(10);
        d.insert(15);
        d.insert(20);
        d.insert(25);
        d.size();
        d.displayAll();

        System.out.println("Deleting first and last nodes:");
        d.deleteHead();
        d.displayAll();
        d.size();
        d.deleteTail();
        d.displayAll();
        d.size();

        System.out.println("Deleting multiple nodes:");
        d.deleteHead();
        d.deleteHead();
        d.deleteHead();
        d.deleteHead();
        d.displayAll();
        d.size();

        System.out.println("Deleting remaining nodes:");
        d.deleteHead();
        d.deleteTail();
        d.deleteNode(5);
        d.displayAll();
        d.size();

        System.out.println("Attempting to delete nodes not in the list:");
        d.deleteNode(7);
        d.displayAll();
        d.deleteNode(8);
        d.displayAll();
        d.deleteNode(9);
        d.displayAll();
        d.deleteTail();
        d.displayAll();
        d.deleteHead();
        d.displayAll();
        d.size();
    }
}
