package lab1;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        System.out.print("Hello, World!");

        int age = 35;
        String name = "Karsten";

        System.out.println("Age: " + age);
        System.out.println("Name: " + name);

        if (age > 34) {
            System.out.println("You're OLD!");
        }
        else if (age < 34) {
            System.out.println("You're still young!");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine(); // Reading a string input

        System.out.print("Enter your age: ");
        int userAge = scanner.nextInt();
    }
}
