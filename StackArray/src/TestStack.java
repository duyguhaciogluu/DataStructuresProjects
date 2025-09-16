
import java.util.Scanner;

public class TestStack {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        StackArray stack = new StackArray(105);
        boolean stop = false;
        while (true) {
            System.out.println("Which task you want to do?");
            String menu = "1) Push \n2) Pop\n3) Print\n4) DeleteMiddle \n5) Is Palindrome \n6) Exit ";
            System.out.println(menu);
            System.out.print("Please enter a digit between 1 and 6: ");
            int number = scn.nextInt();
            String name;
            switch (number) {
                case 1:
                    System.out.println("How many pushes you want to do?");
                    int size = scn.nextInt();
                    System.out.println("Enter a name");
                    for (int i = 0; i < size; i++) {
                        name = scn.next();
                        stack.push(name);
                    }
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.printStack(stack);
                    break;
                case 4:
                    stack.deleteMiddle(stack);
                    break;
                case 5:
                    stack.isPalindrome(stack);
                    break;
                case 6:
                    System.out.println("You quited");
                    return;
                default:
                    System.out.println("This number is not in the menu options");
                    break;

            }

        }
    }
}
