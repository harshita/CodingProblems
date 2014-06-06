/**
 * Created with IntelliJ IDEA.
 * User: Harshita Karande
 */
public class CorruptCircularLinkedList {
    String data;
    CorruptCircularLinkedList next;

    public CorruptCircularLinkedList(String data) {
        this.data = data;
        this.next = null;
    }

    /*Adds the next node to the current node */
    public CorruptCircularLinkedList addNode(String data){
        CorruptCircularLinkedList newNode = new CorruptCircularLinkedList(data);
        this.next = newNode;
        return newNode;

    }

    /*Feature of corrupt circular linked list to loop to any node except the start     */
    public CorruptCircularLinkedList addNodeAndLoopTo(String data, CorruptCircularLinkedList loopto) {
        CorruptCircularLinkedList newNode = new CorruptCircularLinkedList(data);
        this.next = newNode;
        newNode.next = loopto;
        return newNode;

    }

    /*Find start of the loop*/
    public static CorruptCircularLinkedList findStartOfLoop(CorruptCircularLinkedList root){

        CorruptCircularLinkedList n1 = root;
        CorruptCircularLinkedList n2 = root;

        while(n1.next!=null && n2.next.next!=null) {
            n1=n1.next;
            n2=n2.next.next;
            if(n1==n2)
                break;

        }

        //If a loop is not present
        if (n1.next == null || n2.next.next == null)
            return null;

        n1 = root;
        while (n1 != n2) {
            n1=n1.next;
            n2=n2.next;
        }

        return n2;

    }


}



