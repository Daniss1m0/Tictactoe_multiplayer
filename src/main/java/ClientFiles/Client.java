package ClientFiles;

import ReplicatedClasses.CommandClass;
import ReplicatedClasses.Input_From_Server;
import ReplicatedClasses.ResultsOutput;
import ReplicatedClasses.SerwerInfo;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public static Client localClient;

    public Client(Socket socket){
        try {
            this.socket=socket;
            this.outputStream=new ObjectOutputStream(socket.getOutputStream());
            this.inputStream=new ObjectInputStream(socket.getInputStream());
        }catch (IOException e){
            closeEverything(socket,inputStream,outputStream);
        }
    }

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

    public void  listenForMessage(){
        new Thread(()->{

            while (socket.isConnected()){
                try {
                    try {
                        Input_From_Server msgFromServer = (Input_From_Server) inputStream.readObject();
                        //System.out.println(msgFromServer.getClass());
                        System.out.println(msgFromServer.getInfoType());
                        switch (msgFromServer.getInfoType()){ //**jakis null wychodzi moze trzeba zrobic getInfoType metode
                            case "ResultsOutput":
                                ResultsOutput input=(ResultsOutput) msgFromServer;
                                resultsController.singletone.loadDataFromDatabase(input.playerList);
                                break;

                            case "test":
                                System.out.println("klient dostal wiadomosc");
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

    public void closeEverything(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
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
