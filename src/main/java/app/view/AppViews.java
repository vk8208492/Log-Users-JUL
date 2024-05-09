package app.view;

import app.utils.AppStarters;
import app.utils.Constantss;

import java.util.InputMismatchException;
import java.util.Scanner;


public class AppViews {

    public int getOption() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        getMenu();
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(Constantss.INCORRECT_VALUE_MSG);
            AppStarters.startApp();
        }
        return option;
    }

    private void getMenu() {
        System.out.print("""

                OPTIONS:
                1 - Create contact.
                2 - Read contacts.
                3 - Update contact.
                4 - Delete contact.
                5 - Read contact by id.
                0 - Close the App.
                """);
        System.out.print("Input your option: ");
    }

    public void getOutput(String output) {
        if (output.equals("0")) {
            System.out.println(Constantss.APP_CLOSE_MSG);
            System.exit(0);
        } else System.out.println(output);
    }
}
