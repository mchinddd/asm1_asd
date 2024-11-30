public class StudentStack {
    private Student[] stack;
    private int top;

    public StudentStack(int capacity) {
        stack = new Student[capacity];
        top = -1;
    }

    public void push(Student student) {
        if (top == stack.length - 1) {
            System.out.println("Stack is full. Cannot push.");
        } else {
            stack[++top] = student;
        }
    }

    public Student pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        } else {
            return stack[top--];
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    // New method to get the capacity of the stack
    public int getCapacity() {
        return stack.length;
    }
}