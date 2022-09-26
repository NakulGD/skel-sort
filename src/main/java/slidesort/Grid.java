package slidesort;

import java.util.*;

public class Grid {
    private int[][] _grid;
    private HashMap<Integer, Integer> ValPositions = new HashMap<Integer, Integer>();
    private Set<Integer> nonzero = new HashSet<>();

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

    public int getRow() {
        int RowN = _grid.length;
        return RowN;
    }

    public int getCol() {
        int ColN = _grid[0].length;
        return ColN;
    }

    public int getPosition(int currentRow, int currentCol) {
        int j = currentCol;
        int i = currentRow;
        int n = getCol();
        int m = getRow();
        int position = (n * i) + j;
        return position;
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
        int rowN = _grid.length;
        int colN = _grid[0].length;
        int c = Math.min(rowN, colN);

        for (int m = 0; m < rowN; m++) {
            for (int n = 0; n < colN; n++) {
                if (1 <= _grid[m][n] && _grid[m][n] <= c) {
                    if (nonzero.add(_grid[m][n])) {
                        int a = getPosition(m, n);
                        ValPositions.put(a, _grid[m][n]);
                    } else {
                        return false;
                    }
                }
                if (_grid[m][n] > c) {
                    return false;
                }
            }
        }
        return true; // TODO: change this
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
        int position1 = -1;
        TreeMap<Integer, Integer> ascending = new TreeMap<>();
        ascending.putAll(ValPositions);
        for (Map.Entry<Integer, Integer> entry : ValPositions.entrySet()) {
            int position2 = entry.getValue();
            if (position2 < position1) {
                return false;
            }
            position1 = position2;
        }
        return true;
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
        List MovesToCheck = moveList;
        Set<Position> finalPosition = new HashSet<>();
        int[][] clone = new int[_grid.length][_grid[0].length];
        for (int i = 0; i < _grid.length; i++) {
            for (int j = 0; j < _grid[0].length; j++) {
                clone[i][j] = _grid[i][j];
            }
        }


        for (int i = 0; i < MovesToCheck.size(); i++) {
            Position start = moveList.get(i).startingPosition;
            int x_start = start.i;
            int y_start = start.j;
            int x_end;
            int y_end;
            boolean row = moveList.get(i).rowMove;
            int displacement = moveList.get(i).displacement;
            int initialVal = clone[x_start][y_start];


            if (initialVal == 0) {
                return false;
            }
            if (row) {
                Position end = new Position(x_start, y_start + displacement);
                x_end = end.i;
                y_end = end.j;

                if (y_end > getCol() - 1 || y_end < 0 || (clone[x_end][y_end] != 0 && y_end != y_start)) {
                    return false;
                }

                if (displacement > 0) {
                    for (int l = y_start + 1; l < y_end; l++) {
                        if (clone[x_end][l] != 0) {
                            return false;
                        }

                    }
                } else if (displacement < 0) {
                    for (int l = y_start - 1; l > y_end; l--) {
                        if (clone[x_end][l] != 0) {
                            return false;
                        }
                    }
                }

            } else {
                Position end = new Position(x_start + displacement, y_start);
                x_end = end.i;
                y_end = end.j;

                if (x_end > getRow() - 1 || x_end < 0 || clone[x_end][y_end] != 0) {
                    return false;
                }

                if (displacement > 0) {
                    for (int l = x_start + 1; l < x_end; l++) {
                        if (clone[l][y_end] != 0) {
                            return false;
                        }

                    }
                } else if (displacement < 0) {
                    for (int l = x_start - 1; l > x_end; l--) {
                        if (clone[l][y_end] != 0) {
                            return false;
                        }
                    }
                }

            }

            if (finalPosition.add(new Position(x_end, y_end)) != true) {
                return false;
            }

            clone[x_start][y_start] = 0;
            clone[x_end][y_end] = initialVal;
        }
        return true; // TODO: change this
    }

    /**
     * Apply the moves in moveList to this grid
     * @param moveList is a valid list of moves
     */
    public void applyMoves(List<Move> moveList) {
        List MovesToImplement = moveList;
        for (int i = 0; i < MovesToImplement.size(); i++) {
            Position initial = moveList.get(i).startingPosition;
            int i_start = initial.i;
            int j_start = initial.j;
            ;
            int disp = moveList.get(i).displacement;
            int initialVal = _grid[i_start][j_start];
            boolean row = moveList.get(i).rowMove;
            int i_end;
            int j_end;

            if (row) {
                i_end = i_start;
                j_end = j_start + disp;

                _grid[i_start][j_start] = 0;
                _grid[i_end][j_end] = initialVal;
            } else {
                i_end = i_start + disp;
                j_end = j_start;

                _grid[i_start][j_start] = 0;
                _grid[i_end][j_end] = initialVal;
            }
        }
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
