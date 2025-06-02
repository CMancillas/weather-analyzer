import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

public class Analyzer 
{
    private List<WeatherData> data;
    
    public Analyzer(List<WeatherData> data)
    {
        this.data = data;
    }

    // getter and setter
    public List<WeatherData> getData()
    {
        return data;
    }

    public void setData(List<WeatherData> data)
    {
        this.data = data;
    }
    
    // Temperature
    public double calculateAvgTemperature()
    {
        double sum = 0.0;
        for ( int i = 0; i < data.size(); i++ )
        {
            sum += data.get(i).getTemperature();
        }
        
        return sum / data.size();
    }    


    public double findMaxTemperature()
    {
        double max = data.get(0).getTemperature();
        for ( int i = 1; i < data.size(); i++)
        {
            if ( max < data.get(i).getTemperature())
                max = data.get(i).getTemperature();
        }
        
        return max;
    }

    public double findMinTemperature()
    {
        double min = data.get(0).getTemperature();
        for ( int i = 1; i < data.size(); i++)
        {
            if ( min > data.get(i).getTemperature())
                min = data.get(i).getTemperature();
        }
        
        return min;
    }


    public void detectTemperatureTrends()
    {
        longestIncreasingStreak();
        longestDecreasingStreak();
        
    }

    private void longestIncreasingStreak()
    {
        HashMap<Integer, int[]> trends = new HashMap<Integer, int[]>();
        
        // Longest increasing streak
        // We begin at 1 to count the  current day as well
        int temp = 1, temp2 = 0,longestStreak = 1;

        for ( int i = 0; i < data.size() - 1; i++)
        {
            if ( temp > longestStreak)
            {
                longestStreak = temp;
                int[] temp3 = {temp2, i};
                //System.out.println(Arrays.toString(temp3));
                trends.put(longestStreak, temp3);
            }
            
            if ( i != data.size() - 1)
            {
                if ( data.get(i).getTemperature() < data.get(i + 1).getTemperature() )
                {
                    if ( temp == 1)
                        temp2 = i;
                    
                    temp++;
                }     
                else
                    temp = 1;
            }
            
        }

        if ( temp > longestStreak)
            longestStreak = temp;
        
        printTemperatureTrend("Increasing", longestStreak, trends.get(longestStreak));
    }

    private void longestDecreasingStreak()
    {
        HashMap<Integer, int[]> trends = new HashMap<Integer, int[]>();
        
        // Longest decreasing streak 
        // We begin at 1 to count the current day as well
        int temp = 1, temp2 = 0,longestStreak = 1;

        for (int i = 0; i < data.size(); i++)
        {
            if ( temp > longestStreak)
            {
                longestStreak = temp;
                int[] temp3 = {temp2, i};
                //System.out.println(Arrays.toString(temp3));
                trends.put(longestStreak, temp3);
            }
            
            if ( i != data.size() - 1)
            {
                if ( data.get(i).getTemperature() > data.get(i + 1).getTemperature() )
                {
                    if ( temp == 1)
                        temp2 = i;
                    
                    temp++;
                }     
                else
                    temp = 1;
            }
                 
        }
        printTemperatureTrend("Decreasing", longestStreak, trends.get(longestStreak));

    }

    private void printTemperatureTrend(String s, int n ,int[] array)
    {
        System.out.println("Longest " + s + " Temperature Trend:");
        System.out.println("Start Date: " + data.get( array[0] ).getTimestamp() );
        System.out.println("End Date: " + data.get( array[1] ).getTimestamp() );
        System.out.println("Length: " + n + " days");
        System.out.print("Temperatures: " );

        int i = array[0];
        System.out.print("[");
        while ( i <= array[1] )
        {
            if ( i == array[1] )
                System.out.print( data.get(i).getTemperature() ); 
            else       
                System.out.print( data.get(i).getTemperature() + ", ");
            i++;
        }
        System.out.print("]");
        System.out.println();
                System.out.println();

    }
/*----------------------------------------------------------------------------------------------------------------------- */
    // Humidity
    public double findMaxHumidity()
    {
        double max = data.get(0).getHumidity();
        
        for ( int i = 1; i < data.size(); i++)
        {
            if ( max < data.get(i).getHumidity())
                max = data.get(i).getHumidity();
        }
        
        return max;
    }

    public double findMinHumidity()
    {
        double min = data.get(0).getHumidity();
        
        for ( int i = 1; i < data.size(); i++)
        {
            if ( min > data.get(i).getHumidity())
                min = data.get(i).getHumidity();
        }
        return min;
    }

    // Pressure
    public double findMaxPressure()
    {
        double max = data.get(0).getPressure();
        
        for ( int i = 1; i < data.size(); i++)
        {
            if ( max < data.get(i).getPressure())
                max = data.get(i).getPressure();
        }

        return max;
    }

    public double findMinPressure()
    {
        double min = data.get(0).getPressure();
        
        for ( int i = 1; i < data.size(); i++)
        {
            if ( min > data.get(i).getPressure())
                min = data.get(i).getPressure();
        }

        return min;
    }

    // Wind speed
    public double findMaxWindspeed()
    {
        double max = data.get(0).getWindspeed();
        
        for ( int i = 1; i < data.size(); i++)
        {
            if ( max < data.get(i).getWindspeed())
                max = data.get(i).getWindspeed();
        }

        return max;
    }

    public double findMinWindspeed()
    {
        double min = data.get(0).getWindspeed();
        
        for ( int i = 1; i < data.size(); i++)
        {
            if ( min > data.get(i).getWindspeed())
                min = data.get(i).getWindspeed();
        }

        return min;
    }

    // EXTRA METHODS

    // method to identify heatwaves or unusually hot days
    public List<WeatherData> getDaysAboveTemperature(double threshold)
    {
        List<WeatherData> hotDays = new LinkedList<WeatherData>();
        
        for (int i = 0; i < data.size(); i++)
        {
            if ( data.get(i).getTemperature() > threshold )
                hotDays.add( data.get(i) );
        }
        /*
        // prints for debbugging
        System.out.println("Show me all days above " + threshold + " °F");
        // print for debugging
        for (int i = 0; i < hotDays.size(); i++)
        {
            System.out.print(hotDays.get(i).getTimestamp() + ": ");
            System.out.println(hotDays.get(i).getTemperature());
        } */

        return hotDays;
    }

    // Count days above wind threshold
    public int countWindyDays(double threshold)
    {   
        int counter = 0;

        for ( int i = 0; i < data.size(); i++)
        {
            if ( data.get(i).getWindspeed() > threshold)
                counter++;
        }

        return counter;
    }

    // more methods to be added .....

    public static void main(String[] args)
    {
        DataReader dr = new DataReader();
        
        Analyzer analyzer = new Analyzer(dr.readCSV());
        analyzer.detectTemperatureTrends();
        //analyzer.findMaxWindspeed();
        //analyzer.findMinWindspeed();
    
     
    }
}
