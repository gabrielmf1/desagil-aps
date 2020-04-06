package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nandC;
    private final NandGate nandD;


    public XorGate() {
        super("XOR", 2);

        nandA = new NandGate();

        nandB = new NandGate();
        nandB.connect(1, nandA);

        nandC = new NandGate();
        nandC.connect(0, nandA);

        nandD = new NandGate();
        nandD.connect(0, nandB);
        nandD.connect(1, nandC);
    }


    @Override
    public boolean read() {
        return nandD.read();
    }


    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        } else if (inputIndex == 0) {
            nandA.connect(0, emitter);
            nandB.connect(0, emitter);
        } else {
            nandA.connect(1, emitter);
            nandC.connect(1, emitter);
        }
    }
}
