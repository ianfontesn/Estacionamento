public class CpfTester
{
    long cpf;
    long cpfComparator;
    long vectorCpf[] = new long[11];
    int summation = 0, firstDigit = 0, secondDigit = 0;


    public CpfTester()
    {
        //nothing to do here
    }

    public Boolean verifyValidCpf(String cpfString)
    {

        cpf = Long.parseLong(cpfString);
        cpfComparator = cpf;

        for (int i = 0; i < 10; i++)
        {
            vectorCpf[10 - i] = cpf % 10;
            cpf = cpf / 10;
            if (i == 9)
            {
                vectorCpf[0] = cpf;
            }
        }


        for (int i = 0; i < 9; i++)
        {
            summation += (vectorCpf[i] * (10 - i));
            if (i == 8)
            {
                summation = summation % 11;
            }
            if (summation < 2)
            {
                firstDigit = 0;
            }
            else
            {
                firstDigit = 11 - summation;
            }
        }

        summation = 0;

        for (int i = 0; i < 10; i++)
        {
            summation += (vectorCpf[i] * (11 - i));
            if (i == 9)
            {
                summation = summation % 11;
            }
            if (summation < 2)
            {
                secondDigit = 0;
            }
            else
            {
                secondDigit = 11 - summation;
            }

        }

        summation = 0;

        if (vectorCpf[9] == firstDigit && vectorCpf[10] == secondDigit)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
