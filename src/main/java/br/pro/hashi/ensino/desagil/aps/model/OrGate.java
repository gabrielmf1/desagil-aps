package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate{
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nand;

    public OrGate() {
        super("OR", 2);

        nandA = new NandGate();
        nandB = new NandGate();
        nand = new NandGate();
        nand.connect(0, nandA);
        nand.connect(1, nandB);
    }

    @Override
    public boolean read() {
        return nandB.read();
    }

    @Override
    public void connect(int input, Emitter emitter) {
        switch (input) {
            case 0:
                nandA.connect(0, emitter);
                nandA.connect(1, emitter);
                break;
            case 1:
                nandB.connect(0, emitter);
                nandB.connect(1, emitter);
                break;
        }
    }
}
