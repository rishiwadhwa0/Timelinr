import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JPanel{
	Base base;
	Intro intro;
	Buttons botones;
	public Home(Base b){
		base = b;
		intro = new Intro(base);
		botones = new Buttons(base);
		setLayout(new BorderLayout());
		add(botones, BorderLayout.SOUTH);
		add(intro, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("This is the Home page", (getWidth()/2)-10, getHeight()/2);
	}
}

class Intro extends JPanel{
	Base base;
	JPanel blankPanel, textPanel;
	public Intro(Base b){
		base = b;
		blankPanel = new JPanel();
		textPanel = new DisplayText();
		setLayout(new GridLayout(2, 1));
		add(textPanel);
		add(blankPanel);
	}
}

class DisplayText extends JPanel{
	JLabel title, subtitle, desc;
	Font titleFont, subtitleFont, descFont;
	public DisplayText(){
		setLayout(new GridLayout(3, 1));
		title = new JLabel("Timelinr");
		titleFont = new Font("Arial", Font.PLAIN, 35);
		title.setFont(titleFont);
		add(title);
		subtitle = new JLabel("Time management made easy");
		subtitleFont = new Font("Arial", Font.PLAIN, 23);
		subtitle.setFont(subtitleFont);
		add(subtitle);
		desc = new JLabel("Timelinr helps you increase your productivity by focusing on individual tasks, one at a time.");
		descFont = new Font("Arial", Font.PLAIN, 16);
		desc.setFont(descFont);
		add(desc);
	}
}

class Buttons extends JPanel implements ActionListener{
	Base base;
	JButton begin;
	JButton help;
	public Buttons(Base b){
		base = b;
		setLayout(new GridLayout(1, 2));
		begin = new JButton("Let's start this shit");
		help = new JButton("WTF pls help");
		begin.addActionListener(this);
		help.addActionListener(this);
		add(begin);
		add(help);
	}
	
	public void actionPerformed(ActionEvent e){
		requestFocus();
		if(e.getSource() == begin)
			base.showIt("sched");
		else
			base.showIt("help");
	}
	
}
