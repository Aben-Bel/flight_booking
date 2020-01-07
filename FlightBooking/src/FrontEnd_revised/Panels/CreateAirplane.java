package FrontEnd_revised.Panels;

import FrontEnd_revised.Components.CustomTextField;
import FrontEnd_revised.Components.ShowMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAirplane extends JPanel {

    public JLabel model;
    public JLabel firstClass;
    public JLabel brand;
    public JLabel businessClass;
    public JLabel economicClass;
    public JLabel seatArrangment;

    public JTextField modelField;
    public JTextField firstClassField;
    public JTextField brandField;
    public JTextField businessClassField;
    public JTextField economicClassField;
    public JTextField seatArrangmentField;

    public JButton create;

    public GridBagConstraints c = new GridBagConstraints();


    public CreateAirplane() {
        super(false);
        setBackground(Color.GRAY);

        model = new JLabel("Model");
        firstClass = new JLabel("First Class");
        brand = new JLabel("Brand");
        businessClass = new JLabel("Business Class");
        economicClass = new JLabel("Economic Class");
        seatArrangment = new JLabel("Seat Arrangment");

        modelField = new JTextField();
        modelField.setPreferredSize( new Dimension( 250, 44 ) );

        firstClassField = new CustomTextField();

        brandField = new CustomTextField();

        businessClassField = new CustomTextField();

        economicClassField = new CustomTextField();

        seatArrangmentField = new CustomTextField();

        create = new JButton("Create Airplane");

        create.addActionListener( new CreateAirplaneActionListener() );

        setLayout(new GridBagLayout());

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;

        add(model, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 1;

        add(modelField, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 0;

        add(firstClass, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 1;

        add(firstClassField, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 0;

        add(businessClass, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 1;

        add(businessClassField, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.gridx = 0;

        add(economicClass, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.gridx = 1;

        add(economicClassField, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        c.gridx = 0;

        add(brand, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        c.gridx = 1;

        add(brandField, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 5;
        c.gridx = 0;

        add(seatArrangment, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 5;
        c.gridx = 1;

        add(seatArrangmentField, c);

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
        modelField.setText("");
        firstClassField.setText("");
        brandField.setText("");
        businessClassField.setText("");
        economicClassField.setText("");
        seatArrangmentField.setText("");
    }


    private class CreateAirplaneActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            reset();
            new ShowMessage(new JFrame(),
                    "Airplane Added",
                    "You have successfully added an Airplane");
        }
    }
}
