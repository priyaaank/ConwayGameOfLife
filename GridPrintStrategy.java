public class GridPrintStrategy {
  
  private Colony grid;

  public GridPrintStrategy(Colony grid) {
    this.grid = grid;
  }

  public void print() {
    int width  = grid.width();
    int height = grid.height();
    StringBuffer currentRow = null; 
    printLines(20);
    for (int row=0; row < height; row++) {
      currentRow = new StringBuffer("|");
      for (int col=0; col < width; col++) {
        currentRow.append((grid.isLivePosition(row,col) ? " X" : " ") + " |");
      }
      printString(currentRow.toString());
    }
    printLines(20);
  }

  private void printString(String string) {
    System.out.println(string);
  }

  private void printLines(int times) {
    for(int i=0; i<times; i++) {
      System.out.print("_");
    }
    System.out.println("");
  }

}
