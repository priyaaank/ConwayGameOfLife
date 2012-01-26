import java.util.List;
import java.util.ArrayList;

public class Conway {

  public static void main(String[] args) {
    TransitionRule rule = new TransitionRule(getSubRules());
    List<Rule> rules = new ArrayList<Rule>();
    rules.add(rule);
    TransitionStrategy strategy = new TransitionStrategy(rules);
    Colony conwayGrid = new Colony(3,3, strategy);
  }

  public static List<TransitionRule.CountBasedTransitions> getSubRules() {
    List<TransitionRule.CountBasedTransitions> subRules = new ArrayList<TransitionRule.CountBasedTransitions>();
    subRules.add(new OvercrowdingTransition());
    subRules.add(new ReproductionTransition());
    subRules.add(new UnderpopulationTransition());
    return subRules;
  }
}
