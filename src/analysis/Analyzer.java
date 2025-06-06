package src.analysis;
import src.model.WeatherData;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

public class Analyzer 
{
    private List<WeatherData> data;
    
    // Constructor

    public Analyzer(List<WeatherData> data)
    {
        if ( data == null || data.isEmpty())
            throw new IllegalArgumentException("Weather data cannot be null or empty.");

        this.data = data;
    }

    // Getter and Setter

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

    // Detects longest increasing and decreasing temperature streaks 
    public String detectTemperatureTrends()
    {
        return String.format("%s\n%s", longestIncreasingStreak(), longestDecreasingStreak() );
    }

    private String longestIncreasingStreak()
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
        
        return printTemperatureTrend("Increasing", longestStreak, trends.get(longestStreak));
    }

    private String longestDecreasingStreak()
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
        
        return printTemperatureTrend("Decreasing", longestStreak, trends.get(longestStreak));
    }

    // Builds formatted output for a temperature trend
    private String printTemperatureTrend(String s, int n ,int[] array)
    {
        StringBuilder trend = new StringBuilder();

        trend.append("Longest " + s + " Temperature Trend:\n");
        trend.append("Start Date: " + data.get( array[0] ).getTimestamp() + "\n");
        trend.append("End Date: " + data.get( array[1] ).getTimestamp() + "\n");
        trend.append("Length: " + n + " days" + "\n");
        trend.append("Temperatures: ");

        int i = array[0];
        trend.append("[");
        
        while ( i <= array[1] )
        {
            if ( i == array[1] )
                trend.append( data.get(i).getTemperature() );
            else 
                trend.append( data.get(i).getTemperature() + ", " );      
            i++;
        }
        trend.append("]");
        trend.append("\n");

        return trend.toString();
    }

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

        return hotDays;
    }

    // Counts how many days exceed a wind speed threshold
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
}
