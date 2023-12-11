import java.util.Arrays;
import java.util.Random;


public class SortingPerformance {

    //Variable for counting the comperisoms in sorting.
    public static int comCount;

    // Method to perform the performance analysis of sorting algorithms
    public static void analyzeSortPerformance() {
        // Define array sizes to test
        int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

        // Headers for the table
        System.out.printf("\n%-30s", "Array Size");
        for (int size : sizes) {
            System.out.printf("%-16s", size);
        }
        System.out.println();

        // Analyzing Insertion sort.
        analyzeAlgorithm("Insertion Sort", sizes, SortingPerformance::InsertionSort);

        // Analyzing Bubble sort.
        analyzeAlgorithm("Bubble Sort", sizes, SortingPerformance::BubbleSort);

        // Analyzing Quick sort.
        analyzeAlgorithm("Quick Sort", sizes, SortingPerformance::QuickSort);

        // Analyzing Merge sort.
        analyzeAlgorithm("Merge Sort", sizes, SortingPerformance::MergeSort);


    }

    // Define a functional interface with a single abstract method
     interface MyFunction {
        void apply(int size);
    }

 
    

    // Method to analyze a specific sorting algorithm
    private static void analyzeAlgorithm(String name, int[] sizes, MyFunction function) {
        System.out.printf("\n%-45s", name + "\n(ex.time(mSec), Comparisons)");
        for (int size : sizes) {

            // Get the current time before sorting
            long startTime = System.currentTimeMillis();

            // Perform the sorting
            function.apply(size);

            // Get the current time after sorting
            long endTime = System.currentTimeMillis();

            // Calculate elapsed time
            long elapsedTime = endTime - startTime;

            // Print the elapsed time  
            System.out.printf("%-3d,%-13d", elapsedTime, comCount);   

            comCount = 0;
        }

        System.out.println();

    }



    //Method for Insertion Sort
    public static void InsertionSort(int arrSize) {

        //Generates an array of random integers within the range [-100, 100).
        int[] numbers = generateRandomArray(arrSize);


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

                comCount++;
            }

            // Insert the current value into its correct position in the sorted part of the array.
            numbers[j + 1] = cValue;

        }

    }





    //Method for Bubble Sort
    public static void BubbleSort(int arrSize) {

        //Generates an array of random integers within the range [-100, 100).
        int[] numbers = generateRandomArray(arrSize);

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
                comCount++;
            }
        }

    }






    //Main method for Quick Sort
    public static void QuickSort(int arrSize) {

        //Generates an array of random integers within the range [-100, 100).
        int[] numbers = generateRandomArray(arrSize);

        //From here executing the main part of the sorting

        // Executing recursive algorithm for Quick Sort
        qsort(numbers, 0, numbers.length - 1);

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
        
        comCount++;
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


        //From here executing the main part of the sorting

        // Executing recursive algorithm for Marge Sort
        mSort(numbers);

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

        comCount++;
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
            arr[i] = rand.nextInt();
        }

        // Returning the generated array
        return arr;
    }

}
