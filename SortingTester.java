/**
 * CSE 017
 * Stephen Friedman
 * saf217
 * Homework #5 DEADLINE: November 25, 2013 
 * Program Description: This program uses 3 sorting algorithms (insertion sort, merge sort, and quick sort)
 * to sort 10 different arrays that are either Integer or String arrays of size 10k, 25k, 50k, 75k, or 100k.
 * Each of these 10 different arrays are sorted using each of the three sorting algorithms. The length of time
 * it takes to sort each array is recorded. After all the sorting, the results of the time it took to sort each array
 *are printed to the screen. Here is one instance I got.
 *
 *Comparing sorts of lists on Integers:
Size	Insert	 Merge	 Quick
10000   74        12       10
25000   690       4       3
50000   3073      9      6
75000   7365      29      14
100000  14537     36       13

Comparing sorts of lists on Strings:
Size   Insert   Merge   Quick
10000   236      16       33
25000   1211     5        162
50000   5245     12        1006
75000   19510    22       2182
100000  40393    27       11427
 *
 *
 *These results tell a lot about different sorting algorithms. First off sorting Integers is much faster than sorting Strings.
 *This is probably a result of not having to use the compareTo method as well as possibly Strings may take more time to call up
 *from memory because Strings I believe take up more memory than Integers. Insertion sort is always the slowest while both
 *Merge sort and Quick sort are magnitudes faster at sorting that insertion sort. It appears that when sorting Integers with
 *quick sort, the amount needed to be sorted does not have much of an impact on the time it takes to sort. Merge sort seems to
 *only get slightly slower as the number of Integers to be sorted increases. When sorting Strings, merge sort seems to again
 *only get slightly slower as the number of Strings to be sorted increases, while quick sort certainly gets slower as the number
 *of Strings to be sorted increases. Overall it appears merge sort would be the sorting algorithm I would implement because
 *it appears to be very consistent and extremely fast.
 */
import java.util.Random;
public class SortingTester {

	/**
	 * This method takes in an array and uses the sorting algorithm insertion sort to sort it.
	 * @param list is the list to be sorted
	 */
	public static <E extends Comparable<E>> void insertionSort(E[]list)
	{
		for(int i=1; i<list.length;i++)
		{
			E temp=list[i];
			int j;
			
			for(j=i-1;j>=0 && temp.compareTo(list[j])<0;j--)
			{
				list[j+1]=list[j];
			}
			list[j+1]=temp;
		}
	}
	/**
	 * This method takes in an array and based on what kind of values the array hold (Integers or Strings)
	 * the appropriate merge sort algorithm is called in order to be able to account for the type of data
	 * in the array.
	 * @param list to be sorted
	 */
	public static <E extends Comparable<E>> void mergeSort(E[]list)
	{
        //if the array contains Integers then mergeSortInt is called
		if(list[0]instanceof Integer)
		{
			Integer[] toInt =(Integer[]) list;
			mergeSortInt(toInt);
		}
		//otherwise mergeSortString is called
		else if(list[0]instanceof String)
		{
			String[] toString = (String[])list;
			mergeSortString(toString);
		}
	}
	/**
	 * This method works with mergeInteger to sort an array of Integers using 
	 * the merge sort algorithm. This algorithm is modified to handle an array of Integers
	 * @param list is the list to be sorted
	 */
	public static void mergeSortInt(Integer[]list)
	{
		if(list.length>1)
		{
			Integer[] firstHalf=new Integer[list.length/2];
			System.arraycopy(list, 0, firstHalf, 0, list.length/2);
			mergeSortInt(firstHalf);
			
			int secondHalfLength=list.length-list.length/2;
			Integer[] secondHalf=new Integer[secondHalfLength];
			System.arraycopy(list, list.length/2, secondHalf, 0, secondHalfLength);
			mergeSortInt(secondHalf);
			mergeInteger(firstHalf,secondHalf,list);
			
		}
	}
	
