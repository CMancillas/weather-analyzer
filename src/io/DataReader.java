package src.io;
import src.model.WeatherData;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException; // class to handle errors
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.time.LocalDate;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class DataReader 
{
    // Reads weather data from a CSV file and returns a list of WeatherData objects   
    public List<WeatherData> readCSV(String filename)
    {   
        // Expected column names
        LinkedList<String> column = new LinkedList<String>();
        column.add("datetime");
        column.add("temp");
        column.add("humidity");
        column.add("sealevelpressure");
        column.add("windspeed");

        HashMap<String,Integer > columns = new HashMap<String,Integer >();
        
        try 
        {
            File myFile = new File("../weather-analyzer/csv/" + filename + ".csv");
            Scanner myReader = new Scanner(myFile);
            
            // Header processing
            String data = myReader.nextLine();
            String[] headers = data.split(",");
            
            for ( int i = 0; i < headers.length; i++)
            {
                if ( column.contains( headers[i]))
                {
                    columns.put(headers[i], i);
                }   
            }
            
            // Creating list of WeatherData objects
            List<WeatherData> weatherDataList = new LinkedList<WeatherData>();
            
            // Read and parse each line into a WeatherData object
            while ( myReader.hasNextLine() )
            {
                data = myReader.nextLine();
                headers = data.split(",");
                LocalDate timestamp = LocalDate.parse( headers[(columns.get("datetime"))], ISO_LOCAL_DATE );
                double temperature = Double.parseDouble( validateData( headers[(columns.get("temp"))] ) );
                double humidity = Double.parseDouble( validateData( headers[(columns.get("humidity"))] ) );
                double pressure = Double.parseDouble( validateData( headers[(columns.get("sealevelpressure"))] ) );
                double windSpeed = Double.parseDouble( validateData( headers[(columns.get("windspeed"))] ) );
                
                WeatherData weather = new WeatherData(timestamp, temperature, humidity, windSpeed, pressure);
                weatherDataList.add(weather);

            }
            
            myReader.close();
            return weatherDataList;
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("There's no such existing file.");
        }

        return null;
    }

    // Replaces missing or invalid data with "0"
    private String validateData(String s)
    {
        if ( s.equals("NA") || s.equals("na") || s.equals("-") || s.equals(" "))
            return "0";
        
        return s;
    }
}
