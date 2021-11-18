import java.time.LocalDate;
import static java.lang.Thread.sleep;

public class TimeSim implements Runnable
{
    static LocalDate date = LocalDate.now();

    @Override
    public void run()
    {
        try
        {
            TimeIncrese();
        }
        catch (InterruptedException e)
        {
            System.out.println("Something interrupted the date.");;
        }
    }

    private synchronized void TimeIncrese() throws InterruptedException
    {
            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
                date = date.plusDays(1);
                sleep(5000);
            }
    }
}
