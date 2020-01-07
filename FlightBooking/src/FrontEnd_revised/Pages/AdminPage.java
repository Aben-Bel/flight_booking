package FrontEnd_revised.Pages;

import FrontEnd_revised.Panels.CreateAirplane;
import FrontEnd_revised.Panels.CreateFlight;
import FrontEnd_revised.Panels.CreateLocation;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.KeyEvent;

public class AdminPage extends JPanel {
    public AdminPage() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent createFlightPanel = new CreateFlight();
        tabbedPane.addTab("Create Flight", createFlightPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent createAirplanePanel = new CreateAirplane();
        tabbedPane.addTab("Create Airplane", createAirplanePanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent createLocationPanel = new CreateLocation();
        tabbedPane.addTab("Create Location", createLocationPanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);


        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Admin Page");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        frame.setSize((int) (screenWidth * 0.75), (int) (screenHeight * 0.75));
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new AdminPage(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab.contentMargins", new Insets(50, 100, 50, 0));
                createAndShowGUI();
            }
        });
    }
}

