package factory;

public class NJSlotComponentFactory implements SlotComponentFactory {

    public String type;

    public NJSlotComponentFactory(String s) {
        type = s;
    }

    public Cabinet createCabinet() {
        if (type.equals("progressive")) {
            return new smallCabinet();
        } else if (type.equals("straight")) {
            return new smallCabinet();
        } else if (type.equals("bonus")) {
            return new largeCabinet();
        }
        return null;
    }

    public Display createDisplay() {
        if (type.equals("progressive")) {
            return new CRT();
        } else if (type.equals("straight")) {
            return new LCD();
        } else if (type.equals("bonus")) {
            return new Reels();
        }
        return null;
    }

    public GPU createGPU() {
        if (type.equals("progressive")) {
            return new X86();
        } else if (type.equals("straight")) {
            return new ARM();
        } else if (type.equals("bonus")) {
            return new ARM();
        }
        return null;
    }

    public OS createOS() {
        if (type.equals("progressive")) {
            return new WindowXP();
        } else if (type.equals("straight")) {
            return new WindowME();
        } else if (type.equals("bonus")) {
            return new WindowME();
        }
        return null;
    }

    public Payment createPayment() {
        if (type.equals("progressive")) {
            return new Bill();
        } else if (type.equals("straight")) {
            return new Coin();
        } else if (type.equals("bonus")) {
            return new Coin();
        }
        return null;
    }
}
