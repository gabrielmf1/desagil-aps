package br.pro.hashi.ensino.desagil.aps.view;
import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener {
    private final Gate gate;
    private final JCheckBox result;
    private final JCheckBox[] inputs;
    private final Switch[] switches;
    private final Image image;

    private Color color;

    public GateView(Gate gate) {
        super(245, 346);

        this.gate = gate;

        int inputSize = gate.getInputSize();
        switches = new Switch[inputSize];
        this.inputs = new JCheckBox[inputSize];

        for (int i=0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputs[i] = new JCheckBox();
            gate.connect(i, switches[i]);
        }

        result = new JCheckBox();

        int margemEsq, margemCima;

        margemEsq = 5;
        margemCima = 25;

        JLabel inputsLabel = new JLabel("Entrada");
        add(inputsLabel, margemEsq, 10, 200, 15);

        for (int i=0; i < inputSize; i++) {
            add(inputs[i], margemEsq, margemCima + 20 * i, 200, 15);
            inputs[i].addItemListener(this);
        }

        JLabel saidaLabel = new JLabel("Saida");
        add(saidaLabel, margemEsq, margemCima + 20 * inputSize, 200, 15);
        add(result, margemEsq, margemCima + 33 * inputSize, 200, 15);

        color = Color.GREEN;

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        result.setEnabled(false);

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputs[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        boolean result = this.gate.read();
        this.result.setSelected(result);
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 10, 80, 221, 221, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        g.fillRect(210, 311, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        update();
    }
}