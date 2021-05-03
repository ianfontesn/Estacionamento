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
        anInterface = new Interface();
        parkingData = new ParkingData();
        prices = new Prices();
        report = new Report();
    }








}
