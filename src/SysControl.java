import java.text.DateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class SysControl
{

    /**
     * This class is responsible for
     * containing the main loop of the system, where the
     * objects that represent all the entities of the
     * system will be instantiated.
     */


    private ParkingData parkingData;
    private Prices prices;
    private Report report;
    private Integer idVehicle = 0;
    private Integer idClient = 0;

    public SysControl()
    {
        //start a new DataBase (abstraction of)
        parkingData = new ParkingData();
        //start the list of price
        prices = new Prices();
        //start a daily report
        report = new Report();

    }

    public void registryClient()
    {
        System.out.println("NEW CLIENT");

    }

    public String setANewClientId()
    {
        idClient++;
        String id = idClient.toString();
        return id;
    }

    public String setANewVehicleId()
    {
        idVehicle++;
        String id = idVehicle.toString();
        return id;
    }


    public Date getDateNow()
    {
        Date data = Calendar.getInstance().getTime();
        return data;
    }

    public String dateNow()
    {
        Date data = Calendar.getInstance().getTime();
        DateFormat d = DateFormat.getDateInstance(DateFormat.SHORT);
        String dateNow = d.format(data);

        return dateNow;
    }

    public String hourNow()
    {
        Date data = Calendar.getInstance().getTime();
        DateFormat h = DateFormat.getTimeInstance();
        String hourNow = h.format(data);

        return hourNow;
    }

    public Vehicle createNewVehicle(String id, String plaque, String type, String model, String color, Date entryHour, Client client)
    {
        Vehicle vehicle = null;

        if(type.equals("CAR"))
        {
            vehicle = new Car(id, plaque, model, color, entryHour, client);
        }
        else if (type.equals("MOTORCYCLE"))
        {
            vehicle = new Motorcycle(id, plaque, model, color, entryHour, client);
        }
        else if(type.equals("TRUCK"))
        {
            vehicle = new Truck(id, plaque, model, color, entryHour, client);
        }

        return vehicle;

    }

    public void addVehicleOnList(String plaque, Vehicle vehicle)
    {
        parkingData.setVehiclesOnList(plaque, vehicle);
    }


    //CALCULATE THE PRICE OF PERMANENCY BASED ON MINUTES
    public String exitACar(String plaque)
    {
        Vehicle vehicle = parkingData.getVehicleFromList(plaque);
        long dateNow = getDateNow().getTime();
        long vehicleEntry = vehicle.getEntryHour().getTime();

        long timeToPay = dateNow - vehicleEntry;
         Integer time = (int)((timeToPay/60000));

//         parkingData.removeVehicleFromList(plaque);

        Double value = time * 10.0;




        return "R$ " + value;

    }






}
