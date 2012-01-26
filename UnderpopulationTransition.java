public class UnderpopulationTransition extends TransitionRule.CountBasedTransitions {
    
  public void inferFromCount(Cell cell, int aliveNeighbourCount) {
    if (aliveNeighbourCount < 2 && cell.isAlive())
      cell.initiateKill();
  }
}
