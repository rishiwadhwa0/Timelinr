import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class draws the timeline using the data from the Schedule class.
 * 
 * @authors Rishi Wadhwa
 * @version 1/30/16
 */

public class Timeline extends JPanel{
	ArrayList<ArrayList<String>> st;
	String end;
	Top t;
	Middle m;
	Bottom b;
	public Timeline(){
		setLayout(new GridLayout(3, 1));
		st = null;
		end = "";
	}
	public void setST(ArrayList<ArrayList<String>> s){
		removeAll();
		st = s;
		end = st.get(st.size()-1).get(2);
		t = new Top(st, this, end);
		m = new Middle(st);
		b = new Bottom(st, this);
		add(t);
		add(m);
		add(b);
		revalidate();
		repaint();
	}		      
}
	
class Top extends JPanel implements ActionListener{
	Timeline timel;
	Timer time;
	String currTime;
	DateFormat dateFormat;
	Calendar cal;
	JLabel timela;
	String end;
	ArrayList<ArrayList<String>> data;
	public Top(ArrayList<ArrayList<String>> st, Timeline ti, String e){
		data = st;
		timel = ti;
		end = e.substring(0, 2) + ":" + e.substring(2, 4) + ":" + "00";
		time = new Timer(1, this);
		time.addActionListener(this);
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		cal = Calendar.getInstance();
		String str = dateFormat.format(cal.getTime());
		currTime = str.substring(str.indexOf(" ")+1);
		timela = new JLabel(currTime);
		add(timela);
		time.start();
	}

	public void actionPerformed(ActionEvent e){
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		cal = Calendar.getInstance();
		String str = dateFormat.format(cal.getTime());
		currTime = str.substring(str.indexOf(" ")+1);
		String[] lumos = currTime.split(":");
		int timeHrs = Integer.parseInt(lumos[0]);
		int timeMin = Integer.parseInt(lumos[1]);
		int timeSec = Integer.parseInt(lumos[2]);
		String[] lomsees = end.split(":");
		int endHrs = Integer.parseInt(lomsees[0]);
		int endMin = Integer.parseInt(lomsees[1]);
		int endSec = Integer.parseInt(lomsees[2]);
		int diffHrs = endHrs - timeHrs;
		int diffMin = endMin - timeMin;
		int diffSec = endSec - timeSec;
		if(diffSec < 0){
			diffMin--;
			diffSec = 60 + diffSec;
		}
		if(diffMin < 0){
			diffHrs--;
			diffMin = 60 + diffMin;
		}
		String diff = "";
		if(diffHrs < 10)
			diff += "0" + diffHrs;
		else
			diff += "" + diffHrs;
		if(diffMin < 10)
			diff += ":0" + diffMin;
		else
			diff += ":" + diffMin;
		if(diffSec < 10)
			diff += ":0" + diffSec;
		else
			diff += ":" + diffSec;
		if(diffHrs < 0 || diffMin < 0 || diffSec < 0){
			diff = "00:00:00";
			timela.setForeground(Color.RED);
			timela.setText(diff);
			timela.revalidate();
			time.stop();
		}
		else{
			timela.setText(diff);
			timela.revalidate();
		}
	}
}

class Middle extends JPanel{
	ArrayList<ArrayList<String>> data;
	int[] hourpixels;
	
	public Middle(ArrayList<ArrayList<String>> st){
		data = st;
		hourpixels = new int[25];
	}
	
	public void paintComponent(Graphics g){
		setBackground(Color.BLACK);
		super.paintComponent(g);

		int width = (int)getWidth();
		int height = (int)getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(50, (height/2) - 5, width -100, 10);
		
		int hour = 0;
		String hstring = "";
		int twentyfour = (width - 100)/24;
		int temphour = 0;
		for(int i = 50; hour < 25; i += twentyfour){//add numbers to number line
			hourpixels[hour] = i;
			if(hour >= 13)
				temphour = hour - 12;
			else
				temphour = hour;
			
			if (hour == 0)
				g.drawString("12", i, (height/2) + 40);
			else{
				hstring = temphour + "";
				g.drawString(hstring, i, (height/2) + 40);
			}
			
			hour++;
		}/*
		ArrayList<String> testData = new ArrayList<String>();
		testData.add("Math");
		testData.add("1");
		testData.add("pm");
		testData.add("4");
		testData.add("pm");
		ArrayList<String> testData2 = new ArrayList<String>();
		testData2.add("Chem");
		testData2.add("3");
		testData2.add("am");
		testData2.add("6");
		testData2.add("am");
		ArrayList<String> testData3 = new ArrayList<String>();
		testData3.add("History");
		testData3.add("1");
		testData3.add("am");
		testData3.add("2");
		testData3.add("am");
		ArrayList<ArrayList<String>> testbigData = new ArrayList<ArrayList<String>>();
		testbigData.add(testData);
		testbigData.add(testData2);
		testbigData.add(testData3);
		//getInfo(testbigData);
		*/
		String task = "";
		//String ampm1 = "";
		//String ampm2 = "";
		String start = "";
		String end = "";
		int p1 = 0;
		int p2 = 0;
		for (int c = 0; c < data.size(); c++){
			task = (data.get(c)).get(0);
			start = (data.get(c)).get(1);
			//ampm1 = (data.get(c)).get(2);
			end = (data.get(c)).get(2);
			//ampm2 = (data.get(c)).get(4);
			
			System.out.println(task);
			System.out.println(start);
			System.out.println(end);

			
			//g.setColor(Color.GREEN);
			Color co = new Color((int)(Math.random()*255)+1, (int)(Math.random()*255)+1, (int)(Math.random()*255)+1);
			g.setColor(co);
			
			System.out.println(twentyfour);
			
			g.drawRect(60 * c, height - 30, 7, 7);
			g.drawString(task, 60 * c + 9, height - 20);

			p1 = hourpixels[Integer.parseInt(start.substring(0,2))] + (Integer.parseInt(start.substring(2,4))*twentyfour)/60;
			p2 = hourpixels[Integer.parseInt(end.substring(0,2))] + (Integer.parseInt(end.substring(2,4))*twentyfour)/60;
			g.fillRect(p1, (height/2) - 7, p2 - p1, 14);
			System.out.println("REPAINTED");
		}
		
	}
	/*
	public void getInfo(ArrayList<ArrayList<String>> userdata){
		data = userdata;
	}*/
}


