/**
 * Created with IntelliJ IDEA.
 * User: Harshita Karande
 */

import java.util.Scanner;

public class LinkedListTest {
    public static void main(String args[]) {
        LinkedList a = new LinkedList(1);
        LinkedList t = a;
        int numElements = 10;
        for (int i = 2; i <= numElements; i++) {
            t = t.addNode(i);
        }

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
