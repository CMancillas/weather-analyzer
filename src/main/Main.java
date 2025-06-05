import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import src.io.DataReader;
import src.io.ReportGenerator;
import src.model.WeatherData;

public class Main {
    public static void main(String[] args){
        List<WeatherData> data = new LinkedList<WeatherData>();
        DataReader dataReader = new DataReader();
        System.out.println("===============================");
        System.out.println("     WEATHER ANALYZER 1.0     ");
        System.out.println("===============================");
        
        Scanner sc = new Scanner( System.in );
        System.out.print("Enter the CSV file (witout extension): ");
        String filename = sc.nextLine();

        System.out.println();
        //sc.close();

        data = dataReader.readCSV(filename);
        if ( data == null || data.isEmpty() )
        {
            sc.close();
            return;
        }
        
        
        int option;
        do 
        {
            System.out.println( "1 - Display weather summary report" );
            System.out.println( "2 - Generate .txt report file" );
            System.out.println( "3 - Generate .csv report file" );
            System.out.println( "4 - Generate all reports" );
            System.out.println("0 - Exit");
            System.out.print("Select an option: ");
            option = sc.nextInt();
            sc.nextLine(); // clear the buffer

            System.out.println();
        
            System.out.println("Your choice: " + option );
            System.out.println();
        

            if ( option == 2 || option == 3 || option == 4 )
            {
                System.out.print("Enter output base filename (no extension): ");
                filename = sc.nextLine();
            }

            ReportGenerator reportGenerator = new ReportGenerator(data);

            switch (option) 
            {
                case 1:
                    reportGenerator.displaySummaryReport();        
                    break;
                
                case 2:
                    reportGenerator.generateTextReport(filename);
                    break;
                
                case 3:
                    reportGenerator.generateCSVReport(filename);
                    break;
                
                case 4:
                    reportGenerator.generateTextReport(filename);
                    reportGenerator.generateCSVReport(filename);
                    reportGenerator.displaySummaryReport();
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
            
            System.out.println();
            System.out.println("=======================================");
            System.out.println();
        
        } while( option != 0);
        
        sc.close();
        return;
    }  
}
