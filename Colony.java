import java.util.List;
import java.util.ArrayList;

public class Colony {
  
  private int gridWidth;
  private int gridHeight;

  private TransitionStrategy transitionStrategy;
  private GridPrintStrategy printStrategy;

  private Cell[][] grid;
  private List<Cell> cells;

  public Colony(int gridWidth, int gridHeight, TransitionStrategy transitionStrategy) {
    this.gridWidth  = gridWidth;
    this.gridHeight = gridHeight;
    this.transitionStrategy = transitionStrategy;
    this.printStrategy = new GridPrintStrategy(this);
    this.cells = new ArrayList<Cell>();
    grid = new Cell[gridHeight][gridWidth];
    this.allocateCells();
  }

  public void step() {
    transitionStrategy.nextGeneration(this);
  }

  public List<Cell> getCells() {
    return cells;
  }

  public void display() {
    printStrategy.print();
  }

  public int width() {
    return this.gridWidth;
  }

  public int height() {
    return this.gridHeight;
  }

  public boolean isLivePosition(int xCord, int yCord) {
    return grid[xCord][yCord].isAlive() ? true : false;
  }

  public void resurrectAtPosition(int xCord, int yCord) {
    grid[xCord][yCord].initiateResurrection().commitTransition();
  }

  private void allocateCells() {
    for(int rowNum=0; rowNum<gridHeight; rowNum++) {
      fillRowsWithCells(rowNum);
    }
  }

  private void fillRowsWithCells(int rowNum) {
    Cell newCell;
    for(int columnNum = 0; columnNum < gridWidth; columnNum++) {
      newCell = new Cell();
      cells.add(newCell);
      grid[rowNum][columnNum] = newCell;
      associateNeighboursFor(newCell, rowNum, columnNum);
    }
  }

  private void associateNeighboursFor(Cell cell, int rowNum, int columnNum) {
    associateCells(cell, rowNum-1, columnNum+1);
    associateCells(cell, rowNum-1, columnNum);
    associateCells(cell, rowNum-1, columnNum-1);
    associateCells(cell, rowNum, columnNum-1);
  }

  private void associateCells(Cell cell, int xCord, int yCord) {
    if(xCord > -1 && yCord > -1 && xCord < gridHeight && yCord < gridWidth) {
      Cell currentCell = grid[xCord][yCord];
      currentCell.addNeighbour(cell);
      cell.addNeighbour(currentCell);
    }
  }
}
