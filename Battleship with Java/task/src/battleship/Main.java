package battleship;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {



        private static void placeShip(BattleshipGame battleshipGame,Scanner scanner, String shipName, int shipLength) {
                boolean validPlacement = false;

                while (!validPlacement) {
                        System.out.println("Enter the coordinates of the " + shipName + " (" + shipLength + " cells):");
                        String startCoordinate = scanner.next().toUpperCase();
                        String endCoordinate = scanner.next().toUpperCase();

                        if (!battleshipGame.isValidInput(startCoordinate, endCoordinate)) {
                                System.out.println("Error: Invalid input. Please try again.");
                                continue;
                        }
                        if(battleshipGame.getShipLength(startCoordinate,endCoordinate) != shipLength){
                                System.out.println("Error! Wrong length of the " + shipName );
                                continue;
                        }
                        validPlacement = battleshipGame.placeShip(startCoordinate, endCoordinate);
                        if (!validPlacement) {
                                System.out.println("Error! Try again:");

                        }else{
                                battleshipGame.printGameField();
                        }
                }


        }
        public static void main(String[] args) {

                BattleshipGame battleshipGame = new BattleshipGame();

                battleshipGame.initializeGameField();
                battleshipGame.printGameField();
                Scanner scanner = new Scanner(System.in);

                placeShip(battleshipGame,scanner, "Aircraft Carrier", 5);
                placeShip(battleshipGame,scanner, "Battleship", 4);
                placeShip(battleshipGame,scanner, "Submarine", 3);
                placeShip(battleshipGame,scanner, "Cruiser", 3);
                placeShip(battleshipGame,scanner, "Destroyer", 2);

        }


}
