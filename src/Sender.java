import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.jdom2.*;
import org. jdom2.output.*;
import java.net.*;

public class Sender {

    public static void main(String[] args){
        String host = "localhost";
        int port = 8000;
        ArrayList<Object> objectsToSerialize = ObjectCreator.userCreationPrompt();
        sendToSerializer(port, host, objectsToSerialize);

    }
    private static void sendToSerializer(int port, String host, ArrayList<Object> objectToSerialize){
        for (Object object: objectToSerialize){
            Class classObject = object.getClass();
            System.out.println("Serializing object " +
                    classObject.getName() + "...");
            //TODO make method in Serializer to serialize the object
            Document doc = Serializer.serializeObject(object);

        }
    }
    private static File generateXML(Document doc){
        File f = new File("FileSent.xml");
        try {
            XMLOutputter xmlOut = new XMLOutputter();
            xmlOut.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            xmlOut.output(doc, bw);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }return f;
    }

}
