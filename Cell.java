import java.util.List;
import java.util.ArrayList;

public class Cell {

  public enum State {ALIVE, DEAD};
  
  private State state = State.DEAD;
  private State transientState = State.DEAD;
  private List<Cell> neighbours;

  public Cell() {
    neighbours = new ArrayList<Cell>(8);
  }

  public void setNeighbours(List<Cell> neighbours) {
    this.neighbours = neighbours;
  }

  public void addNeighbour(Cell neighbour) {
    if(neighbour != null)
      this.neighbours.add(neighbour);
  }

  public boolean isAlive() {
    return (this.state == State.ALIVE);
  }

  public boolean isDead() {
    return (this.state == State.DEAD);
  }

  public void commitTransition() {
    this.state = this.transientState;
  }
  
  public List<Cell> neighbours() {
    return this.neighbours;
  }

  public Cell initiateResurrection() {
    this.transientState = State.ALIVE;
    return this;
  }
  
  public Cell initiateKill() {
    this.transientState = State.DEAD;
    return this;
  }
}
