/**
 * Created with IntelliJ IDEA.
 * User: Harshita Karande
 */
public class CorruptCircularLinkedListTest {
    public static void main(String args[]) {
        CorruptCircularLinkedList a = new CorruptCircularLinkedList("A");
        CorruptCircularLinkedList b = a.addNode("B");
        CorruptCircularLinkedList c = b.addNode("C");
        CorruptCircularLinkedList d = c.addNode("D");
        CorruptCircularLinkedList e = d.addNode("E");
        CorruptCircularLinkedList f = e.addNode("F");
        CorruptCircularLinkedList g = f.addNode("G");
        CorruptCircularLinkedList h = g.addNode("H");
        CorruptCircularLinkedList i = h.addNode("I");
        CorruptCircularLinkedList j = i.addNode("J");
        CorruptCircularLinkedList k = j.addNodeAndLoopTo("K", d);

        CorruptCircularLinkedList loopStart =  CorruptCircularLinkedList.findStartOfLoop(a);
        if (loopStart!=null)
            System.out.println("The start of the loop is at: "+ loopStart.data);
        else
            System.out.println("Start of loop not found");
    }
}
