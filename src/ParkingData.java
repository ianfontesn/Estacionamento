import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingData
{
    /**
     * This class is responsible to make a Abstract DataBase
     * with the information needed for the System
     * like a client and vehicles list, information of a possible vacancy
     * and/or no vacancy
     */

    private HashMap <String, Vehicle> listOfVehicles;
    private ArrayList <Client> listOfClient;
    private Boolean haveVacancy;


    public ParkingData()
    {
        //represents the vehicles parked in the park
        listOfVehicles = new HashMap <>();
        //represent a collection of all clients with registry in the systemm
        listOfClient = new ArrayList <>();
        // will be used to verify the vacancy
        haveVacancy = true;
    }

    //Set a vehicle on the list. Need to pass the plaque and a vehicle object
    public void setVehiclesOnList(String plaque, Vehicle vehicle)
    {
        listOfVehicles.put(plaque, vehicle);

    }

    public void setClientOnList(Client client)
    {
        listOfClient.add(client);
    }

    public Vehicle getVehicleFromList(String plaque)
    {
        return listOfVehicles.get(plaque);
    }

    public void removeVehicleFromList(String plaque)
    {
        listOfVehicles.remove(plaque);
    }


    public List <Client> getClientFromList(String name, String cpf, String contact)
    {
        List <Client> clientReturn = new ArrayList <>();

        for (Client client : listOfClient)
        {
            if ((!client.getName().isEmpty() && client.getName().contains(name)) || client.getCpf().equals(cpf) || client.getContact().equals(contact))
            {
                clientReturn.add(client);
            }
        }
        return clientReturn;

    }

    public Boolean removeAClientFromList(String cpf)
    {
        Boolean deleted = false;
        for(Client client : listOfClient)
        {
            if(client.getCpf().equals(cpf))
            {
                listOfClient.remove(client);
                deleted = true;
            }
        }
            return deleted;
    }

}