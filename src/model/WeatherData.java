package src.model;
import java.time.LocalDate;

public class WeatherData
{
    private LocalDate timestamp;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double pressure;
    

    // Default constructor
    public WeatherData()
    {
        timestamp = null;
        temperature = 0.0;
        humidity = 0.0;
        pressure = 0.0;
        windSpeed = 0.0;

    }

    // Parametrized constructor
    public WeatherData(LocalDate timestamp, double temperature, double humidity, double windSpeed, double pressure)
    {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }


    // Getters and setters
    public LocalDate getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp)
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