	/**
	 * This method works with mergeString to sort an array of Strings using
	 * the merge sort algorithm. The algorithm is modified to be able to sort Strings.
	 * @param list is the list to be sorted
	 */
	public static void mergeSortString(String[]list)
	{
		if(list.length>1)
		{
			String[] firstHalf=new String[list.length/2];
			System.arraycopy(list, 0, firstHalf, 0, list.length/2);
			mergeSortString(firstHalf);
			
			int secondHalfLength=list.length-list.length/2;
			String[] secondHalf=new String[secondHalfLength];
			System.arraycopy(list, list.length/2, secondHalf, 0, secondHalfLength);
			mergeSortString(secondHalf);
			mergeString(firstHalf,secondHalf,list);
		}
	}
	/**
	 * This method does the comparing of the Integers and moving of the Integers in the arrays
	 * to their correct locations using the merge sort algorithm.
	 * @param list1 is the first half of the array to be sorted
	 * @param list2 is the second half of the array to be sorted
	 * @param temp is a temporary array to hold Integers
	 */
	public static void mergeInteger(Integer[] list1, Integer[] list2, Integer[] temp)
	{
		int current1=0;
		int current2=0;
		int current3=0;
		
		while(current1<list1.length && current2<list2.length)
		{
			if(list1[current1]<list2[current2])
			{
				temp[current3++]=list1[current1++];
			}
			else
			{
				temp[current3++]=list2[current2++];
			}
		}
		
		while(current1<list1.length)
		{
			temp[current3++]=list1[current1++];
		}
		while(current2<list2.length)
		{
			temp[current3++]=list2[current2++];
		}
	}
	/**
	 * This method works in the same way as mergeInteger except is modified to be able to
	 * accomodate Strings. The main difference is that to compare the Strings in the arrays
	 * the compareTo method is used.
	 * @param list1 is the first half of the array to be sorted
	 * @param list2 is the second half of the array to be sorted
	 * @param temp is a temporary array to hold Strings
	 */
	public static void mergeString(String[] list1, String[] list2, String[] temp)
	{
		int current1=0;
		int current2=0;
		int current3=0;
		
		while(current1<list1.length && current2<list2.length)
		{
			if(list1[current1].compareTo(list2[current2])<1)
			{
				temp[current3++]=list1[current1++];
			}
			else
			{
				temp[current3++]=list2[current2++];
			}
		}
		
		while(current1<list1.length)
		{
			temp[current3++]=list1[current1++];
		}
		while(current2<list2.length)
		{
			temp[current3++]=list2[current2++];
		}
	}
	/**
	 * This method calls quickSortInteger or quickSortString depending on the 
	 * kind of data that list contains. In the end, this method calls the necessary methods
	 * to sort list using the quick sort algorithm.
	 * @param list is the list to be sorted using quick sort
	 */
	public static <E extends Comparable<E>> void quickSort(E[]list)
	{
		//if list contains Integers then call quickSortInteger
		if(list[0]instanceof Integer)
		{
			Integer[] toInt =(Integer[]) list;
			quickSortInteger(toInt,0,list.length-1);
		}
		//otherwise call quickSortString if list contains Strings
		else if(list[0]instanceof String)
		{
			String[] toString = (String[])list;
			quickSortString(toString,0,list.length-1);
		}
	}
	
	/**
	 * This method works with partitionInteger to sort an array using the quick
	 * sort algorithm. This is a recursive method.
	 * @param list is the list to be sorted
	 * @param first is the first index in list
	 * @param last is the last index in list
	 */
	public static void quickSortInteger(Integer[]list,int first,int last)
	{
		if(last>first)
		{
			int pivotIndex=partitionInteger(list,first,last);
			quickSortInteger(list,first,pivotIndex-1);
			quickSortInteger(list,pivotIndex+1,last);
		}
	}
	
