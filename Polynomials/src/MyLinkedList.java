
import java.util.Scanner;

public class MyLinkedList {

    Node first, last;

    public MyLinkedList() {
        first = null;
        last = null;
    }

    Scanner scn = new Scanner(System.in);

    public static MyLinkedList addTwoPolynomials(MyLinkedList polynomial1, MyLinkedList polynomial2) {
        MyLinkedList addingList;
        int coefficient;
        int power;
        Node pol1 = polynomial1.first;
        Node pol2 = polynomial2.first;
        Node other;
        addingList = new MyLinkedList();
        while (pol1 != null && pol2 != null) {
            if (pol1.power == pol2.power) {
                coefficient = pol1.coefficient + pol2.coefficient;
                power = pol2.power;
                pol1 = pol1.next;
                pol2 = pol2.next;
            } else if (pol1.power > pol2.power) {
                power = pol1.power;
                coefficient = pol1.coefficient;
                pol1 = pol1.next;
            } else {
                power = pol2.power;
                coefficient = pol2.coefficient;
                pol2 = pol2.next;
            }
            if (coefficient != 0) {
                Node addingNode = new Node(power, coefficient);
                addingList.insertLast(addingNode);
            }
        }

        if (pol2 == null) {
            other = pol1;
        } else {
            other = pol2;
        }
        while (other != null) {
            Node addingNode = new Node(other.power, other.coefficient);
            addingList.insertLast(addingNode);
            other = other.next;

        }
        return addingList;
    }

    public static MyLinkedList multiplyTwoPolynomial(MyLinkedList polynomial1, MyLinkedList polynomial2) {
        MyLinkedList multiplyingList = new MyLinkedList();
        int coefficient;
        int power;
        Node pol1 = polynomial1.first;
        Node pol2 = null;
        int count = 0;
        while (pol1 != null) {
            pol2 = polynomial2.first;
            while (pol2 != null) {
                coefficient = pol1.coefficient * pol2.coefficient;
                power = pol1.power + pol2.power;
                Node addingNode = new Node(power, coefficient);
                multiplyingList.insertLast(addingNode);
                pol2 = pol2.next;
            }
            pol1 = pol1.next;
        }

        Node newPol = multiplyingList.first;
        while (newPol != null) {
            Node newPol2 = multiplyingList.first.next;
            while (newPol2 != null) {
                if (newPol.power == newPol2.power) {
                    coefficient = newPol.coefficient + newPol2.coefficient;
                    Node addingNode = new Node(newPol2.power, coefficient);
                }
                newPol2 = newPol2.next;
            }
            newPol = newPol.next;
        }
        return multiplyingList;
    }

    public void print() {
        Node tmp = this.first;
        while (tmp != null) {
            if (tmp.coefficient > 0) {
                System.out.print("+" + tmp.coefficient + "x^" + tmp.power);
                tmp = tmp.next;
            } else if (tmp.coefficient < 0) {
                System.out.print(tmp.coefficient + "x^" + tmp.power);
                tmp = tmp.next;
            }
        }
        System.out.println();
    }

    public void insertLast(Node node) {
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    
    public static void removePower(MyLinkedList polynomial1, MyLinkedList polynomial2) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Which power you want to be deleted from both polynomials? ");
        int power = scn.nextInt();
        Node pol1 = polynomial1.first;
        Node pol2 = polynomial2.first;
        Node prev1 = null;
        Node prev2 = null;
        int k = 0;
        boolean isDelete = false;
        while (k < 10) {
            if (pol1 != null && pol2 != null && pol1.power == power && pol2.power == power && prev1 == null && prev2 == null) {

                pol1 = polynomial1.first.next;
                pol2 = polynomial2.first.next;
                System.out.println("The elements having this power has been deleted");
            }
            if (pol1 != null && pol1.power == power && prev1 == null) {

                polynomial1.first = polynomial1.first.next;
                System.out.println("The elements having this power has been deleted");
            }
            if (pol2 != null && pol2.power == power && prev2 == null) {

                polynomial2.first = polynomial2.first.next;
                System.out.println("The elements having this power has been deleted");
            }

            if (polynomial1.first != null) {
                pol1 = polynomial1.first.next;
            }
            while (pol1 != null) {

                prev1 = polynomial1.first;

                if (pol1.power == power) {

                    while (prev1.next != pol1) {
                        prev1 = prev1.next;
                    }

                    pol1 = prev1.next;
                    prev1.next = prev1.next.next;
                    isDelete = true;
                    System.out.println("The elements having this power has been deleted");
                    break;

                }
                pol1 = pol1.next;

            }
            if (polynomial1.first != null) {
                pol2 = polynomial2.first.next;
            }
            while (pol2 != null) {

                prev2 = polynomial2.first;

                if (pol2.power == power) {

                    while (prev2.next != pol2) {
                        prev2 = prev2.next;
                    }

                    pol2 = prev2.next;
                    prev2.next = prev2.next.next;
                    isDelete = true;
                    System.out.println("The elements having this power has been deleted");
                    break;
                }
                pol2 = pol2.next;

            }
            k++;
        }

        if (isDelete == false) {
            System.out.println("This power does not exist in any polynomial.");
        }
    }

    
    public static void sortList(MyLinkedList polynomial) {
        Node current = polynomial.first, index = null;
        int tempOfPower;
        int tempOfCoefficient;
        int k = 0;
        if (current == null) {
            return;
        } else {
            index = current.next;
            while (k < 10) {
                if (current.power < index.power) {
                    tempOfPower = current.power;
                    current.power = index.power;
                    index.power = tempOfPower;

                    tempOfCoefficient = current.coefficient;
                    current.coefficient = index.coefficient;
                    index.coefficient = tempOfCoefficient;
                }
                k++;
                index = index.next;
            }
            current = current.next;
        }
    }
}