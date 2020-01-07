package FrontEnd_revised.Panels;

import FrontEnd_revised.Components.CustomTextField;
import FrontEnd_revised.Components.ShowMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateLocation extends JPanel {
    public JLabel city;
    public JLabel country;
    public JLabel airportName;

    public JTextField cityField;
    public JTextField countryField;
    public JTextField airportNameField;

    public JButton create;

    public GridBagConstraints c = new GridBagConstraints();



    public CreateLocation() {
        super(false);

        city = new JLabel("City");
        country = new JLabel("Country");
        airportName = new JLabel("Airport Name");

        cityField = new CustomTextField();
        countryField = new CustomTextField();
        airportNameField = new CustomTextField();

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

        add(airportNameField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0, 0);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 4;
        add(create, c);
    }

    public void reset(){
        cityField.setText("");
        countryField.setText("");
        airportNameField.setText("");
    }

    private class CreateLocationActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            reset();
            new ShowMessage(new JFrame(),
                    "Location Added",
                    "You have successfully added a Location");
        }
    }
}