import java.util.Date;

public class Report
{

    /**
     * ReportType = 1, Movement.  0 == CashFlow
     */
    private int reportType;
    private String reportString;


     public Report(int reportType, String id, String plaque, Date entryHour
                   ,Date exitHour, String model, String color, String type, String value)
     {
        if(reportType == 1)
        {
            this.reportType = 1;
            this.reportString = "ID: " + id + "  |   Plaque: " + plaque + "  |   Entry: " + entryHour + "  |   Exit: "
                    + exitHour + "  |   Model: " + model + "  |   Color: " + color + "  |   type: " + type
                    + "  |   Value: R$" + value + "\n";

        }
        else
        {
            this.reportType = 0;
            this.reportString = "ID: " + id + "  |   Plaque: " + plaque + "  |   Type: " + type + "  |   Value: R$" + value + "\n";
        }

     }

    public int getReportType()
    {
        return reportType;
    }

    public String getReportString()
    {
        return reportString;
    }

}
