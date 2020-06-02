import java.util.Scanner;


/**
 * Tic-Tac-Toe: Two-player console, non-graphics, non-OO version.
 * All variables/methods are declared as static (belong to the class)
 * in the non-OO version.
 */
public class tictactoe_v2 {
    // Name-constants to represent the seeds and cell contents
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;

    // Name-constants to represent the various states of the game
    public static final int PLAYING = 0;
    public static final int DRAW = 1;
    public static final int CROSS_WON = 2;
    public static final int NOUGHT_WON = 3;

    // The game board and the game status
    //public static int ROWS, COLS; // number of rows and columns
    //public static int[][] board = new int[ROWS][COLS]; // game board in 2D array
    //  containing (EMPTY, CROSS, NOUGHT)
    public static int currentState;  // the current state of the game
    // (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
    public static int currentPlayer; // the current player (CROSS or NOUGHT)
    public static int currntRow, currentCol; // current seed's row and column

    public static Scanner in = new Scanner(System.in); // the input Scanner

    /**
     * The entry main method (the program starts here)
     */
    public static void main(String[] args) {

        //Set Game Board Size
        int ROWS = scale();
        int COLS = ROWS;
        int[][] board = new int[ROWS][COLS];

        // Initialize the game-board and current status
        initGame(ROWS, COLS, board);

        // Play the game once
        do {
            playerMove(currentPlayer, ROWS, COLS, board); // update currentRow and currentCol
            updateGame(currentPlayer, currntRow, currentCol, ROWS, COLS, board); // update currentState
            printBoard(ROWS, COLS, board);
            // Print message if game-over
            if (currentState == CROSS_WON) {
                System.out.println("'X' won! Bye!");
            } else if (currentState == NOUGHT_WON) {
                System.out.println("'O' won! Bye!");
            } else if (currentState == DRAW) {
                System.out.println("It's a Draw! Bye!");
            }
            // Switch player
            currentPlayer = (currentPlayer == CROSS) ? NOUGHT : CROSS;
        } while (currentState == PLAYING); // repeat if not game-over
    }

    /**
     * Customize Game Board size with OOP Implementation
     */
    public static int scale() {

        int ROWS, COLS;
        Scanner in1 = new Scanner(System.in);

        System.out.print("Type your Game Board Size here : ");
        ROWS = in1.nextInt();
        COLS = ROWS;

        return ROWS;
    }

    /**
     * Initialize the game-board contents and the current states
     *
     * @param ROWS
     */
    public static void initGame(int ROWS, int COLS, int[][] board) {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = EMPTY;  // all cells empty
            }
        }
        currentState = PLAYING; // ready to play
        currentPlayer = CROSS;  // cross plays first
    }

    /**
     * Player with the "theSeed" makes one move, with input validation.
     * Update global variables "currentRow" and "currentCol".
     */
    public static void playerMove(int theSeed, int ROWS, int COLS, int[][] board) {
        boolean validInput = false;  // for input validation
        do {
            if (theSeed == CROSS) {
                System.out.print("Player 'X', enter your move (row[1-Size] column[1-Size]): ");
            } else {
                System.out.print("Player 'O', enter your move (row[1-Size] column[1-Size]): ");
            }
            int row = in.nextInt() - 1;  // array index starts at 0 instead of 1
            int col = in.nextInt() - 1;
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY) {
                currntRow = row;
                currentCol = col;
                board[currntRow][currentCol] = theSeed;  // update game-board content
                validInput = true;  // input okay, exit loop
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
            }
        } while (!validInput);  // repeat until input is valid
    }

    /**
     * Update the "currentState" after the player with "theSeed" has placed on
     * (currentRow, currentCol).
     */
    public static void updateGame(int theSeed, int currentRow, int currentCol, int ROWS, int COLS, int[][] board) {
        if (hasWon(theSeed, currentRow, currentCol, ROWS, COLS, board)) {  // check if winning move
            currentState = (theSeed == CROSS) ? CROSS_WON : NOUGHT_WON;
        } else if (isDraw(ROWS, COLS, board)) {  // check for draw
            currentState = DRAW;
        }
        // Otherwise, no change to currentState (still PLAYING).
    }

    /**
     * Return true if it is a draw (no more empty cell)
     */
    // TODO: Shall declare draw if no player can "possibly" win
    public static boolean isDraw(int ROWS, int COLS, int[][] board) {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == EMPTY) {
                    return false;  // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // no empty cell, it's a draw
    }

    /**
     * Return true if the player with "theSeed" has won after placing at
     * (currentRow, currentCol)
     */
    public static boolean hasWon(int theSeed, int currentRow, int currentCol, int ROWS, int COLS, int[][] board) {

        //Checking Rows
        for (int Row = 0; Row < ROWS; Row++) {
            int rowSeedCount = 0;
            for (int Col = 0; Col < COLS; Col++) {
                if (board[Row][Col] == theSeed) {
                    rowSeedCount++;
                }
            }
            if (rowSeedCount == ROWS) {
                return true;
            }
        }

        //Checking Columns
        for (int Col = 0; Col < COLS; Col++) {
            int colSeedCount = 0;
            for (int Row = 0; Row < ROWS; Row++) {
                if (board[Row][Col] == theSeed) {
                    colSeedCount++;
                }
            }
            if (colSeedCount == COLS) {
                return true;
            }
        }

        //Checking Diagonal
        int diaSeedCount = 0;
        for (int Row = 0; Row < ROWS; Row++) {
            for (int Col = 0; Col < ROWS; Col++) {
                if (board[Row][Col] == theSeed) {
                    diaSeedCount++;
                }
            }
            if (diaSeedCount == ROWS) {
                return true;
            }
        }
        return false;
    }


        /**
         * Print the game board
         */
        public static void printBoard ( int ROWS, int COLS, int[][] board){
            for (int row = 0; row < ROWS; ++row) {
                for (int col = 0; col < COLS; ++col) {
                    printCell(board[row][col]); // print each of the cells
                    if (col != COLS - 1) {
                        System.out.print("|");   // print vertical partition
                    }
                }
                System.out.println();
                if (row != ROWS - 1) {
                    for (int i = 0; i < ROWS * 4 - 1; i++) {
                        System.out.print("-"); // print horizontal partition
                    }
                }
                System.out.println();
            }
        }

        /**
         * Print a cell with the specified "content"
         */
        public static void printCell ( int content){
            switch (content) {
                case EMPTY:
                    System.out.print("   ");
                    break;
                case NOUGHT:
                    System.out.print(" O ");
                    break;
                case CROSS:
                    System.out.print(" X ");
                    break;
            }
        }
    }