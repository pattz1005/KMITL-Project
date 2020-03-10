public class DefaultMessageService implements MessageService {

  private MessageSource source;
  private MessageDestination destination;

  public void setSource(MessageSource source) {
    this.source = source;
  }

  public void setDestination(MessageDestination destination) {
    this.destination = destination;
  }

  public void execute() {
    destination.write(source.getMessage());
  }

}
