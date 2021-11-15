import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Start
{
    List<Space> apartmentList;
    List<Person> peopleList;
    List<Space> parkingSpotList;
    Scanner myScanner;
    boolean userChosen = false;
    Person user;

    public Start(List<Space> apartmentList, List<Person> peopleList, List<Space> parkingSpotList)
    {
        this.apartmentList = apartmentList;
        this.peopleList = peopleList;
        this.parkingSpotList = parkingSpotList;
        this.myScanner = new Scanner(System.in);
    }

    void Start()
    {
        System.out.println("\n---MAIN MENU---");

        try
        {
            if (!userChosen)
            {
                System.out.println("Welcome to estate management application. Choose who are you, from provided list, by press" +
                        "the right number. Type 'exit' to terminate." + '\n');

                for (var person : peopleList)
                {
                    System.out.println(person);
                }
                System.out.print("\n" + "Enter number or type 'exit': ");
                user = ChoiceMenu();
                userChosen = true;
            }

            System.out.println("\nAvailable options: ");
            System.out.println(" 1. Show information about poeple. ");
            System.out.println(" 2. Show information about estates.");
            System.out.println(" 3. Renting menu.");
            System.out.println(" 4. Belongings handling menu.");
            System.out.println(" 5. Main menu. ");
            System.out.println(" 6. Save and Exit. ");
            System.out.println(" 7. Load previous state.");
            System.out.print("Choose by providing number: ");
            int option = myScanner.nextInt();
            myScanner.nextLine();
            switch (option)
            {
                case 1 -> PeopleInfoMenu();
                case 2 -> EstatesInfoMenu();
                case 3 -> RentingMenu();
                case 4 -> BelongingsMenu();
                case 5 -> Start();
                case 6 -> {
                    Save();
                    System.exit(1);
                }
                case 7 -> {
                    Load();
                    Start();
                }
                default -> throw new IllegalStateException("Unexpected value: " + option);
            }

        }
        catch (UnsupportedOperationException e)
        {
            System.out.println("Type number or 'exit'. Try again.");
        }
    }

    private void BelongingsMenu()
    {
        System.out.println("\nAvailable options: ");
        System.out.println(" 1. Show my belongings.");
        System.out.println(" 2. Park vehicle into parking spot.");
        System.out.println(" 3. Put items in parking spot or apartment");
        System.out.println(" 4. Remove item or car from parking spot or apartment");
        System.out.println(" 5. Save and Exit. ");
        System.out.println(" 6. Go back to main menu. ");
        System.out.print(" Choose by providing number: ");

        int option = myScanner.nextInt();
        myScanner.nextLine();
        switch (option)
        {
            case 1 -> {
                ShowBelongingsInfo();
                BelongingsMenu();
            }
            case 2 -> {
                VehicleParking();
                BelongingsMenu();
            }
            case 3 -> {
                ItemsIntoPs();
                BelongingsMenu();
            }
            case 4 -> {
                RemoveItem();
                BelongingsMenu();
            }
            case 5 -> {
                Save();
                System.exit(1);
            }
            case 6 -> Start();
        }
    }

    private void RemoveItem()
    {
        System.out.println("Your belongings stored or parked:");
        for (Item item : user.stash)
            if (item.isParkedOrStored)
                System.out.println(item);
        System.out.println("\nWould you like to remove vehicle, item or return? V/I/R");
        String choice = myScanner.nextLine();
        switch (choice)
        {
            case "I" -> {
                System.out.print("\nProvide full ID (for ex. I1) of item for removal: ");
                String itemToRemoveID = myScanner.nextLine();
                Item itemToRemove = RightItem(itemToRemoveID);
                if (itemToRemove != null)
                {
                    itemToRemove.RemoveFromStash();
                    BelongingsMenu();
                } else
                {
                    System.out.println("No such item!");
                    BelongingsMenu();
                }
            }
            case "V" -> {
                System.out.print("\nProvide full ID (for ex. V1) of item for removal: ");
                String itemToRemoveID = myScanner.nextLine();
                Item itemToRemove = RightCar(itemToRemoveID);
                if (itemToRemove != null)
                {
                    itemToRemove.RemoveFromStash();
                    BelongingsMenu();
                } else
                {
                    System.out.println("No such vehicle!");
                    BelongingsMenu();
                }
            }
            case "R" -> BelongingsMenu();
            default -> {
                System.out.println("Type V for Vehicle, I for Item or R to return.");
                RemoveItem();
            }
        }

    }

    private void ItemsIntoPs()
    {
        if (user.rentedList.stream().noneMatch(ps -> ps instanceof ParkingSpot) ||
                user.rentedList.stream().noneMatch(ps -> ps instanceof ParkingSpot))
        {
            System.out.println("You don't rent parking sport or apartment! Rent one first!");
            BelongingsMenu();
        }

        if (user.stash.stream().noneMatch(v -> v != null))
        {
            System.out.println("You don't own anything!");
            BelongingsMenu();
        }

        System.out.println("\nChoose one apartment or parking spot to stash your stuff.\n");

        for (Space lodg: user.rentedList)
        {
            if (lodg instanceof Lodging)
                System.out.println(lodg);
        }
        System.out.print("Provide full ID (for ex. A1 or PS1): ");
        try
        {
            String id = myScanner.nextLine();
            System.out.println("You have chosen " + id + ".");
            System.out.println("\nChoose item you wish to stash in " + id + ".\n");
            for (Item i : user.stash)
            {
                    System.out.println(i);
            }
            System.out.print("Provide full ID (for ex. I1 or I2): ");
            String iId = myScanner.nextLine();
            Item itemToStore = RightItem(iId);
            Lodging placeToStore = RightPlace(id);
            if (placeToStore != null && itemToStore != null)
                if(!itemToStore.isParkedOrStored)
                    itemToStore.Park(placeToStore, user.rentedList);
                else
                    System.out.println("Item is already stored.");
            else if (placeToStore == null)
            {
                System.out.println("\nYou don't own that place or it doesn't exist!");
                ItemsIntoPs();
            }
            else
            {
                System.out.println("\nThis is not your item or it doesn't exist!");
                ItemsIntoPs();
            }
        }
        catch (Exception e)
        {
            System.out.println("\nPass only valid numbers for ID.");
            ItemsIntoPs();
        }
    }

    private Item RightItem(String iId)
    {
        Item itemToStore = null;
        for (Item item : user.stash)
        {
            if (!(item instanceof Vehicle))
                if ( item.id.equals(iId))
                    itemToStore = item;
        }
        return itemToStore;
    }

    private void VehicleParking()
    {
        if (user.rentedList.stream().noneMatch(ps -> ps instanceof ParkingSpot))
        {
            System.out.println("You don't rent parking sport! Rent one first!");
            BelongingsMenu();
        }

        if (user.stash.stream().noneMatch(v -> v instanceof Vehicle))
        {
            System.out.println("You don't own a vehicle to park!");
            BelongingsMenu();
        }

        System.out.println("\nChoose one of your parking spots to park your vehicle.\n");

        for (Space ps: user.rentedList)
        {
            if (ps instanceof ParkingSpot)
                System.out.println(ps);
        }
        System.out.print("Provide ID: ");
        try
        {
            String psId = myScanner.nextLine();
            System.out.println("You have chosen " + psId + ".");
            System.out.println("\nChoose vehicle you wish to park in " + psId + ".\n");
            for (Item veh : user.stash)
            {
                if (veh instanceof Vehicle)
                    System.out.println(veh);
            }
            System.out.print("Provide ID: ");
            String vehID = myScanner.nextLine();
            Vehicle vehToPark = RightCar(vehID);
            ParkingSpot psToPark = RightSpot(psId);
            if (psToPark != null && vehToPark != null)
                if(!vehToPark.isParkedOrStored)
                    vehToPark.Park(psToPark, user.rentedList);
                else
                    System.out.println("Vehicle already parked.");
            else if (psToPark == null)
            {
                System.out.println("\nYou don't own that parking spot!");
                VehicleParking();
            }
            else
            {
                System.out.println("\nThis is not your vehicle!");
                VehicleParking();
            }
        }
        catch (Exception e)
        {
            System.out.println("\nPass only valid numbers for ID.");
            VehicleParking();
        }
    }

    private Vehicle RightCar(String vehID)
    {
        Vehicle vehToPark = null;
        for (Item v : user.stash)
        {
            if (v instanceof Vehicle)
                if (v.id.equals(vehID))
                    vehToPark = (Vehicle) v;
        }
        return vehToPark;
    }

    private ParkingSpot RightSpot(String psId)
    {
        ParkingSpot spotToPark = null;
        for (Space space : user.rentedList)
        {
            if (space instanceof ParkingSpot)
            {
                if(((ParkingSpot) space).id.equals(psId))
                    spotToPark = (ParkingSpot) space;
            }
        }
        return spotToPark;
    }

    private Lodging RightPlace(String psId)
    {
        Lodging spotToStore = null;
        for (Space space : user.rentedList)
        {
                if(((Lodging) space).id.equals(psId))
                    spotToStore = (Lodging) space;
        }
        return spotToStore;
    }

    private void ShowBelongingsInfo()
    {
        System.out.println("\nYour stash: "+ user.stash + "\n");
        BelongingsMenu();
    }

    private void RentingMenu()
    {
        System.out.println("\nAvailable options: ");
        System.out.println(" 1. Show my apartments and parking spots.");
        System.out.println(" 2. Show free apartments and parking spots.");
        System.out.println(" 3. Save and Exit. ");
        System.out.println(" 4. Go back to main menu. ");
        System.out.print(" Choose by providing number: ");

        int option = myScanner.nextInt();
        myScanner.nextLine();
        switch (option)
        {
            case 1 -> {
                ShowMySpaces();
                RentingMenu();
            }
            case 2 -> {
                ShowFreeSpaces();
                RentingMenu();
            }
            case 3 -> {
                Save();
                System.exit(1);
            }
            case 4 -> Start();
        }
    }

    private void ShowFreeSpaces()
    {
        for (Space apartment: apartmentList)
        {
            if (!apartment.isOwnedOrRented)
                System.out.println(apartment);
        }
        for (Space parkingspot: parkingSpotList)
        {
            if (!parkingspot.isOwnedOrRented)
                System.out.println(parkingspot);
        }
        System.out.println("\n Do you want to rent one of free apartments or parking spots? y/n");
        String ans = myScanner.nextLine();
        if (ans.equals("y"))
        {
            System.out.println("Provide A for apartment or PS for parking spot. Next chose the right ID.");
            ans = myScanner.nextLine();
            if (ans.equals("A"))
            {
                System.out.print("Provide ID: ");
                int id = myScanner.nextInt();
                //myScanner.nextLine();
                System.out.println("You have chosen " + "A" + id + " for rental.");
                if (apartmentList.size() >= id)
                {
                    String ID = "A" + id;
                    for (Space apartment : apartmentList)
                    {
                        if (((Apartment) apartment).id.equals(ID))
                        {
                            if(!apartment.isOwnedOrRented)
                            {
                                Rent(apartment);
                                System.out.println(apartment + " has been successfully rented.\n");
                            }
                            else
                                System.out.println("That place is already rented to "
                                        + apartment.Owner.name + " " + apartment.Owner.surename +".\n");
                                ShowFreeSpaces();
                        }
                    }
                }
                else
                {
                    System.out.print("There is no such Apartment available, choose different one.\n");
                    ShowFreeSpaces();
                }
            }
            else if (ans.equals("PS"))
            {
                System.out.print("Provide ID: ");
                int id = myScanner.nextInt();
                myScanner.nextLine();
                System.out.println("You have chosen " + "PS" + id + " for rental.");
                if (parkingSpotList.size() >= id)
                {
                    String ID = "PS" + id;
                    for (Space ps : parkingSpotList)
                    {
                        if (((ParkingSpot) ps).id.equals(ID))
                        {
                            if(!ps.isOwnedOrRented)
                            {
                                Rent(ps);
                                System.out.println(ps + " has been successfully rented.");
                            }
                            else
                                System.out.println("That place is already rented to "
                                        + ps.Owner.name + " " + ps.Owner.surename +".");
                            ShowFreeSpaces();
                        }
                    }
                }
                else
                {
                    System.out.print("There is no such Parking Spot available, choose different one.");
                    ShowFreeSpaces();
                }
            }
            else
            {
                System.out.println("Only A or PS. Try again.");
                ShowFreeSpaces();
            }
        }
        else
        {
            System.out.println("Going back to Renting Menu.");
            RentingMenu();
        }


    }

    private void Rent(Space space)
    {
        user.rentedList.add(space);
        space.isOwnedOrRented = true;
        space.Owner = user;
        user.OwnCheck();
    }

    private void ShowMySpaces()
    {
        for (Space rentedApartment : user.rentedList)
            System.out.println(rentedApartment);
        System.out.println("\n Would you like to free one of your properties? y/n");
        String ans = myScanner.nextLine();
        if (ans.equals("y"))
        {
            System.out.println("Provide A for apartment or PS for parking spot. Next chose the right ID.");
            ans = myScanner.nextLine();
            if (ans.equals("A"))
            {
                System.out.print("Provide ID: ");
                int id = myScanner.nextInt();
                //myScanner.nextLine();
                System.out.println("You have chosen " + "A" + id + " to end rental.");
                if (apartmentList.size() >= id)
                {
                    String ID = "A" + id;
                    for (Space apartment : apartmentList)
                    {
                        if (((Apartment) apartment).id.equals(ID))
                        {
                            if(apartment.Owner == null)
                            {
                                System.out.println("This apartment is already free.");
                            }
                            else
                            {
                                Unrent(apartment);
                                System.out.println(apartment + " has been successfully freed.");
                            }
                        }
                    }
                }
                else
                {
                    System.out.print("You are not renting this Apartment, choose different one.\n");
                    ShowFreeSpaces();
                }
            }
            else if (ans.equals("PS"))
            {
                System.out.print("Provide ID: ");
                int id = myScanner.nextInt();
                myScanner.nextLine();
                System.out.println("You have chosen " + "PS" + id + " to end rental.");
                if (parkingSpotList.size() >= id)
                {
                    String ID = "PS" + id;
                    for (Space ps : parkingSpotList)
                    {
                        if (((ParkingSpot) ps).id.equals(ID))
                        {
                            if(ps.Owner == null)
                            {
                                System.out.println("This apartment is already free.");
                            }
                            else
                            {
                                Unrent(ps);
                                System.out.println(ps + " has been successfully freed.");
                            }
                        }
                    }
                }
                else
                {
                    System.out.print("You are not renting this Parking Spot, choose different one.");
                    ShowFreeSpaces();
                }
            }
            else
            {
                System.out.println("Only A or PS. Try again.");
                ShowFreeSpaces();
            }
        }
        else
        {
            System.out.println("Going back to Renting Menu.");
            RentingMenu();
        }

    }

    private void Unrent(Space space)
    {
        user.rentedList.remove(space);
        space.isOwnedOrRented = false;
        space.Owner = null;
        user.OwnCheck();
    }

    void PeopleInfoMenu()
    {
        System.out.println("\nAvailable options: ");
        System.out.println(" 1. Add new person to neighbourhood.");
        System.out.println(" 2. Remove person from neighbourhood.");
        System.out.println(" 3. Show information about living people and their belongings.");
        System.out.println(" 4. Save and Exit. ");
        System.out.println(" 5. Go back to main menu. ");
        System.out.print(" Choose by providing number: ");
        int option = myScanner.nextInt();
        myScanner.nextLine();
        switch (option)
        {
            case 1 ->
                    {
                        peopleList.add(AddPerson());
                        System.out.println();
                        System.out.println("---Person added---");
                        PeopleInfoMenu();
                    }
            case 2 -> RemovePerson();
            case 3 -> {
                ShowPeopleInfo();
                PeopleInfoMenu();
            }
            case 4 ->
                    {
                        Save();
                        System.exit(1);
                    }
            case 5 -> Start();
        }
    }

    private void ShowPeopleInfo()
    {
        System.out.println("---Printing info about inhabitants---");
        for (Person person : peopleList)
        {
            System.out.println(person);
        }
    }

    private void RemovePerson()
    {
        System.out.println("---REMOVE PERSON---");
        ShowPeopleInfo();
        System.out.println("Provide ID number of person to remove: ");
        boolean rightNumber = true;
        while (rightNumber)
        {
            try
            {
                int id = myScanner.nextInt();
                myScanner.nextLine();
                System.out.println("You have chosen " + "O" + id + " for removal.");
                if (peopleList.size() >= id)
                {
                    Person toRemove = peopleList.get(id - 1);
                    if (toRemove == user)
                    {
                        System.out.println("You cannot remove User. Try different person.\n");
                        RemovePerson();
                    }
                    System.out.println(toRemove.id + " " + toRemove.name + " " + toRemove.surename + " will be removed.");
                    System.out.println("Are you sure ? y/n");
                    String ans = myScanner.nextLine();
                    if (ans.equals("y"))
                    {
                        peopleList.remove(toRemove);
                        System.out.println("Person removed.");
                        rightNumber = false;
                        PeopleInfoMenu();
                    } else
                    {
                        System.out.println("Going back to People Menu.");
                        rightNumber = false;
                        PeopleInfoMenu();
                    }
                }
                else
                {
                    System.out.print("There is no such person, choose different one: ");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Numbers only. Try again.");
                myScanner.nextLine();
            }
        }
    }

    private Person AddPerson()
    {
        System.out.println("---ADD PERSON---");

        System.out.println("Provide name: ");
        String name = myScanner.nextLine();

        System.out.println("Provide surname: ");
        String surname = myScanner.nextLine();

        System.out.println("Provide PESEL: ");
        int pesel = Pesel();
        Date bdate = Dayofbirth();
        System.out.println("Provide address: ");
        String adaress = myScanner.nextLine();



        return new Person(name,surname,pesel, bdate,adaress);
    }

    private int Pesel()
    {
        try
        {
            int pesel = myScanner.nextInt();
            myScanner.nextLine();
            return pesel;
        }
        catch (InputMismatchException e)
        {
            myScanner.nextLine();
            System.out.println("PESEL must be an Integer. Try again");
            Pesel();
        }
        return 0;
    }

    private Date Dayofbirth()
    {
        Date bdate;

        System.out.println("Provide date of birth: ");
        String bornDate = myScanner.nextLine();
        String[] dateCheck = bornDate.split("/");
        try
        {
            if (Integer.parseInt(dateCheck[0]) > 31 || Integer.parseInt(dateCheck[1]) > 12)
            {
                throw new IllegalArgumentException();
            }
            bdate = new SimpleDateFormat("dd/MM/yyyy").parse(bornDate);
        }
        catch (Exception e)
        {
            System.out.println("Wrong date format, use dd/MM/yyyy, and try again.");
            bdate = Dayofbirth();
        }
        return bdate;
    }

    private void EstatesInfoMenu()
    {
        System.out.println("\nAvailable options: ");
        System.out.println(" 1. Add new estate.");
        System.out.println(" 2. Remove estate.");
        System.out.println(" 3. Show information about estates.");
        System.out.println(" 4. Save and Exit. ");
        System.out.println(" 5. Go back to main menu. ");
        System.out.print(" Choose by providing number: ");
        int option = myScanner.nextInt();
        myScanner.nextLine();
        switch (option)
        {
            case 1 -> {
                System.out.println("Add apartment or parking spot? a/ps");
                String choice = myScanner.nextLine();
                    if (choice.equals("a") || choice.equals("ps"))
                    {
                        List<Space> listToAdd = choice.equals("a") ? apartmentList : parkingSpotList;
                        listToAdd.add(AddEstate(choice));
                        System.out.println();
                        System.out.println("---Estate added---");
                        EstatesInfoMenu();
                    }
                    else
                        System.out.println("Provide only a or ps.");
                    EstatesInfoMenu();
            }
            case 2 -> RemoveEstate();
            case 3 -> {
                ShowEstatesInfo();
                EstatesInfoMenu();
            }
            case 4 -> {
                Save();
                System.exit(1);
            }
            case 5 -> Start();
        }
    }

    private Space AddEstate(String choice)
    {
        double height = 0;
        double width = 0;
        double length = 0;
        String address = "";
        try
        {
            System.out.println("---ADD ESTATE---");
            System.out.println("Height: ");
            height = myScanner.nextInt();
            myScanner.nextLine();
            System.out.println("Width: ");
            width = myScanner.nextInt();
            myScanner.nextLine();
            System.out.println("Length: ");
            length = myScanner.nextInt();
            myScanner.nextLine();
            System.out.println("Provide address: ");
            address = myScanner.nextLine();
        }
        catch (Exception e)
        {
            myScanner.nextLine();
            System.out.println("Pass numbers for height, width, length. Try again.");
            EstatesInfoMenu();
        }

        if (choice.equals("a"))
        {
            return new Apartment(height,length,width,address);
        }
        else
            return new ParkingSpot(height, length, width, address);
    }

    private void ShowEstatesInfo()
    {
        System.out.println("---Printing info about estates---");
        System.out.println("Apartments:");
        for (Space estate : apartmentList)
        {
            System.out.println(estate);
        }
        System.out.println("\nParking spots:");
        for (Space parkingSpot : parkingSpotList)
        {
            System.out.println(parkingSpot);
        }

    }

    private void RemoveEstate()
    {
        System.out.println("---REMOVE ESTATE---");
        ShowEstatesInfo();
        List<Space> list = null;


        boolean rightNumber = true;
        while (rightNumber)
        {
            try
            {
                System.out.println("\nRemove apartment or parking spot? a/ps");
                String choice = myScanner.nextLine();
                if (choice.equals("a"))
                {
                    System.out.println("You have chosen apartments. Provide right id.");
                    list = apartmentList;

                }
                else if (choice.equals("ps"))
                {
                    System.out.println("You have chosen parking spots. Provide right id.");
                    list = parkingSpotList;
                }
                else
                {
                    System.out.println("Wrong choice. Try again.");
                    RemoveEstate();
                }
                int id = myScanner.nextInt();
                myScanner.nextLine();
                System.out.println("You have chosen " + (choice.equals("a") ? "A" : "PS") + id + " for removal.");
                if (list.size() >= id)
                {
                    Space toRemove = list.get(id - 1);
                    if (toRemove instanceof Apartment)
                    {
                        System.out.println(((Apartment) toRemove).id+ " " + ((Apartment) toRemove).address +  " will be removed.");
                        System.out.println("Are you sure ? y/n");
                        String ans = myScanner.nextLine();
                        if (ans.equals("y"))
                        {
                            list.remove(toRemove);
                            System.out.println("Apartment removed.");
                            rightNumber = false;
                            EstatesInfoMenu();
                        } else
                        {
                            System.out.println("Going back to Estates Menu.");
                            rightNumber = false;
                            EstatesInfoMenu();
                        }

                    }
                    else if (toRemove instanceof ParkingSpot)
                    {
                        System.out.println(((ParkingSpot) toRemove).id+ " " + ((ParkingSpot) toRemove).address +  " will be removed.");
                        System.out.println("Are you sure ? y/n");
                        String ans = myScanner.nextLine();
                        if (ans.equals("y"))
                        {
                            list.remove(toRemove);
                            System.out.println("Apartment removed.");
                            rightNumber = false;
                            EstatesInfoMenu();
                        } else
                        {
                            System.out.println("Going back to Estates Menu.");
                            rightNumber = false;
                            EstatesInfoMenu();
                        }
                    }
                }
                else
                {
                    System.out.print("There is no such space, choose different one: ");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Numbers only. Try again.");
                myScanner.nextLine();
            }
        }
    }

    private void Save()
    {
        List<Object> stateOfApp = new ArrayList<>();
        stateOfApp.add(peopleList);
        stateOfApp.add(apartmentList);
        stateOfApp.add(parkingSpotList);
        try
        {
            FileOutputStream fos = new FileOutputStream("StanAplikacji.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(stateOfApp);
            oos.close();
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong.");
        }
    }

    private void Load()
    {
        try
        {
            FileInputStream fis = new FileInputStream("StanAplikacji.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Object> stateOfApp = (List<Object>) ois.readObject();
            peopleList = (List<Person>) stateOfApp.get(0);
            apartmentList = (List<Space>) stateOfApp.get(1);
            parkingSpotList = (List<Space>) stateOfApp.get(2);
            ois.close();
            System.out.println("Data loaded!");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong.");
        }

    }

    private Person ChoiceMenu()
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
