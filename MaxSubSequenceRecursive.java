/**
 * Author: hkarande
 * Find max sum of subsequence given an array of values
 */
import java.util.Scanner;
public class MaxSubSequenceRecursive {
    int N;
    int MaxSum;
    int[] sequence;
    public static void main(String args[]){
        System.out.println("How many numbers do you want to enter?");

        MaxSubSequenceRecursive maxSubSequence=new MaxSubSequenceRecursive();
        maxSubSequence.readElements();
        System.out.println("Max sum of subsequence is "+maxSubSequence.getMaxSubSequence(0, (maxSubSequence.N-1)));

    }

    public void readElements(){
        Scanner scan=new Scanner(System.in);
        N=scan.nextInt();
        sequence=new int[N];
        for(int i=0;i<N;i++){
            System.out.println("Enter number "+(i+1));
            sequence[i]=scan.nextInt();
        }
    }

    public int getMaxSubSequence(int left, int right){

        int maxLeftSum, maxRightSum, maxLeftBorderSum, maxRightBorderSum, leftBorderSum, rightBorderSum;
        int center;
        if(left==right)
            return sequence[left];

        center=(left+right)/2;
        maxLeftSum=getMaxSubSequence(left,center);
        maxRightSum=getMaxSubSequence(center+1,right);

        maxLeftBorderSum=0;
        leftBorderSum=0;
        for(int i=center;i>=left;i--){
            leftBorderSum+=sequence[i];
            if(leftBorderSum>maxLeftBorderSum)
                maxLeftBorderSum=leftBorderSum;
        }

        maxRightBorderSum=0;
        rightBorderSum=0;
        for(int i=center+1;i<=right;i++){
            rightBorderSum+=sequence[i];
            if(rightBorderSum>maxRightBorderSum)
                maxRightBorderSum=rightBorderSum;


        }

        return Math.max((maxLeftBorderSum+maxRightBorderSum),Math.max(maxLeftSum,maxRightSum));



    }
}
