package slidesort;

import java.util.List;

public class Grid {
    private int[][] _grid;

    /**
     * Create a new grid
     * @param seedArray is not null
     *                  and seedArray.length > 0
     *                  and seedArray[0].length > 0
     */
    public Grid(int[][] seedArray) {
        int rows = seedArray.length;
        int cols = seedArray[0].length;
        _grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                _grid[i][j] = seedArray[i][j];
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Grid) {
            Grid g2 = (Grid) other;
            if (this._grid.length != g2._grid.length) {
                return false;
            }
            if (this._grid[0].length != g2._grid[0].length) {
                return false;
            }
            int rows = _grid.length;
            int cols = _grid[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (this._grid[i][j] != g2._grid[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Check if this grid is a valid grid.
     * A grid is valid if, for c = min(rows, cols),
     * the grid contains zero or more values in [1, c]
     * exactly once and all other entries are 0s.
     *
     * @return true if this is a valid grid and false otherwise
     */
    public boolean isValid() {
        // TODO: implement this method
        return false; // TODO: change this
    }

    /**
     * Check if this grid is sorted.
     * A grid is sorted iff it is valid and,
     *  for all pairs of entries (x, y)
     *  such that x > 0 and y > 0,
     *  if x < y then the position(x) < position(y).
     * If x is at location (i, j) in the grid
     * then position(x) = i * (number of cols) + j.
     *
     * @return true if the grid is sorted and false otherwise.
     */
    public boolean isSorted() {
        // TODO: implement this method
        return false; // TODO: change this
    }

    /**
     * Check if a list of moves is feasible.
     * A move is feasible if it starts with a non-zero entry,
     * does not move that number off the grid,
     * and it does not involve jumping over another non-zero number.
     *
     * @param   moveList is not null.
     * @return  true if the list of moves are all feasible
     *          and false otherwise.
     *          By definition an empty list is always feasible.
     */
    public boolean validMoves(List<Move> moveList) {
        // TODO: implement this method
        return false; // TODO: change this
    }

    /**
     * Apply the moves in moveList to this grid
     * @param moveList is a valid list of moves
     */
    public void applyMoves(List<Move> moveList) {

    }

    /**
     * Return a list of moves that, when applied, would convert this grid
     * to be sorted
     * @return a list of moves that would sort this grid
     */
    public List<Move> getSortingMoves() {
        // TODO: implement this method
        return null; // TODO: change this
    }
}
