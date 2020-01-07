package FrontEnd_revised.Components;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    public CustomTextField(Dimension d){
        super();
        setPreferredSize(d);
    }
    public CustomTextField(){
        super();
        setPreferredSize(new Dimension( 250, 44 ));
    }
}