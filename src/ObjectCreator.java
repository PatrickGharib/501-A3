import java.util.Scanner;

public class ObjectCreator{
    public static void main(String[] args){
        System.out.println("Please Proceed in creating the objects you wish to send: \n" +
                "1) Simple object with primitive fields\n" +
                "2) Object containing references to other objects\n" +
                "3) Object containing array of primitives\n" +
                "4) Object that contains an array of object references\n" +
                "5) collection objects that refers to other objects");
        Scanner userIn = new Scanner(System.in);
        int choice = userIn.nextInt();
        while(choice > 1 || choice < 5){
            System.out.println("Please enter a number specified");
            choice = userIn.nextInt();
        }
        switch (choice){
            case 1:
                System.out.println("What would you like the value of your int field to be: " ){

            }
                //TODO create simple object
                //send to method to create a simple object
                //append that to the byte stream
                //prompt again
            case 2:
                //TODO create object with object reference
                //create reference object
            case 3:
                //TODO create object with array of prims
            case 4:
                //TODO create object with array of objects
            case 5:
                //TODO create a collection of objects
        }
        public SimpleObject createSimpleObject(){
            System.out.println("please enter an integer value for your simple object: ");
            while()
            SimpleObject newSimpleObject = new SimpleObject()
            return;
        }
    }

}
