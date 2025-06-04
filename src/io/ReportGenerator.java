package src.io;
import java.util.List;

import src.analysis.Analyzer;
import src.model.WeatherData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator 
{   
    private List<WeatherData> data;
    private Analyzer analyzer;
    
    public ReportGenerator(List<WeatherData> data)
    {
        this.data = data;
        analyzer = new Analyzer(data);
    }

    // method that writes a readable, well-formatted summary to the console and a .txt file
    public void generateTextReport(String filename)
    {
        try 
        {
            File myFile = new File("out/" + filename + ".txt");
            
            if ( myFile.createNewFile() )
                System.out.println("File created: " + myFile.getName() );
            else
                System.out.println("File already exists.");    
        } 
        catch (IOException e) 
        {
            System.out.println("An error ocurred while creating the .txt file.");
        }

        try 
        {
            FileWriter myFileWriter = new FileWriter("out/" + filename + ".txt" );
            myFileWriter.write(printTextReport()); 
            myFileWriter.close();
            System.out.println("Successfully wrote to the .txt file.");
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while writing to the .txt file.");
        }
    }
    
    private String printTextReport()
    {   
        StringBuilder report = new StringBuilder();
        
        report.append("Weather Report Summary\n");
        report.append("----------------------\n");
        report.append("Date Range: " + data.get(0).getTimestamp() + " to " + data.get( data.size() - 1 ).getTimestamp());
        report.append("\n");
        
        report.append("Temperature: \n");
        report.append("Max: " + analyzer.findMaxTemperature() + "°F\n");
        report.append("Min: " + analyzer.findMinTemperature() + "°F\n");
        report.append(String.format("Avg: %.2f°F\n", analyzer.calculateAvgTemperature()) );
        report.append("\n");

        report.append("Humidity: \n");
        report.append("Max: " + analyzer.findMaxHumidity() + "%\n");
        report.append("Min: " + analyzer.findMinHumidity() + "%\n");
        report.append("\n");

        report.append("Pressure: \n");
        report.append("Max: " + analyzer.findMaxPressure() + "hPa\n");
        report.append("Min: " + analyzer.findMinPressure() + "hPa\n");
        report.append("\n");

        report.append("Wind Speed: \n");
        report.append("Max: " + analyzer.findMaxWindspeed() + "mph\n");
        report.append("Min: " + analyzer.findMinWindspeed() + "mph\n");
        report.append("\n");

        report.append( String.format("%s", analyzer.detectTemperatureTrends() ));
         

        return report.toString();
    }

    /*------------------------------------------------------------------------------------- */
    
    // method that exports the raw data into a .csv file
    public void generateCSVReport(String filename)
    {
        try 
        {   
            File myFile = new File("out/"+ filename + ".csv");

            if ( myFile.createNewFile() )
                System.out.println("CSV file created: " + myFile.getName() );
            else
                System.out.println("File already exists.");
        } 
        catch (Exception e) 
        {
            System.out.println("An error ocurred while creating the .csv file.");
        }

        try 
        {
            FileWriter myFileWriter = new FileWriter("out/"+ filename + ".csv");
            myFileWriter.append( printCSVReport() );
            myFileWriter.close();
            System.out.println("Successfully wrote to the .csv file.");
        } 
        catch (Exception e) 
        {
            System.out.println("An error occurred while writing to the .csv file.");
        }
    }

    private String printCSVReport()
    {
        StringBuilder report = new StringBuilder();

        report.append("Metric,Value\n");
        report.append("Max Temperature," + analyzer.findMaxTemperature() + "\n");
        report.append("Min Temperature," + analyzer.findMinTemperature() + "\n");
        report.append( String.format("Avg Temperature,%.2f\n", analyzer.calculateAvgTemperature()) );

        report.append("Max Humidity," + analyzer.findMaxHumidity() + "\n");
        report.append("Min Humidity," + analyzer.findMinHumidity() + "\n");

        report.append("Max Pressure," + analyzer.findMaxPressure() + "\n");
        report.append("Min Pressure," + analyzer.findMinPressure() + "\n");

        report.append("Max Wind Speed," + analyzer.findMaxWindspeed() + "\n");
        report.append("Min Wind Speed," + analyzer.findMinWindspeed() + "\n");

        return report.toString();
    }

    public void displaySummaryReport()
    {
        System.out.println( "=================================" );
        System.out.println("         Weather Summary         ");
        System.out.println("=================================");
        System.out.println("Date Range: " + data.get(0).getTimestamp() + " to " + data.get( data.size() - 1 ).getTimestamp());
        System.out.println("\n");
        
        System.out.println("Temperature: ");
        System.out.println("Max: " + analyzer.findMaxTemperature() + "°F");
        System.out.println("Min: " + analyzer.findMinTemperature() + "°F");
        System.out.printf( "Avg: %.2f°F\n", analyzer.calculateAvgTemperature() );
        System.out.println();

        System.out.println("Humidity: ");
        System.out.println("Max: " + analyzer.findMaxHumidity() + "%");
        System.out.println("Min: " + analyzer.findMinHumidity() + "%");
        System.out.println();

        System.out.println("Pressure: ");
        System.out.println("Max: " + analyzer.findMaxPressure() + "hPa");
        System.out.println("Min: " + analyzer.findMinPressure() + "hPa");
        System.out.println();

        System.out.println("Wind Speed: ");
        System.out.println("Max: " + analyzer.findMaxWindspeed() + "mph");
        System.out.println("Min: " + analyzer.findMinWindspeed() + "mph");
        System.out.println();

        System.out.printf("%s", analyzer.detectTemperatureTrends());

    }

    public static void main(String[] args)
    {
        DataReader dr = new DataReader();
        List<WeatherData> weatherData = dr.readCSV();

        //error al momentod e llamar PRINT TEXT REPORT !!!!
        ReportGenerator rg = new ReportGenerator(weatherData);
        //rg.generateTextReport("Esele");
        //rg.generateCSVReport("esele2.0");
        rg.displaySummaryReport();
    }
}
