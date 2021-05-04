public class System
{

    /**
     * This class is responsible for
     * containing the main loop of the system, where the
     * objects that represent all the entities of the
     * system will be instantiated.
     */

    private Interface anInterface;
    private ParkingData parkingData;
    private Prices prices;
    private Report report;

    public System()
    {
        //return the current frame
        anInterface = new Interface();
        //start a new DataBase (abstraction of)
        parkingData = new ParkingData();
        //start the list of price
        prices = new Prices();
        //start a daily report
        report = new Report();


    }











}
