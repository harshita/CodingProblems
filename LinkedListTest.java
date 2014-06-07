/**
 * Created with IntelliJ IDEA.
 * User: Harshita Karande
 */

import java.util.Scanner;

public class LinkedListTest {
    public static void main(String args[]) {
        LinkedList a = new LinkedList(1);
        LinkedList b = a.addNode(2);
        LinkedList c = b.addNode(3);
        LinkedList d = c.addNode(4);
        LinkedList e = d.addNode(5);
        LinkedList f = e.addNode(6);
        LinkedList g = f.addNode(7);
        LinkedList h = g.addNode(8);
        LinkedList i = h.addNode(9);
        LinkedList j = i.addNode(10);


        Scanner scan = new Scanner(System.in);
        System.out.println("Enter N: ");
        int N = scan.nextInt();

        LinkedList NElement = LinkedList.findNElementFromLast(a, N);
        if (NElement == null)
            System.out.println("Insufficient number of elements in Linked List. Please try again.");
        else
            System.out.println("The element at Nth position from last is: " + NElement.data);
    }
}
