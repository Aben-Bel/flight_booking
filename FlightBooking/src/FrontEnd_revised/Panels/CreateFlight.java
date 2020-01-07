package FrontEnd_revised.Panels;

import FrontEnd_revised.Components.CustomTextField;
import FrontEnd_revised.Components.FilterComboBox;
import FrontEnd_revised.Components.ShowMessage;
import com.github.lgooddatepicker.components.DateTimePicker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateFlight extends JPanel {
    public JLabel fromLocation;
    public JLabel toLocation;
    public JLabel departureTime;
    public JLabel arrivalTime;
    public JLabel firstCPrice;
    public JLabel businessCPrice;
    public JLabel economicCPrice;
    public JLabel seatAvailable;
    public JLabel airportName;


    public FilterComboBox fromLocationCBox;
    public FilterComboBox toLocationCBox;
    public DateTimePicker departureTimePicker;
    public DateTimePicker arrivalTimePicker;
    public JTextField firstCPriceField;
    public JTextField businessCPriceField;
    public JTextField economicCPriceField;
    public JTextField seatAvailableField;
    public FilterComboBox airportNameCBox;

    public List<String> to = new ArrayList<>();
    public List<String> from = new ArrayList<>();
    public List<String> airports = new ArrayList<>();

    public JButton create;

    public GridBagConstraints c = new GridBagConstraints();

    public CreateFlight() {
        super(false);
        fromLocation = new JLabel("Departure City or Airport");
        toLocation = new JLabel("Arrival City or Airport");
        departureTime = new JLabel("Departure Time");
        arrivalTime = new JLabel("Arrival Time");
        firstCPrice = new JLabel("First Class Price");
        businessCPrice = new JLabel("Business Class Price");
        economicCPrice = new JLabel("Economic Class Price");
        seatAvailable = new JLabel("Seats Available");
        airportName = new JLabel("Airplane ID");

        from.add("one");
        from.add("two");
        from.add("three");
        fromLocationCBox = new FilterComboBox(from);

        to.add("one");
        to.add("two");
        to.add("three");
        toLocationCBox = new FilterComboBox(to);

        departureTimePicker = new DateTimePicker();
        arrivalTimePicker = new DateTimePicker();
        firstCPriceField = new CustomTextField();
        businessCPriceField = new CustomTextField();
        economicCPriceField = new CustomTextField();
        seatAvailableField = new CustomTextField();

        airports.add("one");
        airports.add("two");
        airports.add("three");
        airportNameCBox = new FilterComboBox(airports);

        create = new JButton("Create Flight");
        create.addActionListener(new CreateFlightActionListener());

        setLayout(new GridBagLayout());

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;

        add(fromLocation, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 1;
        add(fromLocationCBox,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 0;
        add(toLocation, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 1;
        add(toLocationCBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 0;
        add(departureTime, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 1;
        add(departureTimePicker, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.gridx = 0;
        add(arrivalTime, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.gridx = 1;
        add(arrivalTimePicker, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        c.gridx = 0;
        add(firstCPrice, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        c.gridx = 1;
        add(firstCPriceField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 5;
        c.gridx = 0;
        add(businessCPrice, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 5;
        c.gridx = 1;
        add(businessCPriceField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 6;
        c.gridx = 0;
        add(economicCPrice, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 6;
        c.gridx = 1;
        add(economicCPriceField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 7;
        c.gridx = 0;
        add(seatAvailable, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 7;
        c.gridx = 1;
        add(seatAvailableField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 8;
        c.gridx = 0;
        add(airportName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 8;
        c.gridx = 1;
        add(airportNameCBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,0);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 4;
        add(create, c);

        setBackground(Color.GRAY);

    }

    public void reset(){
        fromLocationCBox.setSelectedItem("");
        toLocationCBox.setSelectedItem("");
        firstCPriceField.setText("");
        businessCPriceField.setText("");
        economicCPriceField.setText("");
        seatAvailableField.setText("");
        airportNameCBox.setSelectedItem("");
    }

    private class CreateFlightActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String fromLocationValue = fromLocationCBox.getSelectedItem().toString();
            String toLocationValue = toLocationCBox.getSelectedItem().toString();
            String firstCPriceValue = firstCPriceField.getText();
            String businessCPriceValue = businessCPriceField.getText();
            String economicCPriceValue = economicCPriceField.getText();
            String seatAvailableValue = seatAvailableField.getText();
            String airportNameValue = airportNameCBox.getSelectedItem().toString();

            if(
                    fromLocationValue.length() > 3 &&
                            toLocationValue.matches("\\d+")&&
                            firstCPriceValue.length() > 3 &&
                            businessCPriceValue.matches("\\d+")&&
                            economicCPriceValue.matches("\\d+") &&
                            seatAvailableValue.matches("\\d+") &&
                            airportNameValue.length() > 3
            ){
                // TODO set in the database
                reset();
                new ShowMessage(new JFrame(),
                        "Flight Added",
                        "You have successfully added an Flight");
            }else{
                new ShowMessage(new JFrame("Error"),
                        "Invalid form",
                        "Check the field values you have entered match their logical domain ");
            }
        }
    }

}