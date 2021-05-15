public class Client
{
    /**
     * This Class is a representation of a client
     * You can set a sequence of information for each client
     * like name, cpf, phone number. To add a new information
     * just add a new Field to the Client.
     */

    private String id;
    private String name;
    private String gender;
    private String cpf;
    private String contact;
    private String email;


    /**
     * Make a new Client on the System and save on the Client List, on the ParkingData Class.
     * @param id Receive a unique identification number
     * @param name is the complete name of the client
     * @param cpf is a number of the cpf identification
     * @param contact a phone number
     * @param email a email of the client
     */
    public Client(String id, String name, String gender, String cpf, String contact, String email)
    {
       this.id = id;
       this.name = name;
       this.cpf = cpf;
       this.contact = contact;
       this.email = email;
       this.gender = gender;
    }


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}
