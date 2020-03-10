package factory;

public class WASlotComponentFactory implements SlotComponentFactory {

    public String type;

    public WASlotComponentFactory(String s) {
        type = s;
    }

    public Cabinet createCabinet() {
        if (type.equals("progressive")) {
            return new largeCabinet();
        } else if (type.equals("straight")) {
            return new largeCabinet();
        } else if (type.equals("bonus")) {
            return new mediumCabinet();
        }
        return null;
    }

    public Display createDisplay() {
        if (type.equals("progressive")) {
            return new Reels();
        } else if (type.equals("straight")) {
            return new Reels();
        } else if (type.equals("bonus")) {
            return new VGA();
        }
        return null;
    }

    public GPU createGPU() {
        if (type.equals("progressive")) {
            return new ARM();
        } else if (type.equals("straight")) {
            return new ARM();
        } else if (type.equals("bonus")) {
            return new ARM();
        }
        return null;
    }

    public OS createOS() {
        if (type.equals("progressive")) {
            return new Android();
        } else if (type.equals("straight")) {
            return new Linux();
        } else if (type.equals("bonus")) {
            return new Symbian();
        }
        return null;
    }

    public Payment createPayment() {
        if (type.equals("progressive")) {
            return new Coin();
        } else if (type.equals("straight")) {
            return new Bill();
        } else if (type.equals("bonus")) {
            return new Ticket();
        }
        return null;
    }
}
