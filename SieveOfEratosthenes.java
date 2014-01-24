/**
 * Created with IntelliJ IDEA.
 * Author: hkarande
 * Date: 10/17/13
 * Time: 4:59 PM
 * Program to find all prime numbers upto a given limit using Sieve of Eratosthenes
 * Refer to http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes for more information
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class SieveOfEratosthenes {
    int N;
    List<Integer> numbers=new ArrayList<Integer>();
    public static void main(String args[]){
        SieveOfEratosthenes se=new SieveOfEratosthenes();
        se.readInputs();
        se.findPrimes();

    }

    public void readInputs(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter N: ");
        N=scan.nextInt();
        for(int i=2;i<=N;i++){
            numbers.add(i);
        }

    }

    public void findPrimes(){
        boolean isAtLeastOnePresent=false;
        for(Integer i:numbers){
            if(i<Math.sqrt(N)){
                isAtLeastOnePresent=true;
                if(numbers.contains(i)){
                    System.out.println(i+" ");
                    numbers.remove(i);
                    int multiple=2;
                    int currentNum=i;
                    i=2*currentNum;
                    while(i<=N){
                        //Integer in=new Integer(i);
                        if(numbers.contains(i))
                             numbers.remove(i);
                        multiple+=1;
                        i=multiple*currentNum;
                    }

                    break;

                }

            }
            else
                for(Integer j:numbers){
                    System.out.println(j+" ");
                }
                break;
        }
        if(isAtLeastOnePresent)
          findPrimes();

    }
}
