import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FinalProject {


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n******************************");
            System.out.println("\nMenu of Searching and sorting:");
            System.out.println("\n\t1) Linear searching");
            System.out.println("\t2) Binary searching");
            System.out.println("\t3) O(n^2) Type of sorting: Insertion Sort");
            System.out.println("\t4) O(n^2) Type of sorting: Bubble Sort");
            System.out.println("\t5) O(n*log(n)) Type of sorting: Quick Sort");
            System.out.println("\t6) O(n*log(n)) Type of sorting: Merge Sort");
            System.out.println("\t7) Sorting performance");
            System.out.println("\n\tq/Q) Quit");

            System.out.print("\nEnter your choice from the menu: ");
            String choice = scan.nextLine().trim();

            if (choice.equalsIgnoreCase("q")) {
                System.out.println("\nQuitting the program. Goodbye!");
                break;
            }

            try {
                int option = Integer.parseInt(choice);
                processChoice(option, scan);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number or 'q/Q' to quit.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

        scan.close();

    }





    //Method for processing the user choice from the menu and executing the desired searching or sorting method
    private static void processChoice(int option, Scanner scan) {

        // Specifyiing array size, that we will work on in the  
        int arrSize = 25;
        switch (option) {
            case 1:

                System.out.println("\nYou selected Linear searching.");
                // Calling the method for Linear searching
                linearSearch(scan, arrSize);
                break;

            case 2:

                System.out.println("\nYou selected Binary searching.");
                // Calling the method for Binary searching
                BinarySearch(scan, arrSize);
                break;

            case 3:

                System.out.println("\nYou selected O(n^2) Type of sorting: Insertion Sort.");
                // Calling the method for Insertion Sort
                InsertionSort(arrSize);
                break;
                
            case 4:

                System.out.println("\nYou selected O(n^2) Type of sorting: Bubble Sort.");
                // Calling the method for Bubble Sort
                BubbleSort(arrSize);
                break;

            case 5:

                System.out.println("\nYou selected O(n*log(n)) Type of sorting: Quick Sort.");
                // Calling the method for Quick Sort
                QuickSort(arrSize);
                break;

            case 6:

                System.out.println("\nYou selected O(n*log(n)) Type of sorting: Merge Sort.");
                // Calling the method for Merge Sort
                MergeSort(arrSize);
                break;
                
            case 7:

                System.out.println("\nYou selected Sorting performance.");
                SortingPerformance.analyzeSortPerformance();
                break;

            default:
                throw new IllegalArgumentException("\nInvalid choice. Please enter a valid option.");
        }
    }

    
    
    
    
    
    //Method for linear search
    private static void linearSearch(Scanner scan, int arrSize){

        //Generates an array of random integers within the range [-100, 100).
        int[] numbers = generateRandomArray(arrSize);

        //Printing the randomly genarated array
        System.out.println("\nRandomly generated array of number: ");
        printArray(numbers);

        System.out.println();

        // asking input for the target number to search
        int target = 0;

        // Loop until a valid integer is entered
        while (true) {
            try {
                // Asking input for the target number to search
                System.out.print("\nEnter target number to search: ");
                target = Integer.parseInt(scan.nextLine());
                break;  // Breaking the loop if parsing is successful
            } catch (NumberFormatException e) {
                // Handling the exception if the input is not a valid integer
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }


        //from here executing the main part of the searching
        int result = -1;
        for(int i=0; i<numbers.length; i++){
            if (numbers[i] == target){
                result = i;
            }
        }

        if(result != -1){
            System.out.println("\nTerget number found!");
        } else {
            System.out.println("\nTerget number is not in the array!");
        }
        
    }

    




    //Method for Binary search
    private static void BinarySearch(Scanner scan, int arrSize){

        //Generates an array of random integers within the range [-100, 100).
        int[] numbers = generateRandomArray(arrSize);

        //Printing the randomly genarated array
        System.out.println("\nRandomly generated array of number: ");
        printArray(numbers);

        System.out.println();

        // asking input for the target number to search
        int target = 0;

        // Loop until a valid integer is entered
        while (true) {
            try {
                // Asking input for the target number to search
                System.out.print("\nEnter target number to search: ");
                target = Integer.parseInt(scan.nextLine());
                break;  // Breaking the loop if parsing is successful
            } catch (NumberFormatException e) {
                // Handling the exception if the input is not a valid integer
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }


        //From here executing the main part of the searching 


        //Sorting the array before starting binarry search.
        Arrays.sort(numbers);

        // Setting value for the first and last index for the search
        int first = 0;
        int last = numbers.length - 1;

        // Calculate the initial mid-point of the array
        int mid = (first + last) / 2;

        // Continuing the search while the first index is less than or equal to the last index
        while (first <= last) {
            // If the middle element is less than the target, adjust the search range to the right half
            if (numbers[mid] < target) {
                first = mid + 1;
            }
            // If the middle element is equal to the target, the element is found, and the search is terminated
            else if (numbers[mid] == target) {
                System.out.println("\nTerget number found!");
                break;
            }
            // If the middle element is greater than the target, then adjusting the search range to the left half
            else {
                last = mid - 1;
            }

            // Recalculating the mid-point for the next iteration
            mid = (first + last) / 2;
        }

        // If the first index is greater than the last index, the target element is not found in the array
        if (first > last) {
            System.out.println("\nTerget number is not in the array!");
        }
        
        
    }

    




    //Method for Insertion Sort
    public static void InsertionSort(int arrSize) {

        //Generates an array of random integers within the range [-100, 100).
        int[] numbers = generateRandomArray(arrSize);

        //Printing the randomly genarated array before sorting
        System.out.println("\nRandomly generated array of number(Before sorting): ");
        printArray(numbers);

        System.out.println();


        //From here executing the main part of the sorting

        // Walking through the array.
        for (int i = 1; i < numbers.length; i++) {
            // Current value to be inserted into the sorted part of the array.
            int cValue = numbers[i];

            //Comparing with the last element in the sorted part of the array.
            int j = i - 1;

            // Comparing the value of the array and shifting elements to the right if needed.
            while (j >= 0 && numbers[j] > cValue) {
                numbers[j + 1] = numbers[j];
                j--;
            }

            // Insert the current value into its correct position in the sorted part of the array.
            numbers[j + 1] = cValue;
        }


        //Printing the randomly genarated array after sorting
        System.out.println("\nRandomly generated array of number(After sorting): ");
        printArray(numbers);
        System.out.println("\n");
        

    }

    




    //Method for Bubble Sort
    public static void BubbleSort(int arrSize) {

        //Generates an array of random integers within the range [-100, 100).
        int[] numbers = generateRandomArray(arrSize);

        //Printing the randomly genarated array before sorting
        System.out.println("\nRandomly generated array of number(Before sorting): ");
        printArray(numbers);

        System.out.println();


        //From here executing the main part of the sorting

        // Get the count of numbers in the list
        int n = numbers.length;

        // Go through each number in the list
        for (int i = 0; i < n - 1; i++) {

            // Check each pair of neighboring numbers
            for (int j = 0; j < n - i - 1; j++) {

                // If the current number is bigger than the next one, swap them
                if (numbers[j] > numbers[j + 1]) {
                    // Swap inputArray[j] and inputArray[j + 1]
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }


        //Printing array after sorting
        System.out.println("\nRandomly generated array of number(After sorting): ");
        printArray(numbers);
        System.out.println("\n");

    }





    //Main method for Quick Sort
    public static void QuickSort(int arrSize) {

        //Generates an array of random integers within the range [-100, 100).
        int[] numbers = generateRandomArray(arrSize);

        //Printing the randomly genarated array before sorting
        System.out.println("\nRandomly generated array of number(Before sorting): ");
        printArray(numbers);

        System.out.println();


        //From here executing the main part of the sorting

        // Executing recursive algorithm for Quick Sort
        qsort(numbers, 0, numbers.length - 1);


        //Printing array after sorting
        System.out.println("\nRandomly generated array of number(After sorting): ");
        printArray(numbers);
        System.out.println("\n");

    }

    // Recursive algorithm for Quick Sort
    public static void qsort(int[] array, int low, int high) {

        if (low < high) {

            // Partition the array to get the index of the pivot element.
            int pi = partition(array, low, high);

            // Recursively sorting the sub-arrays before and after the pivot.
            qsort(array, low, pi - 1);
            qsort(array, pi + 1, high);

        }
    }

    // Method to partition the array for Quick Sort
    private static int partition(int[] array, int low, int high) {

        // Selecting the last element as the pivot.
        int pivot = array[high];

        // Index of the smaller element.
        int i = low - 1;

        // Rearrange elements based on the pivot.
        for (int j = low; j < high; j++) {

            if (array[j] < pivot) {
                // Swaping array[i+1] and array[j].
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

            }
        }

        // Swaping array[i+1] and array[high] (pivot).
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        // Returning the index of the pivot element.
        return i + 1;
    }





    //Main method for Marge Sort
    public static void MergeSort(int arrSize) {

        //Generates an array of random integers within the range [-100, 100).
        int[] numbers = generateRandomArray(arrSize);

        //Printing the randomly genarated array before sorting
        System.out.println("\nRandomly generated array of number(Before sorting): ");
        printArray(numbers);

        System.out.println();


        //From here executing the main part of the sorting

        // Executing recursive algorithm for Marge Sort
        mSort(numbers);


        //Printing array after sorting
        System.out.println("\nRandomly generated array of number(After sorting): ");
        printArray(numbers);
        System.out.println("\n");

    }

    // Recursive algorithm for Marge Sort
    public static void mSort(int[] array) {
        // Check for base case: if the array has one or zero elements, it is already sorted.
        if (array.length <= 1) {
            return;
        }

        // Calculate the middle index of the array.
        int middle = array.length / 2;

        // Divide the array into two halves.
        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);

        // Recursively sort the left and right halves.
        mSort(left);
        mSort(right);

        // Merge the sorted halves.
        merge(array, left, right);
    }

    // Helper method to merge two sorted arrays into a single sorted array.
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Compare elements from left and right arrays and merge them in sorted order.
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copy the remaining elements from left array, if any.
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Copy the remaining elements from right array, if any.
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }


    



    //Generating an array of random integers within the range (-100, 100).
    private static int[] generateRandomArray(int size) {
        // Creating a Random object to generate random numbers
        Random rand = new Random();

        // Creating an array to store the random numbers
        int[] arr = new int[size];

        // Filling array with random integers within the range [-100, 100)
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(201) - 100;
        }

        // Returning the generated array
        return arr;
    }

    

    // Function for printing the Array.
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+ "  ");
        }
    }

    
}
