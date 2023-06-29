package main.java;
/**
 * Lab 1: Java Basics, Merge Sort and Maven <br />
 * The {@code MergeSort} class
 * @ZixuanWan 1586035<your-name>
 */
public class MergeSort {
    
    // More methods can be added here
    //mergeing two arrays together
    public static void merge(int[] numbers, int low, int mid, int high)
    {
        //get the size of two arrays and create new arrays to store them
        int size1 = mid-low+1;
        int arr1[] = new int [size1];
        for (int i =0; i<size1; i++)
        {
            arr1[i] = numbers[low+i];
        }

        int size2 = high-mid;
        int arr2[] = new int [size2];
        for (int j =0; j<size2; j++)
        {
            arr2[j] = numbers[mid+1+j];
        }

        //now merge the two arrays from small to big
        //initial the indexes, where i j means the index of arr1, arr2, k is the index of the sum array, starts from low
        int i =0;
        int j =0;
        int k =low;
        //compare the arr1 and arr2 to insert the smaller number to the total array first
        while (i<size1 && j<size2)
        {
            if (arr1[i] < arr2[j])
            {
                numbers[k] = arr1[i];
                i++;
            }
            else
            {
                numbers[k] = arr2[j];
                j++;
            }
            k++;
        }

        //copy the remaning elements in arr1 and arr2, if there's any
        while (i<size1)
        {
            numbers[k] = arr1[i];
            k++;
            i++;
        }
        while (j<size2)
        {
            numbers[k] = arr2[j];
            k++;
            j++;
        }

    }

    //low and high here means the indexs of the array from the start to end
    public static int[] sort(int[] numbers, int low, int high) {
        
        if (low < high)
        {   
            //find the middel point
            int mid = (low+high)/2;

            //Divide the array into half and sort the first and second half
            sort(numbers,low,mid);
            sort(numbers,mid+1,high);

            //combine the sorted two arrays
            merge(numbers,low,mid,high);
        }
	return numbers;
    }

    /**
     * Main entry: test the HeapSort
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 200);
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

       numbers = sort(numbers,0,numbers.length-1);

        for (int n: numbers)
            System.out.print(n + " ");
        System.out.println();
    }

}

