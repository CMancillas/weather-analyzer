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
    public List<WeatherData> readCSV()
    {   
        LinkedList<String> column = new LinkedList<String>();
        column.add("datetime");
        column.add("temp");
        column.add("humidity");
        column.add("sealevelpressure");
        column.add("windspeed");

        HashMap<String,Integer > columns = new HashMap<String,Integer >();

        System.out.print("Enter name of the file without the extension: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        sc.close();
        
        try 
        {
            //File myFile = new File( "../weather-analyzer/csv/tucson_last7days.csv");

            File myFile = new File("../weather-analyzer/csv/" + fileName + ".csv");
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

            // prints for debugging
            /*System.out.println();
            System.out.println("Ahora el hashmap");
            System.out.println(columns.get("datetime"));
            System.out.println(columns.get("temp"));
            System.out.println(columns.get("humidity"));
            System.out.println(columns.get("windspeed"));
            System.out.println(columns.get("sealevelpressure"));
            */
            
            // Creating list of WeatherData objects
            List<WeatherData> weatherDataList = new LinkedList<WeatherData>();
            
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
            
            // prints for debugging
            /*for ( int i = 0; i < weatherDataList.size(); i++)
            {
                System.out.print(weatherDataList.get(i).getTimestamp() + " ");
                System.out.print(weatherDataList.get(i).getTemperature() + " ");
                System.out.print(weatherDataList.get(i).getHumidity() + " ");
                System.out.print(weatherDataList.get(i).getWindspeed() + " ");
                System.out.print(weatherDataList.get(i).getPressure() + " ");
                System.out.println();
            }*/
            
            myReader.close();
            return weatherDataList;
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("There's no such existing file.");
            //e.printStackTrace();
        }

        return null;
    }

    private String validateData(String s)
    {
        if ( s.equals("NA") || s.equals("na") || s.equals("-") || s.equals(" "))
            return "0";
        
        return s;
    }
}
