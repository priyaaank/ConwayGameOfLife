public class OvercrowdingTransition extends TransitionRule.CountBasedTransitions {
  
  public void inferFromCount(Cell cell, int aliveNeighbourCount) {
    if(aliveNeighbourCount > 3 && cell.isAlive())
      cell.initiateKill();
  }

}
