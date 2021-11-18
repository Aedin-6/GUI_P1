import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class TimeCheck implements Runnable
{
    LocalDate date = LocalDate.now();

    @Override
    public void run()
    {
        try
        {
            TimeRentCheck();
        }
        catch (InterruptedException e)
        {
            System.out.println("Something interrupted the date.");;
        }
    }

    private synchronized void TimeRentCheck() throws InterruptedException
    {
            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
                RentCheck();
                sleep(10000);
            }
    }

    private void RentCheck()
    {
        for (Space estate : Start.user.rentedList)
        {
            if (((Lodging) estate).dueDate != null)
            {
                LocalDate check = ((Lodging) estate).dueDate;
                if (TimeSim.date.isAfter(check) || TimeSim.date.isEqual(check))
                {
                    File file = new File();
                    ArrayList<File> fileList = null;
                    fileList.add(file);
                    Start.user.files.put((Lodging) estate, fileList);
                    System.out.println("File issued.");
                }
            }
        }
    }
}
