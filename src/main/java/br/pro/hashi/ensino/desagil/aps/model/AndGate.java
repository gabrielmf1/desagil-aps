package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nandA;
    private final NandGate nandB;


    public AndGate() {
        super("AND", 2);

        nandA = new NandGate();

        nandB = new NandGate();
        nandB.connect(0, nandA);
        nandB.connect(1, nandA);
    }


    @Override
    public boolean read() {
        return nandB.read();
    }


    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nandA.connect(inputIndex, emitter);
    }
}
