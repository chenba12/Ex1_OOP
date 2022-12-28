To run this project;
clone/download this repository and once you have the repository on your computer open it up in an IDE,
go to the test/java folder and open the Tests.java class and press run.

We implemented the design pattern Observer as follows:
ConcreteMember-a class that represents a type of Member that implements the Member interface.
The class holds 2 data members String name and UndoableStringBuilder usb.
The ConcreteMember class is an observer that observer and listen to changes that happen on the GroupAdmin (more on the class later).

GroupAdmin- a class that implements the Sender Interface and holds 2 data members : List<member> memberList and UndoableStringBuilder usb
the GroupAdmin allows members to register and listen to changes that happens in the GroupAdmin
Every change that happen to the UndoableStringBuilder will notify the list of members that are listening to those changes.
the methods that change the UndoableStringBuilder are delete,undomappend and insert.

We have also added a Test class to test some functionality of the GroupAdmin and the observer pattern.
Using the JVMUtilities class we measured efficiency of the code by checking the size of our objects and what happens in memory with those objects.
We used the init() function From the Tests class.
This test runs before all the other tests to initialize the objects.
In this function we created a GroupAdmin object and 3 ConcreteMembers.
We added the 3 members to the GroupAdmin's memberList and appended a new String to the StringBuilder.
At the start we print the jvmInfo() and then in each step we printed what happens in memory using the objectFootprint function.

