import java.awt.*;
import javax.swing.*;



public class Interface
{
    /**
     * this Class is responsible for create and generate
     * all the GUIs of the system.
     * first create a JFrame (window) and create the menuBar on the top
     * (file, open, about, for example)
     */


    public Interface()
    {
        JFrame principalFrame = new JFrame("Park System v0.1");
        Container contentPane = principalFrame.getContentPane();

        //Set the menuBar in the frame
        principalFrame.setJMenuBar(makeMenuBar());

        // make Visible
        principalFrame.pack();
        principalFrame.setVisible(true);

    }


    public void windowNewClientEntry()
    {
       JFrame registerClient = new JFrame("Entry new Client - Park System v0.1") ;
       


    }



     //create a menuBar to use in all frames.
 public JMenuBar makeMenuBar()
 {
     JMenuBar menuBar = new JMenuBar();
     //vehicles menu
     JMenu vehiclesMenu = new JMenu("Vehicle");
     menuBar.add(vehiclesMenu);

     JMenuItem enterVehicle = new JMenuItem("Enter Vehicle");
     vehiclesMenu.add(enterVehicle);

     JMenuItem exitVehicle = new JMenuItem("Exit Vehicle");
     vehiclesMenu.add(exitVehicle);

     //Client menu
     JMenu clientMenu = new JMenu("Client");
     menuBar.add(clientMenu);

     JMenuItem newClient = new JMenuItem("New Client");
     clientMenu.add(newClient);

     JMenuItem removeClient = new JMenuItem("Remove Client");
     clientMenu.add(removeClient);


     //Reports menu
     JMenu reportsMenu = new JMenu("Reports");
     menuBar.add(reportsMenu);

     JMenuItem dailyMovement = new JMenuItem("Daily Movement");
     reportsMenu.add(dailyMovement);

     JMenuItem cashFlow = new JMenuItem("Cash Flow");
     reportsMenu.add(cashFlow);


     //About menu
     JMenu aboutMenu = new JMenu("About");
     menuBar.add(aboutMenu);

     JMenuItem aboutUs = new JMenuItem("About Us");
     aboutMenu.add(aboutUs);

     return menuBar;
 }





}
