public class RubiksCube {
	private char[][]  white = new char[3][3];
	private char[][]  red = new char[3][3];
	private char[][]  blue = new char[3][3];
	private char[][]  orange = new char[3][3];
	private char[][]  green = new char[3][3];
	private char[][]  yellow = new char[3][3];
	private int[][][] edges;
	private int[][][] corners;

	// top, left, right
	private char[][][] frontView = { white, red, blue };

	// top, left, right
	private char[][][] backView = { yellow, green, orange };

	// all six sides in one array variable
	private char[][][] cube = { frontView[0], frontView[1], frontView[2],
			backView[0], backView[1], backView[2] };

	/**
	 * Constructor
	 */
	public RubiksCube(String s) {
		String[] split = s.split(" ");
		char[] color = new char[split.length];
		int n = 0;
		for (int i = 0; i < split.length; i++) color[i] = 
				split[i].toUpperCase().charAt(0);
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 3; j++)
				for (int k = 0; k < 3; k++){
					cube[i][j][k] = color[n];
					n++;
				}
		initializeEdgeIndices();
		initializeCornerIndices();
	}

	public RubiksCube() {
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++) {
				white[i][j] = 'W';
				red[i][j] = 'R';
				blue[i][j] = 'B';
				orange[i][j] = 'O';
				green[i][j] = 'G';
				yellow[i][j] = 'Y';
			}
		initializeEdgeIndices();
		initializeCornerIndices();
	}

	private void initializeEdgeIndices() {
		int[][] top1 = {{0,0,1},{5,2,1}}; // white orange
		int[][] top2 = {{0,1,2},{2,0,1}}; // white blue
		int[][] top3 = {{0,2,1},{1,0,1}}; // white red
		int[][] top4 = {{0,1,0},{4,2,1}}; // white green 
		int[][] middle1 = {{4,1,2},{5,1,0}}; // green orange
		int[][] middle2 = {{5,1,2},{1,1,2}}; // orange blue
		int[][] middle3 = {{0,0,0},{0,0,0}}; // blue red
		int[][] middle4 = {{1,1,0},{4,1,0}}; // red green
		int[][] bottom1 = {{3,1,2},{5,0,1}}; // yellow orange
		int[][] bottom2 = {{3,0,1},{2,2,1}}; // yellow blue
		int[][] bottom3 = {{3,1,0},{1,2,1}}; // yellow red
		int[][] bottom4 = {{3,2,1},{4,0,1}}; // yellow green
		int[][][] initEdges = {top1,top2,top3,top4,middle1,middle2,middle3,middle4,bottom1,
				bottom2,bottom3,bottom4};
		edges = initEdges;
	}

	private void initializeCornerIndices() {
		int[][] toptopleft = {{0,0,0},{4,2,2},{5,2,0}}; // white green orange
		int[][] toptopright = {{0,0,2},{5,2,2},{2,0,2}}; // white orange blue
		int[][] topbottomright = {{0,2,2},{2,0,0},{1,0,2}}; // white blue red
		int[][] topbottomleft = {{0,2,0},{1,0,0},{4,2,0}}; // white red green
		int[][] bottomtopleft = {{3,0,0},{1,2,2},{2,2,0}}; // yellow red blue
		int[][] bottomtopright = {{3,0,2},{2,2,2},{5,0,2}}; // yellow blue orange
		int[][] bottombottomright = {{3,2,2},{5,0,0},{4,0,2}}; // yellow orange green
		int[][] bottombottomleft = {{3,2,0},{4,0,0},{1,2,0}}; // yellow green red
		int[][][] initCorners = {toptopleft,toptopright,topbottomright,topbottomleft,
				bottomtopleft,bottomtopright,bottombottomright,bottombottomleft};
		corners = initCorners;
	}

	/**
	 * Checks each side for the right values
	 * 
	 * @return whether or not cube is solved
	 */
	public boolean isSolved() {
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++) {
				if (white[i][j] != 1) return false;
				if (red[i][j] != 2) return false;
				if (blue[i][j] != 3) return false;
				if (orange[i][j] != 4) return false;
				if (green[i][j] != 5) return false;
				if (yellow[i][j] != 6) return false;
			}
		return true;
	}

	public void rotateViewLeft() {
		char[][] temp = frontView[1];
		frontView[1] = frontView[2];
		frontView[2] = backView[2];
		backView[2] = backView[1];
		backView[1] = temp;
	}

	public void rotateViewRight() {
		this.rotateViewLeft();
		this.rotateViewLeft();
		this.rotateViewLeft();
	}

	public void rotateViewUpLeft() {
		char[][] temp = frontView[0];
		frontView[0] = frontView[2];
		frontView[2] = backView[0];
		backView[0] = backView[1];
		backView[1] = temp;
	}

	public void rotateViewUpRight() {
		char[][] temp = frontView[0];
		frontView[0] = frontView[1];
		frontView[1] = backView[0];
		backView[0] = backView[2];
		backView[2] = temp;
	}

	public void rotateViewDownLeft() {
		this.rotateViewUpRight();
		this.rotateViewUpRight();
		this.rotateViewUpRight();
	}

	public void rotateViewDownRight() {
		this.rotateViewUpLeft();
		this.rotateViewUpLeft();
		this.rotateViewUpLeft();

	}

	/**
	 * Allows user to look at back side of cube.
	 */
	public void flipView() {
		this.rotateViewDownRight();
		this.rotateViewDownRight();
		this.rotateViewRight();
	}

	public void printFront() {
		this.printCube();
	}

	public void printBack() {
		this.flipView();
		this.printCube();
		this.flipView();
	}

	public void rotateLayer(char rowOrCol, char direction, int index) {
		char [] temp = new char[3];

		switch (rowOrCol) {

		// move row
		case 'r':
			switch (direction) {

			// move row left
			case 'r':
				rotateLayer('r','l',index);
				rotateLayer('r','l',index);
				rotateLayer('r','l',index);
				break;

				// move row right
			case 'l':
				if (index == 0 || index == 2) {
					char tempChar = ' ';
					if (index == 0) {

						// edges
						tempChar = frontView[0][0][1];
						frontView[0][0][1] = frontView[0][1][0];
						frontView[0][1][0] = frontView[0][2][1];
						frontView[0][2][1] = frontView[0][1][2];
						frontView[0][1][2] = tempChar;

						// corners
						tempChar = frontView[0][0][0];
						frontView[0][0][0] = frontView[0][2][0];
						frontView[0][2][0] = frontView[0][2][2];
						frontView[0][2][2] = frontView[0][0][2];
						frontView[0][0][2] = tempChar;

					}
					else {

						// edges
						tempChar = backView[0][0][1];
						backView[0][0][1] = backView[0][1][2];
						backView[0][1][2] = backView[0][2][1];
						backView[0][2][1] = backView[0][1][0];
						backView[0][1][0] = tempChar;

						// corners
						tempChar = backView[0][0][0];
						backView[0][0][0] = backView[0][0][2];
						backView[0][0][2] = backView[0][2][2];
						backView[0][2][2] = backView[0][2][0];
						backView[0][2][0] = tempChar;

					}
				}
				for (int i = 0; i < 3; i++)
					temp[i] = frontView[1][index][i];
				for (int i = 0; i < 3; i++)
					frontView[1][index][i] = frontView[2][index][i];
				for (int i = 0; i < 3; i++)
					frontView[2][index][i] = backView[2][2 - index][i];
				for (int i = 0; i < 3; i++)
					backView[2][2 - index][i] = backView[1][2 - index][i];
				for (int i = 0; i < 3; i++)
					backView[1][2 - index][i] = temp[i];
				break;
			}
			break;

			// move column
		case 'p':
			switch (direction) {
			case 'r':
				rotateLayer('p','l',index);
				rotateLayer('p','l',index);
				rotateLayer('p','l',index);
				break;
			case 'l':

				// rotate face
				if (index == 0 || index == 2) {
					char tempChar = ' ';
					if (index == 0) {

						// edges
						tempChar = frontView[1][0][1];
						frontView[1][0][1] = frontView[1][1][2];
						frontView[1][1][2] = frontView[1][2][1];
						frontView[1][2][1] = frontView[1][1][0];
						frontView[1][1][0] = tempChar;

						// corners
						tempChar = frontView[1][0][0];
						frontView[1][0][0] = frontView[1][0][2];
						frontView[1][0][2] = frontView[1][2][2];
						frontView[1][2][2] = frontView[1][2][0];
						frontView[1][2][0] = tempChar;

					}
					else {

						// edges
						tempChar = backView[2][0][1];
						backView[2][0][1] = backView[2][1][0];
						backView[2][1][0] = backView[2][2][1];
						backView[2][2][1] = backView[2][1][2];
						backView[2][1][2] = tempChar;

						// corners
						tempChar = backView[2][0][0];
						backView[2][0][0] = backView[2][2][0];
						backView[2][2][0] = backView[2][2][2];
						backView[2][2][2] = backView[2][0][2];
						backView[2][0][2] = tempChar;

					}
				}

				for (int i = 0; i < 3; i++)
					temp[i] = frontView[0][2 - index][i];
				for (int i = 0; i < 3; i++)
					frontView[0][2 - index][i] = frontView[2][i][index];
				for (int i = 0; i < 3; i++)
					frontView[2][i][index] = backView[0][i][index];
				for (int i = 0; i < 3; i++) 
					backView[0][i][index] = backView[1][i][index];
				for (int i = 0; i < 3; i++) 
					backView[1][i][index] = temp[i];
				break;
			}
			break;
		case 'c':
			switch (direction) {

			// move column up
			case 'd':
				if (index == 0 || index == 2) {
					char tempChar = ' ';
					if (index == 0) {

						//edges
						tempChar = backView[1][0][1];
						backView[1][0][1] = backView[1][1][0];
						backView[1][1][0] = backView[1][2][1];
						backView[1][2][1] = backView[1][1][2];
						backView[1][1][2] = tempChar;

						//corners
						tempChar = backView[1][0][0];
						backView[1][0][0] = backView[1][2][0];
						backView[1][2][0] = backView[1][2][2];
						backView[1][2][2] = backView[1][0][2];
						backView[1][0][2] = tempChar;
					}
					else {

						//edges
						tempChar = frontView[2][0][1];
						frontView[2][0][1] = frontView[2][1][2];
						frontView[2][1][2] = frontView[2][2][1];
						frontView[2][2][1] = frontView[2][1][0];
						frontView[2][1][0] = tempChar;

						//corners
						tempChar = frontView[2][0][0];
						frontView[2][0][0] = frontView[2][0][2];
						frontView[2][0][2] = frontView[2][2][2];
						frontView[2][2][2] = frontView[2][2][0];
						frontView[2][2][0] = tempChar;
					}
				}

				for (int i = 0; i < 3; i++)
					temp[i] = frontView[1][i][index];
				for (int i = 0; i < 3; i++)
					frontView[1][i][index] = frontView[0][i][index];
				for (int i = 0; i < 3; i++)
					frontView[0][i][index] = backView[2][i][index];
				for (int i = 0; i < 3; i++)
					backView[2][i][index] = backView[0][2 - index][i];
				for (int i = 0; i < 3; i++)
					backView[0][2 - index][i] = temp[i];
				break;

				// move column down
			case 'u':
				rotateLayer('c','d',index);
				rotateLayer('c','d',index);
				rotateLayer('c','d',index);
				break;
			}
			break;
		}
	}

	/**
	 * Prints out Rubik's cube based on front view
	 */
	public void printCube() {
		//line 1        W    
		//line 2      W   W
		//line 3    W   W   W
		//line 4   R  W   W  B
		//line 5   R R  W  B B
		//line 6   R R R B B B
		//line 7     R R B B
		//line 8       R B
		String front1 = "     "+frontView[0][0][0]+"     ";
		String front2 = "   "+frontView[0][1][0]+"   "+frontView[0][0][1]+"   ";
		String front3 = " "+frontView[0][2][0]+"   "+frontView[0][1][1]+"   "+frontView[0][0][2]+" ";
		String front4 = frontView[1][0][0]+"  "+frontView[0][2][1]+"   "+frontView[0][1][2]+"  "+frontView[2][0][2];
		String front5 = frontView[1][1][0]+" "+frontView[1][0][1]+"  "+frontView[0][2][2]+"  "+frontView[2][0][1]+" "+frontView[2][1][2];
		String front6 = frontView[1][2][0]+" "+frontView[1][1][1]+" "+frontView[1][0][2]+" "+frontView[2][0][0]+" "+frontView[2][1][1]+" "+frontView[2][2][2];
		String front7 = "  "+frontView[1][2][1]+" "+frontView[1][1][2]+" "+frontView[2][1][0]+" "+frontView[2][2][1]+"  ";
		String front8 = "    "+frontView[1][2][2]+" "+frontView[2][2][0]+"    ";

		String back8 = "     "+backView[0][0][0];
		String back7 = "   "+backView[0][1][0]+"   "+backView[0][0][1];
		String back6 = " "+backView[0][2][0]+"   "+backView[0][1][1]+"   "+backView[0][0][2];
		String back5 = backView[1][0][0]+"  "+backView[0][2][1]+"   "+backView[0][1][2]+"  "+backView[2][0][2];
		String back4 = backView[1][1][0]+" "+backView[1][0][1]+"  "+backView[0][2][2]+"  "+backView[2][0][1]+" "+backView[2][1][2];
		String back3 = backView[1][2][0]+" "+backView[1][1][1]+" "+backView[1][0][2]+" "+backView[2][0][0]+" "+backView[2][1][1]+" "+backView[2][2][2];
		String back2 = "  "+backView[1][2][1]+" "+backView[1][1][2]+" "+backView[2][1][0]+" "+backView[2][2][1];
		String back1 = "    "+backView[1][2][2]+" "+backView[2][2][0];
		System.out.println();
		System.out.println("    Front        Bottom\n");
		System.out.println(front1 +"    "+back1);
		System.out.println(front2 +"    "+back2);
		System.out.println(front3 +"    "+back3);
		System.out.println(front4 +"    "+back4);
		System.out.println(front5 +"    "+back5);
		System.out.println(front6 +"    "+back6);
		System.out.println(front7 +"    "+back7);
		System.out.println(front8 +"    "+back8);
	}

	public void orientCube() {
		
		// get white on top
		if (frontView[0][1][1] != 'W') {
			int[] whiteSide = new int[2];

			// check if it is in front view already
			for (int i = 1; i < 3; i++) {
				if (frontView[i][1][1] == 'W') whiteSide[1] = i;
			}

			// it is on the back
			if (whiteSide[1] == 0) {
				whiteSide[0] = 1;
				for (int i = 0; i < 3; i++) {
					if (backView[i][1][1] == 'W') whiteSide[1] = i;
				}
			}

			switch (whiteSide[0]) {

			// front
			case 0:
				switch (whiteSide[1]) {
				case 1: this.rotateViewUpRight(); break;
				case 2: this.rotateViewUpLeft(); break;
				}
				break;

			// back
			case 1: 
				switch (whiteSide[1]) {
				case 0: this.flipView(); break;
				case 1: this.rotateViewDownRight(); break;
				case 2: this.rotateViewDownLeft(); break;
				}
				break;
			}
		}
		
		// fix sides
		if (frontView[1][1][1] != 'R') {
			while (frontView[1][1][1] != 'R') this.rotateViewRight();
		}
	}

	public void solve() {
		this.orientCube();
		this.solveFirstLayer();
		this.solveSecondLayer();
		this.solveThirdLayer();
	}

	public void solveFirstLayer() {
		solveWhiteCross();
		solveWhiteCorners();
	}

	public void solveSecondLayer() {

	}

	public void solveThirdLayer() {

	}

	public void solveWhiteCross() {
		whiteEdgePiece(this.blue);
		whiteEdgePiece(this.red);
		whiteEdgePiece(this.green);
		whiteEdgePiece(this.orange);
	}

	public void whiteEdgePiece(char[][] side) {
		char color = side[1][1];
		int[] index = findEdge('W',color);
	}

	public int[] findEdge(char colorMid, char colorEdge) {
		// there are 12 edges
		// blue is opposite green
		// red is opposite orange
		// [0][][] - [][][] white - 
		// [0][][] - [][][] white - 
		// [0][][] - [][][] white - 
		// [0][][] - [][][] white -  
		// [3][][] - [][][]
		// [3][][] - [][][]
		// [3][][] - [][][]
		// [3][][] - [][][]
		// [1][][] - [][][] red - 
		// [1][][] - [][][] red - 
		// [][][] - [][][]
		// [][][] - [][][]
		return null;
	}

	public int[] findCorner(char colorMid, char colorEdge1, char colorEdge2) {
		return null;
	}

	public void solveWhiteCorners() {

	}
}