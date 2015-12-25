import java.util.Scanner;
import java.util.Random;

public class fourSquare {
	public static void computerLogic(char[][] a) {
		Random rand = new Random();
		boolean moveMade = false;
		int randMove = 0;

		//checkWin
		for (int col = 1; col <= BOARD_LENGTH; col++) {
			if (checkMove(a, col)) {
				makeMove(a, col, COMPUTER);
				if (checkWin(a)) {
					System.out.println("\nWin found!\n");
					moveMade = true;
					break;
				}
				else
					makeMove(a, col, ' ');
			}
		}

		//checkBlock
		if (moveMade == false) {
			for (int col = 1; col <= BOARD_LENGTH; col++) {
				if (checkMove(a, col)) {
					makeMove(a, col, 'B');
					if (checkWin(a)) {
						makeMove(a, col, ' ');
						System.out.println("\nBlocked!");
						makeMove(a, col, COMPUTER);
						moveMade = true;
						break;
					}
					else
						makeMove(a, col, ' ');
				}
			}
		}

		//else: random move
		while (!moveMade) {
			randMove = rand.nextInt(7)+1;
			if (checkMove(a, randMove)) {
				makeMove(a, randMove, COMPUTER);
				moveMade = true;
				System.out.println("\nRandom Move"+" ("+randMove+").");
				break;
			}
		}
	}
	public static boolean checkMove(char[][] a, int b) {
		if (b > 0 && b <= BOARD_LENGTH) {
			for (int row = 0; row < BOARD_HEIGHT; row++) {
				if (a[row][b-1] == ' ')
					return true;
			}
			return false;
		}
		return false;
	}
	public static void makeMove(char[][] a, int b, char c) {
		if (c == ' ') {
			for (int row = 0; row < BOARD_HEIGHT; row++) {
				if (a[row][b-1] == 'O' || a[row][b-1] == 'B') {
					a[row][b-1] = c;
					break;
				}
			}
		}
		else {
			for (int row = BOARD_HEIGHT - 1; row >= 0; row--) {
				if (a[row][b-1] == ' ') {
					a[row][b-1] = c;
					break;
				}
			}
		}
	}
	public static boolean fullBoard(char [][] a) {
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_LENGTH; j++) {
				if (a[i][j] == ' ')
					return false;
			}
		}
		return true;
	}
	public static boolean checkWin(char [][] a) {
		//check previous turn win
		for (int row = 0; row < BOARD_HEIGHT; row++) {
			for (int col = 0; col < BOARD_LENGTH; col++) {
				if (a[row][col] == 'W')
					return true;
			}
		}

		//check straight across
		for (int row = 0; row < BOARD_HEIGHT; row++) {
			for (int col = 0; col < 4; col++) {
				if (a[row][col] == 'X' || a[row][col] == 'B') {
					if (a[row][col+1] == 'X' || a[row][col+1] == 'B')
						if (a[row][col+2] == 'X' || a[row][col+2] == 'B')
							if (a[row][col+3] == 'X' || a[row][col+3] == 'B') {
								if (a[row][col] == 'X' && a[row][col+1] == 'X' && a[row][col+2] == 'X' && a[row][col+3] == 'X') {
									for (int i = 0; i < 4; i++)
										a[row][col+i] = 'W';
								}
								return true;
							}
				}
				else if (a[row][col] == 'O') {
					if (a[row][col+1] == 'O')
						if (a[row][col+2] == 'O')
							if (a[row][col+3] == 'O') {
								for (int i = 0; i < 4; i++)
									a[row][col+i] = 'W';
								return true;
							}
				}
			}
		}

		//check straight down
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < BOARD_LENGTH; col++) {
				if (a[row][col] == 'X' || a[row][col] == 'B') {
					if (a[row+1][col] == 'X' || a[row+1][col] == 'B')
						if (a[row+2][col] == 'X' || a[row+2][col] == 'B')
							if (a[row+3][col] == 'X' || a[row+3][col] == 'B')
								if (a[row][col] == 'X' && a[row+1][col] == 'X' && a[row+2][col] == 'X' && a[row+3][col] == 'X') {
									for (int i = 0; i < 4; i++)
										a[row+i][col] = 'W';
								}
								return true;
				}
				else if (a[row][col] == 'O') {
					if (a[row+1][col] == 'O') 
						if (a[row+2][col] == 'O')
							if (a[row+3][col] == 'O') {
								for (int i = 0; i < 4; i++)
									a[row+i][col] = 'W';
								return true;
							}
				}
			}
		}

		//check diagonal "\"
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 4; col++) {
				if (a[row][col] == 'X' || a[row][col] == 'B') 
					if (a[row+1][col+1] == 'X' || a[row+1][col+1] == 'B') 
						if (a[row+2][col+2] == 'X' || a[row+2][col+2] == 'B') {
							if (a[row+3][col+3] == 'X' || a[row+3][col+3] == 'B') {
								if (a[row][col] == 'X' && a[row+1][col+1] == 'X' && a[row+2][col+2] == 'X' && a[row+3][col+3] == 'X') {
									for (int i = 0; i < 4; i++)
										a[row+i][col+i] = 'W';
								}
								return true;
							}
				}
				else if (a[row][col] == 'O') {
					if (a[row+1][col+1] == 'O') 
						if (a[row+2][col+2] == 'O') 
							if (a[row+3][col+3] == 'O') {
								for (int i = 0; i < 4; i++)
									a[row+i][col+i] = 'W';
								return true;
							}
				}
			}
		}

		//check diagonal "/"
		for (int row = BOARD_HEIGHT - 1; row >= 3; row--) {
			for (int col = 0; col < 4; col++) {
				if (a[row][col] == 'X' || a[row][col] == 'B') 
					if (a[row-1][col+1] == 'X' || a[row-1][col+1] == 'B') 
						if (a[row-2][col+2] == 'X' || a[row-2][col+2] == 'B') 
							if (a[row-3][col+3] == 'X' || a[row-3][col+3] == 'B') {
								if (a[row][col] == 'X' && a[row-1][col+1] == 'X' && a[row-2][col+2] == 'X' && a[row-3][col+3] == 'X') {
									for (int i = 0; i < 4; i++)
										a[row-i][col+i] = 'W';
								}
								return true;
							}
				else if (a[row][col] == 'O') 
					if (a[row-1][col+1] == 'O') 
						if (a[row-2][col+2] == 'O') 
							if (a[row-3][col+3] == 'O') {
								for (int i = 0; i < 4; i++)
									a[row-i][col+i] = 'W';
								return true;
							}
			}
		}
		return false;
	}
	public static void printBoard(char[][] a) {
		System.out.println(" (1) (2) (3) (4) (5) (6) (7)");
		System.out.println("------------------------------");
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_LENGTH; j++) {
				if ( j == 0)
					System.out.print("| ");
				System.out.print(a[i][j]);
				if (i <= BOARD_LENGTH)
					System.out.print(" | ");
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}
	public static final int BOARD_LENGTH = 7;
	public static final int BOARD_HEIGHT = 6;
	public static final char PLAYER = 'X';
	public static final char COMPUTER = 'O';
	public static void main(String[] args) {
		//variables
		Scanner s = new Scanner(System.in);
		boolean game = true;
		boolean playerTurn = true;
		int playerMove = 0;
		int turn = 0;
		char[] row_1 = {' ', ' ', ' ', ' ', ' ', ' ', ' '};
		char[] row_2 = {' ', ' ', ' ', ' ', ' ', ' ', ' '};
		char[] row_3 = {' ', ' ', ' ', ' ', ' ', ' ', ' '};
		char[] row_4 = {' ', ' ', ' ', ' ', ' ', ' ', ' '};
		char[] row_5 = {' ', ' ', ' ', ' ', ' ', ' ', ' '};
		char[] row_6 = {' ', ' ', ' ', ' ', ' ', ' ', ' '};
		char [][] board = {row_1,row_2,row_3,row_4,row_5,row_6};

		//welcome message
		System.out.println("Welcome to Vaughn's Connect Four!\n");
		System.out.println("You will be playing against the AI.\n");
		System.out.println("Starting Board: \n");
		printBoard(board);
		do {
			//playerTurn
			turn++;
			playerTurn = true;
			while(playerTurn) {
				System.out.print("Enter 1-7 to make a move: ");
				if (s.hasNextInt()) {
					playerMove = s.nextInt();
					if (checkMove(board, playerMove)) {
						makeMove(board, playerMove, PLAYER);
						playerTurn = false;
					}
					else {
						System.out.println("\nNo space in that column!\n");
						playerTurn = true;
						s.nextLine();
					}
				}
				else {
					System.out.println("\nPlease follow the instructions.\n");
					playerTurn = true;
					s.nextLine();
				}
			}
			if (checkWin(board)) {
				game = false;
				System.out.println("\nYou beat the computer in "+turn+" turns!\n");
				printBoard(board);
			}
			else if (!checkWin(board) && fullBoard(board)) {
				System.out.println("\nDraw! Wow.");
				game = false;
			}
			else {
				//computerTurn
				turn++;
				computerLogic(board);
				if (checkWin(board)) {
					game = false;
					System.out.println("The computer beat you in "+turn+" turns!");
				}
				else if (!checkWin(board) && fullBoard(board)) {
					System.out.println("\nDraw! Wow.");
					game = false;
				}
				System.out.println();
				printBoard(board);
			}
		} while (game);
		s.close();
	}
}