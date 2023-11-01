import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class Boggle
{
	static String[][] board;
	static long startTime,endTime; // for timing
	static final long MILLISEC_PER_SEC = 1000;
	
	static TreeSet<String> dict = new TreeSet<String>();

	static TreeSet<String> hits = new TreeSet<String>();

	public static void main( String args[] ) throws Exception
	{
		startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );

		File dictFile = new File(args[0]);
		Scanner inFile = new Scanner(dictFile);
		while (inFile.hasNext()) {
			dict.add(inFile.next());
		}
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, ""  ); // FOR EACH [R][C] THE WORD STARTS EMPTY

		for (String hit : hits) {
			System.out.println(hit);
		}
		endTime =  System.currentTimeMillis(); // for timing
		System.out.println("GENERATION COMPLETED: runtime=" + (endTime-startTime)/MILLISEC_PER_SEC );

	} // END MAIN ----------------------------------------------------------------------------
	static boolean hasPrefix(String word) {
		return !dict.subSet(word, word + Character.MAX_VALUE).isEmpty();
	}
	static void dfs( int r, int c, String word  )
	{
		word += board[r][c];

		if(word.length() >= 3 && dict.contains(word)) {
			hits.add(word);
		}
		if (word.length() >= 3 && !hasPrefix(word)) {
			return;
		}
	
		if ( r-1 >= 0   &&   board[r-1][c] != null )   
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r-1, c, word ); 
			board[r][c] = unMarked; 
		}

		if ( r-1 >= 0 && c+1 < board[r].length   &&   board[r-1][c+1] != null )
		{	String unMarked = board[r][c];
			board[r][c] = null; 
			dfs( r-1, c+1, word ); 
			board[r][c] = unMarked; 
		}
		
		if (c + 1 < board[r].length && board[r][c+1] != null ) {
			String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r, c+1, word ); 
			board[r][c] = unMarked; 
		}
		
		if ( r+1 < board[c].length && c+1 < board[r].length   &&   board[r+1][c+1] != null )
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r+1, c+1, word ); 
			board[r][c] = unMarked; 
		}
		
		if (r + 1 < board[c].length && board[r+1][c] != null ) {
			String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r+1, c, word ); 
			board[r][c] = unMarked; 
		}
		
		if ( r+1 < board[c].length && c-1 >= 0   &&   board[r+1][c-1] != null )
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r+1, c-1, word ); 
			board[r][c] = unMarked; 
		}
		
		if (c - 1 >= 0 && board[r][c-1] != null ) {
			String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r, c-1, word ); 
			board[r][c] = unMarked; 
		}
		
		if ( r-1 >= 0 && c-1 >= 0   &&   board[r-1][c-1] != null )
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r-1, c-1, word ); 
			board[r][c] = unMarked; 
		}
	} // END DFS ----------------------------------------------------------------------------

	//=======================================================================================
	static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} //END LOADBOARD

} // END BOGGLE CLASS
