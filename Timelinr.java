import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JFrame and main JPanel container for the Timelinr application
 * 
 * @author Neel Chaudhari
 */

public class Timelinr extends JFrame{
	public static void main(String[] args){
		new Timelinr();
	}
	
	public Timelinr(){
		super("Timelinr");
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new Base());
		setResizable(true);
		setVisible(true);
	}
}

class Base extends JPanel implements MouseListener{
	CardLayout cards;
	Home home;
	Help help;
	public Schedule sched;
	Timeline time;
	End end;
	public Base(){
		cards = new CardLayout();
		home = new Home(this);
		help = new Help(this);
		sched = new Schedule(this);
		time = new Timeline();
		end = new End(/*cards*/);
		setLayout(cards);
		add(home, "home");
		add(help, "help");
		add(sched, "sched");
		add(time, "time");
		//add(end, "end");
		
		addMouseListener(this);
	}
	
	public void showIt(String name){
		cards.show(this, name);
	}
	
	public void mouseClicked(MouseEvent e){
		cards.next(this);
	}
	
	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0){}
	public void mousePressed(MouseEvent arg0){}
	public void mouseReleased(MouseEvent arg0){}
}
