import java.util.Scanner;

public class GradeCalc {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("How many subjects? ");
        int subjects = input.nextInt();

        int total = 0;

        // Read marks one by one
        for (int i = 1; i <= subjects; i++) {
            System.out.print("Enter marks for subject " + i + ": ");
            total += input.nextInt();
        }

        // Calculate average percentage
        double average = total / (double) subjects;

        // Decide grade
        char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 75) {
            grade = 'B';
        } else if (average >= 60) {
            grade = 'C';
        } else if (average >= 50) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Show result
        System.out.println("\nResult");
        System.out.println("Total Marks: " + total);
        System.out.println("Average Percentage: " + average + "%");
        System.out.println("Grade: " + grade);

        input.close();
    }
}
