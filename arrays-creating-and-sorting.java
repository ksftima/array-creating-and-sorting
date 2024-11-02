import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Constant messages for user prompts and outputs
    static final String USER_INPUT_PROMPT = "How many random numbers in the range 0 - 999 are desired?";
    static final String RANDOM_NUMBERS_LIST_MESSAGE = "Here are the random numbers:";
    static final String RANDOM_NUMBERS_SORTED_MESSAGE = "Here are the random numbers arranged:";
    static final String NO_ODD_NUMBERS_MESSAGE = "No Odd Numbers";
    static final String NO_EVEN_NUMBERS_MESSAGE = "No Even Numbers";
    static final String INVALID_INPUT_MESSAGE = "Invalid Input";
    static final int MAX_RANDOM_NUMBER = 999; // Maximum value for random numbers

    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);

        // Arrays to hold the generated random numbers, even numbers, and odd numbers
        int[] randomArray;
        int randomCount = 0;

        int[] evenArray;
        int evenCount = 0;

        int[] oddArray;
        int oddCount = 0;

        // Loop until valid input is received
        while (true) {
            System.out.println(USER_INPUT_PROMPT);

            try {
                String temp = input.nextLine();
                randomCount = Integer.parseInt(temp); // Parse user input to integer

                // Validate the input count
                if (randomCount <= 0 || randomCount > MAX_RANDOM_NUMBER) {
                    System.out.println(INVALID_INPUT_MESSAGE);
                    continue; // Prompt again if input is invalid
                }

                // Initialize arrays based on user input
                randomArray = new int[randomCount];
                evenArray = new int[randomCount];
                oddArray = new int[randomCount];

                break; // Exit the loop if input is valid
            } catch (OutOfMemoryError e) {
                System.out.println(INVALID_INPUT_MESSAGE); // Handle memory issues
            } catch (InputMismatchException e) {
                System.out.println(INVALID_INPUT_MESSAGE); // Handle input mismatch
                input.nextLine(); // Clear the invalid input
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MESSAGE); // Handle number format issues
            }
        }

        // Generate random numbers and categorize them into even and odd
        for (int i = 0; i < randomCount; i++) {
            int randomNumber = (int) (Math.random() * MAX_RANDOM_NUMBER) + 1; // Generate a random number
            randomArray[i] = randomNumber; // Store in the random array

            // Classify the number as even or odd
            if (randomArray[i] % 2 == 0) {
                evenArray[evenCount] = randomArray[i];
                evenCount++;
            } else {
                oddArray[oddCount] = randomArray[i];
                oddCount++;
            }
        }

        // Sort even numbers using Bubble Sort
        for (int i = 0; i < evenCount; i++) {
            for (int j = 0; j < evenCount - 1; j++) {
                if (evenArray[j] > evenArray[j + 1]) {
                    // Swap if the current number is greater than the next
                    int temp = evenArray[j];
                    evenArray[j] = evenArray[j + 1];
                    evenArray[j + 1] = temp;
                }
            }
        }

        // Sort odd numbers in descending order using Bubble Sort
        for (int i = 0; i < oddCount; i++) {
            for (int j = 0; j < oddCount - 1; j++) {
                if (oddArray[j] < oddArray[j + 1]) {
                    // Swap if the current number is less than the next
                    int temp = oddArray[j];
                    oddArray[j] = oddArray[j + 1];
                    oddArray[j + 1] = temp;
                }
            }
        }

        // Output the generated random numbers
        System.out.println("\n" + RANDOM_NUMBERS_LIST_MESSAGE);
        for (int i = 0; i < randomCount; i++) {
            System.out.print(randomArray[i] + " ");
        }
        System.out.println();

        System.out.println("\n" + RANDOM_NUMBERS_SORTED_MESSAGE);

        // Output even numbers or a message if there are none
        if (evenCount > 0) {
            for (int i = 0; i < evenCount; i++) {
                System.out.print(evenArray[i] + " ");
            }
        } else {
            System.out.print(NO_EVEN_NUMBERS_MESSAGE);
        }

        System.out.print("- "); // Separator

        // Output odd numbers or a message if there are none
        if (oddCount > 0) {
            for (int i = 0; i < oddCount; i++) {
                System.out.print(oddArray[i] + " ");
            }
        } else {
            System.out.print(NO_ODD_NUMBERS_MESSAGE);
        }
        System.out.println();

        // Final summary of counts
        System.out.print("\nOf the above " + randomCount + " numbers, " + evenCount + " were even and " + oddCount + " were odd.");

        input.close(); // Close the scanner
    }
}
