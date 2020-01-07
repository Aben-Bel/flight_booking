package FrontEnd_revised.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ScreenPane extends JPanel {

    public ScreenPane parentContainer;

    public void nextScreen(){};
    public void backScreen(){};

    public ScreenPane(){
        super();
        setBackground(new Color(174, 214, 150));
        parentContainer = this;
    }

    public class BackActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parentContainer.backScreen();
        }
    }
    public class ContinueActionListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            parentContainer.nextScreen();
        }
    }
}

