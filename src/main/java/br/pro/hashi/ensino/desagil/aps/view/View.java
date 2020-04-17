package br.pro.hashi.ensino.desagil.aps.view;
import br.pro.hashi.ensino.desagil.aps.model.Gate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class View extends JPanel implements ActionListener {
    private JComboBox<Gate> menu;
    private GateView gateView;

    public View(LinkedList<Gate> model) {
        menu = new JComboBox<>();
        for (Gate gate : model) {
            menu.addItem(gate);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(menu);
        addGateView(0);

        menu.addActionListener(this);
    }

    private void addGateView(int index) {

        Gate gate = menu.getItemAt(index);

        // 2. Construir a representação gráfica a partir dela.
        gateView = new GateView(gate);

        // 3. Adicionar essa representação gráfica no JPanel.
        add(gateView);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        // ...tirar a atual representação gráfica de calculadora...
        remove(gateView);

        // ...descobrir qual é o índice da calculadora selecionada...
        int index = menu.getSelectedIndex();

        // ...e usar o método acima para adicionar a nova.
        addGateView(index);

        // Mantenha esta linha, mas não precisa entendê-la.
        // É necessária para evitar bugs em alguns sistemas.
        ((JFrame) SwingUtilities.getRoot(this)).pack();
    }
}
