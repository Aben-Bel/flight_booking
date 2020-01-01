import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class AdminPage{
	String fName;
	String lName;
	String sType = "e";

	void genID(JTextField t){
		String ans = "";
		int uni;
		Random r = new Random();
		for (int a = 0; a < 20; a++){
			uni = 65 + r.nextInt(26);
			ans = ans + (char)uni;

		}
		t.setText(ans);

	}
	



	//Listener method
	
	void firstChoice(){

		JFrame theframe = new JFrame();
		//theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setLayout(new FlowLayout());
		theframe.setVisible(true);



		JButton aButton = new JButton("Log in as Admin");
		JButton bButton = new JButton("Book a flight!");
		aButton.setPreferredSize(new Dimension(400, 40));
		bButton.setPreferredSize(new Dimension(400, 40));
		theframe.setSize(430,135);
		aButton.addActionListener(new AL(this, theframe, "1-1"));
		bButton.addActionListener(new AL(this, theframe, "1-2"));
		


		theframe.add(aButton);
		theframe.add(bButton);
	}

	void adminChoice(){

		JFrame theframe = new JFrame("Choose action");
		//theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setLayout(new FlowLayout());
		theframe.setVisible(true);



		JButton aButton = new JButton("Add new Aircraft");
		JButton bButton = new JButton("Create a Flight");
		aButton.setPreferredSize(new Dimension(400, 40));
		bButton.setPreferredSize(new Dimension(400, 40));
		theframe.setSize(430,135);
		aButton.addActionListener(new AL(this, theframe, "2-1"));
		bButton.addActionListener(new AL(this, theframe, "2-2"));
		

		theframe.add(aButton);
		theframe.add(bButton);
	}
/*
	JPanel getFPanel(String[][] f){
		int i;
		for (String[] a: f){
			i = 0;
			JLabel l1 = JLabel("First Name: ")
		}
	}
*/
	void chooseFlight(){
		JFrame theframe = new JFrame();
		theframe.setLayout(new FlowLayout());
		theframe.setVisible(true);

		JPanel lc = new JPanel(); //contains all the flight panels.
		JPanel f = new JPanel();

		f.setLayout(new BorderLayout());
		JPanel f2 = new JPanel();
		f2.setLayout(new BorderLayout());
		JPanel v;
		JLabel v1;
		JLabel v2;
		boolean d = false;
		for (String[] a:mock.getFList()){
			System.out.println("A: " + a);
			v1 = new JLabel ("To: ");
			v2 = new JLabel(a[1]);
			v = new JPanel();
			v.add(v1);
			v.add(v2);
			f2.add(v, BorderLayout.NORTH);

			v1 = new JLabel("Check in: ");
			v2 = new JLabel(a[2]);
			v = new JPanel();
			v.add(v1);
			v.add(v2);
			f2.add(v, BorderLayout.CENTER);

			v1 = new JLabel("Arrival Time: ");
			v2 = new JLabel(a[3]);
			v = new JPanel();
			v.add(v1);
			v.add(v2);
			f2.add(v, BorderLayout.SOUTH);

			v1 = new JLabel("From: ");
			v2 = new JLabel(a[0]);
			v = new JPanel();
			v.add(v1);
			v.add(v2);
			f.add(v, BorderLayout.NORTH);

			f.add(f2, BorderLayout.CENTER);

			v1 = new JLabel("Available Seats: ");
			if (this.sType == "e") v2 = new JLabel(a[6]);
			if (this.sType == "b") v2 = new JLabel(a[5]);
			if (this.sType == "f") v2 = new JLabel(a[4]);
			v = new JPanel();
			v.add(v1);
			v.add(v2);
			f.add(v, BorderLayout.SOUTH);
			if (d) lc.add(f, BorderLayout.NORTH);
			else{
				lc.add(f, BorderLayout.CENTER);
				d = true;}
		}
		JButton thebutton = new JButton("Next");
		thebutton.addActionListener(new AL(this, theframe, "5-1"));

		theframe.add(lc, BorderLayout.NORTH);
		theframe.add(thebutton, BorderLayout.SOUTH);
		theframe.pack();





		

		//JScrollPane sp = new JScrollPane(lc);


	}

	void createAircraft(){



		//Create a SEAT NUMBER variable that 


		//Creating and setting up the frame
		JFrame theFrame1 = new JFrame("Add Aircraft");
		Container cp = theFrame1.getContentPane();
		theFrame1.setLayout(new FlowLayout());
		//theFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame1.setSize(350,100);
		theFrame1.setVisible(true);
		cp.setLayout(new BorderLayout());
		JPanel aircraftInfo = new JPanel();

		JPanel header = new JPanel();
		JLabel label1 = new JLabel("Aircraft Information");

		JPanel tFields = new JPanel();
		JPanel airID = makeTField("Aircraft ID");
		JPanel airMod = makeTField("Aircraft Model");
		JPanel airMan = makeTField("Aircraft Manufacturer");

		JButton gnr = new JButton("Generate ID");
		airID.add(gnr);
		gnr.addActionListener(new AL(this, theFrame1, "gnr", (JTextField)airID.getComponent(1)));

		header.add(label1);

		tFields.setLayout(new BorderLayout());
		tFields.add(airID, BorderLayout.NORTH);
		tFields.add(airMod, BorderLayout.SOUTH);
		tFields.add(airMan, BorderLayout.CENTER);

		aircraftInfo.setLayout(new BorderLayout());
		aircraftInfo.add(header, BorderLayout.NORTH);
		aircraftInfo.add(tFields, BorderLayout.SOUTH);
		


		JLabel label2 = new JLabel("Seat Information");
		JPanel carriesLabel = new JPanel();
		JPanel fSeats = makeTField("First Class Seats: ");
		JPanel bSeats = makeTField("Bussiness Class Seats: ");
		JPanel eSeats = makeTField("Economy Class Seats: ");
		JPanel seatArr = makeTField("Seating Arrangement: ");

		JPanel seatInfo = new JPanel();
		JPanel seatInfoInner = new JPanel();
		seatInfo.setLayout(new BorderLayout());
		seatInfoInner.setLayout(new BorderLayout());

		carriesLabel.add(label2);
		///seatInfo.add(carriesLabel, BorderLayout.NORTH);
		//seatInfo.add(seatInfoInner, BorderLayout.CENTER);
		//seatInfo.add(seatArr, BorderLayout.SOUTH);
		seatInfoInner.add(fSeats, BorderLayout.NORTH);
		seatInfoInner.add(bSeats, BorderLayout.CENTER);
		seatInfoInner.add(eSeats, BorderLayout.SOUTH);

		JPanel fs = new JPanel();
		fs.setLayout(new BorderLayout());
		JLabel fsL1 = new JLabel("First Class Seating Information");
		JPanel fsL = new JPanel();
		fsL.add(fsL1);
		JPanel fR = makeTField("Number of rows: ");
		JPanel fC = makeTField("Number of columns: ");
		fs.add(fsL, BorderLayout.NORTH);
		fs.add(fR, BorderLayout.CENTER);
		fs.add(fC, BorderLayout.SOUTH);

		JPanel bs = new JPanel();
		bs.setLayout(new BorderLayout());
		JLabel bsL1 = new JLabel("Bussiness Class Seating Information");
		JPanel bsL = new JPanel();
		bsL.add(bsL1);
		JPanel bR = makeTField("Number of rows: ");
		JPanel bC = makeTField("Number of columns: ");
		bs.add(bsL, BorderLayout.NORTH);
		bs.add(bR, BorderLayout.CENTER);
		bs.add(bC, BorderLayout.SOUTH);
		
		JPanel es = new JPanel();
		es.setLayout(new BorderLayout());
		JLabel esL1 = new JLabel("First Class Seating Information");
		JPanel esL = new JPanel();
		esL.add(esL1);
		JPanel eR = makeTField("Number of rows: ");
		JPanel eC = makeTField("Number of columns: ");
		es.add(esL, BorderLayout.NORTH);
		es.add(eR, BorderLayout.CENTER);
		es.add(eC, BorderLayout.SOUTH);


		seatInfo.add(fs, BorderLayout.NORTH);
		seatInfo.add(bs, BorderLayout.CENTER);
		seatInfo.add(es, BorderLayout.SOUTH);

		

		JButton crFlight = new JButton("Create");
		JPanel button = new JPanel();
		button.add(crFlight);
		crFlight.addActionListener(new AL(this, theFrame1, "3-1"));


		cp.add(aircraftInfo,BorderLayout.NORTH);
		cp.add(seatInfo, BorderLayout.CENTER);
		cp.add(button, BorderLayout.SOUTH);

		theFrame1.pack();
	}

	JPanel makeTField(String title){ //Helper Function. (Not directly involved in GUI)
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel(title);
		panel1.add(label1);
		JTextField textfield = new JTextField("Copyright", 18);
		panel1.add(textfield);

		return panel1; 

	}
	JPanel comboMaker(String inp, String[] items){
		JComboBox cb = new JComboBox();
		JPanel p = new JPanel();
		for (String s:items){
			cb.addItem(s);
		}
		p.add(new JLabel(inp));
		p.add(cb);
		return p;

	}
	void createFlight(){
		JFrame theframe = new JFrame("Add Flight");
		Container cp = theframe.getContentPane();
		//theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setVisible(true);
		cp.setVisible(true);
		cp.setLayout(new BorderLayout());

		JPanel airInfo = new JPanel();
		airInfo.setLayout(new BorderLayout());
		JPanel airID = comboMaker("Airplane ID", mock.getPlaneIDList());
		JPanel airName = makeTField("Name");
		JPanel flightID = makeTField("Flight");
		flightID.add(new JButton("Generate ID"));
		airInfo.add(airID, BorderLayout.NORTH);
		airInfo.add(flightID, BorderLayout.CENTER);
		airInfo.add(airName, BorderLayout.SOUTH);


		JPanel locInfo = new JPanel();
		locInfo.setLayout(new BorderLayout());
		JLabel dLabel = new JLabel("Destination", SwingConstants.CENTER);
		JPanel dest1 = makeTField("From: ");
		JPanel dest2 = makeTField("To: ");
		locInfo.add(dLabel, BorderLayout.NORTH);
		locInfo.add(dest1, BorderLayout.CENTER);
		locInfo.add(dest2, BorderLayout.SOUTH);

		JPanel timeInfo = new JPanel();
		timeInfo.setLayout(new BorderLayout());
		JLabel tLabel = new JLabel("Time", SwingConstants.CENTER);
		JPanel t1 = makeTField("Departure Time: ");
		JPanel t2 = makeTField("Arrival Time: ");
		timeInfo.add(tLabel, BorderLayout.NORTH);
		timeInfo.add(t1, BorderLayout.CENTER);
		timeInfo.add(t2, BorderLayout.SOUTH);

		JPanel entries = new JPanel();
		entries.setLayout(new BorderLayout());
		entries.add(airInfo, BorderLayout.NORTH);
		entries.add(locInfo, BorderLayout.CENTER);
		entries.add(timeInfo, BorderLayout.SOUTH);

		JLabel crLabel = new JLabel("Create Flight", SwingConstants.CENTER);
		JButton crButton = new JButton("Create Flight");

		cp.add(crLabel, BorderLayout.NORTH);
		cp.add(entries, BorderLayout.CENTER);
		cp.add(crButton, BorderLayout.SOUTH);




		//theframe.add(entries);
		theframe.pack();
	}

	JPanel datePicker(){
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		JComboBox d = new JComboBox();
		JComboBox m = new JComboBox();
		JComboBox y = new JComboBox();

		for (int i = 1; i <= 31; i++){
			d.addItem(Integer.toString(i));
			if (i<13) m.addItem(Integer.toString(i));
		}
		for (int i = 1900; i <= 2020; i++) y.addItem(Integer.toString(i));

		p.add(d);
		p.add(m);
		p.add(y);
		return (p);
	}
	JPanel datePicker(String[] args){
		JPanel p = new JPanel();
		JComboBox d = new JComboBox();
		for ( String s:args){
			d.addItem(s);
		}

		p.add(d);
		return (p);
	}
	



	void bookFlight1(){
		JFrame theframe = new JFrame("Book a Flight");
		//theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		theframe.setVisible(true);
		Container cp = theframe.getContentPane();

		JPanel loc1 = makeTField("Departure City: ");
		JPanel loc2 = makeTField("Arrival City: ");
		JPanel date1 = new JPanel();
		date1.add(new JLabel("Departure Date: "));
		date1.add(datePicker());
		//JPanel date1 = makeTField("Departure Date: "); //Change text field to date-input-calendar
		String[] args = new String[3];
		args[0] = "Economy";
		args[1] = "Bussiness";
		args[2] = "First Class";
		JPanel seat = new JPanel();
		seat.add(new JLabel("Seat Type"));
		seat.add(datePicker(args)); //Change text field to drop-down list
		JButton theButton = new JButton("Start Booking");
		theButton.addActionListener(new AL(this, theframe, "4-1"));

		JPanel outer = new JPanel();
		outer.setLayout(new BorderLayout());
		JPanel mid = new JPanel();
		mid.setLayout(new BorderLayout());
		JPanel inner = new JPanel();
		inner.setLayout(new BorderLayout());

		inner.add(loc2, BorderLayout.NORTH);
		inner.add(date1, BorderLayout.SOUTH);

		mid.add(loc1, BorderLayout.NORTH);
		mid.add(inner, BorderLayout.CENTER);
		mid.add(seat, BorderLayout.SOUTH);

		outer.add(mid, BorderLayout.NORTH);
		outer.add(theButton, BorderLayout.SOUTH);

		cp.add(outer);
		theframe.pack();
	}



	void selectFlight(){
		
	}
	
	void passengerInfo(){
		JFrame theframe = new JFrame();
		theframe.setVisible(true);
		//theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel basic1 = new JLabel("Basic Info");//1
		JPanel basic = new JPanel();
		basic.add(basic1);
		JPanel titlePanel = new JPanel();//2 //I should change this to drop down. @SemereTereffe
		JComboBox tcb = new JComboBox();
		tcb.addItem("Mr.");
		tcb.addItem("Mrs.");
		tcb.addItem("Miss.");
		tcb.addItem("Dr.");
		titlePanel.add(new JLabel("Title"));
		titlePanel.add(tcb);
		JPanel fNamePanel = makeTField("First Name");//3
		JPanel mNamePanel = makeTField("Middle Name");//4
		JPanel lNamePanel = makeTField("Last Name");//5
		JPanel DOB = new JPanel();//6
		DOB.add(new JLabel("Date of Birth: "));
		DOB.add(datePicker());
		JPanel gender = new JPanel();//2 //I should change this to drop down. @SemereTereffe
		JComboBox gcb = new JComboBox();
		gcb.addItem("M");
		gcb.addItem("F");
		gcb.addItem("Rather Not Say");
		gender.add(new JLabel("Gender"));
		gender.add(gcb);
		JLabel contactLabel1 = new JLabel("Contact Info");//8
		JPanel contactLabel = new JPanel();
		contactLabel.add(contactLabel1);
		JPanel numberPanel = makeTField("Phone Number");//9
		JPanel emailAddress = makeTField("Email Address");//10

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		JPanel panel4 = new JPanel();
		panel4.setLayout(new BorderLayout());
		JPanel panel5 = new JPanel();
		panel5.setLayout(new BorderLayout());

		
		panel1.add(lNamePanel, BorderLayout.NORTH);
		panel1.add(DOB, BorderLayout.SOUTH);

		panel2.add(mNamePanel, BorderLayout.NORTH);
		panel2.add(panel1, BorderLayout.CENTER);
		panel2.add(gender, BorderLayout.SOUTH);
		panel3.add(fNamePanel, BorderLayout.NORTH);
		panel3.add(panel2, BorderLayout.CENTER);
		panel3.add(contactLabel, BorderLayout.SOUTH);

		panel4.add(titlePanel, BorderLayout.NORTH);
		panel4.add(panel3, BorderLayout.CENTER);
		panel4.add(numberPanel, BorderLayout.SOUTH);

		panel5.add(basic, BorderLayout.NORTH);
		panel5.add(panel4, BorderLayout.CENTER);
		panel5.add(emailAddress, BorderLayout.SOUTH);
		theframe.add(panel5, BorderLayout.NORTH);
		JButton theButton = new JButton("Next");
		theframe.add(theButton, BorderLayout.SOUTH);
		theButton.addActionListener(new AL(this, theframe, "6-1"));
		theframe.pack();
	}

	void selectSeat(){
		JFrame theframe = new JFrame();
		//theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setVisible(true);
		theframe.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());

		JLabel l1 = new JLabel("Passenger: " + this.fName + this.lName); //declare the variable
		JLabel l2 = new JLabel("Choose Seat: ");

		JComboBox s1 = new JComboBox();
		JComboBox s2 = new JComboBox();

		s1.addItem("a");
		s1.addItem("b");
		s1.addItem("c");
		s1.addItem("d");
		s1.addItem("e");
		s1.addItem("f");
		s1.addItem("g");
		for (int i = 1; i<=7; i++){
			String a = Integer.toString(i);
			s2.addItem(a);
		}

		p1.add(l1);

		p2.add(l2, BorderLayout.WEST);
		p2.add(s1, BorderLayout.CENTER);
		p2.add(s2, BorderLayout.EAST);

		theframe.add(p1, BorderLayout.NORTH);
		theframe.add(p2, BorderLayout.CENTER);
		JButton thebutton = new JButton("Next");
		theframe.add(thebutton, BorderLayout.SOUTH);
		thebutton.addActionListener(new AL(this, theframe, "7-1"));
		theframe.pack();
	}

	void finalPage(){
		JFrame theframe = new JFrame();
		//theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setVisible(true);
		theframe.setLayout(new BorderLayout());
		theframe.pack();

		String inf1 = "Detailed info about your flight: ";
		JLabel inf = new JLabel(inf1);

		JPanel panel1 = new JPanel();
		panel1.add(inf, BorderLayout.CENTER);


		JPanel flInf = generateflightinfo();



		String lsc1 = "[RadioButtonHere] ";
		JLabel lsc = new JLabel(lsc1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(new JCheckBox("I agree to the lisence and term agreements.", true), BorderLayout.SOUTH);
		JButton thebutton = new JButton("Reserve");

		JPanel panelz = new JPanel();
		panelz.setLayout(new BorderLayout());
		panelz.add(flInf, BorderLayout.NORTH);
		panelz.add(panel2, BorderLayout.SOUTH);

		theframe.add(panel1, BorderLayout.NORTH);
		theframe.add(panelz, BorderLayout.CENTER);
		theframe.add(thebutton, BorderLayout.SOUTH);
		theframe.pack();
	}

	JPanel generateflightinfo(){
		JPanel thepanel = new JPanel();
		String info1 =  " [FLIGHT INFO] ";
		JLabel info = new JLabel(info1);
		thepanel.add(info);
		return thepanel;

	}


	
	//void addAField(String field, JPanel thePanel);
}