package FrontEnd_revised.Panels;

import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import javax.swing.*;

import FrontEnd_revised.Components.FilterComboBox;
import FrontEnd_revised.Pages.PassengerPage;
import FrontEnd_revised.Components.ShowMessage;
import com.github.lgooddatepicker.components.DatePicker;

public class BookFlight extends ScreenPane {
    public JLabel fromLocation;
    public JLabel toLocation;
    public JLabel flightClass;
    public JLabel departureDate;

    public FilterComboBox fromLocationCBox;
    public FilterComboBox toLocationCBox;
    public FilterComboBox flightClassCBox;

    public DatePicker departureDatePicker;

    public JButton book;

    public List<String> flightClasses = new ArrayList<>();
    public List<String>  to = new ArrayList<>();
    public List<String> from = new ArrayList<>();

    public GridBagConstraints c = new GridBagConstraints();
    public Map<String, String> fields;
    public PassengerPage passengerPageThis;

    public BookFlight(PassengerPage obj){
        super();
        fields = obj.data;
        passengerPageThis = obj;

        fromLocation = new JLabel("Departure City or Airport");
        fromLocation.setFont(new Font("Serif", Font.BOLD, 18));
        toLocation = new JLabel("Arrival City or Airport");
        toLocation.setFont(new Font("Serif", Font.BOLD, 18));
        departureDate = new JLabel("Departure Date");
        departureDate.setFont(new Font("Serif", Font.BOLD, 18));
        flightClass = new JLabel("Class");
        flightClass.setFont(new Font("Serif", Font.BOLD, 18));

        departureDatePicker = new DatePicker();

        flightClasses.add("First");
        flightClasses.add("Business");
        flightClasses.add("Economic");
        flightClassCBox = new FilterComboBox(flightClasses);

        from.add("one");
        from.add("two");
        from.add("three");
        fromLocationCBox = new FilterComboBox(from);

        to.add("one");
        to.add("two");
        to.add("three");
        toLocationCBox = new FilterComboBox(to);

        book = new JButton("Book Flight");
        book.addActionListener(new ContinueActionListener());

        setLayout(new GridBagLayout());

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;

        add(fromLocation, c);

        c.insets = new Insets(15,50,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 1;

        add(toLocation, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 0;

        add(fromLocationCBox, c);

        c.insets = new Insets(15,50,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 1;

        add(toLocationCBox, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 0;

        add(departureDate, c);

        c.insets = new Insets(15,50,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 1;

        add(flightClass, c);

        c.insets = new Insets(15,10,15,0);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.gridx = 0;

        add(departureDatePicker, c);

        c.insets = new Insets(15,50,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.gridx = 1;

        add(flightClassCBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,0);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 4;
        add(book, c);
    }

    @Override
    public void nextScreen(){
        String departureCityValue = (String)fromLocationCBox.getSelectedItem();
        String arrivalCityValue = (String)toLocationCBox.getSelectedItem();
        String classValue = (String)flightClassCBox.getSelectedItem();
        String departureDateValue = departureDatePicker.getDateStringOrEmptyString();

        if (departureDatePicker.getDate() != null){
            if(0 < departureDatePicker.getDate().compareTo(LocalDate.now())){
                fields.put("Departure City",departureCityValue);
                fields.put("Arrival City",arrivalCityValue);
                fields.put("Class",classValue);
                fields.put("Departure Date", departureDateValue);

//            System.out.println("dc: "+departureCityValue);
//            System.out.println("ac: "+arrivalCityValue);
//            System.out.println("cv: "+classValue);
//            System.out.println("dv: "+departureDateValue);

                passengerPageThis.tabbedPane.setSelectedIndex(2);

            }else{
                new ShowMessage(new JFrame("Error"),
                        "Invalid Form Value Entry",
                        "The date you entered is invalid" );
            }
        }else{
            new ShowMessage(new JFrame("Error"),
                    "Invalid form value entry",
                    "Please enter a date");
        }



    }

}
