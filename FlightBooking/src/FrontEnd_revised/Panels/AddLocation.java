package FrontEnd_revised.Panels;

import Backend.Exceptions.InvalidEntry;
import Backend.Exceptions.NoMatchingRow;
import Backend.Location;
import FrontEnd_revised.Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class AddLocation extends JPanel {
    private final HashMap pairs;
    public JLabel city;
    public JLabel country;
    public JLabel airportName;

    public JTextField cityField;
    public JTextField countryField;
    public FilterComboBox airportNameCBox;

    public JButton create;

    public GridBagConstraints c = new GridBagConstraints();



    public AddLocation() {
        super(false);

        city = new JLabel("City");
        country = new JLabel("Country");
        airportName = new JLabel("Airport Name");

        cityField = new CustomTextField();
        countryField = new CustomTextField();

        pairs = (HashMap) new MakeAirportList().getAirports();
        ArrayList loc = new ArrayList(pairs.keySet());

        airportNameCBox = new FilterComboBox(loc);

        create = new JButton("Create Location");
        create.addActionListener(new CreateLocationActionListener());

        setLayout(new GridBagLayout());

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;

        add(city, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 1;

        add(cityField, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 0;

        add(country, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 1;

        add(countryField, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 0;

        add(airportName, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 1;

        add(airportNameCBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0, 0);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 4;
        add(create, c);
        setBackground(Color.GRAY);
    }

    public void reset(){
        cityField.setText("");
        countryField.setText("");
    }


    private class CreateLocationActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){

            String cityValue = cityField.getText();
            String countryValue = countryField.getText();
            String airportNameValue = airportNameCBox.getSelectedItem().toString();

            if(
                    cityValue.length() >= 3 &&
                            countryValue.length() >= 3 &&
                            airportNameValue.length() >= 3
            ){
                try {
                    System.out.println(pairs.get(airportNameValue).toString());
                    Location val = new Location(pairs.get(airportNameValue).toString());

                    val.setCity(cityValue);
                    val.setAirportName(airportNameValue);
                    val.setCountry(countryValue);
                    val.update();
                } catch (NoMatchingRow | InvalidEntry noMatchingRow) {
                    noMatchingRow.printStackTrace();
                }
                reset();
                new ShowMessage(new JFrame(),
                        "Location Added",
                        "You have successfully added an Location");
            }else{
                new ShowMessage(new JFrame("Error"),
                        "Invalid form",
                        "Check the field values you have entered match their logical domain ");
            }
        }
    }
}
