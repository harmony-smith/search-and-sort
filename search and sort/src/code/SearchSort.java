package code;

import java.util.Scanner;

public class SearchSort {

	public static void main(String[] args) {
		int choice = 0;
		int[] array = new int[100];
		Scanner input = new Scanner(System.in);
		int target = 0;
		
		do {
			System.out.println("================");
			System.out.println("0.  Exit");
			System.out.println("1.  Populate Sequentially");
			System.out.println("2.  Populate Randomly");
			System.out.println("3.  Check Sort");
			System.out.println("4.  Display");
			System.out.println("5.  Shuffle");
			System.out.println("6.  Linear Search");
			System.out.println("7.  Binary Search");
			System.out.println("8.  Bubble Sort");
			System.out.println("9.  Sta-bubble Sort");
			System.out.println("10. Selection Sort");
			System.out.println("11. Insertion Sort");
			System.out.println("12. Radix Sort");
			System.out.println("13. Quick Sort");
			System.out.println("================");
			
			choice = input.nextInt();
			
			if (choice == 1) {
				populateSequentially(array);
			} else if (choice == 2) {
				populateRandomly(array);
			} else if (choice == 3) {
				if (checkSort(array)) {
					System.out.println("Sorted");
				} else {
					System.out.println("Not Sorted");
				}
			} else if (choice == 4) {
				display(array);
			} else if (choice == 5) {
				shuffle(array);
			} else if (choice == 6) {
				System.out.println("Target:");
				target = input.nextInt();
				System.out.println("Index: " + linearSearch(array, target));
			}
			else if (choice == 7) {
				System.out.println("Target:");
				target = input.nextInt();
				System.out.println("Index: " + binarySearch(array, target));
			} else if (choice == 8) {
				bubbleSort(array);
			} else if (choice == 9) {
				bubbleSortStable(array);
			} else if (choice == 10) {
				selectionSort(array);
			} else if (choice == 11) {
				insertionSort(array);
			} else if (choice == 12) {
				array = radixSort(array);
			} else if (choice == 13) {
				qSort(array, 0, array.length - 1);
			}
			//kept if else structure in case of future add ons to program
			
			
		} while (choice != 0);
		
		input.close();
	}
	
	public static void populateSequentially (int[] array) {
		for (int x = 0; x < array.length; x++) {
			array[x] = x + 1;
		}
	}
	
	public static void populateRandomly (int[] array) {
		for (int x = 0; x < array.length; x++) {
			array[x] = (int)(Math.random() * array.length + 1);
		}
	}
	
	public static boolean checkSort (int[] array) {
		for (int x = 0; x < array.length - 1; x++) {
			if (array[x + 1] < array[x]) {
				return false;
			}
		}
		return true;
	}
	
	public static void display (int[] array) {
		for (int x = 0; x < array.length; x++) {
			System.out.print(array[x] + " ");
			if ((x + 1)% 10 == 0) {
				System.out.print("\n");
			}
		}
	}
	
	public static void shuffle (int[] array) {
		int temp = 0;
		for (int x = 0; x < array.length; x++) {
			int rand = (int)(Math.random() * array.length);
			temp = array[x];
			array[x] = array[rand];
			array[rand] = temp;
		}
	}

	public static int linearSearch(int[] array, int target) {
		for (int x = 0; x < array.length; x++) {
			if (target == array[x]) {
				return x;
			}
		}
		return -1;
	}
	
	public static int binarySearch(int[] array, int target) {
		int min = 0, max = array.length - 1, middle = 0;
		
		if (target == array[min]) {
			return 0;
		} else if (target == array[max]) {
			return max;
		}
		
		while (max - min > 1) {
			middle = (max + min)/2;
			if (array[middle] == target) {
				return middle;
			} else if (array[middle] > target) {
				max = middle;
			} else {
				min = middle;
			}
		}
		
		return -1;
	}
	
	public static void bubbleSort(int[] array) {
		int temp = 0;
		
		for (int x = 0; x < array.length - 1; x++) {
			for (int y = x + 1; y < array.length; y++) {
				if (array[x] > array[y]) {
					temp = array[x];
					array[x] = array[y];
					array[y] = temp;
				}
			}
		}
	}
	
	public static void bubbleSortStable(int[] array) {
		int temp = 0;
		for (int x = 0; x < array.length - 1; x++) {
			for (int y = 0; y < array.length - (x + 1); y++) {
				if (array[y] > array[y + 1]) {
					temp = array[y];
					array[y] = array[y + 1];
					array[y + 1] = temp;
				}
			}
		}
	}
	
	public static void selectionSort(int[] array) {
		int temp = 0, min = 0, max = 0;
		for (int x = 0; x < array.length/2; x++) {
			min = x;
			max = x;
			for (int y = x; y < array.length - x; y++) {
				if (array[y] < array[min]) {
					min = y;
				} else if (array[y] > array[max]) {
					max = y;
				}
			}
			
			temp = array[x];
			array[x] = array[min];
			array[min] = temp;
			
			if (max == x) {
				temp = array[array.length - (x + 1)];
				array[array.length - x] = array[min];
				array[min] = temp;
			} else {
				temp = array[array.length - (x + 1)];
				array[array.length - (x + 1)] = array[max];
				array[max] = temp;
			}
		}
	}
	
	public static void insertionSort(int[] array) {
		int temp = 0;
		for (int x = 1; x < array.length; x++) {
			int y = x - 1;
			while (y > -1 && array[y] > array[x]) {
				y--;
			}
			temp = array[x];
			for (int z = x; z > y + 1; z--) {
				array[z] = array[z - 1];
			}
			array[y + 1] = temp;
		}
	}
	
	public static int[] radixSort(int[] array) {
		int max = getMax(array);
		int place = 0;
		int[] copy = new int[array.length];
		
		for (int position = 0; max >= Math.pow(10, position) ; position++) {
			place = 0;
			for (int x = 0; x < 10; x ++) {
				for (int y = 0; y < array.length; y++) {
					if (x == getDigit(array[y], position)) {
						copy[place] = array[y];
						place++;
					}
				}
			}
			array = copy;
			copy = new int[array.length];
		}
		
		return array;
	}
	
	public static int getMax (int[] array) {
		int max = array[0];
		for (int x = 1; x < array.length; x++) {
			if (array[x] > array[max]) {
				max = array[x];
			}
		}
		return max;
	}
	
	public static int getDigit(int num, int place) {
		return (int)(num / Math.pow(10, place)) % 10;
	}
	
	public static void qSort (int[] array, int min, int max) {
		int LS = min;
		int RS = max;
		int temp = 0;
		
		while (LS != RS) {
			for (;array[RS] >= array[LS] && LS != RS; RS--);
			temp = array[RS];
			array[RS] = array[LS];
			array[LS] = temp;
			
			for (;array[LS] <= array[RS] && LS != RS; LS++);			
			temp = array[RS];
			array[RS] = array[LS];
			array[LS] = temp;
		}
			
		if (LS - min > 1) {
			qSort(array, min, LS - 1);
		}
		
		if (max - LS > 1) {
			qSort(array, LS + 1, max);
		}
	}
}
