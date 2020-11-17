import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {

        char [][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        String result;

        System.out.println("would you like to start? '1' = Yes || '0' = No");
        Scanner decision = new Scanner(System.in);
        int playerDecision = decision.nextInt();

        if(playerDecision == 0){
            System.out.println("CPU start, please continue...");
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            placePiece(gameBoard, cpuPos, "cpu");
        }


        while(true){ //forever running - will break at the end of the game.
            System.out.println("Enter your placement (1-9):");
            printGameBoard(gameBoard);

            Scanner scan = new Scanner(System.in);
            int playerPos = scan.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position is taken, please try again...");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");
            result = checkWinner();
            if (!result.equals(" ")){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");
            result = checkWinner();
            if (!result.equals(" ")){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user){

        char symbol = 'X';

        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        }else if(user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        switch(pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List topCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List botCol = Arrays.asList(3, 6, 9);
        List crossUp = Arrays.asList(7, 5, 3);
        List crossDown = Arrays.asList(1, 5, 9);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(topCol);
        winning.add(midCol);
        winning.add(botCol);
        winning.add(crossDown);
        winning.add(crossUp);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "you won";
            }else if (cpuPositions.containsAll(l)) {
                    return "CPU wins";
                  }else if (playerPositions.size() + cpuPositions.size() == 9){
                        return "tie";
                        }
        }
        return " ";
    }

    public static void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        //System.out.println();
    }
}


