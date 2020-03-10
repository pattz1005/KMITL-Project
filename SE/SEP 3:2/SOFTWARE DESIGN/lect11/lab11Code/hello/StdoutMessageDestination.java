public class StdoutMessageDestination implements MessageDestination {

  public void write(String message) {
    System.out.println(message);
  }

}
