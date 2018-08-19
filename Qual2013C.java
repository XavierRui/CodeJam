import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;

 class Qual2013C
 { 
	
	public static void main(String args[])
 	{ 
		try (BufferedReader br = new BufferedReader(new FileReader("src/C-large-practice-1.in"))) {
			try (PrintWriter pw = new PrintWriter(new FileWriter("src/output.txt"))) {
					
				long numTests = Integer.parseInt(br.readLine());
				long lo, hi, tally, sqrt, num;
				String line;
				long top = 100000000000000L;
				ArrayList<Long> fairSquares = new ArrayList<>();
				int index;
				int tempLo, tempHi;
				boolean lowestFound;
				
				// Generate all fair and squares in range between 1 and top
				sqrt = 1;
				num = 1;
				while (num <= top) {
					num = sqrt*sqrt;
					if (num > top) {
						break;
					}
					
					if (isPal(sqrt) && isPal(num)) {
						fairSquares.add(num);
					}
					sqrt++;
				}
				
				for (long i = 0; i<numTests; i++) {
					
					System.out.format("Case #%d: ", i + 1);
					pw.format("Case #%d: ", i + 1);
					
					line = br.readLine();
					lo = Long.valueOf(line.split(" ")[0]);
					hi = Long.valueOf(line.split(" ")[1]);
					tally = 0;
					
					/*sqrt = 1;
					num = 1;
					tally = 0;
					
					while (num <= hi) {
						num = sqrt*sqrt;
						if (num >= lo) {
							if (num > hi) {
								break;
							}
							
							if (isPal(sqrt) && isPal(num)) {
								//System.out.print(sqrt+","+num+" ");
								tally++;
							}
						}
						sqrt++;
					}*/
					
					index = fairSquares.size()/2;
					lowestFound = false;
					tempHi = fairSquares.size()-1;
					tempLo = 0;
					
					while (!lowestFound) {
						if (fairSquares.get(index) == lo) {
							tally = findTally(index, fairSquares, hi);
							lowestFound = true;
						} else if (fairSquares.get(index) > lo) {
							if (fairSquares.get(index-1) < lo) {
								tally = findTally(index, fairSquares, hi);
								lowestFound = true;
							} else if (fairSquares.get(index-1) == lo) {
								tally = findTally(index-1, fairSquares, hi);
								lowestFound = true;
							} else {
								tempHi = index;
								index = (tempHi+tempLo)/2;
							}
						} else {
							if (fairSquares.get(index+1) > lo) {
								tally = findTally(index+1, fairSquares, hi);
								lowestFound = true;
							} else if (fairSquares.get(index+1) == lo) {
								tally = findTally(index+1, fairSquares, hi);
								lowestFound = true;
							} else {
								tempLo = index;
								index = (tempHi+tempLo)/2;
							}
						}
					}
					
					System.out.print(tally+"\n");
					pw.print(tally+"\n");
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

 	}
	
	public static boolean isPal(long num) {
		if (num == reverse(num)) {
			return true;
		}
		return false;
	}
	
	public static long reverse(long num) {
		String reverse = new String();
		String numString = String.valueOf(num);

		for (int i = numString.length() - 1; i >= 0; i--) {
			reverse += numString.charAt(i);
		}
		
		return Long.valueOf(reverse);
	}
	
	public static int findTally(int index, ArrayList<Long> fairSquares, long hi) {
		int tally = 0;
		for (int i = index; i<fairSquares.size() && fairSquares.get(i) <= hi; i++) {
			tally++;
		}
		
		return tally;
	}
 }