
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AdbRemoteTest {
    public static void main(String[] args) {
        RemoteCommand myRemote = new RemoteCommand();
        new Thread(new Runnable() {
			@Override
			public void run() {
				AdbTest.connectToShell("192.168.0.182", 5555, myRemote);
			}
		}).start();
        
        JFrame frame = new JFrame();
        ActionListener btnListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myRemote.queueCommand(((JButton)e.getSource()).getText());
            }
        };
        JButton currBtn = new JButton("UP");
        currBtn.addActionListener(btnListener);
        currBtn.setBounds(300,0,300,300);
        frame.add(currBtn);

        currBtn = new JButton("DOWN");
        currBtn.addActionListener(btnListener);
        currBtn.setBounds(300,600,300,300);
        frame.add(currBtn);

        currBtn = new JButton("LEFT");
        currBtn.addActionListener(btnListener);
        currBtn.setBounds(0,300,300,300);
        frame.add(currBtn);

        currBtn = new JButton("RIGHT");
        currBtn.addActionListener(btnListener);
        currBtn.setBounds(600,300,300,300);
        frame.add(currBtn);

        currBtn = new JButton("SELECT");
        currBtn.addActionListener(btnListener);
        currBtn.setBounds(300,300,300,300);
        frame.add(currBtn);

        currBtn = new JButton("BACK");
        currBtn.setBounds(0,600,300,300);
        currBtn.addActionListener(btnListener);
        frame.add(currBtn);

        frame.setSize(900, 900);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
