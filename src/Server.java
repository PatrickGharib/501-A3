
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
            serverSocket.setSoTimeout(360_000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        int port = 8000;
        Thread serverT = new Server(port);
        System.out.println("Waiting for Connection...");
        serverT.start();

    }

    public void run(){

        while(true){
            try {
                socket = serverSocket.accept();
                System.out.println("Establishing connection...\n" +
                        "Connection established, connected to "+ socket.getRemoteSocketAddress());
                System.out.println("" +
                        "                     ,---.           ,---.\n" +
                        "                    / /\"`.\\.--\"\"\"--./,'\"\\ \\\n" +
                        "                    \\ \\    _       _    / /\n" +
                        "                     `./  / __   __ \\  \\,'\n" +
                        "                      /    /_O)_(_O\\    \\\n" +
                        "                      |  .-'  ___  `-.  |\n" +
                        "                   .--|       \\_/       |--.\n" +
                        "                 ,'    \\   \\   |   /   /    `.\n" +
                        "                /       `.  `--^--'  ,'       \\\n" +
                        "             .-\"\"\"\"\"-.    `--.___.--'     .-\"\"\"\"\"-.\n" +
                        ".-----------/         \\------------------/         \\--------------.\n" +
                        "| .---------\\         /----------------- \\         /------------. |\n" +
                        "| |          `-`--`--'                    `--'--'-'             | |\n" +
                        "| |                                                             | |\n" +
                        "| |                                                             | |\n" +
                        "| |                                                             | |\n" +
                        "| |                                                             | |\n" +
                        "| |                     Inspection start :)                     | |\n" +
                        "| |                                                             | |\n" +
                        "| |                                                             | |\n" +
                        "| |                                                             | |\n" +
                        "| |                                                             | |\n" +
                        "| |                                                             | |\n" +
                        "| |                                                             | |\n" +
                        "| |                                                             | |\n" +
                        "| |_____________________________________________________________| |\n" +
                        "|_________________________________________________________________|\n" +
                        "                   )__________|__|__________(\n" +
                        "                  |            ||            |\n" +
                        "                  |____________||____________|\n" +
                        "                    ),-----.(      ),-----.(\n" +
                        "                  ,'   ==.   \\    /  .==    `.\n" +
                        "                 /            )  (            \\\n" +
                        "                 `==========='    `==========='");
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
                Document doc = (Document)saxBuilder.build(f);
                Object object = Deserializer.deserialize(doc);

                Inspector inspector = new Inspector();
                inspector.inspect(object, true);


                //close socket
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JDOMException e) {
                e.printStackTrace();
            }
            System.out.println("" +
                    "                     ,---.           ,---.\n" +
                    "                    / /\"`.\\.--\"\"\"--./,'\"\\ \\\n" +
                    "                    \\ \\    _       _    / /\n" +
                    "                     `./  / __   __ \\  \\,'\n" +
                    "                      /    /_O)_(_O\\    \\\n" +
                    "                      |  .-'  ___  `-.  |\n" +
                    "                   .--|       \\_/       |--.\n" +
                    "                 ,'    \\   \\   |   /   /    `.\n" +
                    "                /       `.  `--^--'  ,'       \\\n" +
                    "             .-\"\"\"\"\"-.    `--.___.--'     .-\"\"\"\"\"-.\n" +
                    ".-----------/         \\------------------/         \\--------------.\n" +
                    "| .---------\\         /----------------- \\         /------------. |\n" +
                    "| |          `-`--`--'                    `--'--'-'             | |\n" +
                    "| |                                                             | |\n" +
                    "| |                                                             | |\n" +
                    "| |                                                             | |\n" +
                    "| |                                                             | |\n" +
                    "| |                     Inspection end                          | |\n" +
                    "| |                                                             | |\n" +
                    "| |                                                             | |\n" +
                    "| |                                                             | |\n" +
                    "| |                                                             | |\n" +
                    "| |                                                             | |\n" +
                    "| |                                                             | |\n" +
                    "| |                                                             | |\n" +
                    "| |_____________________________________________________________| |\n" +
                    "|_________________________________________________________________|\n" +
                    "                   )__________|__|__________(\n" +
                    "                  |            ||            |\n" +
                    "                  |____________||____________|\n" +
                    "                    ),-----.(      ),-----.(\n" +
                    "                  ,'   ==.   \\    /  .==    `.\n" +
                    "                 /            )  (            \\\n" +
                    "                 `==========='    `==========='");
        }
    }
}
