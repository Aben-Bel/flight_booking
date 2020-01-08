package FrontEnd_revised.Pages;

import FrontEnd_revised.Panels.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class PassengerPage extends JPanel {
    public  JTabbedPane tabbedPane;
    public Map<String, String> data = new HashMap<>();
    public PassengerPage() {
        super();

        tabbedPane = new JTabbedPane();

        JComponent bookFlightPanel = new BookFlight(this);
        tabbedPane.addTab("Book Flight", bookFlightPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent selectFlightPanel = new FlightSearch(this);
        tabbedPane.addTab("Select Flight", selectFlightPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent passengerInfoPanel = new PassengerInfo(this);
        tabbedPane.addTab("Passenger Info", passengerInfoPanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent seatArrangementPanel = new SeatArrangement(this);
        tabbedPane.addTab("Seat Arrangement", seatArrangementPanel);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        JComponent createPayPanel = new Pay(this);
        tabbedPane.addTab("Pay ", createPayPanel);
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
        add(tabbedPane);

        tabbedPane.setEnabledAt(0, false);
        tabbedPane.setEnabledAt(1, false);
        tabbedPane.setEnabledAt(2, false);
        tabbedPane.setEnabledAt(3, false);
        tabbedPane.setEnabledAt(4, false);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Book Flights");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        frame.setSize((int) (screenWidth * 0.85), (int) (screenHeight * 0.85));
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new PassengerPage(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab.contentMargins", new Insets(100, 100, 100, 100));
                createAndShowGUI();
            }
        });
    }
}


