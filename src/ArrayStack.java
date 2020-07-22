public class ArrayStack<T> implements ArrayStackADT<T> {

	private final int INTIAL_ARRAY_LENGTH = 25;

	/** the underlying data structure for the stack */
	private T[] ArrayStack;

	/** pointer to top of stack */
	private int top;

	/**
	 * Creates an empty stack.
	 */
	public ArrayStack() {

		ArrayStack = (T[]) new Object[INTIAL_ARRAY_LENGTH];
		top = -1;

	} // end ArrayStack

	/**
	 * Creates an empty stack.
	 */
	public ArrayStack(int initialCapacity) {

		ArrayStack = (T[]) new Object[initialCapacity];
		top = -1;

	} // end ArrayStack

	/**
	 * Adds one element to the top of this stack.
	 * 
	 * @param dataItem data item to be pushed onto stack
	 */
	public void push(T dataItem) {

		if (size() >= ArrayStack.length) {

			if (ArrayStack.length < 100) {

				setSize(ArrayStack.length * 3);

			} // end if

			else {

				setSize(ArrayStack.length + 25);

			} // end else

		} // end if

		ArrayStack[++top] = dataItem;

	} // end push

	/**
	 * Sets the new maximum size of the ArrayStack
	 * 
	 * @param newSize the new maximum size
	 */
	private void setSize(int newSize) {

		T[] temp = (T[]) new Object[newSize];

		for (int i = 0; i < size(); i++) {

			temp[i] = ArrayStack[i];

		} // end for

		ArrayStack = temp;

	} // end increaseSize

	/**
	 * Removes and returns the top element from this stack.
	 * 
	 * @return T data item removed from the top of the stack
	 */
	public T pop() throws EmptyStackException {

		if (isEmpty()) {

			throw new EmptyStackException("Stack");

		} // end if

		T result = ArrayStack[top--];

		if (size() < length() / 4 && length() > 40) {

			setSize(length() / 2);

		} // end if

		return result;

	} // end pop

	/**
	 * Returns without removing the top element of this stack.
	 * 
	 * @return T data item on top of the stack
	 */
	public T peek() throws EmptyStackException {

		if (isEmpty()) {

			throw new EmptyStackException("Stack");

		} // end if

		return ArrayStack[top];

	} // end peek

	/**
	 * Returns true if this stack contains no elements.
	 * 
	 * @return true if the stack is empty; false otherwise
	 */
	public boolean isEmpty() {

		if (top == -1) {

			return true;

		} // end if

		else {

			return false;

		} // end else

	} // end isEmpty

	/**
	 * Returns the maximum size of the ArrayStack
	 * 
	 * @return the maximum size
	 */
	public int length() {

		return ArrayStack.length;

	} // end length

	/**
	 * Returns the number of data items in this stack.
	 * 
	 * @return int number of data items in this stack
	 */
	public int size() {

		return top + 1;

	} // end size

	/**
	 * Returns a string representation of this stack.
	 * 
	 * @return String representation of this stack
	 */
	public String toString() {

		String str = "Stack: ";

		for (int i = 0; i < size(); i++) {

			str = str + ArrayStack[i].toString();

			if (i < size() - 1) {

				str = str + ", ";

			} // end if

		} // end for

		return str;

	} // end toString

} // end ArrayStack
