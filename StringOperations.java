/**
 * Created with IntelliJ IDEA.
 * User: Harshita Karande
 */
import java.util.Scanner;
public class StringOperations {
   static  int totalCount=0;

    /*
     Given a string with multiple spaces anywhere in the string
     this function does an in-place trim of all spaces leaving a single space between words
    */
    public static String inPlaceTrim(String str){
        char[] strArray = str.toCharArray();
        int ptr1 = 0;
        int ptr2 = 0;
        if(strArray.length>0){

            ptr2 = advancePointerToNextCharFromSpace(ptr2, strArray);


            if(ptr1 == ptr2){
            ptr1 = advancePointerToNextSpaceFromChar(ptr1, strArray);

            ptr2 = ptr1;

            ptr2 = advancePointerToNextCharFromSpace(ptr2, strArray);

            }

           //now we have ptr1<ptr2 and ptr1 pointing to space and ptr2 pointing to character
           //we can now start swapping
            while(ptr2<strArray.length) {
                if(ptr1>0 && ptr2 - ptr1>1)   {

                    //if more than one space between previous and next word
                    //we want to leave a space between words, so point to the next space
                    ptr1++;

                    while(ptr2< strArray.length && !Character.isWhitespace(strArray[ptr2])){
                        strArray[ptr1] = strArray[ptr2];
                        strArray[ptr2] = ' ';
                        ptr1 ++;
                        ptr2 ++;

                    }

                    ptr2 = advancePointerToNextCharFromSpace(ptr2,strArray);

                }

                // which means there is already a space between previous word and next word, we want to find the next space and character
                else if (ptr1>0 && ptr2 - ptr1==1){
                    ptr1++;
                    ptr1 = advancePointerToNextSpaceFromChar(ptr1, strArray);

                    ptr2 = ptr1;

                    ptr2 = advancePointerToNextCharFromSpace(ptr2, strArray);

                }

                //if the space is at the very beginning, just swap
                else if(ptr1==0){
                    while(ptr2<strArray.length && !Character.isWhitespace(strArray[ptr2])){
                       strArray[ptr1] = strArray[ptr2];
                       strArray[ptr2] = ' ';
                       ptr1 ++;
                       ptr2 ++;

                    }

                    ptr2 = advancePointerToNextCharFromSpace(ptr2,strArray);

                }

            }

        }
        return new String(strArray);
    }

    public static int advancePointerToNextSpaceFromChar(int ptr, char[] strArray){
        while(ptr<strArray.length && !Character.isWhitespace(strArray[ptr])) {
            ptr++;
        }
        return ptr;
    }

    public static int advancePointerToNextCharFromSpace(int ptr, char[] strArray){
        while(ptr<strArray.length && Character.isWhitespace(strArray[ptr])) {
            ptr++;

        }
        return ptr;
    }

    public static void getOccurrencesIn2DArray(char[][] strArray, String strToSearch, int maxRow, int maxColumn) {
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxColumn; j++) {
              StringOperations.checkForCharInIndex(strArray, strToSearch.toCharArray(), 0, i, j, maxRow, maxColumn);
            }
        }
        System.out.println("Total count is: "+StringOperations.totalCount);
    }

    public static void checkForCharInIndex(char[][] strArray, char[] strToSearch, int index, int row, int column, int maxRow, int maxColumn) {
        if (strArray[row][column] == strToSearch[index] && index + 1 < strToSearch.length) {
            if (row - 1 >= 0)
                checkForCharInIndex(strArray, strToSearch, index+1, row-1, column, maxRow, maxColumn);
            if (row + 1 < maxRow)
                checkForCharInIndex(strArray, strToSearch, index+1, row+1, column, maxRow, maxColumn);
            if (column - 1 >= 0)
                checkForCharInIndex(strArray, strToSearch, index+1, row, column-1, maxRow, maxColumn);
            if (column + 1 < maxColumn)
                checkForCharInIndex(strArray, strToSearch, index+1, row, column+1, maxRow, maxColumn);
        } else if (strArray[row][column] == strToSearch[index] && index + 1 == strToSearch.length) {
            StringOperations.totalCount += 1;
        }
    }

    /*
    Given a string (for example: "a?bc?def?g"),
    this method generates all the possible strings by replacing ? with 0 and 1.
     */
    public static void replaceQuestionMarksWithBinaryStrings(String str) {
        int count = 0;
        char[] strArray = str.toCharArray();

        //Get the count of question marks
        for (int i=0; i < strArray.length; i++) {
            if(strArray[i]=='?')
                count += 1;

        }

        //Run the for loop from 0 to 2^count-1
        for (int j=0 ; j<new Double(Math.pow(2, count)).intValue(); j++) {
            String binary = StringOperations.decimalToBinary(j);

            int zeroestofill = count - binary.length();
            int ctr1 = 0;
            int ctr2 = 0;

            char[] tempStrArray = new String(str).toCharArray();
            for (int k = 0; k < tempStrArray.length; k++) {
                if (tempStrArray[k]=='?') {
                    if (ctr1 < zeroestofill) {
                         tempStrArray[k] = '0';
                         ctr1++;
                    }
                    else {
                        tempStrArray[k] = binary.charAt(ctr2);
                        ctr2++;
                    }
                }
            }
            System.out.println(tempStrArray);
        }





    }

    //Convert dec to binary.
    public static String decimalToBinary(int dec) {
       int remainder = dec % 2;
       if (dec >> 1 >= 1)
           return decimalToBinary(dec >> 1) + Integer.valueOf(remainder).toString();

       return Integer.valueOf(remainder).toString();
    }




    public static void main(String args[]){

        //Test In-place trim here
        System.out.println("Please enter a string that you want to do in-place trim on: ");
        Scanner scan= new Scanner(System.in);
        String strInput = scan.nextLine();
        System.out.println("After trimming: "+ StringOperations.inPlaceTrim(strInput));

        System.out.println();

        //Test count of occurences of string in 2D array here
        System.out.println("Output of occurences of a string in 2D array");
        char[][] strArray = new char[][]{
            {'S', 'N', 'B', 'S', 'N'},
            {'B', 'A', 'K', 'E', 'A'},
            {'B', 'K', 'B', 'B', 'K'},
            {'S', 'E', 'B', 'S', 'E'}
        };


        StringOperations.getOccurrencesIn2DArray(strArray, "SNAKES", 4, 5);

        System.out.println();


        // Generate all possible strings by substituting ? in the strings with 0 and 1
        System.out.println("Please enter a string that you want to perform substitutions on: ");
        scan= new Scanner(System.in);
        strInput = scan.nextLine();
        System.out.println("Possibilities are: ");
        StringOperations.replaceQuestionMarksWithBinaryStrings(strInput);


    }
}
