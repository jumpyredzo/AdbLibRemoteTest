import java.io.UnsupportedEncodingException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class RemoteCommand {
    private final String COMMAND_PREFIX = "input keyevent ";
    private BlockingQueue<byte[]> commandQueue;

    public RemoteCommand() {
        commandQueue = new LinkedBlockingDeque<>();
    }

    public byte[] getNextCommand() {
        try {
            return commandQueue.take();
        }
        catch (InterruptedException e) {
            return new byte[0];
        }
    }

    // Adds command to queue with corresponding keycode
    public void queueCommand(String commandStr) {
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
            case "PLAY/PAUSE":
                commandQueue.add(processCommand("85"));
                break;
            case "VOL_UP":
                commandQueue.add(processCommand("24"));
                break;
            case "VOL_DOWN":
                commandQueue.add(processCommand("25"));
                break;
            case "HOME":
                commandQueue.add(processCommand("3"));
                break;
            // Not sure if works
            case "SELECT_LONG":
                commandQueue.add(processCommand("--longpress 66"));
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
        catch (UnsupportedEncodingException e) {
            return new byte[0];
        }
    }
}
