import java.io.*;
import java.util.*;
import org.jdom2.*;
import org. jdom2.output.*;
import java.net.*;

public class Send {
    public static void main(String[] args){
        String host = "localhost";
        int port = 8000;
        ArrayList<Object> objectsToSerialize = ObjectCreator.userCreationPrompt();
        sendToSerializer(port, host, objectsToSerialize);
    }
    private static void sendToSerializer(int port, String host, ArrayList<Object> objectToSerialize){
        for (Object object: objectToSerialize){
            try {
                Class classObject = object.getClass();
                System.out.println("Serializing object " +
                        classObject.getName() + "...");
                Document doc = Serializer.initializeSerialization(object);
                File f = generateXML(doc);
                sendFileToReciever(port,host,f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static File generateXML(Document doc){
        File f = new File("FileSent.xml");
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            XMLOutputter xmlOut = new XMLOutputter();
            xmlOut.output(doc, bw);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }return f;
    }
    private static void sendFileToReciever(int port, String host, File f){
        try {
            Socket s = new Socket(host, port);
            System.out.println("Establishing connection...\n" +
                    "Connection established, connected to "+ s.getRemoteSocketAddress());
            FileInputStream fis = new FileInputStream(f);
            OutputStream os = s.getOutputStream();
            int readBytes = 0;
            byte bytesInFile[] = new byte[1024*1024];
            while(0 < (readBytes = fis.read(bytesInFile))){os.write(bytesInFile,0,readBytes);}
            fis.close();
            os.close();
            s.close();
            System.out.println("File successfully sent, Closing Connection...");
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}




