package factory;

public interface SlotComponentFactory {

    public Cabinet createCabinet();

    public Display createDisplay();
    
    public Payment createPayment();
    
    public GPU createGPU();
    
    public OS createOS();
//	public GPU();
//	public CPU();
}
