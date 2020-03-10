public class WinnerState extends SoldState {

  public WinnerState(GumballMachine gumballMachine) {
    super(gumballMachine);
  }

  public void dispense() {
    System.out.println("YOU'RE A WINNER! Two gumballs for your quarter.");
    gumballMachine.releaseBall();
    if (gumballMachine.getCount() == 0) {
      gumballMachine.setState(gumballMachine.getSoldOutState());
    } else {
      gumballMachine.releaseBall();
      if (gumballMachine.getCount() > 0) {
        gumballMachine.setState(gumballMachine.getNoQuarterState());
      } else {
        System.out.println("Oops, out of gumballs!");
        gumballMachine.setState(gumballMachine.getSoldOutState());
      }
    }
  }

  public String toString() {
    return "despensing two gumballs for your quarter, because YOU'RE A WINNER!";
  }
}
