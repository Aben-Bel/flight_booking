package FrontEnd_revised.Components;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ShowMessage extends JDialog {
    public ShowMessage(JFrame ownerFrame, String title, String message){
        super(ownerFrame, title, true);
        add(new JLabel(message), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton ok = new JButton("Ok");

        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                setVisible(false);
            }
        });

        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize(250, 200);
        setLocationByPlatform(true);
        setVisible(true);

    }
}
