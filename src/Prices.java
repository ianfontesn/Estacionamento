public class Prices
{
    //all prices will be based on MINUTES (price / 60 minutes)

    public Double getCAR_PRICE()
    {
        return CAR_PRICE;
    }

    public Double getMOTORCYCLE_PRICE()
    {
        return MOTORCYCLE_PRICE;
    }

    public Double getTRUCK_PRICE()
    {
        return TRUCK_PRICE;
    }

    private Double CAR_PRICE = 10.0;
        private Double MOTORCYCLE_PRICE = 5.0;
        private Double TRUCK_PRICE = 20.0;


    public Prices()
    {
        // nothing to do here
    }


    public Double getPrice(String type)
    {
        if(type.equals("CAR"))
        {
            return CAR_PRICE/60;
        }
        else if(type.equals("MOTORCYCLE"))
        {
            return MOTORCYCLE_PRICE/60;
        }
        else
        {
            return TRUCK_PRICE/60;
        }
    }

    public Boolean changePrice(String type, Double value)
    {
        switch (type)
        {
            case "CAR":
                CAR_PRICE = value;
                return true;

            case "MOTORCYCLE":
                MOTORCYCLE_PRICE = value;
                return true;

            case "TRUCK":
                TRUCK_PRICE = value;
                return true;

                default:
                    return null;

        }






    }



}
