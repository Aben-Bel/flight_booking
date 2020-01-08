package FrontEnd_revised.Components;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MakeAirportList  {
    public Map<String, String> getAirports() {
        return airports;
    }

    private Map<String, String> airports = new HashMap<>();
    public MakeAirportList(){
        BufferedReader bufferedReader = null;
        try
        {
            bufferedReader = new BufferedReader(new FileReader("/Users/morty/Documents/projects/flight_booking/FlightBooking/src/FrontEnd_revised/Components/airportList.txt"));

            String line = null;
            line = bufferedReader.readLine();
            while (line != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");
                while (stringTokenizer.hasMoreTokens()) {
                    airports.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                }

                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found!");
        } catch (IOException ioe) {
            System.out.println("Error: File operation!");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }
    }

//    public static void main(String[] args) {
//        new MakeAirportList();
//    }
}
