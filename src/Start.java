import java.util.List;
import java.util.Scanner;

public class Start
{
    void Start(List<Apartment> apartmentList, List<Person> peopleList, List<ParkingSpot> parkingSpotList)
    {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Welcome to estate management application. Choose who are you, from provided list, by press" +
                "the right number. Type 'exit' to terminate." + '\n');


        for (var person : peopleList)
        {
            System.out.println(person);
        }
        System.out.print("\n" + "Enter number or type 'exit': ");
        try
        {
            Person user = ChoiceMenu(myScanner, peopleList);

            System.out.println("\nAvailable options: ");
            System.out.println(" 1. Show information about poeple. ");
            System.out.println(" 2. Show information about estates.");
            System.out.println(" 3. Save and Exit. ");
            System.out.print(" Choose by providing number: ");
            int option = myScanner.nextInt();
            switch (option)
            {
                case 1 -> PeopleInfoMenu();
                case 2 -> EstatesInfoMenu();
                case 3 -> {
                    Save();
                    System.exit(1);
                }
            }

        }
        catch (UnsupportedOperationException e)
        {
            System.out.println("Type number or 'exit'. Try again.");
        }
    }

    private void PeopleInfoMenu()
    {
    }

    private void EstatesInfoMenu()
    {
    }

    private void Save()
    {
    }

    private Person ChoiceMenu(Scanner myScanner, List<Person> peopleList)
    {
        Person user = null;
        boolean rightNumber = true;
        while (rightNumber)
        {
            String chosenPerson = myScanner.nextLine();
            if (chosenPerson.equals("exit"))
            {
                System.out.println("Exiting.");
                System.exit(0);
            }
            else
            {
                int choice = Integer.parseInt(chosenPerson);

                if (peopleList.size() < choice)
                {
                    System.out.print("\nThere is no such person try again. \nEnter number or type exit: ");
                    rightNumber = true;
                }
                else
                {
                    System.out.println("\nYou have chosen " + peopleList.get(choice - 1));
                    rightNumber = false;
                    user = peopleList.get(choice - 1);
                }
            }
        }
        return user;
    }
}
