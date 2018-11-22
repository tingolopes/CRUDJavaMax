package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JTabbedFrame extends JFrame {

    private final JTextField jTextFieldNome;
    private final GridLayout layoutGrid = new GridLayout(1, 4);
    private final BorderLayout layoutBorder = new BorderLayout();
    private final JButton Cadastrar = new JButton("Cadastrar");
    private final JButton Editar = new JButton("Editar");
    private final JButton Excluir = new JButton("Excluir");
    private final JButton Limpar = new JButton("Limpar");

    public JTabbedFrame() {
        super("JTabbedPane");
        JTabbedPane tabbedPane = new JTabbedPane(); // Criando JTabbedPane
        jTextFieldNome = new JTextField();

        // Configurando Primeiro Painel e Add ao JTabbedPane
        JLabel labelAdmin = new JLabel("Painel Admin", SwingConstants.CENTER);
        JPanel panelAdmin = new JPanel(); // Criando Primeiro Painel
        

        panelAdmin.setLayout(layoutGrid);
        panelAdmin.add(labelAdmin, BorderLayout.NORTH); // add label to panel
        panelAdmin.add(Cadastrar);
        panelAdmin.add(Editar);
        panelAdmin.add(Excluir);
        panelAdmin.add(Limpar);
        tabbedPane.addTab("Admin", null, panelAdmin, "Painel 1");

        // Configurando Primeiro Painel e Add ao JTabbedPane
        JLabel labelCliente = new JLabel("Painel Cliente", SwingConstants.CENTER);
        JPanel panelCliente = new JPanel(); // Criando Primeiro Painel
        panelCliente.add(labelCliente); // add label to panel
        tabbedPane.addTab("Cliente", null, panelCliente, "Painel 2");

        // Configurando Segundo Painel e Add ao JTabbedPane
        JLabel labelCategoria = new JLabel("Painel Categoria", SwingConstants.CENTER);
        JPanel panelCategoria = new JPanel(); // criando Segundo Painel
        panelCategoria.add(labelCategoria); // add label to panel
        tabbedPane.addTab("Categoria", null, panelCategoria, "Painel 3");

        // Configurando Terceiro Painel e Add ao JTabbedPane
        JLabel label3 = new JLabel("Painel 3", SwingConstants.CENTER);
        JPanel panel3 = new JPanel(); // Criando Terceiro Painel
        panel3.setLayout(new BorderLayout()); // use borderlayout

        //add botões
        panel3.add(new JButton("North"), BorderLayout.NORTH);
        panel3.add(new JButton("West"), BorderLayout.WEST);
        panel3.add(new JButton("East"), BorderLayout.EAST);
        panel3.add(new JButton("South"), BorderLayout.SOUTH);
        panel3.add(new JButton("Center"), BorderLayout.CENTER);
        tabbedPane.addTab("Produto", null, panel3, "Painel 4");

        // Configurando Primeiro Painel e Add ao JTabbedPane
        JLabel labelVenda = new JLabel("Painel Venda", SwingConstants.CENTER);
        JPanel panelVenda = new JPanel(); // Criando Primeiro Painel
        panelVenda.add(labelVenda); // add label to panel
        tabbedPane.addTab("Venda", null, panelVenda, "Painel 5");

        add(tabbedPane); // add JTabbedPane to frame
    }
}
