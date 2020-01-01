import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AL implements ActionListener {
        String var;
        AdminPage ap;
        JFrame f;
        JTextField t;
        public void actionPerformed(ActionEvent e) {
                
                if (var == "1-1"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.adminChoice();
                } else if (var == "1-2"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.bookFlight1();
                } else if (var == "2-1"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.createAircraft();
                } else if (var == "2-2"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.createFlight();
                } else if (var == "3-1"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.firstChoice();
                } else if (var == "3-2"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.firstChoice();
                } else if (var == "4-1"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.chooseFlight();
                } else if (var == "5-1"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.passengerInfo();
                } else if (var == "6-1"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.selectSeat();
                } else if (var == "7-1"){
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        ap.finalPage();
                } else if (var == "gnr"){
                        ap.genID(t);
                }
        }

        AL(AdminPage ap, JFrame f, String s){
                this.var = s;
                this.f = f;
                this.ap = ap;

        }

        AL(AdminPage ap, JFrame f, String s, JTextField t){
                this(ap, f, s);
                this.t = t;
        }

        AL(){}

        

        }