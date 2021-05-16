import java.text.DateFormat;
import java.text.DecimalFormat;
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
    private final ParkingData parkingData;
    private final Prices prices;
    private final CpfTester cpfTester;
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
        //start a cpf tester
        cpfTester = new CpfTester();
    }

    public String setANewClientId()
    {
        idClient++;
        return idClient.toString();
    }

    public String getClientId()
    {
        return idClient.toString();
    }

    public String setANewVehicleId()
    {
        idVehicle++;
        return idVehicle.toString();

    }

    public Date getDateNow()
    {
        return Calendar.getInstance().getTime();
    }

    public String dateNow()
    {
        Date data = Calendar.getInstance().getTime();
        DateFormat d = DateFormat.getDateInstance(DateFormat.SHORT);
        return d.format(data);
    }

    public String hourNow()
    {
        Date data = Calendar.getInstance().getTime();
        DateFormat h = DateFormat.getTimeInstance();
        return h.format(data);
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
            switch (type)
            {
                case "CAR" -> vehicle = new Car(id, plaque, model, color, entryHour, client, type);
                case "MOTORCYCLE" -> vehicle = new Motorcycle(id, plaque, model, color, entryHour, client, type);
                case "TRUCK" -> vehicle = new Truck(id, plaque, model, color, entryHour, client, type);
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
            //update the total of cash here
            parkingData.updateTotalCashFlow(value);

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            decimalFormat.format(value);

            String valor =  decimalFormat.format(value);
            //make a new report here
            makeAReport(vehicle, valor, getDateNow());

            parkingData.removeVehicleFromList(plaque);

            return "R$" + valor;

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
        //If client deleted, return true, else, return false
        return parkingData.removeAClientFromList(cpf);
    }

    public void makeAReport(Vehicle vehicle, String value, Date dateNow)
    {
        //make a report of the type MOVEMENT
        // IS A COMPLETE REPORT
        Report reportType1 = new Report
                        (1,
                        vehicle.getId(),
                        vehicle.getPlaque(),
                        vehicle.getEntryHour(),
                        dateNow,
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getType(),
                        value
                        );

        //make a report with the cash information
        //
        Report reportType0 = new Report
                (0,
                        vehicle.getId(),
                        vehicle.getPlaque(),
                        vehicle.getEntryHour(),
                        dateNow,
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getType(),
                        value
                );

        parkingData.addReportInList(reportType1, reportType0);

    }

    public String returnAReportList(int type)
    {
        String returnString = "";

            if(type == 1)
            {
                returnString += "=======DAILY MOVEMENT REPORT ========================\n";
            }
            else
            {
                returnString += "=======CASHFLOW REPORT ===============================\n";
            }

            List<Report> reportList = parkingData.getAListOfReport(type);

            for(Report report : reportList)
            {
                returnString += report.getReportString();
            }

            return returnString;
    }

    public String getTotalCashValue()
    {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(parkingData.getTotalCashFlow());
    }


    public String getPricesNow()
    {
        String returnString = "====PRICES=====\n" +
                "CAR: R$" + prices.getCAR_PRICE() + "\nMOTORCYCLE: R$" + prices.getMOTORCYCLE_PRICE() +
                "\nTRUCK: R$" + prices.getTRUCK_PRICE();;
                return returnString;
    }

    public String changePriceValue(String type, Double value)
    {
       String returnString = "====PRICES=====";
       Boolean priceChanged = prices.changePrice(type, value);
       if(priceChanged)
       {
           returnString += "\nCAR: R$" + prices.getCAR_PRICE() + "\nMOTORCYCLE: R$" + prices.getMOTORCYCLE_PRICE() +
                   "\nTRUCK: R$" + prices.getTRUCK_PRICE();
       }
        else
       {
           errorString = "Cannot change the prices. Use this format: 12.00";
           returnString = null;
       }
        return returnString;
    }

    public String getAboutUsString()
    {
        return " - Park System \n - Created by: IAN FONTES / AKYNATAN \n - Version v";
    }

    public String getErrorString()
    {
        return errorString;
    }

}
