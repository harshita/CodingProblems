import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Author: Harshita Karande
 * Gray code is a binary numeral system where successive values differ in only 1 bit.
 * Given the number of bits as the input, this program will print all gray codes in order
 */
public class GrayCode {

    public static void main(String args[]){
        GrayCode gc=new GrayCode();
        System.out.println("Enter the number of bits of gray code");
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        ArrayList<Integer> temp=gc.grayCode(n);
        String strformat="%0"+Integer.toString(n)+"d";
        for(Integer i:temp){
            System.out.println(String.format(strformat,Integer.parseInt(gc.getbinaryform(i))));
        }

    }

    public String getbinaryform(int n){
        if(n<=1){
            return Integer.toString(n);
        }
        int remainder=n % 2;
        String rem = getbinaryform(n >> 1);
        return rem + Integer.toString(remainder);
    }

    public ArrayList<Integer> grayCode(int n) {

        ArrayList<Integer> graycodes=new ArrayList<Integer>();
        if(n==0){
            graycodes.add(0);
            return graycodes;
        }
        graycodes.add(0);
        graycodes.add(1);
        if(n==1)
            return graycodes;


        for(int i=2;i<=n;i++){
            ArrayList<Integer> l1= new ArrayList<Integer>();
            l1.addAll(graycodes);

            for(int m=l1.size()-1;m>=0;m--){
                int newVal=l1.get(m)+(int)Math.pow(2,i-1);
                graycodes.add(newVal);
            }

        }
        return graycodes;

    }
}
