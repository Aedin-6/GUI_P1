package NeighbourhoodSim;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ConcurrentModificationException;
import java.util.List;

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
            System.out.println("Something interrupted the date.");
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

    private synchronized void RentCheck()
    {
        List<Space> rentClone = Start.user.rentedList;
        try
        {
            for (Space estate : rentClone)
            {
                if (((Lodging) estate).dueDate != null)
                {
                    LocalDate check = ((Lodging) estate).dueDate;
                    if (TimeSim.date.isAfter(check) || TimeSim.date.isEqual(check))
                    {
                        Start.user.addFile((Lodging) estate);
                        System.out.printf("\n---File issued for %s---", estate);
                        long daysBetween = ChronoUnit.DAYS.between(check, TimeSim.date);
                        if (daysBetween > 2)
                        {
                            System.out.printf("\n---BECAUSE OF NOT PAID RENT %s IS CLEARED AND YOU ARE NOT LONGER OWNER OF THAT PLACE.---%n", ((Lodging) estate).id);
                            ((Lodging) estate).KickStuff();
                            rentClone.remove(estate);
                            estate.isOwnedOrRented = false;
                            Start.user.OwnCheck();
                            Start.user.rentedList = rentClone;
                        }
                    }
                }
            }
        }
        catch (ConcurrentModificationException ignored){ }
    }
}
