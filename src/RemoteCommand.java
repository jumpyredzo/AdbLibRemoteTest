import java.util.Queue;
import java.util.LinkedList;

public class RemoteCommand {
    private final String COMMAND_PREFIX = "input keyevent ";
    private Queue<byte[]> commandQueue;

    public RemoteCommand() {
        commandQueue = new LinkedList<>();
    }

    public synchronized Boolean hasCommand() {
        return commandQueue.size() > 0;
    }

    public synchronized byte[] getNextCommand() {
        return commandQueue.poll();
    }

    // Adds command to queue with corresponding keycode
    public synchronized void queueCommand(String commandStr) {
        switch (commandStr) {
            case "UP":
                commandQueue.add(processCommand("19"));
                break;
            case "DOWN":
                commandQueue.add(processCommand("20"));
                break;
            case "LEFT":
                commandQueue.add(processCommand("21"));
                break;
            case "RIGHT":
                commandQueue.add(processCommand("22"));
                break;
            case "SELECT":
                commandQueue.add(processCommand("66"));
                break;
            case "BACK":
                commandQueue.add(processCommand("4"));
                break;
            default:
                break;
        }
    }

    // Processes keycode to return corresponding command byte array
    private byte[] processCommand(String keyCode) {
        try {
            return (COMMAND_PREFIX + keyCode + "\n").getBytes("UTF-8");
        }
        catch (Exception e) {
            return new byte[0];
        }
    }
}
