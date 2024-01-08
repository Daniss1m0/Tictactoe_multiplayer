package ClientFiles;

import ClientFiles.Controllers.gameController;
import ClientFiles.Controllers.resultsController;
import ReplicatedClasses.*;

import java.io.*;
import java.net.Socket;
/**
 * The Client class handles the communication with the server.
 * It includes methods for sending and receiving messages, as well as handling various commands from the server.
 */
public class Client {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public String playerName;
    public long roomId;
    public static Client localClient;
    /**
     * Constructs a new Client instance with the given socket.
     *
     * @param socket The socket for communication with the server.
     */
    public Client(Socket socket){
        try {
            this.socket=socket;
            this.outputStream=new ObjectOutputStream(socket.getOutputStream());
            this.inputStream=new ObjectInputStream(socket.getInputStream());
        }catch (IOException e){
            closeEverything(socket,inputStream,outputStream);
        }
    }
    /**
     * Sends a command to the server.
     *
     * @param command The command to be sent to the server.
     */
    public void sendMessage(CommandClass command){
        try {
            System.out.println("proba wyslania wiadomosci: "+command.getCommand());
            outputStream.writeObject(command);
            outputStream.flush();
            System.out.println("wiadomosc "+command.getCommand()+" zostala wyslana do serwera");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Listens for messages from the server and processes them accordingly.
     */
    public void  listenForMessage(){
        new Thread(()->{

            while (socket.isConnected() && !socket.isClosed()){
                try {
                    try {
                        System.out.println("klient slucha: ");
                        Input_From_Server msgFromServer = (Input_From_Server) inputStream.readObject();
                        //System.out.println(msgFromServer.getClass());
                        System.out.println(msgFromServer.getInfoType());
                        switch (msgFromServer.getInfoType()){
                            case "ResultsOutput":
                                ResultsOutput input=(ResultsOutput) msgFromServer;
                                resultsController.singletone.loadDataFromDatabase(input.playerList);
                                break;

                            case "test":
                                System.out.println("klient dostal wiadomosc");
                                break;

                            case Commands.ready:
                                boolean isPlr1= msgFromServer.isPlr1();
                                boolean isReady=msgFromServer.isReady();
                                System.out.println("prohuje ustawic ready dla: "+isReady);
                                gameController.localGameController.setReady(isPlr1,isReady);
                                break;

                            case Commands.joinRoom:
                                String plrName=msgFromServer.getPlayerName();
                                boolean isplr1= msgFromServer.isPlr1(); //zamiast idPlr1 jest isplr1 bo nie moze byc to samo w 2 casach
                                gameController.localGameController.setPlrName(isplr1,plrName);
                                break;

                            case Commands.restart:
                                gameController.localGameController.handleRestart();
                                break;

                            case Commands.setChar:
                                int row=msgFromServer.getCharX();
                                int col= msgFromServer.getCharY();
                                System.out.println("GRACZ DOSTAL WIADOMOSC SET CHAR OD SERWERA");
                                gameController.localGameController.handleMove(row,col);
                                break;


                            default:
                                break;
                        }

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }catch (IOException e){
                    closeEverything(this.socket, this.inputStream, this.outputStream);
                }
            }

        }).start();
    }
    /**
     * Closes all communication-related resources.
     *
     * @param socket           The socket to be closed.
     * @param objectInputStream The input stream to be closed.
     * @param objectOutputStream The output stream to be closed.
     */
    public void closeEverything(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        System.out.println("klient rozlaczony");
        try {
            if(objectInputStream!=null){
                objectInputStream.close();
            }
            if(objectOutputStream!=null){
                objectOutputStream.close();
            }
            if(socket!=null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Starts the client by connecting to the server, setting up the client instance, and listening for messages.
     * This is the first method to be called for the client functionality.
     */
    public static void startClient(){ // pierwsza wywolywana metoda
        try{
            Socket socket = new Socket(SerwerInfo.ServerIP,SerwerInfo.ServerPort);
            Client client = new Client(socket);
            //client.sendMessage(new CommandClass(Commands.GetResultsCommand));
            localClient=client;
            client.listenForMessage();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
