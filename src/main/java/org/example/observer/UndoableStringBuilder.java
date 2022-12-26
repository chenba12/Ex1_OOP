package org.example.observer;

import java.util.Stack;


/**
 *
 */
public class UndoableStringBuilder {


    private final StringBuilder stringBuilder = new StringBuilder();
    private final Stack<String> stringStack = new Stack<>();


    /**
     * @return the StringBuilder data member
     */
    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }


    /**
     * @return the Stack data member
     */
    public Stack<String> getStringStack() {
        return stringStack;
    }


    /**
     * appends a new string to the existing one.
     *
     * @param str: the string that you want to append to the existing string of the stringBuilder.
     * @return return the current instance of the UndoableStringBuilder class.
     */
    public UndoableStringBuilder append(String str) {
        stringBuilder.append(str);
        stringStack.push(stringBuilder.toString());
        return this;
    }


    /**
     * delete a part of the existing string.
     *
     * @param start The beginning index.
     * @param end   The end index.
     * @return return the current instance of the UndoableStringBuilder class.
     * @throws IllegalArgumentException if start is a negative number, end is bigger than the current string or when
     *                                  the start index > the end index.
     */
    public UndoableStringBuilder delete(int start, int end) throws IllegalArgumentException {
        if (start < 0) throw new IllegalArgumentException("start not valid");
        if (end > stringBuilder.length()) throw new IllegalArgumentException("end not valid");
        if (start > end) throw new IllegalArgumentException("start can't be bigger than end");
        stringBuilder.delete(start, end);
        stringStack.push(stringBuilder.toString());
        return this;
    }

    /**
     * insert a new string into the existing one.
     *
     * @param offset the index from where to insert the new string.
     * @param str    the string to insert.
     * @return return the current instance of the UndoableStringBuilder class.
     * @throws IllegalArgumentException if offset is a negative number ot offset is bigger than the current string length.
     */
    public UndoableStringBuilder insert(int offset, String str) throws IllegalArgumentException {
        if (offset < 0 || offset > stringBuilder.length()) throw new IllegalArgumentException("offset not valid");
        stringBuilder.insert(offset, str);
        stringStack.push(stringBuilder.toString());
        return this;
    }


    /**
     * replaces a part of the existing string and insert a new one.
     *
     * @param start index to start replace from.
     * @param end   index to end replace from.
     * @param str   the string you want to replace the old string with.
     * @return return the current instance of the UndoableStringBuilder class.
     * @throws IllegalArgumentException if start is a negative number, end is bigger than the current string or when
     *                                  the start index > the end index
     */
    public UndoableStringBuilder replace(int start, int end, String str) throws IllegalArgumentException {
        if (start < 0) throw new IllegalArgumentException("start not valid");
        if (end > stringBuilder.length()) throw new IllegalArgumentException("end not valid");
        if (start > end) throw new IllegalArgumentException("start can't be bigger than end");
        stringBuilder.replace(start, end, str);
        stringStack.push(stringBuilder.toString());
        return this;
    }


    /**
     * reverse the string and add it to the top of the stack.
     *
     * @return return the current instance of the UndoableStringBuilder class.
     */
    public UndoableStringBuilder reverse() {
        stringBuilder.reverse();
        stringStack.push(stringBuilder.toString());
        return this;
    }


    /**
     * undo the last operation that have been done.
     * removes the top of the stack and replace the current string with the string from stringStack.peek().
     *
     * @return return the current instance of the UndoableStringBuilder class.
     * @throws ArrayIndexOutOfBoundsException if there are no more operations to undo.
     */
    public UndoableStringBuilder undo() throws ArrayIndexOutOfBoundsException {
        if (stringStack.isEmpty()) return this;
        stringStack.pop();
        if (stringStack.isEmpty()) throw new ArrayIndexOutOfBoundsException("nothing to undo");
        stringBuilder.replace(0, stringBuilder.length(), stringStack.peek());
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
