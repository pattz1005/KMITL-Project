package factory;

public class NVSlotComponentFactory implements SlotComponentFactory {

    public String type;

    public NVSlotComponentFactory(String s) {
        type = s;
    }

    public Cabinet createCabinet() {
        if (type.equals("progressive")) {
            return new mediumCabinet();
        } else if (type.equals("straight")) {
            return new largeCabinet();
        } else if (type.equals("bonus")) {
            return new smallCabinet();
        }
        return null;
    }

    public Display createDisplay() {
        if (type.equals("progressive")) {
            return new LCD();
        } else if (type.equals("straight")) {
            return new Reels();
        } else if (type.equals("bonus")) {
            return new CRT();
        }
        return null;
    }

    public GPU createGPU() {
        if (type.equals("progressive")) {
            return new X77();
        } else if (type.equals("straight")) {
            return new ARM();
        } else if (type.equals("bonus")) {
            return new X86();
        }
        return null;
    }

    public OS createOS() {
        if (type.equals("progressive")) {
            return new Android();
        } else if (type.equals("straight")) {
            return new Linux();
        } else if (type.equals("bonus")) {
            return new Linux();
        }
        return null;
    }

    public Payment createPayment() {
        if (type.equals("progressive")) {
            return new Ticket();
        } else if (type.equals("straight")) {
            return new Ticket();
        } else if (type.equals("bonus")) {
            return new Ticket();
        }
        return null;
    }
}
