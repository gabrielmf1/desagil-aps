package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate{
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nandC;
    private final NandGate nandD;

    public XorGate() {
        super("XOR", 2);

        nandA = new NandGate();

        nandC = new NandGate();
        nandC.connect(1, nandA);

        nandD = new NandGate();
        nandD.connect(0, nandA);

        nandB = new NandGate();
        nandB.connect(0, nandC);
        nandB.connect(1, nandD);
    }

    @Override
    public boolean read() {
        return nandB.read();
    }

    @Override
    public void connect(int input, Emitter emitter) {
        switch (input) {
            case 0:
                nandC.connect(0,emitter);
                nandA.connect(0,emitter);
                break;
            case 1:
                nandD.connect(1,emitter);
                nandA.connect(1,emitter);
                break;
        }
    }
}
