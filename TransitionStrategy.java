import java.util.List;

public class TransitionStrategy {
  
  private List<Rule> transitionRules;

  public TransitionStrategy(List<Rule> transitionRules) {
    this.transitionRules = transitionRules;
  }

  public void nextGeneration(Colony grid) {
    List<Cell> cells = grid.getCells();
    for(Cell currentCell : cells) {
      applyTransitionRules(currentCell);
    }

    for(Cell currentCell : cells) {
      currentCell.commitTransition();
    }
  }

  private void applyTransitionRules(Cell cell) {
    for (Rule rule : transitionRules) {
      rule.apply(cell);
    }
  }
}
