import java.util.List;

public class TransitionRule implements Rule {

  private List<TransitionRule.CountBasedTransitions> subTransitions;

  public TransitionRule(List<TransitionRule.CountBasedTransitions> transitions) {
    super();
    this.subTransitions = transitions;
  } 


  public void apply(Cell evaluatedCell) {
    int liveNeighbourCount = 0;
    for(Cell neighbourCell : evaluatedCell.neighbours()) {
      if(neighbourCell.isAlive())
        liveNeighbourCount++;
    }
    
    loopOnPossibleTransitions(evaluatedCell, liveNeighbourCount);
  }

  private void loopOnPossibleTransitions(Cell evaluatedCell, int liveNeighbourCount) {
    for (TransitionRule.CountBasedTransitions transition : subTransitions) {
      transition.inferFromCount(evaluatedCell, liveNeighbourCount);
    }
  }

  public static abstract class CountBasedTransitions {
      public abstract void inferFromCount(Cell evaluatedCell, int neighbourCount);
  }

}
