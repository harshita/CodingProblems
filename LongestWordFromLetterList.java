
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * User: Harshita Karande
 */

/**
 * Find the longest words in a given list of words that can be constructed from a given list of letters.
 * The remaining arguments define the list of legal letters. A letter may not appear in any single word
 * more times than it appears in the list of letters
 */
public class LongestWordFromLetterList {
    int[][] cost;
    char[][] direction;

    public String findLongestWordFromLetterList(ArrayList<String> strList, char[] letterList) {
        String longestWord = "";
        int maxLength = Integer.MIN_VALUE;
        HashMap<Character, Integer> originalMap = new HashMap<Character, Integer>();
        for (int i = 0; i < letterList.length; i++) {
            if(originalMap.containsKey(letterList[i])) {
                originalMap.put(letterList[i], originalMap.get(letterList[i])+1);
            }
            else
                originalMap.put(letterList[i], 1);
        }
        for(String str: strList) {
            HashMap<Character, Integer> strMap = new HashMap<Character, Integer>();
            for (int i = 0; i < str.length(); i++) {
                if (!originalMap.containsKey(str.charAt(i)))
                    continue;
                else if (!strMap.containsKey(str.charAt(i))) {
                    strMap.put(str.charAt(i), 1);
                }
                else if(strMap.containsKey(str.charAt(i)) && originalMap.containsKey(str.charAt(i)) && strMap.get(str.charAt(i)) + 1 <= originalMap.get(str.charAt(i))) {
                    strMap.put(letterList[i], strMap.get(letterList[i])+1);
                }
                else
                    continue;
            }

            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            Arrays.sort(letterList);
            int lcs = computeLCS(temp, letterList);
            if (lcs > maxLength){
                maxLength = lcs;
                longestWord = str;
            }


        }
        return longestWord;

    }

    public int computeLCS(char[] tempS1, char[] tempS2) {
        cost = new int[tempS1.length][tempS2.length];
        direction = new char[tempS1.length][tempS2.length];
        for (int i = 0; i < tempS1.length; i++) {
            for (int j = 0; j < tempS2.length; j++) {
                if (tempS1[i]  == tempS2[j]) {
                    cost[i][j] = getCost(i - 1, j - 1) + 1;
                    direction[i][j] = '\\';
                }
                else if (getCost(i - 1, j) >= getCost(i, j - 1)) {
                    cost[i][j] = getCost(i - 1, j);
                    direction[i][j] = '|';
                }
                else {
                    cost[i][j] = getCost(i, j - 1);
                    direction[i][j] = '-';
                }

            }
        }
        return getLCS(new String(tempS2), tempS1.length - 1, tempS2.length - 1);
    }

    public int getCost(int i, int j) {
        if (i == -1 || j == -1)
            return 0;
        return cost[i][j];
    }

    public int getLCS(String s2, int i, int j) {
        if (i == -1 || j == -1)
            return 0;
        if (direction[i][j] == '\\') {
            return 1 + getLCS(s2, i - 1, j - 1);
        }
        else if (direction[i][j] == '|') {
            return getLCS(s2, i - 1, j);
        }
        else
            return getLCS(s2, i, j - 1);

    }
    public static void main(String args[]) {
        LongestWordFromLetterList longestWord = new LongestWordFromLetterList();
        ArrayList<String> strList = new ArrayList<String>();
        strList.add("azotised");
        strList.add("bawdiest");
        strList.add("dystocia");
        strList.add("geotaxis");
        strList.add("iceboats");
        strList.add("oxidates");
        strList.add("oxyacids");
        strList.add("sweatbox");
        strList.add("tideways");
        strList.add("tidewaysxz");
        strList.add("ttidewwaysxz");

        String str = longestWord.findLongestWordFromLetterList(strList, new char[]{'w','g','d','a','s','x','z','c','y','t','e','i','o','b'});
        System.out.println("Longest word:" +str);

    }

}
