package org.example.observer;

import java.util.Objects;

public class ConcreteMember implements Member {


    private UndoableStringBuilder usb;
    private String name;

    public ConcreteMember(String name) {
        this.name = name;
    }

    //for tests
    public ConcreteMember(UndoableStringBuilder usb, String name) {
        this.usb = usb;
        this.name = name;
    }

    public UndoableStringBuilder getUsb() {
        return usb;
    }


    public void setUsb(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    /**
     * @return the name data member
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * When the observerable change updates all the observers each observer calls the update function
     * to change the usb data member
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
        System.out.println("got an update " + this.getName());
    }


    /**
     * hashCode and equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcreteMember that = (ConcreteMember) o;
        return Objects.equals(usb, that.usb) && Objects.equals(name, that.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(usb, name);
    }
}

