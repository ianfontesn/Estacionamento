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

    private Double totalCashFlow;
    private final HashMap <String, Vehicle> listOfVehicles;
    private final ArrayList <Client> listOfClient;
    private final ArrayList<Report> reports;
    private final Boolean haveVacancy;


    public ParkingData()
    {
        //Represents all the money entry on the park
        totalCashFlow = 0.0;
        //represents the vehicles parked in the park
        listOfVehicles = new HashMap <>();
        //represent a collection of all clients with registry in the systemm
        listOfClient = new ArrayList <>();
        //represent a collection of reports
        reports = new ArrayList <>();
        // will be used to verify the vacancy
        haveVacancy = true;
    }

    public void addReportInList(Report reportType1, Report reportType0)
    {
        reports.add(reportType1);
        reports.add(reportType0);
    }

    public void updateTotalCashFlow(Double value)
    {
        this.totalCashFlow += value;
    }

    public Double getTotalCashFlow()
    {
        return totalCashFlow;
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
            if ((!name.isEmpty() && client.getName().contains(name)) || client.getCpf().equals(cpf) || client.getContact().equals(contact))
            {
                clientReturn.add(client);
            }
        }
        return clientReturn;

    }

    //verify the arraylist to found the client owner of the cpf
    //if found the cpf, remove the client (after the loop)
    public Boolean removeAClientFromList(String cpf)
    {
        Boolean deleted = false;
        Client clientToDelete = null;
        for(Client client : listOfClient)
        {
            if(client.getCpf().equals(cpf))
            {
                    deleted = true;
                    clientToDelete = client;
            }
        }
        if(deleted)
        {
            listOfClient.remove(clientToDelete);
        }

        return deleted;

    }

    public List<Report> getAListOfReport(int type)
    {
        List<Report> returnReports = new ArrayList <>();

        for(Report report : reports)
        {
            if(report.getReportType() == type)
            {
                returnReports.add(report);
            }
        }

        return returnReports;
    }



}