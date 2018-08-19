import java.io.BufferedReader;
import java.io.FileReader;

 class Qual2013A
 { 
 	
	public static int checkForWin(char grid[][], int row, int col, int difference)
	 {
	 	char player;
	 	if (grid[row][col] == 'T') {
 			player = grid[row + (col + difference)/4][(col + difference)%4];
 		} else if (grid[row][col] == '.') {
 			return 0;
 		} else{
 			player = grid[row][col];
 		}
	 	
	 	if (player == '.') {
	 		return 0;
	 	}
	 	
		for (int i =1;i<4;i++) {
		 	row += (col + difference)/4;
		 	col = (col + difference) % 4;
			if (grid[row][col] != player && grid[row][col] != 'T') {
				return 0;
			}
		}
		 
		 if (player == 'X') {
 			return 1;
 		} else {
 			return 2;
 		}
	 }
	
	public static void main(String args[])
 	{ 
		try (BufferedReader br = new BufferedReader(new FileReader("src/A-large-practice.in"))) {
			
			int numTests = Integer.parseInt(br.readLine());
			char [][] grid = new char [4][4];
			boolean complete;
			
			String line;
			
			for (int i = 0; i<numTests; i++) {
				complete = true;
				for (int j=0; j<4; j++) {
					line = br.readLine();
					for (int k =0; k<4; k++) {
						grid[j][k] = line.charAt(k);
						if (grid[j][k] == '.') {
							complete = false;
						}
					}
				}
				
				//showGrid(grid);
				
				System.out.format("Case #%d: ", i + 1);
				
				// check diag right
				int winner = 0;
				
				winner = checkForWin(grid, 0, 0, 5);
				if (winner != 0) {
					if (winner == 1) {
						System.out.print("X won\n");
					} else {
						System.out.print("O won\n");
					}
				}
				
				// check diag left
				if (winner == 0 && checkForWin(grid, 0, 3, 3) != 0) {
					winner = checkForWin(grid, 0, 3, 3);
					if (winner == 1) {
						System.out.print("X won\n");
					} else {
						System.out.print("O won\n");
					}
				}
				
				// check all down
				for (int j = 0; j<4; j++) {
					if (winner == 0 && checkForWin(grid, 0, j, 4) != 0) {
						winner = checkForWin(grid, 0, j, 4);
						if (winner == 1) {
							System.out.print("X won\n");
						} else {
							System.out.print("O won\n");
						}
					}
				}
				
				// check all accross
				for (int j = 0; j<4; j++) {
					if (winner == 0 && checkForWin(grid, j, 0, 1) != 0) {
						winner = checkForWin(grid, j, 0, 1);
						if (winner == 1) {
							System.out.print("X won\n");
						} else {
							System.out.print("O won\n");
						}
					}
				}
				
				if (winner == 0) {
					if (!complete) {
						System.out.print("Game has not completed\n");
					} else {
						System.out.print("Draw\n");
					}
				}
				
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

 	}
	
	public static void showGrid(char grid[][]) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.print("\n");
		}
	}
 }