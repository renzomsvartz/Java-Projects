package Chapter_7_Programming_Challenges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Month;

public class RainFallDriver 
{
	private static LocalDate currentdate = LocalDate.now();
	
	public static void main(String[] args) 
	{
		ArrayList<RainFall> rainfallrecords = new ArrayList<>();
		
		int option = 0;
	
		//Getting the current date value
	    System.out.println("Current date: "+ currentdate);
		
	    while (option != 4)
	    {
			switch (option = getMenuOption(1, 4))
			{
				case 1: printYears(rainfallrecords);
					break;
				case 2: editRainFall(rainfallrecords);
					break;
				case 3: displayYear(rainfallrecords);
					break;
				case 4:
					break;
				default: System.out.println("You did not enter " +
	                    "a valid choice.");
					break;
			}
	    }
	}

	public static void displayYear(ArrayList<RainFall> rainfallrecords)
	{
		System.out.println("We currently have rainfall data for:");
		printYears(rainfallrecords);
		int year = getYear("For which year would you like to print the rainfall data.", 1900, currentdate.getYear());

		//Search for the year in the ArrayList
				boolean foundYear = false;
				int index = -1;
				for (int i = 0; i < rainfallrecords.size(); i++)
				{
					if (year == rainfallrecords.get(i).getYear())
					{
						foundYear = true;
						index = i;
						break;
					}
				}
				if (foundYear)
				{
					System.out.println("We have the following rainfall data for " + year + ".");
					printYear(rainfallrecords.get(index));
				}
				else
				{
					System.out.println("We have no records for " + year + ".");
				}
	}
	
	public static void editRainFall(ArrayList<RainFall> rainfallrecords)
	{
		if (rainfallrecords.size() == 0)
		{
			System.out.println("There is no rainfall data.");
		}
		else
		{
			System.out.println("There are records for ");
			printYears(rainfallrecords);
		}
		
		int year = getYear("For which year would you like to input rainfall data. (1900 - " + currentdate.getYear() + ")", 1900, currentdate.getYear());
		
		//Search for the year in the ArrayList
		boolean foundYear = false;
		int index = -1;
		for (int i = 0; i < rainfallrecords.size(); i++)
		{
			if (year == rainfallrecords.get(i).getYear())
			{
				foundYear = true;
				index = i;
				break;
			}
		}
		if (foundYear)
		{
			System.out.println("We have the following rainfall data for " + year + ".");
			printYear(rainfallrecords.get(index));
			
			if (year == currentdate.getYear())
			{
				int month = getYear("For which month would you like to input rainfall data. (JAN (1) - " + currentdate.getMonth() + " (" + currentdate.getMonth().getValue() + "))", 1, currentdate.getMonth().getValue());

				fillMonth(rainfallrecords, year, month);
			}
			else
			{
				int month = getYear("For which month would you like to input rainfall data. (JAN (1) - DEC (12))", 1, 12);

				fillMonth(rainfallrecords, year, month);
			}
		}
		else
		{
			System.out.println("We have no records for " + year + ".");
			
			//Add the year to the ArrayList
			rainfallrecords.add(new RainFall(year));
			
			if (year == currentdate.getYear())
			{
				int month = getYear("For which month would you like to input rainfall data. (JAN (1) - " + currentdate.getMonth() + " (" + currentdate.getMonth().getValue() + "))", 1, currentdate.getMonth().getValue());

				fillMonth(rainfallrecords, year, month);
			}
			else
			{
				int month = getYear("For which month would you like to input rainfall data. (JAN (1) - DEC (12))", 1, 12);

				fillMonth(rainfallrecords, year, month);
			}
		}
		
		rainfallrecords.sort((o1, o2) -> Integer.compare(o1.getYear(), o2.getYear()));
	}
	
	public static void fillMonth(ArrayList<RainFall> rainfallrecords, int year, int month)
	{
		boolean foundYear = false;
		int index = 0;
		
		double amount = getAmount("How much rain fell in " + month + "-" + year + ". (in mm)");
		
		for (int i = 0; i < rainfallrecords.size(); i++)
		{
			if (year == rainfallrecords.get(i).getYear())
			{
				foundYear = true;
				index = i;
				break;
			}
		}
		rainfallrecords.get(index).setMonth(month - 1, amount);
		
		System.out.println(amount + " mm of rain was recorded for " + month + "-" + year + ".");
	}
	
