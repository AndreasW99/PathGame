import java.io.FileNotFoundException;
import java.io.IOException;

public class StartSearch {

	private Map desertMap;

	public StartSearch (String filename) {

		try {

			desertMap = new Map(filename);

		} catch (InvalidMapException e) {

			System.out.println(e);

		} catch (FileNotFoundException e) {

			System.out.println(e);

		} catch (IOException e) {

			System.out.println(e);

		} //end try/catch

	} // end StartSearch (constructor)

	private MapCell bestCell(MapCell cell) {

		MapCell neighbour;

		/**
		 * First check to see if one of the gems is adjacent to us.
		 */
		for (int i = 0; i < 4; i++) {

			try {

				neighbour = cell.getNeighbour(i);

				if (neighbour != null && !neighbour.isMarked()) {

					if (neighbour.isGem()) {
						
						if (cell.isGem()) {
							
							return neighbour;						
							
						} //end if

						if (cell.isCrossPath()) {

							return neighbour;

						} // end if

						else if (cell.isVerticalPath() && (i == 0 || i == 2)) {

							return neighbour;

						} // end else if

						else if (cell.isHorizontalPath() && (i == 1 || i == 3)) {

							return neighbour;

						} // end else if

						else if (cell.isStart()) {

							return neighbour;

						} // end else if

					} // end if

				} // end if

			} catch (InvalidNeighbourIndexException e) {

				System.out.println(e);

			} // end catch

		} // end for

		/**
		 * Next check if a cross path is adjacent to us.
		 */
		for (int i = 0; i < 4; i++) {

			try {

				neighbour = cell.getNeighbour(i);

				if (neighbour != null && !neighbour.isMarked()) {

					if (neighbour.isCrossPath()) {
						
						if (cell.isGem()) {
							
							return neighbour;						
							
						} //end if

						if (cell.isCrossPath()) {

							return neighbour;

						} // end if

						else if (cell.isVerticalPath() && (i == 0 || i == 2)) {

							return neighbour;

						} // end else if

						else if (cell.isHorizontalPath() && (i == 1 || i == 3)) {

							return neighbour;

						} // end else if

						else if (cell.isStart()) {

							return neighbour;

						} // end else if

					} // end if

				} // end if

			} catch (InvalidNeighbourIndexException e) {

				System.out.println(e);

			} // end catch

		} // end for

		/**
		 * Finally check if there is a horizontal or vertical path adjacent to us.
		 */
		for (int i = 0; i < 4; i++) {

			try {

				neighbour = cell.getNeighbour(i);

				if (neighbour != null && !neighbour.isMarked()) {

					if ((i == 0 || i == 2) && neighbour.isVerticalPath()) {

						if (cell.isGem() || cell.isCrossPath() || cell.isVerticalPath() || cell.isStart()) {

							return neighbour;

						} // end if

					} // end if

					else if ((i == 1 || i == 3) && neighbour.isHorizontalPath()) {

						if (cell.isGem() || cell.isCrossPath() || cell.isHorizontalPath() || cell.isStart()) {

							return neighbour;

						} // end if

					} // end else if

				} // end if

			} catch (InvalidNeighbourIndexException e) {

				System.out.println(e);

			} // end catch

		} // end for

		return null;

	} // end bestCell

	public Map getDesertMap() {

		return desertMap;

	} // end getDesertMap

	public static void main(String[] args) {

		StartSearch search = new StartSearch(args[0]);
		ArrayStack<MapCell> stack = new ArrayStack<>();
		
		MapCell start = search.getDesertMap().getStart();
		
		int foundGems = 0;
		
		stack.push(start);
		start.markInitial();

		/**
		 * Keep looping while there are more paths to check and we still can hold more gems.
		 */
		while (!stack.isEmpty() && foundGems < search.getDesertMap().bagSize()) {

			MapCell current = stack.peek();
			MapCell next;
			
			next = search.bestCell(current);

			if (next != null) {
				
				/**
				 * We found a gem.
				 */
				if (next.isGem()) {
					
					System.out.println("Found a gem and put it in Bryan's bag");
					foundGems++;
					
				} // end if

				stack.push(next);
				next.markInStack();

			} // end if

			else {

				stack.pop().markOutStack();

			} // end else
			
		} // end while
		
		while (!stack.isEmpty()) {
			
			stack.pop().markOutStack();
			
		} //end while
		
		if (foundGems == 1) {
			
			System.out.println("Search completed. Found " + foundGems + " gem.");
			
		} //end if 
		
		else {
			
			System.out.println("Search completed. Found " + foundGems + " gems.");
			
		} //end else

	} // end main

} // end StartSearch (class)