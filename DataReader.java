import java.util.List;
import java.io.File;
import java.io.FileNotFoundException; // class to handle errors
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;

public class DataReader 
{
    public List<WeatherData> readCSV()
    {   
        HashMap<String,Integer > columns = new HashMap<String,Integer >();
        HashMap<String,String > columnsInfo = new HashMap<String,String>();
        
        LinkedList<String> column = new LinkedList<String>();
        column.add("datetime");
        column.add("temp");
        column.add("humidity");
        column.add("sealevelpressure");
        column.add("windspeed");

        //System.out.println("Enter name of the file: ");
        //Scanner sc = new Scanner(System.in);
        //String fileName = sc.nextLine();
        
        try 
        {
            File myFile = new File( "tucson_last7days.csv");

//          File myFile = new File( fileName + ".csv");
            Scanner myReader = new Scanner(myFile);
            
            // Header processing
            String data = myReader.nextLine();
            String[] headers = data.split(",");
            
            for ( int i = 0; i < headers.length; i++)
            {
                if ( column.contains( headers[i]))
                    columns.put(headers[i], i);          
            }
            
            // 
            while ( myReader.hasNextLine() )
            {
            }

            System.out.println();
            System.out.println("Ahora el hashmap");
            System.out.println(columns.get("datetime"));
            System.out.println(columns.get("temp"));
            System.out.println(columns.get("humidity"));
            System.out.println(columns.get("sealevelpressure"));
            System.out.println(columns.get("windspeed"));
            
           

            
            myReader.close();
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("An error ocurred.");
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args)
    {
        DataReader dr = new DataReader();
        dr.readCSV();
    }

    public void validateData()
    {

    }
}
