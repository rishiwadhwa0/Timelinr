import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Help extends JPanel{
	Base base;
	Font qFont, aFont;
	JLabel q1, q2, q3, a1, a2, a3;
	public Help(Base b){
		base = b;
		q1 = new JLabel("deez nuts");
		q2 = new JLabel("hah got eem");
		q3 = new JLabel("whats 9 + 10");
		a1 = new JLabel("twenniwan");
		a2 = new JLabel("what are thooose");
		a3 = new JLabel("stfu");
		qFont = new Font("Arial", Font.PLAIN, 23);
		aFont = new Font("Arial", Font.PLAIN, 17);
		q1.setFont(qFont);
		q2.setFont(qFont);
		q3.setFont(qFont);
		a1.setFont(aFont);
		a2.setFont(aFont);
		a3.setFont(aFont);
		add(q1);
		add(a1);
		add(q2);
		add(a2);
		add(q3);
		add(a3);
	}
}
