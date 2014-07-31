package code;

import java.util.HashMap;

/**
 * @author Harshita Karande
 * Find the longest sequence of prefix shared by all the words in a string.
 * "abcdef abcdxxx abcdabcdef abcyy" => "abc"
 */
class Tree {
    private char nodeValue;
    private HashMap<Character, Tree> adjacent;

    public Tree() {
        this.nodeValue = '\0';
        this.adjacent = new HashMap<Character, Tree>();

    }

    public void setNodeValue(char nodeValue) {
        this.nodeValue  = nodeValue;
    }

    public char getNodeValue() {
        return this.nodeValue;
    }

    public Tree addAdjacent(Tree adj) {
       if (!this.adjacent.containsKey(adj.getNodeValue())) {
           adjacent.put(adj.getNodeValue(), adj);
       }
        return adjacent.get(adj.getNodeValue());
    }

    public int numberOfAdjacentNodes() {
        return this.adjacent.size();
    }

    public Tree getTheOnlyAdjacent()  {
       for (Character s: this.adjacent.keySet()) {
           return this.adjacent.get(s);
       }
       return null;
    }




}

public class LongestPrefixOfStrings {
    public static void main(String args[]) {
       LongestPrefixOfStrings.printLongestPrefix(new String[] {"abcdef", "abcdxxx", "abcdabcdef", "abcdyy"});
    }

    public static void printLongestPrefix(String[] words) {
         Tree root = new Tree();
         for (int i = 0; i < words.length; i++) {
             buildTree(words[i], root);
         }

        if (root.numberOfAdjacentNodes() > 1)
            System.out.println("No prefix");
        else
            System.out.println(findPrefix(root.getTheOnlyAdjacent()));
    }

    public static void buildTree(String str, Tree root) {
        for (int i = 0; i < str.length(); i++) {
            Tree temp = new Tree();
            temp.setNodeValue(str.charAt(i));
            root = root.addAdjacent(temp);
        }

    }

    public static String findPrefix(Tree root) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(root.getNodeValue());
        while(root.numberOfAdjacentNodes() == 1) {
            root = root.getTheOnlyAdjacent();
            strBuilder.append(root.getNodeValue());
        }
        return strBuilder.toString();
    }
}
