/**
 * Lab 2: Debugging with an IDE and Prefix Tree) <br />
 * The {@code ResearchGroup} class uses a 2D array to store the names of group members
 * @author Terry Wan (1586035)
 */


public class ResearchGroups {

    /**
     * Search a person to check whether he/she is in the groups
     * @param groups    {@code String[]} The 2D array of groups to be searched
     * @param name      {@code String} name of the person
     */
    public static void searchMember(String[][] groups, String name) {
        boolean exist = false;
        boolean isleader = false;
        
        //create a g_num to store the group numbers for one person
        int[] g_num = new int [groups.length];
        int num = 0;
        
        //iterate for each groups
        for (int i =0;i<groups.length;i++)
        {
            //iterate through each group members
            for(int j=0; j<groups[i].length;j++)
            {
                if (groups[i][j] == name)
                {
                    exist = true;
                    //store the number in g_num and increment num
                    g_num[num] = i;
                    num++;
                    if (j == 0)
                        isleader = true;
                break;
                }
            }
        }
        if (exist)
        {
            System.out.println("Yes, "+name+ " is in the group");
            System.out.print("Group number is: ");
            for (int i = 0; i<num;i++)
            {
                System.out.print(g_num[i]+" ");
            }
            System.out.println();
            System.out.print(name+ " is ");
            System.out.println(String.format(isleader ? "a leader": "not a leader"));
        }
        else
        {
            System.out.println("No, "+name+ " is not in the group");
        }    
    }

    /**
     * Sort groups by number of members <br />
     * @param groups    (<code>String[][]</code>) The 2D array of groups to be sorted
     */
    public static void sortGroups(String[][] groups) {
        //conver the group length to numbers to sort them
        int[] numbers = new int [groups.length];
        for (int i=0;i<groups.length;i++)
        {
            numbers[i] = groups[i].length;
        }
        //sort by the length of the groups
        numbers = sort(numbers,0,numbers.length-1);

        //swap the groups elements according to the sorted numbers
        //increment for numbers
        for (int i=0;i<numbers.length;i++)
        {
            //increment for groups
            for (int j =0;j<groups.length;j++)
            {
                //if the length of groups[j] fits the value of numbers[i]
                if (numbers[i] == groups[j].length)
                {
                    //swap according to the indexes
                    swap(groups,i,j);
                    break;
                }
            }
        }

        //A Test to shown whether the group is successfully sorted
        for (int i = 0; i<groups.length; i++)
        {
            for (int j = 0; j<groups[i].length; j++)
            {
                System.out.print(groups[i][j]+" ");
            }
            System.out.println();
        }
    }
    //a swap function
    public static void swap (String[][] groups, int i, int j) 
    {
        String[] temp = groups[i];
        groups[i] = groups[j];
        groups[j] = temp;
    }
    
    //Merge Sort developed in LAB 1
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
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String[][] groups = { {"Bob", "Carol", "Eric", "Matt"},             // 0
                              {"Jim", "Lucy", "Terry", "Brenda", "Ben"},    // 1
                              {"Susan", "Brad", "Jim"},                     // 2
                              {"Sue", "Wendy", "Sam"},                      // 3
                              {"Kate", "Jack", "James", "Sydney"},          // 4
                              {"Mohammad", "Tim", "Kian"},                  // 5
                              {"Emma", "Carol"},                            // 6
                              {"Nick", "Osama", "Harry", "Ben"},            // 7
                              {"Mary", "John", "Ricky"} };                  // 8

        ResearchGroups.searchMember(groups, "Jim");
        ResearchGroups.searchMember(groups, "Lucy");
        ResearchGroups.searchMember(groups, "John Doe");
        ResearchGroups.sortGroups(groups);
    }

}
