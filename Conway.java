import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Conway {

  public static void main(String[] args) {
    TransitionRule rule = new TransitionRule(getSubRules());
    List<Rule> rules = new ArrayList<Rule>();
    rules.add(rule);
    TransitionStrategy strategy = new TransitionStrategy(rules);
    Cordinate gridSize = captureGridSize();
    Colony conwayGrid = new Colony(gridSize.getXCordinate(), gridSize.getYCordinate(), strategy);
    for(Cordinate cordinate : captureLiveCellsPosition()) {
      conwayGrid.resurrectAtPosition(cordinate.getXCordinate(), cordinate.getYCordinate());
    }
    letUserStepThrough(conwayGrid);
  }

  private static Cordinate captureGridSize() {
    System.out.println("Enter grid size. (e.g. 4 5)");
    Scanner in = new Scanner(System.in);
    return new Cordinate(in.nextLine());
  }

  private static void letUserStepThrough(Colony conwayGrid) {
    conwayGrid.display();
    System.out.println("Enter 'exit' to exit, anything else to step forward.");
    Scanner in = new Scanner(System.in);
    String input = "";
    while(true) {
      input = in.nextLine(); 
      if("exit".equals(input.toLowerCase()))
        break;
      conwayGrid.step();
      conwayGrid.display();
    }
  }

  public static List<TransitionRule.CountBasedTransitions> getSubRules() {
    List<TransitionRule.CountBasedTransitions> subRules = new ArrayList<TransitionRule.CountBasedTransitions>();
    subRules.add(new OvercrowdingTransition());
    subRules.add(new ReproductionTransition());
    subRules.add(new UnderpopulationTransition());
    return subRules;
  }

  private static List<Cordinate> captureLiveCellsPosition() {
    System.out.println("Enter xCordinate<space>yCordinate. (e.g. 2 3) [exit to quit]");
    Scanner in = new Scanner(System.in);
    String input = null;
    List<Cordinate> cordinates = new ArrayList<Cordinate>();
    while(true) {
      input = in.nextLine();
      if("exit".equals(input.toLowerCase()))
        break;
      cordinates.add(new Cordinate(input));
    }
    return cordinates;
  }
}

class Cordinate {
  private int xCordinate, yCordinate;
  
  public Cordinate(int xCord, int yCord) {
    this.xCordinate = xCord;
    this.yCordinate = yCord;
  }

  public Cordinate(String spaceSeparatedCordinates) {
    String[] cordinates = spaceSeparatedCordinates.split(" ");
    this.xCordinate = Integer.parseInt(cordinates[0]);
    this.yCordinate = Integer.parseInt(cordinates[1]);
  }

  public int getXCordinate() {
    return this.xCordinate;
  }

  public int getYCordinate() {
    return this.yCordinate;
  }
}