class Bottom extends JPanel implements ActionListener{
	Timeline timel;
	ArrayList<ArrayList<String>> data;
	Timer time;
	String currTime;
	DateFormat dateFormat;
	int diffHrs, diffMin, inc;
	Calendar cal;
	ArrayList<String> indCurr;
	String end;
	JLabel timela;
	JButton button;
	public Bottom(ArrayList<ArrayList<String>> s, Timeline ti){
		data = s;
		timel = ti;
		inc = 0;
		time = new Timer(1, this);
		time.addActionListener(this);
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		cal = Calendar.getInstance();
		String str = dateFormat.format(cal.getTime());
		currTime = str.substring(str.indexOf(" ")+1);
		indCurr = findCurrTask();
		System.out.println("INDCURR: " + indCurr);
		end = indCurr.get(2).substring(0, 2) + ":" + indCurr.get(2).substring(2, 4) + ":" + "00";
		timela = new JLabel(currTime);
		add(timela);
		button = new JButton("Done with " + indCurr.get(0));
		button.addActionListener(this);
		add(button);
		time.start();
	}
	
	public ArrayList<String> findCurrTask(){
		for(ArrayList<String> a: data){
			if(Integer.parseInt(currTime.substring(0, 2) + currTime.substring(3, 5)) > Integer.parseInt(a.get(1)) && Integer.parseInt(currTime.substring(0, 2) + currTime.substring(3, 5)) < Integer.parseInt(a.get(2))){
				return a;
			}
		}
		return null;
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==time){
			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			cal = Calendar.getInstance();
			String str = dateFormat.format(cal.getTime());
			currTime = str.substring(str.indexOf(" ")+1);
			String[] lumos = currTime.split(":");
			int timeHrs = Integer.parseInt(lumos[0]);
			int timeMin = Integer.parseInt(lumos[1]);
			int timeSec = Integer.parseInt(lumos[2]);
			String[] lomsees = end.split(":");
			int endHrs = Integer.parseInt(lomsees[0]);
			int endMin = Integer.parseInt(lomsees[1]);
			int endSec = Integer.parseInt(lomsees[2]);
			diffHrs = endHrs - timeHrs;
			diffMin = endMin - timeMin;
			int diffSec = endSec - timeSec;
			if(diffSec < inc){
				diffMin--;
				diffSec = 60 + diffSec;
			}
			if(diffMin < inc){
				diffHrs--;
				diffMin = 60 + diffMin;
			}
			String diff = "";
			if(diffHrs < 10)
				diff += "0" + diffHrs;
			else
				diff += "" + diffHrs;
			if(diffMin < 10)
				diff += ":0" + diffMin;
			else
				diff += ":" + diffMin;
			if(diffSec < 10)
				diff += ":0" + diffSec;
			else
				diff += ":" + diffSec;
			if(diffHrs < 0 || diffMin < 0 || diffSec < 0){
				diff = "00:00:00";
				inc = -60;
				timela.setForeground(Color.RED);
				timela.setText(diff);
				timela.revalidate();
				time.stop();
			}
			else{
				timela.setText(diff);
				timela.revalidate();
			}
		}
		else if(e.getSource()==button){
			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			cal = Calendar.getInstance();
			String str = dateFormat.format(cal.getTime());
			currTime = str.substring(str.indexOf(" ")+1);
			int ind = data.indexOf(findCurrTask());
			button.setText("You saved: " + diffHrs + " hours and " + diffMin + " minutes!");
			data.get(ind).set(2, currTime.replace(":", "").substring(0, 4));
			timel.m = new Middle(data);
			System.out.println("DATA: " + timel.m.data);
			timel.m.repaint();
			timel.m.revalidate();
			timel.repaint();
			timel.revalidate();
		}
	}
}
