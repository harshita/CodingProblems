
/**
 * Author: Harshita Karande
 * This program prints all the permutations of a given string and also number of permutations
 */
import java.util.ArrayList;
import java.util.Scanner;
public class Permutations {
    public static void main(String args[]){
        System.out.println("Enter a string: ");
        Scanner scan=new Scanner(System.in);
        String str=scan.nextLine();
       ArrayList<String> permutations=permute(str);
        for(int i=0;i<permutations.size();i++)
            System.out.println(permutations.get(i));

        System.out.println("Total permutations: "+permutations.size());

    }

    public static ArrayList<String> permute(String str){
        ArrayList<String> permutations=new ArrayList<String>();
        if(str==null)
             return null;

        if(str.length()==0){
            permutations.add("");
            return permutations;

        }

        String first=str.substring(0,1);
        String remaining=str.substring(1);
        ArrayList<String> words=permute(remaining);
        for(String word:words){
                for(int i=0;i<=word.length();i++){
                    String temp=word.substring(0,i)+first+word.substring(i);
                    permutations.add(temp);
                }
        }
        return permutations;






    }
}
