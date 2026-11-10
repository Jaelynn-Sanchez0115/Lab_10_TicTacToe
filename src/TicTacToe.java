import java.util.Scanner;

public class TicTacToe
{
    // Class-level constants and board
    private static final int ROWS = 3;
    private static final int COLS = 3;

    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);

        String currentPlayer = "X";
        boolean playAgain = true;

        // PSEUDOCODE:
        // Create Scanner
        // Ask players if they want to play
        // WHILE players want to play
        //     Clear the board
        //     Set current player to X
        //     Set move counter to zero
        //     WHILE game is not over
        //         Display board
        //         Get row and column from current player
        //         Convert coordinates to array indices
        //         Check if move is valid
        //         If invalid, ask for another move
        //         Place player's symbol on board
        //         Increment move counter
        //         Display board
        //         Check for a win
        //         Check for a tie
        //         Switch players if game continues
        //     Ask players if they want to play again

        do
        {
            clearBoard();

            currentPlayer = "X";
            int moveCounter = 0;
            boolean gameOver = false;

            System.out.println("\nWelcome to Tic Tac Toe!");

            while (!gameOver)
            {
                display();

                System.out.println("\nPlayer " + currentPlayer + "'s turn.");

                int rowMove;
                int colMove;

                // Get a valid move from the player
                do
                {
                    rowMove = SafeInput.getRangedInt(
                            console, "Enter row", 1, ROWS);

                    colMove = SafeInput.getRangedInt(
                            console, "Enter column", 1, COLS);

                    // Convert from 1-3 to 0-2
                    rowMove--;
                    colMove--;

                    if (!isValidMove(rowMove, colMove))
                    {
                        System.out.println("That position is already occupied!");
                        System.out.println("Please try again.\n");
                    }

                } while (!isValidMove(rowMove, colMove));

                // Place the player's symbol on the board
                board[rowMove][colMove] = currentPlayer;

                moveCounter++;

                // Display updated board
                display();

                // Check for a win
                if (isWin(currentPlayer))
                {
                    System.out.println("\nPlayer " + currentPlayer + " wins!");
                    gameOver = true;
                }

                // Check for a tie
                else if (isTie())
                {
                    System.out.println("\nThe game is a tie!");
                    gameOver = true;
                }

                // Switch players
                else
                {
                    if (currentPlayer.equals("X"))
                    {
                        currentPlayer = "O";
                    }
                    else
                    {
                        currentPlayer = "X";
                    }
                }
            }

            // Ask whether players want another game
            playAgain = SafeInput.getYNConfirm(
                    console, "\nWould you like to play again?");

        } while (playAgain);

        System.out.println("\nThanks for playing Tic Tac Toe!");

        console.close();
    }

    // Clears every position on the board
    private static void clearBoard()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    // Displays the Tic Tac Toe board
    private static void display()
    {
        System.out.println();

        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                System.out.print(" " + board[row][col] + " ");

                if (col < COLS - 1)
                {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (row < ROWS - 1)
            {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }

    // Checks whether the selected position is empty
    private static boolean isValidMove(int row, int col)
    {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS)
        {
            return false;
        }

        return board[row][col].equals(" ");
    }

    // Checks all possible winning conditions
    private static boolean isWin(String player)
    {
        return isRowWin(player) ||
                isColWin(player) ||
                isDiagnalWin(player);
    }

    // Checks for a horizontal win
    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROWS; row++)
        {
            if (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }

        return false;
    }

    // Checks for a vertical win
    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COLS; col++)
        {
            if (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }

        return false;
    }

    // Checks both diagonal winning conditions
    private static boolean isDiagnalWin(String player)
    {
        // Top-left to bottom-right
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player))
        {
            return true;
        }

        // Top-right to bottom-left
        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player))
        {
            return true;
        }

        return false;
    }

    // Checks whether the board is completely full
    private static boolean isTie()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                if (board[row][col].equals(" "))
                {
                    return false;
                }
            }
        }

        return true;
    }
}