	/**
	 * This method handles comparing the Integers in list and moving the Integers
	 * into the correct spot in list. This uses the quick sort algorithm. This algorithm
	 * is modified to be able to accommodate for Integers.
	 * @param list is the list to be sorted
	 * @param first is the index of the first spot in list
	 * @param last is the index of the last spot in list
	 * @return the new pivotIndex
	 */
	public static int partitionInteger(Integer[] list, int first, int last)
	{
		Integer pivot=list[first];
		int low=first+1;
		int high=last;
		
		while(high>low)
		{
			while(low<=high && list[low]<=pivot)
			{
				low++;
			}
			while(low<=high && list[high]>pivot)
			{
				high--;
			}
			
			if(high>low)
			{
				Integer temp = list[high];
				list[high]=list[low];
				list[low]=temp;
			}
		}
		while(high>first && list[high]>=pivot)
		{
			high--;
		}
		
		if(pivot>list[high])
		{
			list[first]=list[high];
			list[high]=pivot;
			return high;
		}
		else
		{
			return first;
		}
	}
	
	/**
	 * Just like quickSortInteger, this method handles sorting a list 
	 * of Strings using the quick sort method. It is a recursive method 
	 * that uses the quick sort algorithm to sort list.
	 * @param list is the list to be sorted
	 * @param first is the index of the first spot in list
	 * @param last is the index of the last spot in list
	 */
	public static void quickSortString(String[]list,int first,int last)
	{
		if(last>first)
		{
			int pivotIndex=partitionString(list,first,last);
			quickSortString(list,first,pivotIndex-1);
			quickSortString(list,pivotIndex+1,last);
		}
	}
	/**
	 * This method is called by quickSortString and handles the comparing of Strings
	 * and moving of the Strings to their correct sorted spot in list, using the
	 * quick sort algorithm.
	 * @param list is the list to be sorted
	 * @param first is the index of the first spot in list
	 * @param last is the index of the last spot in list
	 * @return the sorted list
	 */
	public static int partitionString(String[]list,int first,int last)
	{
		String pivot=list[first];
		int low=first+1;
		int high=last;
		
		while(high>low)
		{
			while(low<=high && list[low].compareTo(pivot)<=0)
			{
				low++;
			}
			while(low<=high && list[high].compareTo(pivot)>0) 
			{
				high--;
			}
			
			if(high>low)
			{
				String temp = list[high];
				list[high]=list[low];
				list[low]=temp;
			}
		}
		while(high>first && list[high].compareTo(pivot)>=0)
		{
			high--;
		}
		
		if(pivot.compareTo(list[high])>0)
		{
			list[first]=list[high];
			list[high]=pivot;
			return high;
		}
		else
		{
			return first;
		}
	}
	
	/**
	 * This method given a size parameter creates and returns an array of Integers
	 * that is filled with random Integers. These Integers range in size from 0 to 
	 * size.
	 * @param size is the size of the array to be returned and is the bounds for the size of the random numbers
	 * @return the array of random Integers with a length of size
	 */
	public static Integer[] makeRandomIntList(int size)
	{
		Integer[] nums=new Integer[size];
		for(int i=0;i<size;i++)
		{
			Random r= new Random();
			nums[i]=r.nextInt(size+1);
		}
		return nums;
	}
	
	/**
	 * Creates and returns an array of random Strings that range from 3-8 chars long.
	 * Each String consists of random letters. The size parameter determines the 
	 * size of the String array to be returned.
	 * @param size is the size of the array to be created and returned
	 * @return a String array of random Strings of length size
	 */
	public static String[] makeRandomStringList(int size)
	{
		String[] stuff=new String[size];
		for(int i=0;i<size;i++)
		{
			int randomSize=(int) (Math.random()*6+3);
			for(int j=0;j<randomSize;j++)
			{
				int randomNum=(int) (Math.random()*26);
				if(stuff[i]==null)
				{
					stuff[i]=Character.toString((char)('a'+randomNum));
				}
				else
					stuff[i].concat(Character.toString((char)('a'+randomNum)));
			}
		}
		return stuff;
	}
	
