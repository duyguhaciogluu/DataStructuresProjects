
import java.util.Scanner;

public class Polynomials {

    public static void main(String[] args) {

        MyLinkedList l1 = new MyLinkedList();
        MyLinkedList l2 = new MyLinkedList();
       
        Node n1 = null;
        Node n2 = null;
        Scanner scn = new Scanner(System.in);
        String s = "";
        int coefficient = 1, power = 1;
        int i = 0;
        int j = 0;
        while (i < 1) {
            System.out.println("Enter the coefficient and power of the first polynomial. Type 0 at the end:");
            while (coefficient != 0) {
                coefficient = scn.nextInt();
                if (coefficient == 0) {
                    break;
                }
                power = scn.nextInt();
                if(coefficient > 0){
                s = s + "+" + coefficient + "x^" + power;
                }
                else if(coefficient < 0){
                 s = s + coefficient + "x^" + power;   
                }
                n1 = new Node(power, coefficient);
                n1.next = n1;
                l1.insertLast(n1);              
            }
            l1.sortList(l1);
            n1.next = null;
            i++;
            System.out.println(s);
            coefficient = 1;
            s = "";
        }
        while (j < 1) {
            System.out.println("Enter the coefficient and power of the second polynomial. Type 0 at the end:");
            while (coefficient != 0) {
                coefficient = scn.nextInt();
                if (coefficient == 0) {
                    break;
                }
                power = scn.nextInt();
               if(coefficient > 0){
                s = s + "+" + coefficient + "x^" + power;
                }
                else if(coefficient < 0){
                 s = s + coefficient + "x^" + power;
                }     
                n2 = new Node(power, coefficient);
                n2.next = n2;
               l2.insertLast(n2);
            }
            l2.sortList(l2);
            n2.next = null;
            j++;
            System.out.println(s);
            coefficient = 1;
            s = "";
        }

        boolean stop = false;

        while (true) {
            System.out.println("Which task you want to do?");
            String menu = "1) Add \n2) Multiply\n3) Print\n4) Delete \n5) Exit";
            System.out.println(menu);
            System.out.print("Please enter a digit between 1 and 5: ");
            int number = scn.nextInt();

            switch (number) {
                case 1:
                    System.out.println("Adding two polynomials:");
                    MyLinkedList.addTwoPolynomials(l1, l2).print();
                    break;
                case 2:
                    System.out.println("Multiplying two polynomials:");
                    MyLinkedList.multiplyTwoPolynomial(l1, l2).print();
                    break;
                case 3:
                    System.out.println("The polynomials are:");
                    l1.print();
                    l2.print();
                    break;
                case 4:
                    MyLinkedList.removePower(l1, l2);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("This number is not in the menu options");
                    break;
            }
        }

    }
   
    }
