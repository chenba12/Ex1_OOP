package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class GroupAdmin implements Sender {


    private final UndoableStringBuilder usb;
    private final List<Member> memberList;

    public GroupAdmin() {
        this.usb = new UndoableStringBuilder();
        this.memberList = new ArrayList<>();
    }

    /**
     * @return the StringBuilder data member
     */
    public UndoableStringBuilder getUsb() {
        return usb;
    }

    /**
     * @return the memberList data member
     */
    public List<Member> getMemberList() {
        return memberList;
    }

    /**
     * register a new observer to observe the GroupAdmin
     */
    @Override
    public void register(Member obj) {
        if (obj == null) return;
        obj.update(usb);
        memberList.add(obj);
    }

    /**
     * unregister an observer from the memberList
     */
    @Override
    public void unregister(Member obj) {
        ConcreteMember concreteMember = (ConcreteMember) obj;
        concreteMember.setUsb(null);
        memberList.remove(obj);
    }

    /**
     * insert a new string into the existing one.
     * updates the members about the change.
     *
     * @param offset the index from where to insert the new string.
     * @param obj    the string to insert.
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);
        update();
    }

    /**
     * appends a new string to the existing one.
     * updates the members about the change.
     *
     * @param obj: the string that you want to append to the existing string of the stringBuilder.
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
        update();
    }

    /**
     * delete a part of the existing string.
     * updates the members about the change.
     *
     * @param start The beginning index.
     * @param end   The end index.
     */
    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
        update();
    }

    /**
     * undo the last operation that have been done.
     * removes the top of the stack and replace the current string with the string from stringStack.peek().
     * updates the members about the change.
     */
    @Override
    public void undo() {
        usb.undo();
        update();
    }


    /**
     * go over the memberList that registered to the GroupAdmin and for each member call the update method
     * updates the members about the change.
     */
    public void update() {
        for (Member member : memberList) {
            member.update(this.getUsb());
        }
    }
}
