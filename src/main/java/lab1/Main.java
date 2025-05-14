package lab1;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        System.out.print("Hello, World!");

        int age = 35;
        String name = "Karsten";


        Scanner scanner = new Scanner(System.in);


        getPositiveAge(scanner);

        //System.out.print("Enter your name: ");
        //String userName = scanner.nextLine();

        //System.out.print("Enter your age: ");
        //int userAge = scanner.nextInt();
    }

    private static int getPositiveAge(Scanner scanner) {

        int age = 0;
        while(true){

            System.out.print("Enter your age: ");
            age = scanner.nextInt();

            if(age < 0){
                System.out.println("You entered negative number!");
            }
            else {
                break;
            }
        }


       // if (age < 0) {
         //   System.out.println("Age cannot be negative. Please try again.");
        //    return getPositiveAge(scanner);
       // }

        return age;
    }

    System.out.println("Age: " + age);
        System.out.println("Name: " + name);

        if (userAge > 34) {
        System.out.println("You're OLD!");
    }
        else if (userAge < 34) {
        System.out.println("You're still young!");
    }


    try (BufferedWriter writer = new BufferedWriter(new FileWriter("input_user_info.txt"))) {
        writer.write("User Name: " + userName);
        writer.newLine();
        writer.write("User Age: " + userAge);
        System.out.println("\nUser information saved to input_user_info.txt");
    } catch (IOException e) {
        System.out.println("An error occurred while saving to file: " + e.getMessage());
    }
}
