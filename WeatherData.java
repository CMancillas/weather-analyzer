import java.time.LocalDateTime;

public class WeatherData
{
    private LocalDateTime timestamp;
    private double temperature;
    private double humidity;
    private double pressure;
    private double windSpeed;

    // constructor methods
    public WeatherData()
    {
        timestamp = null;
        temperature = 0.0;
        humidity = 0.0;
        pressure = 0.0;
        windSpeed = 0.0;

    }

    public WeatherData(LocalDateTime timestamp, double temperature, double humidity, double pressure, double windSpeed)
    {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
    }

    /*----------------------------------------------------------------------------------- */

    // getters and setters for each attribute
    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public double getTemperature()
    {
        return temperature;
    }

    public void setTemperature(double temperature)
    {
        this.temperature = temperature;
    }

    public double getHumidity()
    {
        return humidity;
    }

    public void setHumidity(double humidity)
    {
        this.humidity = humidity;
    }

    public double getPressure()
    {
        return pressure;
    }

    public void setPressure(double pressure)
    {
        this.pressure = pressure;
    }

    public double getWindspeed()
    {
        return windSpeed;
    }

    public void setWindspeed(double windSpeed)
    {
        this.windSpeed = windSpeed;
    }
    
}