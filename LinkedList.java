/**
 * Created with IntelliJ IDEA.
 * User: Harshita Karande
 */
public class LinkedList {
    int data;
    LinkedList next;

    public LinkedList(int data){
           this.data = data;
           this.next = null;
    }

    /*Adds the next node to the current node */
    public LinkedList addNode(int data) {
        LinkedList newNode = new LinkedList(data);
        this.next = newNode;
        return newNode;

    }

    /* Returns the reference to the N from last element in LinkedList */
    public static LinkedList findNElementFromLast(LinkedList root, int N) {
        if (root == null || N < 1)
            return null;

        LinkedList ptr1 = root;
        LinkedList ptr2 = root;

        //Make ptr2 sit N steps away from ptr1
        int steps = 1;
        while(steps <= N) {
            ptr2 = ptr2.next;
            if(ptr2 == null)
                return null;
            steps++;

        }

        //Now increment ptr1 and ptr 2 one step at a time

        while (ptr2 != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
         //We show now have ptr1 N steps from last element
         return ptr1;


    }


}
