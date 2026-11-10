import java.util.Scanner;

public class SafeInput
{
    public static int getRangedInt(Scanner console, String prompt, int low, int high)
    {
        int value;
        boolean valid;

        do
        {
            System.out.print(prompt + " (" + low + "-" + high + "): ");

            if (console.hasNextInt())
            {
                value = console.nextInt();
                console.nextLine();

                if (value >= low && value <= high)
                {
                    valid = true;
                }
                else
                {
                    System.out.println("Please enter a number between " + low + " and " + high + ".");
                    valid = false;
                }
            }
            else
            {
                System.out.println("Please enter a valid integer.");
                console.nextLine();
                value = 0;
                valid = false;
            }

        } while (!valid);

        return value;
    }

    public static boolean getYNConfirm(Scanner console, String prompt)
    {
        String response;

        do
        {
            System.out.print(prompt + " (Y/N): ");
            response = console.nextLine().trim();

            if (response.equalsIgnoreCase("Y"))
            {
                return true;
            }
            else if (response.equalsIgnoreCase("N"))
            {
                return false;
            }
            else
            {
                System.out.println("Please enter Y or N.");
            }

        } while (true);
    }
}