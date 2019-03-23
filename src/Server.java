import javax.swing.text.Document;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.jdom2.*;
import org.jdom2.input.*;

public class Server extends Thread{
    private Socket socket;
    private ServerSocket serverSocket;

    public Server(int port){

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(360000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        int port = 8000;
        Thread serverT = new Server(port);
        serverT.start();
    }
    @Override
    public void run(){
        while(true){
            socket = serverSocket.accept();
            System.out.println("Establishing connection...\n" +
                    "Connection established, connected to "+ socket.getRemoteSocketAddress());
            File f = new File("FileReceived.xml");
            InputStream is = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(f);
            int readBytes = 0;
            byte[] bytesInFile = new byte[1024 * 1024];
            while((readBytes =  is.read(bytesInFile)) > 0){
               fos.write(bytesInFile, 0, readBytes);
                break;
            }
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = (Document) saxBuilder.build(f);
            Object object = Deserializer.deserialization(doc);

            System.out.println(\n");
            Inspector inspectorGadget = new Inspector();
            inspectorGadget.inspect(obj, false);
            System.out.println("\n======================================================");

            //close socket
            socket.close();
        }
            catch (Exception e){
            e.printStackTrace();
        }

    }
}

        }
    }
}
