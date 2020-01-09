package FrontEnd_revised.Panels;

import Backend.Exceptions.InvalidEntry;
import Backend.Exceptions.SessionExpired;
import Backend.SessionManager;
import Backend.User;
import FrontEnd_revised.Pages.PassengerPage;
import FrontEnd_revised.Components.ShowMessage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class Pay extends ScreenPane {
    public Map<String, String> fields;
    public JCheckBox agree;
    public GridBagConstraints c = new GridBagConstraints();

    public Pay(PassengerPage obj){
        super();
        fields = obj.data;
        String text = "Your flight has been successfully registered\n"
                +"Confirm below for reservation and make sure to make payment in 24hours\n"
                +"The seats and flight information you provided will be reserved accordingly\n"
                +"Select below to agree to our terms and condition.\n"
                +"We value our customers' privacy therefore we don't use your information\n"
                +"other than to book your flight and check in. We thank you for using our service";
        JTextArea message = new JTextArea(text);
        message.setBorder(BorderFactory.createCompoundBorder(
                message.getBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        message.setEditable(false);


        agree = new JCheckBox("I have read and agreed to the terms and conditions");

        JButton reserve = new JButton("Reserve a Seat");

        reserve.addActionListener(new ContinueActionListener());
        setLayout(new GridBagLayout());
        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 0;
        add(message, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 0;
        add(agree, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.gridx = 0;
        add(reserve, c);


    }

    @Override
    public void nextScreen() {
        if(agree.isSelected()){
            ArrayList<String> creds = new ArrayList<>();
            creds.add(fields.get("Username"));// null object if not entered
            creds.add(fields.get("Password"));// null object if not entered
            creds.add(fields.get("First Name"));
            creds.add(fields.get("Middle Name"));
            creds.add(fields.get("Last Name"));
            creds.add("0");
            creds.add(fields.get("Gender"));
            creds.add(fields.get("Phone Number"));
            creds.add(fields.get("Email"));
            creds.add(fields.get("Date Of Birth"));
            User user;
            try {
                user = SessionManager.createUser(creds);
            } catch (InvalidEntry invalidEntry) {
                invalidEntry.printStackTrace();
            }

            fields.get("Departure City");
            fields.get("Arrival City");
            fields.get("Class");
            fields.get("Departure Date");
            fields.get("Title");
            fields.get("Seat Arrangement");
            String flightID = fields.get("Flight Id");


            System.exit(0);
        }else{
            new ShowMessage(new JFrame("Error"),
                    "Invalid Form Value Entry",
                    "Please agree to the terms and conditions." );
        }

    }
}
