package src.io;
import src.analysis.Analyzer;
import src.model.WeatherData;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator 
{   
    private List<WeatherData> data;
    private Analyzer analyzer;
    
    // Constructor initializes data and analyzer
    public ReportGenerator(List<WeatherData> data)
    {
        this.data = data;
        analyzer = new Analyzer(data);
    }

    // Generates a formatted text report and writes it to a .txt file 
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
            System.out.println();
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while writing to the .txt file.");
        }
    }
    
    // Builds the contet for the text report 
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

    // Generates a summary CSV report    
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
            System.out.println();
        } 
        catch (Exception e) 
        {
            System.out.println("An error occurred while writing to the .csv file.");
        }
    }

    // Builds the content for the CSV report
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

    // Displays a text-based summary report with visual bars
    public void displaySummaryReport()
    {
        System.out.println( "=================================" );
        System.out.println("         Weather Summary         ");
        System.out.println("=================================");
        System.out.println("Date Range: " + data.get(0).getTimestamp() + " to " + data.get( data.size() - 1 ).getTimestamp());
        System.out.println("\n");
        
        // Temperature with bars
        System.out.println("Temperature: ");
        printBar("Max: ", analyzer.findMaxTemperature(), analyzer.findMaxTemperature());
        printBar("Min: ", analyzer.findMinTemperature(), analyzer.findMaxTemperature());
        printBar("Avg: ", analyzer.calculateAvgTemperature(), analyzer.findMaxTemperature());
        System.out.println();

        // Humidity with bars
        System.out.println("Humidity: ");
        printBar("Max: ", analyzer.findMaxHumidity(), analyzer.findMaxHumidity() );
        printBar("Min: ", analyzer.findMinHumidity(), analyzer.findMaxHumidity() );
        System.out.println();

        // Pressure with bars 
        System.out.println("Pressure: ");
        printBar("Max: ", analyzer.findMaxPressure(), analyzer.findMaxPressure() );
        printBar("Min: ", analyzer.findMinPressure(), analyzer.findMaxPressure() );
        System.out.println();

        // Wind speed with bard
        System.out.println("Wind Speed: ");
        printBar("Max: ", analyzer.findMaxWindspeed() , analyzer.findMaxWindspeed() );
        printBar("Min: ", analyzer.findMinWindspeed() , analyzer.findMaxWindspeed() );
        System.out.println();
        
        // Temperature trends
        System.out.printf("%s", analyzer.detectTemperatureTrends());
    }

    // Displays a horizontal bar based on value vs scale
    private void printBar(String label, double value, double maxScale)
    {
        int barLength = (int) ((value/maxScale) * 25); // will scale to max 25 blocks
        StringBuilder bar = new StringBuilder();

        for ( int i = 0; i < barLength; ++i)
            bar.append("█");

        System.out.printf("%-5s %6.1f°F | %s\n", label, value, bar.toString());
    }
}
