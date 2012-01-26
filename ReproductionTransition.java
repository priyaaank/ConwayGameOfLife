public class ReproductionTransition extends TransitionRule.CountBasedTransitions {

  public void inferFromCount(Cell cell, int aliveNeighbourCount) {
    if (aliveNeighbourCount == 3 && cell.isDead())
      cell.initiateResurrection();
  }
}
