import java.util.Date;

public class Vehicle
{
    private String plaque;
     private String model;
     private String color;
     private Date entryHour;
     private Client client;
     private String id;


     public Vehicle(String id, String plaque, String model, String color, Date entryHour, Client client)
     {
         this.id = id;
        this.plaque = plaque;
        this.model = model;
        this.color = color;
        this.client = client;
        this.entryHour = entryHour;
     }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPlaque()
    {
        return plaque;
    }

    public void setPlaque(String plaque)
    {
        this.plaque = plaque;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public Date getEntryHour()
    {
        return entryHour;
    }

    public void setEntryHour(Date entryHour)
    {
        this.entryHour = entryHour;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }








}
