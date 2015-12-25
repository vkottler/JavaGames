import java.util.Random;
import java.util.Scanner;
public class playRubiks {
	public static final int RANDOMIZE = 50;
	public static final Scanner SCAN = new Scanner(System.in);
	public static Random rng = new Random();
	public static RubiksCube cube;
	public static void main(String[] args) {
		cube = new RubiksCube();
		displayHelpMessage();
		cube.printCube();
		System.out.print("Choose: ");
		while (true) {
			String userinp = SCAN.nextLine();
			String[] inputs = userinp.split(" ");
			processUserInput(inputs);
			cube.printCube();
			System.out.print("Choose: ");
		}
	}

	/**
	 * Allows move to be made on cube.
	 * 
	 * @param input User's input
	 * @param cube
	 */
	public static void processUserInput(String input, RubiksCube cube) {
		input = checkInput(input);
		switch (input) {
		case "tr": cube.rotateLayer('r', input.charAt(1), 0); break;
		case "tl": cube.rotateLayer('r', input.charAt(1), 0); break;
		case "mr": cube.rotateLayer('r', input.charAt(1), 1); break;
		case "ml": cube.rotateLayer('r', input.charAt(1), 1); break;
		case "br": cube.rotateLayer('r', input.charAt(1), 2); break;
		case "bl": cube.rotateLayer('r', input.charAt(1), 2); break;
		case "lu": cube.rotateLayer('c', input.charAt(1), 0); break;
		case "ld": cube.rotateLayer('c', input.charAt(1), 0); break;
		case "mu": cube.rotateLayer('c', input.charAt(1), 1); break;
		case "md": cube.rotateLayer('c', input.charAt(1), 1); break;
		case "ru": cube.rotateLayer('c', input.charAt(1), 2); break;
		case "rd": cube.rotateLayer('c', input.charAt(1), 2); break;
		case "fr": cube.rotateLayer('p', input.charAt(1), 0); break;
		case "fl": cube.rotateLayer('p', input.charAt(1), 0); break;
		case "hl": cube.rotateLayer('p', input.charAt(1), 1); break;
		case "hr": cube.rotateLayer('p', input.charAt(1), 1); break;
		case "rr": cube.rotateLayer('p', input.charAt(1), 2); break;
		case "rl": cube.rotateLayer('p', input.charAt(1), 2); break;
		case "solve": cube.solve(); break;
		case "flip": cube.flipView(); break;
		case "peek": cube.printBack(); break;
		case "left": cube.rotateViewLeft(); break;
		case "right": cube.rotateViewRight(); break;
		case "upr": cube.rotateViewUpRight(); break;
		case "upl": cube.rotateViewUpLeft(); break;
		case "downr": cube.rotateViewDownRight(); break;
		case "downl": cube.rotateViewDownLeft(); break;
		case "scramble": scramble(cube); break;
		case "help": displayHelpMessage(); break;
		case "q": System.exit(0); 
		case "new": newCube(); break;
		case "orient": cube.orientCube(); break;
		default: System.out.println("Invalid input."); break;
		}
	}

	/**
	 * Scrambles the cube, making only valid moves.
	 * 
	 * @param cube
	 */
	public static void scramble(RubiksCube cube) {
		for (int i = 0; i < RANDOMIZE; i++) {
			int choice = rng.nextInt(18);
			String move = "";
			switch (choice) {
			case 0: move = "tr"; break;
			case 1: move = "tl"; break;
			case 2: break;
			case 3: break;
			case 4: move = "br"; break;
			case 5: move = "bl"; break;
			case 6: move = "lu"; break;
			case 7: move = "ld"; break;
			case 8: break;
			case 9: break;
			case 10: move = "ru"; break;
			case 11: move = "rd"; break;
			case 12: move = "fl"; break;
			case 13: move = "fr"; break;
			case 14: break;
			case 15: break;
			case 16: move = "rr"; break;
			case 17: move = "rl"; break;
			}
			processUserInput(move, cube);
		}
	}
	
	public static void processUserInput(String[] s) {
		for (int i = 0; i < s.length; i++) processUserInput(s[i], cube);
	}
	
	public static String checkInput(String s) {
		String retval = "";
		switch (s) {
		case "U": retval = "tr"; break;
		case "U'": retval = "tl"; break;
		case "L": retval = "ld"; break;
		case "L'": retval = "lu"; break;
		case "F": retval = "fr"; break;
		case "F'": retval = "fl"; break;
		case "R": retval = "ru"; break;
		case "R'": retval = "rd"; break;
		case "B": retval = "rl"; break;
		case "B'": retval = "rr"; break;
		case "D": retval = "br"; break;
		case "D'": retval = "bl"; break;
		default: return s;
		}
		return retval;
	}

	public static void newCube() {
		System.out.println("Enter each color character separated by a space: ");
		String s = SCAN.nextLine();
		cube = new RubiksCube(s);
	}

	public static void displayHelpMessage() {
		System.out.println("View Commands:");
		System.out.println("flip -- flip around cube to see other side");
		System.out.println("peek -- view other side but do not flip view");
		System.out.println("right / left -- 90 degree turn left or right");
		System.out.println("upr / upl -- 90 degree turn up (from left or right)");
		System.out.println("downr / downl -- 90 degree turn down (from left or right)\n");
		System.out.println("Move Commands:");
		System.out.println("2 characters for each move, first character is the");
		System.out.println("layer you would like to move, and the second is the");
		System.out.println("direction you will be moving.\n");
		System.out.println("Lateral: 't' is for top. 'm' is for middle. 'b' is for bottom.");
		System.out.println("Can move left 'l' and right 'r'. \n");
		System.out.println("Vertical: 'l' is for left. 'm' is for middle. 'r' is for right.");
		System.out.println("Can move up 'u' and down 'd'. \n");
		System.out.println("Planar: 'f' is for front. 'h' is for middle. 'r' is for rear.");
		System.out.println("Can move left 'l' and right 'r'.\n");
		System.out.println("Lateral Moves: tr, tl, mr, ml, br, br\n");
		System.out.println("Vertical Moves: lu, ld, mu, md, ru, rd\n");
		System.out.println("Planar Moves: fr, fl, hr, hl, rr, rl\n\n");
		System.out.println("Other commands:");
		System.out.println("scramble -- randomly scramble the cube");
		System.out.println(" help -- displays this message again");
		System.out.println("q -- quits the program");
	}
}