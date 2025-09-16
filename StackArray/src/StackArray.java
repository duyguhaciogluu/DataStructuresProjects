
import java.util.Scanner;

public class StackArray {

    String data[]; // array to hold items
    int top; // the top most item index

    public StackArray(int N) {
        data = new String[N];
        top = -1;
    }

    public void push(String name) {
        if (!isFull()) {
            data[++top] = name;
        }
    }

    public String peek() { //top
        if (!isEmpty()) {
            return data[top];
        } else {
            System.exit(1);
        }
        return null;
    }

    public String pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } else {
            top--;
            return data[top + 1];
        }
    }

    public boolean isFull() {
        if (top == data.length - 1) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (top == - 1) {
            return true;
        }
        return false;
    }

    public void printStack(StackArray stack) {
        if (stack.isEmpty()) {
            return;
        }
        String x = stack.peek();
        stack.pop();
        printStack(stack);
        System.out.println(x + " ");
        stack.push(x);
    }

    public void isPalindrome(StackArray stack) {
        int n = stack.size();
        String nameData="";
        StackArray tempStack = new StackArray(105);
        StackArray tempStack2 = new StackArray(105);
        StackArray tempStack3=new StackArray(105);
        if (n % 2 == 0) {
            for (int i = 0; i < n / 2; i++) {
                String name = stack.peek();
                stack.pop();
                tempStack.push(name);
            }
            for (int i = 0; i < n / 2; i++) {
                String name = stack.peek();
                stack.pop();
                tempStack2.push(name);
            }
            for (int i = 0; i < n / 2; i++) {
                String name = tempStack2.peek();
                tempStack2.pop();
                tempStack3.push(name);
            }
          }else {
            for (int i = 0; i < n / 2; i++) {
                String name = stack.peek();
                stack.pop();
                tempStack.push(name);
            }
            nameData=stack.pop();
            for (int i = 0; i < n / 2; i++) {
                String name = stack.peek();
                stack.pop();
                tempStack2.push(name);
            }
            for (int i = 0; i < n / 2; i++) {
                String name = tempStack2.peek();
                tempStack2.pop();
                tempStack3.push(name);
                stack.push(name);
            }
        }

        boolean palindrome = false;
        boolean endCondition=true;
        while (!tempStack.isEmpty() && !tempStack3.isEmpty()) {
            String i1 = tempStack.peek();
            String i2 = tempStack3.peek();
            tempStack.pop();
            tempStack3.pop();
            while(endCondition==true){
            stack.push(nameData);
            endCondition=false;
            }
            stack.push(i1);
            if (i1.equals(i2)) {
                palindrome = true;
                break;
            }
        }
        if (palindrome) {
            System.out.println("The stack is palindrome!");
        } else {
            System.out.println("The stack is not palindrome!");
        }
    }

    public int size() {
        int size = top + 1;
        return size;
    }

    public void deleteMiddle(StackArray stack) {
        int n = stack.size();
        StackArray tempStack = new StackArray(n);
        if ((n % 2 == 0) || stack.isEmpty()) {
            System.out.println("There is no middle element as the size of stack is even.");
        } else {
            for (int i = 0; i < n / 2; i++) {
                String name = stack.peek();
                stack.pop();
                tempStack.push(name);
            }
            stack.pop();
            System.out.println("The middle element has been deleted");
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.peek());
                tempStack.pop();
            }
        }
    }
}