	/**
	 * This method handles creating all the Integer and String arrays of sizes 10,000 , 25,000 ,
	 * 50,000 , 75,000 , and 100,000. Each of these arrays is declared and then passed into 
	 * each of the sorting algorithms. Clone arrays are what are actually passed into the algroithms
	 * to prevent the original randomized array from changing order, to keep consistency between 
	 * testing each array into each of the sorting algorithms. The amount of time it takes to sort
	 * each of these arrays is calculated by taking the time of the system before the sort is engaged 
	 * and is subtracted from the time of the system just after the sort has completed. These times for
	 * all 30 sorts is printed to the screen.
	 * @param args there are none given in this program
	 */
	public static void main(String[]args)
	{
	    //Randomized arrays of each size are created in both Integer and String forms
		Integer[] tenK=makeRandomIntList(10000);		
		String[] strings10000=makeRandomStringList(10000);
		
		Integer[] twentyfiveK=makeRandomIntList(25000);
		String[] strings25000=makeRandomStringList(25000);

		Integer[] fiftyK=makeRandomIntList(50000);
		String[] strings50000=makeRandomStringList(50000);
		
		Integer[] seventyfiveK=makeRandomIntList(75000);
		String[] strings75000=makeRandomStringList(75000);
		
		Integer[] hundredK=makeRandomIntList(100000);
		String[] strings100000=makeRandomStringList(100000);
		
		
		//Times are recorded for how long it takes to sort each array
		//Note that clones are used in each sort to prevent the original
		//arrays from being sorted
		long insertionSort10000Start=System.currentTimeMillis();
		insertionSort(tenK.clone());
		long insertionSort10000End=System.currentTimeMillis();
		long insertionSort10000Total=insertionSort10000End-insertionSort10000Start;
		
		long mergeSort10000Copy1Start=System.currentTimeMillis();
		mergeSort(tenK.clone());
		long mergeSort10000Copy1End=System.currentTimeMillis();
		long mergeSort10000Copy1Total=mergeSort10000Copy1End-mergeSort10000Copy1Start;
		
		long quickSort10000Copy2Start=System.currentTimeMillis();
		quickSort(tenK.clone());
		long quickSort10000Copy2End=System.currentTimeMillis();
		long quickSort10000Copy2Total=quickSort10000Copy2End-quickSort10000Copy2Start;
		
		
		long stringInsertionSort10000Start=System.currentTimeMillis();
		insertionSort(strings10000.clone());
		long stringInsertionSort10000End=System.currentTimeMillis();
		long stringInsertionSort10000Total=stringInsertionSort10000End-stringInsertionSort10000Start;
		
		long stringMergeSort10000Copy1Start=System.currentTimeMillis();
		mergeSort(strings10000.clone());
		long stringMergeSort10000Copy1End=System.currentTimeMillis();
		long stringMergeSort10000Copy1Total=stringMergeSort10000Copy1End-stringMergeSort10000Copy1Start;
		
		long stringQuickSort10000Copy2Start=System.currentTimeMillis();
		quickSort(strings10000.clone());
		long stringQuickSort10000Copy2End=System.currentTimeMillis();
		long stringQuickSort10000Copy2Total=stringQuickSort10000Copy2End-stringQuickSort10000Copy2Start;
		
	
		
		long insertionSort25000Start=System.currentTimeMillis();
		insertionSort(twentyfiveK.clone());
		long insertionSort25000End=System.currentTimeMillis();
		long insertionSort25000Total=insertionSort25000End-insertionSort25000Start;
				
		long mergeSort25000Copy1Start=System.currentTimeMillis();
		mergeSort(twentyfiveK.clone());
		long mergeSort25000Copy1End=System.currentTimeMillis();
		long mergeSort25000Copy1Total=mergeSort25000Copy1End-mergeSort25000Copy1Start;
		
		long quickSort25000Copy2Start=System.currentTimeMillis();
		quickSort(twentyfiveK.clone());
		long quickSort25000Copy2End=System.currentTimeMillis();
		long quickSort25000Copy2Total=quickSort25000Copy2End-quickSort25000Copy2Start;
		
		
		
		
		long stringInsertionSort25000Start=System.currentTimeMillis();
		insertionSort(strings25000.clone());
		long stringInsertionSort25000End=System.currentTimeMillis();
		long stringInsertionSort25000Total=stringInsertionSort25000End-stringInsertionSort25000Start;
		
		long stringMergeSort25000Copy1Start=System.currentTimeMillis();
		mergeSort(strings25000.clone());
		long stringMergeSort25000Copy1End=System.currentTimeMillis();
		long stringMergeSort25000Copy1Total=stringMergeSort25000Copy1End-stringMergeSort25000Copy1Start;
		
		long stringQuickSort25000Copy2Start=System.currentTimeMillis();
		quickSort(strings25000.clone());
		long stringQuickSort25000Copy2End=System.currentTimeMillis();
		long stringQuickSort25000Copy2Total=stringQuickSort25000Copy2End-stringQuickSort25000Copy2Start;
		
		
		
	
		long insertionSort50000Start=System.currentTimeMillis();
		insertionSort(fiftyK.clone());
		long insertionSort50000End=System.currentTimeMillis();
		long insertionSort50000Total=insertionSort50000End-insertionSort50000Start;
		
		long mergeSort50000Copy1Start=System.currentTimeMillis();
		mergeSort(fiftyK.clone());
		long mergeSort50000Copy1End=System.currentTimeMillis();
		long mergeSort50000Copy1Total=mergeSort50000Copy1End-mergeSort50000Copy1Start;
		
		long quickSort50000Copy2Start=System.currentTimeMillis();
		quickSort(fiftyK.clone());
		long quickSort50000Copy2End=System.currentTimeMillis();
		long quickSort50000Copy2Total=quickSort50000Copy2End-quickSort50000Copy2Start;
		
		
		

		long stringInsertionSort50000Start=System.currentTimeMillis();
		insertionSort(strings50000.clone());
		long stringInsertionSort50000End=System.currentTimeMillis();
		long stringInsertionSort50000Total=stringInsertionSort50000End-stringInsertionSort50000Start;
		
		long stringMergeSort50000Copy1Start=System.currentTimeMillis();
		mergeSort(strings50000.clone());
		long stringMergeSort50000Copy1End=System.currentTimeMillis();
		long stringMergeSort50000Copy1Total=stringMergeSort50000Copy1End-stringMergeSort50000Copy1Start;
		
		long stringQuickSort50000Copy2Start=System.currentTimeMillis();
		quickSort(strings50000.clone());
		long stringQuickSort50000Copy2End=System.currentTimeMillis();
		long stringQuickSort50000Copy2Total=stringQuickSort50000Copy2End-stringQuickSort50000Copy2Start;
		
		
		long insertionSort75000Start=System.currentTimeMillis();
		insertionSort(seventyfiveK.clone());
		long insertionSort75000End=System.currentTimeMillis();
		long insertionSort75000Total=insertionSort75000End-insertionSort75000Start;
		
		long mergeSort75000Copy1Start=System.currentTimeMillis();
		mergeSort(seventyfiveK.clone());
		long mergeSort75000Copy1End=System.currentTimeMillis();
		long mergeSort75000Copy1Total=mergeSort75000Copy1End-mergeSort75000Copy1Start;
		
		long quickSort75000Copy2Start=System.currentTimeMillis();
		quickSort(seventyfiveK.clone());
		long quickSort75000Copy2End=System.currentTimeMillis();
		long quickSort75000Copy2Total=quickSort75000Copy2End-quickSort75000Copy2Start;
		
		
		

		long stringInsertionSort75000Start=System.currentTimeMillis();
		insertionSort(strings75000.clone());
		long stringInsertionSort75000End=System.currentTimeMillis();
		long stringInsertionSort75000Total=stringInsertionSort75000End-stringInsertionSort75000Start;
		
		long stringMergeSort75000Copy1Start=System.currentTimeMillis();
		mergeSort(strings75000.clone());
		long stringMergeSort75000Copy1End=System.currentTimeMillis();
		long stringMergeSort75000Copy1Total=stringMergeSort75000Copy1End-stringMergeSort75000Copy1Start;
		
		long stringQuickSort75000Copy2Start=System.currentTimeMillis();
		quickSort(strings75000.clone());
		long stringQuickSort75000Copy2End=System.currentTimeMillis();
		long stringQuickSort75000Copy2Total=stringQuickSort75000Copy2End-stringQuickSort75000Copy2Start;
		
		
		long insertionSort100000Start=System.currentTimeMillis();
		insertionSort(hundredK.clone());
		long insertionSort100000End=System.currentTimeMillis();
		long insertionSort100000Total=insertionSort100000End-insertionSort100000Start;
		
		long mergeSort100000Copy1Start=System.currentTimeMillis();
		mergeSort(hundredK.clone());
		long mergeSort100000Copy1End=System.currentTimeMillis();
		long mergeSort100000Copy1Total=mergeSort100000Copy1End-mergeSort100000Copy1Start;
		
		long quickSort100000Copy2Start=System.currentTimeMillis();
		quickSort(hundredK.clone());
		long quickSort100000Copy2End=System.currentTimeMillis();
		long quickSort100000Copy2Total=quickSort100000Copy2End-quickSort100000Copy2Start;

		

		long stringInsertionSort100000Start=System.currentTimeMillis();
		insertionSort(strings100000.clone());
		long stringInsertionSort100000End=System.currentTimeMillis();
		long stringInsertionSort100000Total=stringInsertionSort100000End-stringInsertionSort100000Start;
		
		long stringMergeSort100000Copy1Start=System.currentTimeMillis();
		mergeSort(strings100000.clone());
		long stringMergeSort100000Copy1End=System.currentTimeMillis();
		long stringMergeSort100000Copy1Total=stringMergeSort100000Copy1End-stringMergeSort100000Copy1Start;
		
		long stringQuickSort100000Copy2Start=System.currentTimeMillis();
		quickSort(strings100000.clone());
		long stringQuickSort100000Copy2End=System.currentTimeMillis();
		long stringQuickSort100000Copy2Total=stringQuickSort100000Copy2End-stringQuickSort100000Copy2Start;
		
		//The results from the length of time it took to run each sorting algortihm are printed to the screen
		 System.out.println("Comparing sorts of lists on Integers:");
		 System.out.println("Size	Insert	 Merge	 Quick");
		 System.out.println("10000   "+insertionSort10000Total+"        "+mergeSort10000Copy1Total+"       "+quickSort10000Copy2Total);
		 System.out.println("25000   "+insertionSort25000Total+"       "+mergeSort25000Copy1Total+"       "+quickSort25000Copy2Total);
	     System.out.println("50000   "+insertionSort50000Total+"      "+mergeSort50000Copy1Total+"      "+quickSort50000Copy2Total);
		 System.out.println("75000   "+insertionSort75000Total+"      "+mergeSort75000Copy1Total+"      "+quickSort75000Copy2Total);
		 System.out.println("100000  "+insertionSort100000Total+"     "+mergeSort100000Copy1Total+"       "+quickSort100000Copy2Total);

		 System.out.println("");
		 
		 System.out.println("Comparing sorts of lists on Strings:");
		 System.out.println("Size   Insert   Merge   Quick");   
		 System.out.println("10000   "+stringInsertionSort10000Total+"      "+stringMergeSort10000Copy1Total+"       "+stringQuickSort10000Copy2Total);
		 System.out.println("25000   "+stringInsertionSort25000Total+"     "+stringMergeSort25000Copy1Total+"        "+stringQuickSort25000Copy2Total);
		 System.out.println("50000   "+stringInsertionSort50000Total+"     "+stringMergeSort50000Copy1Total+"        "+stringQuickSort50000Copy2Total);
		 System.out.println("75000   "+stringInsertionSort75000Total+"    "+stringMergeSort75000Copy1Total+"       "+stringQuickSort75000Copy2Total);
		 System.out.println("100000  "+stringInsertionSort100000Total+"    "+stringMergeSort100000Copy1Total+"       "+stringQuickSort100000Copy2Total);
	}
}
