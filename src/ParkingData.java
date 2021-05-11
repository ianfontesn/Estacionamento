import java.util.ArrayList;
import java.util.HashMap;

public class ParkingData
{
    /**
     * This class is responsible to make a Abstract DataBase
     * with the information needed for the System
     * like a client and vehicles list, information of a possible vacancy
     * and/or no vacancy
     */

    private HashMap<String, Vehicle> listOfVehicles;
    private ArrayList<Client> listOfClient;
    private Boolean haveVacancy;


    public ParkingData()
    {
        listOfVehicles = new HashMap <>();
        listOfClient = new ArrayList <>();
        haveVacancy = true;
    }

    //Set a vehicle on the list. Need to pass the plaque and a vehicle object
    public void setVehiclesOnList(String plaque, Vehicle vehicle)
    {
        listOfVehicles.put(plaque, vehicle);

    }

    public Vehicle getVehicleFromList(String plaque)
    {
        return listOfVehicles.get(plaque);
    }

    public void removeVehicleFromList(String plaque)
    {
        listOfVehicles.remove(plaque);
    }


}
