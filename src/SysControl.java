import java.text.DateFormat;
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










}
