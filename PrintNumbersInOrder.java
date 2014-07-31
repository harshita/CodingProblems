/**
 * @author Harshita Karande
 * Output top N positive integer in string comparison order. For example, let's say N=1000, then you need to output in string comparison order as below:
 * 1, 10, 100, 1000, 101, 102, ... 109, 11, 110, ...
 */
public class PrintNumbersInOrder {

	public static void main(String args[]) {
		PrintNumbersInOrder.printNumbers(1000);
	}
	public static void printNumbers(int n) {
		for (int i = 1; i <= 9; i++) {
			System.out.print(i + " ");
			printNumbersWithPrefix(i, n);
			System.out.println();
		}
	}
	
	public static void printNumbersWithPrefix(int prefix, int n) {
		int k = 0;
		while(k <= 9) {
			int newNumber = (prefix * 10) + k;
			if (newNumber <= n) {
				System.out.print(newNumber + " ");
				printNumbersWithPrefix(newNumber, n);
			}
			else {
				break;
			}
			k++;
 		}
		return;
	}
}