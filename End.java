import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class End extends JPanel implements ActionListener{
	Message m;
	JButton b;
	Base base;
	public End(Base ba, String s, int h, int m){
		base = ba;
		setLayout(new BorderLayout());
		m = new Message(s, h, m);
		b = new JButton("Go Back to Home");
		b.addActionListener(this);
		add(b, BorderLayout.SOUTH);
		add(m, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent e){
		base.showIt("home");
	}
}

class Message extends JPanel {
	String message;
	int hours;
	int minutes;
	public Message(String s, int h, int m){
		message = s;
		hours = h;
		mintues = m;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("You have " + message + " " + hours + " hours and " + minutes + " minutes.", (getWidth()/2)-10, getHeight()/2);
	}
}
