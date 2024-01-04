package battleship;

public class BattleshipGame {
    private static final int FIELD_SIZE = 10;
    private static final char FOG_OF_WAR = '~';
    private static final char SHIP = 'O';
    private static final char HIT = 'X';
    private static final char MISS = 'M';

    private static char[][] gameField = new char[FIELD_SIZE][FIELD_SIZE];


    /**
     * initial Field with ~ Symbol
     */

    public void initializeGameField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                gameField[i][j] = FOG_OF_WAR;
            }
        }
    }

    /***
     * print game Field
     */
    public  void printGameField() {
        // 1 2 3 4 5 6 7 8 9 10
        System.out.print("  ");
        for (int i = 1; i <= FIELD_SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        // A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * check if the input valid or not
     * @param startCoordinate
     * @param endCoordinate
     * @return
     */
    public  boolean isValidInput(String startCoordinate, String endCoordinate) {
        if ((startCoordinate.length() > 3 || endCoordinate.length() > 3)
        ||(startCoordinate.length() < 2 || endCoordinate.length() < 2)){
            return false;
        }

        char startRow = startCoordinate.charAt(0);
        char endRow = endCoordinate.charAt(0);
        int startCol = Integer.parseInt(startCoordinate.substring(1));//Character.getNumericValue(startCoordinate.charAt(1));// todo
        int endCol = Integer.parseInt(endCoordinate.substring(1));//Character.getNumericValue(endCoordinate.charAt(1));

        return startRow >= 'A' && startRow <= 'J' &&
                endRow >= 'A' && endRow <= 'J' &&
                startCol >= 1 && startCol <= FIELD_SIZE &&
                endCol >= 1 && endCol <= FIELD_SIZE &&
                (startRow == endRow || startCol == endCol);
    }

    public  boolean placeShip(String startCoordinate, String endCoordinate) {
        char startRow = startCoordinate.charAt(0);
        char endRow = endCoordinate.charAt(0);
        int startCol = Integer.parseInt(startCoordinate.substring(1));//Character.getNumericValue(startCoordinate.charAt(1));// todo
        int endCol = Integer.parseInt(endCoordinate.substring(1));//Character.getNumericValue(endCoordinate.charAt(1));

        int startRowIdx = startRow - 'A';
        int startColIdx = startCol - 1;
        int endRowIdx = endRow - 'A';
        int endColIdx = endCol - 1;
        //int shipLength = 0;
        if (startRow == endRow) {// A3 , A7
            int minCol = Math.min(startColIdx, endColIdx);//7,3  = 3,7
            int maxCol = Math.max(startColIdx, endColIdx);
            // Check for adjacent or overlapping ships horizontally
            for (int j = minCol - 1; j <= maxCol + 1; j++) {
                if (j >= 0 && j < FIELD_SIZE) {
                    if (startRowIdx >= 1 && startRowIdx < FIELD_SIZE - 1) {
                        if (gameField[startRowIdx - 1][j] == SHIP || gameField[startRowIdx + 1][j] == SHIP) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                    if (gameField[startRowIdx][j] == SHIP) {
                        System.out.println("Error");
                        return false;
                    }
                }
            }

            // check if there is no other ship there
            for (int j = minCol; j <= maxCol; j++) {
                if (gameField[startRowIdx][j] != FOG_OF_WAR) {
                    return false;
                }
            }

            for (int j = minCol; j <= maxCol; j++) {
                gameField[startRowIdx][j] = SHIP;
            }
        } else {
            int minRow = Math.min(startRowIdx, endRowIdx);//
            int maxRow = Math.max(startRowIdx, endRowIdx);

            // Check for adjacent or overlapping ships vertically
            for (int i = minRow - 1; i <= maxRow + 1; i++) {
                if (i >= 0 && i < FIELD_SIZE) {
                    if (startColIdx >= 1 && startColIdx < FIELD_SIZE - 1) {
                        if (gameField[i][startColIdx - 1] == SHIP || gameField[i][startColIdx + 1] == SHIP) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }

                    if (gameField[i][startColIdx] == SHIP) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }
            }

            // check if there is no other ship there
            for (int i = minRow; i <= maxRow; i++) {
                if (gameField[i][startColIdx] != FOG_OF_WAR) {
                    return false;
                }
            }

            for (int i = minRow; i <= maxRow; i++) {
                gameField[i][startColIdx] = SHIP;
            }
        }

        return true;
    }
    public  int getShipLength(String startCoordinate, String endCoordinate) {
        char startRow = startCoordinate.charAt(0);
        char endRow = endCoordinate.charAt(0);
        int startCol = Integer.parseInt(startCoordinate.substring(1));
        int endCol = Integer.parseInt(endCoordinate.substring(1));

        int length;
        if (startRow == endRow) {
            length = Math.abs(endCol - startCol) + 1;
        } else {
            length = Math.abs(endRow - startRow) + 1;
        }

        return length;
    }
    public  void printShipCoordinates(String startCoordinate, String endCoordinate) {
        char startRow = startCoordinate.charAt(0);
        char endRow = endCoordinate.charAt(0);
        int startCol = Integer.parseInt(startCoordinate.substring(1));
        int endCol = Integer.parseInt(endCoordinate.substring(1));

        if (startRow == endRow) {
            if(startCol > endCol){

                for (int j = startCol; j >= endCol; j--) {
                    System.out.print( (startRow) + "" + j + " ");
                }

            }else{
                for (int j = startCol; j <= endCol; j++) {
                    System.out.print( (startRow) + "" + j + " ");
                }
            }

            System.out.println();
        } else { // A3 D3
            if(startRow > endRow){//D3 A3

                for (char i = startRow; i >= endRow; i--) {
                    System.out.print(i + "" + startCol + " ");
                }
            }else {//A3 D3
                for (char i = startRow; i <= endRow; i++) {
                    System.out.print(i + "" + startCol + " ");
                }
            }
            System.out.println();
        }
    }
}
