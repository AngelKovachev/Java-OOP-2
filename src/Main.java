import core.ManagerControllerImpl;
import core.interfaces.ManagerController;
import repositories.CardRepositoryImpl;
import repositories.PlayerRepositoryImpl;
import repositories.interfaces.CardRepository;
import repositories.interfaces.PlayerRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        PlayerRepository playerRepository = new PlayerRepositoryImpl();
        CardRepository cardRepository = new CardRepositoryImpl();

        ManagerController managerController = new ManagerControllerImpl(playerRepository, cardRepository);

        while (!input.equals("Exit")) {
            String[] data = input.split("\\s+");

            String command = data[0];

            switch (command) {
                case "AddPlayer":
                    try {

                        System.out.println(managerController.addPlayer(data[1], data[2]));
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "AddCard":
                    try {
                        System.out.println(managerController.addCard(data[1], data[2]));
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "AddPlayerCard":
                    try {

                        System.out.println(managerController.addPlayerCard(data[1], data[2]));
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Fight":
                    try {
                        System.out.println(managerController.fight(data[1], data[2]));
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Report":
                    System.out.println(managerController.report());

            }

            input = sc.nextLine();
        }

        //System.out.println("Hello World!");
    }
}
