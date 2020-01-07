package FrontEnd_revised.Panels;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.*;

import FrontEnd_revised.Components.CustomTextField;
import FrontEnd_revised.Components.FilterComboBox;
import FrontEnd_revised.Pages.PassengerPage;
import FrontEnd_revised.Components.ShowMessage;
import com.github.lgooddatepicker.components.DatePicker;

public class PassengerInfo extends ScreenPane {
    public JLabel basicInfo;
    public JLabel title;
    public JLabel firstName;
    public JLabel middleName;
    public JLabel lastName;
    public JLabel dateOfBirth;
    public JLabel gender;
    public JLabel contactInfo;
    public JLabel phoneNumber;
    public JLabel email;

    public FilterComboBox titleCBox;
    public FilterComboBox genderCBox;

    public CustomTextField firstNameField;
    public CustomTextField middleNameField;
    public CustomTextField lastNameField;
    public CustomTextField phoneNumberField;
    public CustomTextField emailField;

    public DatePicker dateOfBirthPicker;

    public JButton continuebtn;
    public JButton backbtn;

    public List<String> titleList = new ArrayList<>();
    public List<String> genderList = new ArrayList<>();

    public GridBagConstraints c = new GridBagConstraints();
    public Map<String, String> fields;
    public PassengerPage passengerPageThis;

    public PassengerInfo(PassengerPage obj){
        parentContainer = this;
        fields = obj.data;
        passengerPageThis = obj;

        basicInfo = new JLabel("Basic Info");
        basicInfo.setFont(new Font("Serif", Font.BOLD, 24));
        title = new JLabel("Title");
        title.setFont(new Font("Serif", Font.BOLD, 18));
        firstName = new JLabel("First Name");
        firstName.setFont(new Font("Serif", Font.BOLD, 18));
        middleName = new JLabel("Middle Name");
        middleName.setFont(new Font("Serif", Font.BOLD, 18));
        lastName = new JLabel("Last Name");
        lastName.setFont(new Font("Serif", Font.BOLD, 18));
        dateOfBirth = new JLabel("Date of Birth");
        dateOfBirth.setFont(new Font("Serif", Font.BOLD, 18));
        gender = new JLabel("Gender");
        gender.setFont(new Font("Serif", Font.BOLD, 18));
        contactInfo = new JLabel("Contact Info");
        contactInfo.setFont(new Font("Serif", Font.BOLD, 18));
        phoneNumber = new JLabel("Phone Number");
        phoneNumber.setFont(new Font("Serif", Font.BOLD, 18));
        email = new JLabel("Email Address");
        email.setFont(new Font("Serif", Font.BOLD, 18));

        titleList.add("MR ");
        titleList.add("MRS ");
        titleList.add("Sir ");
        titleCBox = new FilterComboBox(titleList);

        genderList.add("Male");
        genderList.add("Female");
        genderCBox = new FilterComboBox(genderList);

        firstNameField = new CustomTextField();
        middleNameField = new CustomTextField();
        lastNameField = new CustomTextField();
        phoneNumberField = new CustomTextField();
        emailField = new CustomTextField();

        dateOfBirthPicker = new DatePicker();

        continuebtn = new JButton("Continue");
        continuebtn.addActionListener(new ContinueActionListener());

        backbtn = new JButton("Back");
        backbtn.addActionListener(new BackActionListener());

        setLayout(new GridBagLayout());

        c.insets = new Insets(15,50,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;

        add(basicInfo, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 0;

        add(title, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 1;

        add(firstName, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 2;

        add(middleName, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 3;

        add(lastName, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 0;

        add(titleCBox, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 1;

        add(firstNameField, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 2;

        add(middleNameField, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 3;

        add(lastNameField, c);


        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.gridx = 0;

        add(dateOfBirth, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        c.gridx = 1;

        add(gender, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        c.gridx = 0;

        add(dateOfBirthPicker, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        c.gridx = 1;

        add(genderCBox, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 5;
        c.gridx = 0;

        add(contactInfo, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 6;
        c.gridx = 0;

        add(phoneNumber, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 6;
        c.gridx = 1;

        add(email, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 7;
        c.gridx = 0;

        add(phoneNumberField, c);

        c.insets = new Insets(15,30,15,30);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 7;
        c.gridx = 1;

        add(emailField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,0);
        c.gridx = 3;
        c.gridy = 10;
        c.gridwidth = 2;
        add(continuebtn, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,0);
        c.gridx = 2;
        c.gridy = 10;
        c.gridwidth = 1;
        add(backbtn, c);
    }

    @Override
    public void nextScreen() {
        super.nextScreen();
        System.out.println("Going Forward");

        String titleValue = titleCBox.getSelectedItem().toString();
        String genderValue = genderCBox.getSelectedItem().toString();
        String firstNameValue = firstNameField.getText();
        String middleNameValue = middleNameField.getText();
        String lastNameValue = lastNameField.getText();
        String phoneNumberValue = phoneNumberField.getText();
        String dateOfBirthValue = dateOfBirthPicker.getDateStringOrEmptyString();
        String emailValue = emailField.getText();

        var test = "^[a-zA-Z"+"\\"+" ]{3,}$";
        if(
                Pattern.matches(test, titleValue)
                        && Pattern.matches(test, genderValue)
                        && Pattern.matches(test, firstNameValue)
                        && Pattern.matches(test, middleNameValue)
                        && Pattern.matches(test, lastNameValue)
                        && Pattern.matches(""+
                        "^(\\d{10})|(([\\(]?([0-9]{3})[\\)]?)?[ \\.\\-]?([0-9]{3})[ \\.\\-]([0-9]{4}))$",
                        phoneNumberValue)
                        && Pattern.matches("" +
                        "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$"+
                        "",emailValue)
                        && 18 <= ChronoUnit.YEARS.between(dateOfBirthPicker.getDate(), LocalDate.now())
        ){

            fields.put("Title", titleValue);
            fields.put("Gender", genderValue);
            fields.put("First Name", firstNameValue);
            fields.put("Middle Name", middleNameValue);
            fields.put("Last Name", lastNameValue);
            fields.put("Phone Number", phoneNumberValue);
            fields.put("Date Of Birth", dateOfBirthValue);
            fields.put("Email", emailValue);

            passengerPageThis.tabbedPane.setSelectedIndex(3);
        }else{
            new ShowMessage(new JFrame("Error"),
                    "Invalid Form Value Entry",
                    "Check your form for valid values\n and if you are older than 18" );

        }
    }

    @Override
    public void backScreen() {
        passengerPageThis.tabbedPane.setSelectedIndex(0);
        System.out.println("Going Back");
    }
}