	public static void printYears(ArrayList<RainFall> rainfallrecords)
	{
		if (rainfallrecords.size() == 0)
		{
			System.out.println("There is no rainfall data.");
		}
		
		for (int i = 0; i < rainfallrecords.size(); i++)
		{
			System.out.println(rainfallrecords.get(i).getYear());
		}
	}
	
	public static void printYear(RainFall year)
	{
		String month[] = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
		for (int i = 0; i < 12; i++)
		{
			System.out.println(month[i] + ": " + year.getMonth(i) + " mm.");
		}
	}
	
	public static void printMenu()
	{
		System.out.println("Welcome to the rainfall records program menu. Type a number to select an option.");
		System.out.println("1. See which years have recorded rainfall data.");
		System.out.println("2. Enter/edit rainfall data.");
		System.out.println("3. Print the rainfall data for a specific year.");
		System.out.println("4. Exit program.");
	}
	
	/** Method used to check if a string is numeric, sourced online and editted to fit this program. Source: https://stackabuse.com/java-check-if-string-is-a-number/
	 * 
	 * @param str a string that may contain a number which will be processed to determine if the string is numeric
	 * @return a boolean, whether the input string is numeric or not
	 */
	public static boolean isNumeric(String str) 
	{ 
		  try 
		  {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e)
		  {  
		    return false;  
		  }  
	}
	
	/** Method used to obtain an integer from the user and provide feedback and input validation
	 * 
	 * @return an integer, input from the user
	 */
	public static int getMenuOption(int min, int max)
	{
		//Create a Scanner object named keyboard that reads from System.in
		Scanner keyboard = new Scanner(System.in);
		
		//Create a String object named userInput to save the user's input, and an integer variable for input validation
		String inputStr = "";
		int responseLength = 0;
		int option = -1;
		
		while (responseLength == 0 || !isNumeric(inputStr) || option < min || option > max)
		{
			//Prompt user
			printMenu();
			
			
			//Save user input as String variable inputStr
			inputStr = keyboard.nextLine();
			responseLength = inputStr.length();
			

			if (responseLength == 0)
			{
				System.out.println("Empty input. Retrying prompt. ");
			}
			else if (!isNumeric(inputStr))
			{
				System.out.println("Invalid input. Retrying prompt. ");
			}
			else if ((option = Integer.parseInt(inputStr)) < min || option > max)
			{
				System.out.printf("Your choice must be between %d and %d. Retrying prompt. ", min, max);
			}
		}
		
		return option;
	}
	
	public static int getYear(String str, int min, int max)
	{
		//Create a Scanner object named keyboard that reads from System.in
		Scanner keyboard = new Scanner(System.in);
		
		//Create a String object named userInput to save the user's input, and an integer variable for input validation
		String inputStr = "";
		int responseLength = 0;
		int year = -1;
		
		while (responseLength == 0 || !isNumeric(inputStr) || year < min || year > max)
		{
			//Prompt user
			System.out.println(str);
			
			//Save user input as String variable inputStr
			inputStr = keyboard.nextLine();
			responseLength = inputStr.length();
			
			
			if (responseLength == 0)
			{
				System.out.println("Empty input. Retrying prompt. ");
			}
			else if (!isNumeric(inputStr))
			{
				System.out.println("Invalid input. Retrying prompt. ");
			}
			else if ((year = Integer.parseInt(inputStr)) < min || year > max)
			{
				System.out.printf("The year must be between %d and %d. Retrying prompt. ", min, max);
			}
		}
		
		return year;
	}
	
	public static double getAmount(String str)
	{
		//Create a Scanner object named keyboard that reads from System.in
		Scanner keyboard = new Scanner(System.in);
		
		//Create a String object named userInput to save the user's input, and an integer variable for input validation
		String inputStr = "";
		int responseLength = 0;
		double amount = -1;
		
		while (responseLength == 0 || !isNumeric(inputStr))
		{
			//Prompt user
			System.out.println(str);
			
			//Save user input as String variable inputStr
			inputStr = keyboard.nextLine();
			responseLength = inputStr.length();
			
			
			if (responseLength == 0)
			{
				System.out.println("Empty input. Retrying prompt. ");
			}
			else if (!isNumeric(inputStr))
			{
				System.out.println("Invalid input. Retrying prompt. ");
			}
			else if ((amount = Double.parseDouble(inputStr)) < 0)
			{
				System.out.printf("The amount must be greater than or equal to 0. Retrying prompt. ");
			}
		}
		
		return amount;
	}
}
