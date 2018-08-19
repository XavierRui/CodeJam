import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;

 class Qual2013B
 { 
	
	public static void main(String args[])
 	{ 
		try (BufferedReader br = new BufferedReader(new FileReader("src/B-large-practice.in"))) {
			//try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
					
				int numTests = Integer.parseInt(br.readLine());
				int N;
				int M;
				int [][] grid;
				String line;
				String lineArray [];
				boolean finished = false;
				int [] colsMax;
				int [] rowsMax;
				
				for (int i = 0; i<numTests; i++) {
					
					finished = false;
					System.out.format("Case #%d: ", i + 1);
					line = br.readLine();
					N = Integer.parseInt(line.split(" ")[0]);
					M = Integer.parseInt(line.split(" ")[1]);
					grid = new int [N][M];
					
					// Set all cols and rows to 0
					colsMax = new int [M];
					for (int j=0; j<M; j++) {
						colsMax[j] = 0;
					}
					rowsMax = new int [N];
					for (int j=0; j<N; j++) {
						rowsMax[j] = 0;
					}
					
					
					// Fill rows and cols max and fill grid
					for (int j=0; j<N; j++) {
						line = br.readLine();
						lineArray = line.split(" ");
						for (int k =0; k<M; k++) {
							grid[j][k] = Integer.parseInt(lineArray[k]);
							
							if (grid[j][k] > colsMax[k]) {
								colsMax[k] = grid[j][k];
							}
							if (grid[j][k] > rowsMax[j]) {
								rowsMax[j] = grid[j][k];
							}
						}
					}
					
					// Check equal to either row or column
					for (int j=0; j<N; j++) {
						for (int k =0; k<M; k++) {
							if (finished) {
								break;
							}
							
							if (grid[j][k] != colsMax[k] && grid[j][k] != rowsMax[j]) {
								System.out.print("NO\n");
								finished = true;
							}
						}
					}
					
					if (!finished) {
						System.out.print("YES\n");
					}			
				}
			
			/*} catch (Exception e) {
				e.printStackTrace();
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

 	}
 }