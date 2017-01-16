import java.util.LinkedList;

public class MazeTester{
	public static void main(String[] args){
		int[][] data={
				{0,1,1,1,1,1,1,1,1,1},
				{0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,0},
				{1,0,1,1,1,0,1,0,1,0},
				{1,0,0,0,0,0,1,0,0,0},
				{1,0,1,1,0,1,1,1,0,1},
				{1,0,1,0,0,0,1,1,1,1},
				{0,0,0,0,1,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,0},
		};
		/*int [][] data={
				{0,0,0},
				{0,0,0},
				{0,0,0},
		};*/
		boolean[][] mazeData = new boolean[data.length][data[0].length];
		for (int i=0; i<data.length; i++)
			for(int j=0; j<data[0].length; j++){
				if (data[i][j]==1)
					mazeData[i][j]=true;
				else
					mazeData[i][j]=false;
			}
		MazeCoord start = new MazeCoord(2,0);
		MazeCoord end = new MazeCoord(0,2);
		
		Maze ma = new Maze(mazeData, start, end);
		
		ma.search();
		
		LinkedList<MazeCoord> newPath = ma.getPath();
		
		System.out.print(newPath);
		
		
		
	}
}