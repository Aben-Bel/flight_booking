package FrontEnd_revised.Panels;
import FrontEnd_revised.Pages.PassengerPage;
import FrontEnd_revised.Components.ShowMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class SeatArrangement extends ScreenPane {
    public Map<String, String> fields;
    public String seat = "";
    public SeatArrangement thisValue;
    public PassengerPage passengerPageThis;

    public SeatArrangement(PassengerPage obj){
        super();
        thisValue = this;
        passengerPageThis = obj;
        fields = obj.data;
        JPanel west = new JPanel();
        west.setBackground(Color.GRAY);
        JLabel legend = new JLabel("LEGEND");
        JLabel green = new JLabel("Unoccupied");
        green.setBackground(Color.GREEN);
        green.setOpaque(true);
        JLabel lightGreen = new JLabel("Occupied");
        lightGreen.setBackground(new Color(180,255,190));
        lightGreen.setOpaque(true);
        west.add(legend);
        west.add(green);
        west.add(lightGreen);

        JPanel top = new JPanel();
        top.setBackground(Color.LIGHT_GRAY);
        top.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        top.add(new JLabel("CHOOSE A SEAT"));

        setLayout(new BorderLayout(4,4));
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.GRAY,3));
        add(top, BorderLayout.NORTH);
        add(new SeatGrid(8, 8), BorderLayout.CENTER);
        add(west, BorderLayout.WEST);

        JButton aContinue = new JButton("Continue");
        aContinue.addActionListener(new ContinueActionListener());
        JButton aBack = new JButton("Back");
        aBack.addActionListener(new BackActionListener());

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.LIGHT_GRAY);
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        bottom.add(aBack);
        bottom.add(aContinue);

        add(bottom, BorderLayout.SOUTH);

    }

    @Override
    public void nextScreen(){
        if(thisValue.seat != ""){
            fields.put("Seat Arrangement", (String)fields.get("Class") + seat);
            passengerPageThis.tabbedPane.setSelectedIndex(4);
        }else{
            new ShowMessage(new JFrame("Error"),
                    "Invalid Form Value Entry",
                    "Select a valid seat please" );
        }

    }

    @Override
    public void backScreen(){
        passengerPageThis.tabbedPane.setSelectedIndex(1);
    }

    private class SeatGrid extends JPanel implements MouseListener {

        private final Color LIGHT_GREEN = new Color(180,255,190);

        private final int UNOCCUPIED = 0;
        private final int OCCUPIED = 1;
        private final int SPACE = 2;

        private int[] chosenSeat;

        private int[][] state;

        private boolean haveBooked;

        private JLabel message;

        public int height;
        public int width;

        public SeatGrid(int height, int width) {
            setPreferredSize( new Dimension (50*height, 50*width) );
            this.height = height;
            this.width = width;
            addMouseListener(this);
            setFont(new Font("Serif", Font.BOLD, 16));
            draw();
        }


        public void draw(){
            state = new int[height][width];
            for(int i=0; i<height; i++){
                state[i][2] = SPACE;
                state[i][5] = SPACE;
            }
            chosenSeat = new int[2];
        }

        protected void paintComponent(Graphics g){

            double squareWidth = (double)getWidth() / width;
            double squareHeight = (double)getHeight() / height;
            for (int row = 0; row < height; row++){
                for (int col = 0; col < width; col++){
                    int y1 = (int)(row*squareHeight);
                    int x1 = (int)(col*squareWidth);
                    int y2 = (int)((row+1)*squareHeight);
                    int x2 = (int)((col+1)*squareWidth);
                    int width = x2 - x1;
                    int height = y2 - y1;

                    if(state[row][col] == OCCUPIED){
                        g.setColor(LIGHT_GREEN);
                    }else if (state[row][col] == UNOCCUPIED ){
                        g.setColor(Color.GREEN);
                    }else{
                        g.setColor(Color.GRAY);
                    }

                    g.fillRect(x1, y1, width, height);
                    g.setColor(Color.GRAY);
                    g.drawRect(x1,y1,width,height);
                    g.setColor(Color.BLACK);
                    if(col == 2 | col == 5){

                    }else{
                        g.drawString(" "+col+"-"+row,x1+6,y1+21);
                    }
                }
            }

        }

        public void mousePressed(MouseEvent evt) {
            int row, col;

            double squareWidth = (double)getWidth() / width;
            double squareHeight = (double)getHeight() / height;
            row = (int)(evt.getY() / squareHeight);
            col = (int)(evt.getX() / squareWidth);

            if (col == 2 | col == 5){
                state[row][col] = SPACE;
            }else{
                if (haveBooked == false){
                    chosenSeat[0] = row;
                    chosenSeat[1] = col;

                    state[row][col] = OCCUPIED;
                    haveBooked = true;
                }{
                    state[chosenSeat[0]][chosenSeat[1]] = UNOCCUPIED;
                    chosenSeat[0] = row;
                    chosenSeat[1] = col;
                    state[row][col] = OCCUPIED;
                }
                thisValue.seat = " "+chosenSeat[1]+"-"+chosenSeat[0];
            }
            repaint();
        }

        public void mouseReleased(MouseEvent evt) {
        }
        public void mouseClicked(MouseEvent evt) {
        }
        public void mouseEntered(MouseEvent evt) {
        }
        public void mouseExited(MouseEvent evt) {
        }

    }


}
