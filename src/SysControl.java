import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private CpfTester cpfTester;
    private Integer idVehicle = 0;
    private Integer idClient = 0;

    //this string will be
    private String errorString;

    public SysControl()
    {
        //start a new DataBase (abstraction of)
        parkingData = new ParkingData();
        //start the list of price
        prices = new Prices();
        //start a daily report
        report = new Report();
        //start a cpf tester
        cpfTester = new CpfTester();

    }

    public String setANewClientId()
    {
        idClient++;
        String id = idClient.toString();
        return id;
    }

    public String getClientId()
    {
        return idClient.toString();
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


    public Client createNewClient(String id, String name, String gender, String cpf, String contact, String email)
    {

        Client client = null;

        if(name.isEmpty())
        {
            errorString = "The name cannot be empty.";
        }
        else if(cpf.isEmpty())
        {
            errorString = "The CPF cannot be empty.";
        }
        else if(!cpfTester.verifyValidCpf(cpf))
        {
            errorString = "CPF Invalid.";
        }
        else
        {
            client = new Client(id, name, gender, cpf, contact, email);
        }

        return client;
    }


    public Vehicle createNewVehicle(String id, String plaque, String type, String model, String color, Date entryHour, Client client)
    {
        Vehicle vehicle = null;

        if(plaque.isEmpty())
        {
            errorString = "The plaque cannot be empty";
        }
        else if (parkingData.getVehicleFromList(plaque) != null)
        {
            errorString = "There is a vehicle parked with that plaque.";
        }
        else if(id.isEmpty())
        {
            errorString = "You need to set a time before entry a new vehicle.";
        }
        else
        {
            if (type.equals("CAR"))
            {
                vehicle = new Car(id, plaque, model, color, entryHour, client, type);
            }
            else if (type.equals("MOTORCYCLE"))
            {
                vehicle = new Motorcycle(id, plaque, model, color, entryHour, client, type);
            }
            else if (type.equals("TRUCK"))
            {
                vehicle = new Truck(id, plaque, model, color, entryHour, client, type);
            }

        }
            return vehicle;
    }

    // add a new vehicle on the list of vehicles in parkingData.
    public void addVehicleOnList(String plaque, Vehicle vehicle)
    {
        parkingData.setVehiclesOnList(plaque, vehicle);
    }

    //add a new Client on the client list in parkingData
    public void addClientOnList(Client client)
    {
        parkingData.setClientOnList(client);
    }

    //CALCULATE THE PRICE OF PERMANENCY BASED ON MINUTES
    //AND REMOVE THE CAR FROM THE LIST
    public String exitACar(String plaque)
    {
        try
        {
            Vehicle vehicle = parkingData.getVehicleFromList(plaque);
            long dateNow = getDateNow().getTime();
            long vehicleEntry = vehicle.getEntryHour().getTime();

            long timeToPay = dateNow - vehicleEntry;
            Integer time = (int) ((timeToPay / 60000)); //Make the time millis to minutes

            Double value = time * prices.getPrice(vehicle.getType());
            parkingData.removeVehicleFromList(plaque);

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            decimalFormat.format(value);

            return "R$" + decimalFormat.format(value);

        }
        catch (NullPointerException vehicleNull)
        {
            errorString = "This vehicle has already been removed or not exist.";
            return null;
        }
    }


 public String findClient(String name, String cpf, String contact)
 {
     try
     {
         //return a list of clients found
         List <Client> clients = parkingData.getClientFromList(name, cpf, contact);

         //make a string to show in interface
         String returnString = "========Clients found========\n";

         for (Client client : clients)
         {
             returnString += "ID: " + client.getId() + "\n" +
                     "NAME: " + client.getName() + "\n" +
                     "CPF: " + client.getCpf() + "\n" +
                     "CONT:" + client.getContact() + "\n" +
                     "E-MAIL: " + client.getEmail() + "\n" +
                     "==============================\n\n";
         }

         return returnString;

     } catch (NullPointerException clientError)
     {
         errorString = "Client Not Found";
         return null;
     }
 }

    public Boolean removeClient(String cpf)
    {
        Boolean deleted = parkingData.removeAClientFromList(cpf);
        return deleted;
    }



    public String getErrorString()
    {
        return errorString;
    }

}
