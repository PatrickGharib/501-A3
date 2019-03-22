import java.util.Scanner;

public class ObjectCreator{
    public static void main(String[] args){



        }
    public static userCreationPrompt(){
        System.out.println("Please Proceed in creating the objects you wish to send: \n" +
                "1) Simple object with primitive fields\n" +
                "2) Object containing references to other objects\n" +
                "3) Object containing array of primitives\n" +
                "4) Object that contains an array of object references\n" +
                "5) collection objects that refers to other objects" +
                "6) serialize" +
                "7) quit");
        Scanner userIn = new Scanner(System.in);
        int choice = userIn.nextInt();
        while(!userIn.hasNextInt()){
            System.out.println("Please enter a valid ");
            choice = userIn.nextInt();
        }
        switch (choice){
            case 1:
                SimpleObject newSimpleObject = createSimpleObject();
            case 2:
                //TODO create object with object reference
                //create reference object
            case 3:
                //TODO create object with array of prims
            case 4:
                //TODO create object with array of objects
            case 5:
                //TODO create a collection of objects
            case 6:
            case 7:
        }
    }
    public static SimpleObject createSimpleObject(){
        System.out.println("Simple object has two fields: " +
                "\n\t- intField" +
                "\n\t- booleanField");
        System.out.println("Please assign an integer for intField: ");
        Scanner userin = new Scanner(System.in);

        while (!userin.hasNextInt()){
            userin.next();
            System.out.println("Please enter a valid integer!");
        }
        int intField = userin.nextInt();
        System.out.println("Please enter a boolean(\"true\" or \"false\") for booleanField: ");
        while (!userin.hasNextBoolean()){
            userin.next();
            System.out.println("Please enter either \"true\" or \"false\"!");
        }
        boolean booleanField = userin.nextBoolean();
        System.out.println("SimpleObject Creation in progress...")
        SimpleObject newSimpleObject = new SimpleObject(intField, booleanField);
        System.out.println("Done...")
        return newSimpleObject;
    }
    private static ReferenceObject createReferenceObject(){
       System.out.println("Reference object has one field: " +
               "\n\t- SimpleObjectField");
       SimpleObject newSimpleObject = createSimpleObject();
       System.out.println("Creation of object with reference to an object now in progress...");
       ReferenceObject newReferenceObject = new ReferenceObject(newSimpleObject);
       return newReferenceObject;
    }
    
}
