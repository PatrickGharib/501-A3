import java.util.ArrayList;
import java.util.Scanner;

public class ObjectCreator {
    public static void main(String[] args) {


    }

    //TODO give this a return type
    private static void userCreationPrompt() {
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
        while (!userIn.hasNextInt()) {
            System.out.println("Please enter a valid ");
            choice = userIn.nextInt();
        }
        switch (choice) {
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

    public static SimpleObject createSimpleObject() {
        System.out.println("Simple object has two fields: " +
                "\n\t- intField" +
                "\n\t- booleanField");
        System.out.println("Please assign an integer for intField: ");
        Scanner userin = new Scanner(System.in);

        while (!userin.hasNextInt()) {
            userin.next();
            System.out.println("Please enter a valid integer!");
        }
        int intField = userin.nextInt();
        System.out.println("Please enter a boolean(\"true\" or \"false\") for booleanField: ");
        while (!userin.hasNextBoolean()) {
            userin.next();
            System.out.println("Please enter either \"true\" or \"false\"!");
        }
        boolean booleanField = userin.nextBoolean();
        System.out.println("SimpleObject Creation in progress...");
        SimpleObject newSimpleObject = new SimpleObject(intField, booleanField);
        System.out.println("Done...");
        return newSimpleObject;
    }

    private static ReferenceObject createReferenceObject() {
        System.out.println("ReferenceObject has one field: " +
                "\n\t- SimpleObjectField");
        SimpleObject newSimpleObject = createSimpleObject();
        System.out.println("Creation of object with reference to an object now in progress...");
        ReferenceObject newReferenceObject = new ReferenceObject(newSimpleObject);
        System.out.println("done...");
        return newReferenceObject;
    }

    private static PrimArrayObject createPrimitiveArrayObject() {
        System.out.println("PrimArrayObject has one field:" +
                "\n\t- intArray");
        Scanner userin = new Scanner(System.in);
        int arrayLength;
        System.out.println("Please enter the number of integers you would like in your array: ");

        while (!userin.hasNextInt()) {
            userin.next();
            System.out.print("Please enter a valid Integer");
        }
        arrayLength = userin.nextInt();
        int[] primitiveArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            System.out.println("Please specify a number to insert at index " +
                    primitiveArray[i] + ":");
            while (!userin.hasNextInt()) {
                userin.next();
                System.out.println("Please enter a valid Integer!");
            }
            primitiveArray[i] = userin.nextInt();
        }
        System.out.println("creation of object with primitive array in progress...");
        PrimArrayObject newPrimArrayObject = new PrimArrayObject(primitiveArray);
        System.out.println("done...");
        return newPrimArrayObject;
    }

    private static ReferenceArrayObject createReferenceArrayObject() {
        System.out.println("ReferenceArrayObject has one Field: " +
                "\n\t- objectArrayField");
        Scanner userin = new Scanner(System.in);
        System.out.println("Please enter the number of obejcts you would like to create: ");
        while (!userin.hasNextInt()) {
            userin.next();
            System.out.println("Please enter a valid Integer!");
        }
        int numOfObjects = userin.nextInt();
        SimpleObject[] objectArray = new SimpleObject[numOfObjects];
        for (int i = 0; i < numOfObjects; i++) {
            System.out.println("Creating object #" + i);
            objectArray[i] = createSimpleObject();
        }
        System.out.println("creation of object with object array in progress...");
        ReferenceArrayObject newReferenceArrayObject = new ReferenceArrayObject(objectArray);
        System.out.println("done...");

        return newReferenceArrayObject;
    }

    private static CollectionObject createCollectionObject() {
        System.out.println("Collection object has one field: " +
                "\n\t- collectionField");
        Scanner userin = new Scanner(System.in);
        ArrayList<SimpleObject> objectArrayList = new ArrayList<SimpleObject>();
        CollectionObject newCollectionObject;
        while (true) {

            System.out.println("would you like to add an object to the coollection(0->yes, 1-> no)?: ");
            String choice = userin.nextLine();
            if (choice.equals("0")){
                SimpleObject newCollectionSimpleObject = createSimpleObject();
                objectArrayList.add(newCollectionSimpleObject);}
            else if (choice.equals("1")){
                newCollectionObject = new CollectionObject();
                break;}
            else{
                System.out.println("Please enter either 1 or 0!");
            }
        }
        return newCollectionObject;

    }



}
