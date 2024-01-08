package ReplicatedClasses;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;
/**
 * The Commands class contains constant strings representing different commands used in the communication
 * between the client and server.
 */
public class Commands {
    public static final String GetResultsCommand ="GetResults";
    public static final String registerPlayer="registerPlayer";
    public static final String joinRoom="joinRoom";
    public static final String ready="ready";
    public static final String setChar="setChar";
    public static final String restart="restart";
    public static final String leaveRoom="leaveRoom";
}
