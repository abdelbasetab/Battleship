package battleship;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {



public static void main(String[] args) {

        BattleshipGame battleshipGame = new BattleshipGame();

        battleshipGame.initializeGameField();
        battleshipGame.printGameField();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter start coordinate of the ship:");
        String startCoordinate = scanner.next().toUpperCase();
        String endCoordinate = scanner.next().toUpperCase();
        if (!battleshipGame.isValidInput(startCoordinate, endCoordinate)) {
                System.out.println("Error");
        }else {
                battleshipGame.placeShip(startCoordinate, endCoordinate);
                int shipLength = battleshipGame.getShipLength(startCoordinate, endCoordinate);
                System.out.println("Length: " + shipLength);
                System.out.print("Parts: ");
                battleshipGame.printShipCoordinates(startCoordinate, endCoordinate);
        }




    }
}
