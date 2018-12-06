package view;

import bean.Cliente;
import bean.ItensVenda;
import bean.Produto;
import bean.Venda;
import dao.ClienteDAO;
import dao.ItensVendaDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import tablemodel.CupomFiscalTableModel;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class frmEfetuarVenda extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmEfetuarVenda
     */
    public frmEfetuarVenda() {
        initComponents();
        this.setLocation(250, 100);
        jListClientes.setVisible(false);
        jListaProdutos.setVisible(false);
        tblCupomFiscal.setVisible(false);
        dataHoje();
        listarItensDaVenda(0);
        
        setLayout(new BorderLayout());
        add(jPanelVenda, BorderLayout.WEST);
        add(jDetalhamentoCupom);
        jDetalhamentoCupom.setLayout(new FlowLayout());
        
        jDetalhamentoCupom.setLayout(new BorderLayout());
        jDetalhamentoCupom.add(jPanelTabela, BorderLayout.NORTH);
        jDetalhamentoCupom.add(jTotal);
        jTotal.setLayout(new FlowLayout());
        
    }

    public final void dataHoje() {
        txtDataVenda.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        txtDataVenda.setDate(Calendar.getInstance().getTime());
        txtDataVenda.setEnabled(false);
    }

    public void pesquisaDinamicaCliente() {
        List<Cliente> listaCliente = new ArrayList<>();
        ClienteDAO dao = new ClienteDAO();
        listaCliente = dao.read(txtPesquisaCliente.getText());
        DefaultListModel listModel = new DefaultListModel();
        if (txtPesquisaCliente.getText().isEmpty()) {
            listModel.clear();
            jListClientes.setModel(listModel);
            jListClientes.setVisible(false);
        } else {
            jListClientes.setVisible(true);
            for (Cliente percorrer : listaCliente) {
                listModel.addElement(percorrer.getIdcliente() + " - " + percorrer.toString());
            }
            jListClientes.setModel(listModel);
        }
    }

    public void selecionaCliente() {
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();

        String a = jListClientes.getSelectedValue();
        String id[] = a.split(" - ");
        int idCliente = Integer.parseInt(id[0]);
        cliente.setIdcliente(idCliente);

        txtIdCliente.setText(String.valueOf(cliente.getIdcliente()));
        cliente.setNome(dao.read(idCliente).getNome());

        jListClientes.setVisible(false);
        txtPesquisaCliente.setEnabled(false);
        txtPesquisaCliente.setText(cliente.getNome());
    }

    public void novaVenda() {
        Venda v = new Venda();
        VendaDAO daov = new VendaDAO();
        ClienteDAO daoc = new ClienteDAO();
        v.setCliente(daoc.read(Integer.parseInt(txtIdCliente.getText())));
        v.setDatavenda(txtDataVenda.getDate());
        v.setIdcliente(v.getCliente().getIdcliente());
        int ultimaVenda = daov.create(v);
        txtNumeroVenda.setText(String.valueOf(ultimaVenda));
        txtPesquisaCliente.setEnabled(false);
        jButtonNovaVenda.setEnabled(false);
        jButtonLimpaPesquisaCliente.setEnabled(false);
    }

    public void inserirProduto() {
        ItensVenda iv = new ItensVenda();
        ItensVendaDAO dao = new ItensVendaDAO();
        ProdutoDAO daop = new ProdutoDAO();
        iv.setIdvenda(Integer.parseInt(txtNumeroVenda.getText()));
        iv.setProduto(daop.read(Integer.parseInt(txtIdProduto.getText())));
        iv.setQtdproduto(Integer.parseInt(txtQtdProd.getText()));
        dao.create(iv);
        listarItensDaVenda(iv.getIdvenda());
        habilitaPesquisaProduto();
    }

    public void listarItensDaVenda(Integer pesquisaPorId) {
        CupomFiscalTableModel modelo = new CupomFiscalTableModel();
        ItensVendaDAO dao = new ItensVendaDAO();
        modelo.setListaVenda(dao.read(String.valueOf(pesquisaPorId)));
        tblCupomFiscal.setModel(modelo);
        tblCupomFiscal.setVisible(true);
        ajustaTabela();
        
        //seta total do cupom
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("R$ #,##0.00");
        Double soma = 0.0;
        soma = dao.readSomaCupom(pesquisaPorId);
        jLabelTotalCupom.setText(String.valueOf(df.format(soma)));
    }
    
    public void ajustaTabela() {
        //seta tamanho das colunas
        tblCupomFiscal.getColumnModel().getColumn(0).setPreferredWidth(220);
        tblCupomFiscal.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblCupomFiscal.getColumnModel().getColumn(2).setPreferredWidth(130);
        tblCupomFiscal.getColumnModel().getColumn(3).setPreferredWidth(130);

        //configura centralizaçao das colunas
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblCupomFiscal.getColumnModel().getColumn(1).setCellRenderer(centralizado);
    }

    public void pesquisaDinamicaProduto() {
        List<Produto> listaProduto = new ArrayList<>();
        ProdutoDAO dao = new ProdutoDAO();
        listaProduto = dao.read(txtPesquisaProduto.getText());
        DefaultListModel listModel = new DefaultListModel();
        if (txtPesquisaProduto.getText().isEmpty()) {
            listModel.clear();
            jListaProdutos.setModel(listModel);
            jListaProdutos.setVisible(false);
        } else {
            jListaProdutos.setVisible(true);
            for (Produto percorrer : listaProduto) {
                listModel.addElement(percorrer.getIdproduto() + " - " + percorrer.toString());
            }
            jListaProdutos.setModel(listModel);
        }
    }

    public void selecionaProduto() {
        ProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();

        String a = jListaProdutos.getSelectedValue();
        String id[] = a.split(" - ");
        int idProduto = Integer.parseInt(id[0]);

        produto.setIdproduto(idProduto);
        produto.setNome(dao.read(idProduto).getNome());
        produto.setPreco(dao.read(idProduto).getPreco());

        txtIdProduto.setText(String.valueOf(produto.getIdproduto()));
        txtPesquisaProduto.setEnabled(false);
        txtPesquisaProduto.setText(produto.getNome());
        txtPrecoProduto.setText(String.valueOf(produto.getPreco()));

        jListaProdutos.setVisible(false);
    }

    public void habilitaPesquisaCliente() {
        txtPesquisaCliente.setEnabled(true);
        txtPesquisaCliente.setText("");
        txtIdCliente.setText("");
        txtPesquisaCliente.requestFocus();
    }

    public void habilitaPesquisaProduto() {
        txtPesquisaProduto.setEnabled(true);
        txtPesquisaProduto.setText("");
        txtPrecoProduto.setText("");
        txtIdProduto.setText("");
        txtQtdProd.setText("");
        txtPesquisaProduto.requestFocus();
        jListaProdutos.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanelVenda = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jListaProdutos = new javax.swing.JList<>();
        txtPesquisaProduto = new javax.swing.JTextField();
        jButtonInserirItemVenda = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jListClientes = new javax.swing.JList<>();
        txtPesquisaCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPrecoProduto = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        LimpaPesquisaProduto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtQtdProd = new javax.swing.JTextField();
        txtDataVenda = new org.jdesktop.swingx.JXDatePicker();
        txtNumeroVenda = new javax.swing.JTextField();
        txtIdProduto = new javax.swing.JTextField();
        jButtonNovaVenda = new javax.swing.JToggleButton();
        jButtonLimpaPesquisaCliente = new javax.swing.JButton();
        jDetalhamentoCupom = new javax.swing.JPanel();
        jPanelTabela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCupomFiscal = new javax.swing.JTable();
        jTotal = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabelTotalCupom = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("PDV SIS-VENDAS");
        setMinimumSize(new java.awt.Dimension(950, 430));
        setPreferredSize(new java.awt.Dimension(950, 430));
        setVisible(true);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jListaProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jListaProdutos.setDoubleBuffered(true);
        jListaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListaProdutosMouseClicked(evt);
            }
        });
        jPanel2.add(jListaProdutos, java.awt.BorderLayout.CENTER);

        txtPesquisaProduto.setMinimumSize(new java.awt.Dimension(6, 23));
        txtPesquisaProduto.setPreferredSize(new java.awt.Dimension(6, 23));
        txtPesquisaProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaProdutoKeyReleased(evt);
            }
        });
        jPanel2.add(txtPesquisaProduto, java.awt.BorderLayout.PAGE_START);

        jButtonInserirItemVenda.setText("Inserir");
        jButtonInserirItemVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirItemVendaActionPerformed(evt);
            }
        });

        jLabel3.setText("Pesquisa Produto");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jListClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jListClientes.setDoubleBuffered(true);
        jListClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListClientesMouseClicked(evt);
            }
        });
        jPanel1.add(jListClientes, java.awt.BorderLayout.CENTER);

        txtPesquisaCliente.setMinimumSize(new java.awt.Dimension(6, 23));
        txtPesquisaCliente.setPreferredSize(new java.awt.Dimension(6, 23));
        txtPesquisaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaClienteKeyReleased(evt);
            }
        });
        jPanel1.add(txtPesquisaCliente, java.awt.BorderLayout.PAGE_START);

        jLabel7.setText("Data Venda");

        jLabel8.setText("Número da venda");

        jLabel4.setText("Preço");

        jLabel6.setText("Id");

        jLabel5.setText("Quantidade");

        txtPrecoProduto.setEnabled(false);

        txtIdCliente.setEnabled(false);

        LimpaPesquisaProduto.setText("Limpar");
        LimpaPesquisaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpaPesquisaProdutoActionPerformed(evt);
            }
        });

        jLabel1.setText("Pesquisa Cliente");

        jLabel2.setText("Id");

        txtNumeroVenda.setEnabled(false);

        txtIdProduto.setEnabled(false);

        jButtonNovaVenda.setText("Nova Venda");
        jButtonNovaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaVendaActionPerformed(evt);
            }
        });

        jButtonLimpaPesquisaCliente.setText("Limpar");
        jButtonLimpaPesquisaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpaPesquisaClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelVendaLayout = new javax.swing.GroupLayout(jPanelVenda);
        jPanelVenda.setLayout(jPanelVendaLayout);
        jPanelVendaLayout.setHorizontalGroup(
            jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
            .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelVendaLayout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelVendaLayout.createSequentialGroup()
                            .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelVendaLayout.createSequentialGroup()
                                    .addComponent(jButtonNovaVenda)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonLimpaPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelVendaLayout.createSequentialGroup()
                                    .addComponent(LimpaPesquisaProduto)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtQtdProd, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButtonInserirItemVenda, javax.swing.GroupLayout.Alignment.LEADING))))))
                        .addGroup(jPanelVendaLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNumeroVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelVendaLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(28, 28, 28)
                            .addComponent(jLabel1)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelVendaLayout.setVerticalGroup(
            jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
            .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelVendaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(6, 6, 6)
                    .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonNovaVenda)
                            .addComponent(jButtonLimpaPesquisaCliente))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumeroVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel7)
                        .addComponent(txtDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)))
                    .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelVendaLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelVendaLayout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(jPanelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LimpaPesquisaProduto)
                                .addComponent(txtQtdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonInserirItemVenda)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jDetalhamentoCupom.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalhamento do cupom", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        tblCupomFiscal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCupomFiscal.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblCupomFiscal);

        javax.swing.GroupLayout jPanelTabelaLayout = new javax.swing.GroupLayout(jPanelTabela);
        jPanelTabela.setLayout(jPanelTabelaLayout);
        jPanelTabelaLayout.setHorizontalGroup(
            jPanelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
            .addGroup(jPanelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
        );
        jPanelTabelaLayout.setVerticalGroup(
            jPanelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
            .addGroup(jPanelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("TOTAL DO CUPOM");

        jLabelTotalCupom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelTotalCupom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jTotalLayout = new javax.swing.GroupLayout(jTotal);
        jTotal.setLayout(jTotalLayout);
        jTotalLayout.setHorizontalGroup(
            jTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jTotalLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabelTotalCupom, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jTotalLayout.setVerticalGroup(
            jTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
            .addGroup(jTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jTotalLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(jTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelTotalCupom, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jDetalhamentoCupomLayout = new javax.swing.GroupLayout(jDetalhamentoCupom);
        jDetalhamentoCupom.setLayout(jDetalhamentoCupomLayout);
        jDetalhamentoCupomLayout.setHorizontalGroup(
            jDetalhamentoCupomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDetalhamentoCupomLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jDetalhamentoCupomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jDetalhamentoCupomLayout.setVerticalGroup(
            jDetalhamentoCupomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDetalhamentoCupomLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jDetalhamentoCupom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDetalhamentoCupom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("PDV SIS-VENDAS");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisaProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaProdutoKeyReleased
        pesquisaDinamicaProduto();
    }//GEN-LAST:event_txtPesquisaProdutoKeyReleased

    private void jListClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListClientesMouseClicked
        selecionaCliente();
    }//GEN-LAST:event_jListClientesMouseClicked

    private void jButtonLimpaPesquisaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpaPesquisaClienteActionPerformed
        habilitaPesquisaCliente();
    }//GEN-LAST:event_jButtonLimpaPesquisaClienteActionPerformed

    private void txtPesquisaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaClienteKeyReleased
        pesquisaDinamicaCliente();
    }//GEN-LAST:event_txtPesquisaClienteKeyReleased

    private void LimpaPesquisaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpaPesquisaProdutoActionPerformed
        habilitaPesquisaProduto();
    }//GEN-LAST:event_LimpaPesquisaProdutoActionPerformed

    private void jButtonNovaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaVendaActionPerformed
        if (txtIdCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione o cliente", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            novaVenda();
        }
    }//GEN-LAST:event_jButtonNovaVendaActionPerformed

    private void jListaProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListaProdutosMouseClicked
        selecionaProduto();
    }//GEN-LAST:event_jListaProdutosMouseClicked

    private void jButtonInserirItemVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirItemVendaActionPerformed
        if (txtNumeroVenda.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Clique em Nova Venda primeiro", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtPrecoProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O produto não pode ficar vazio", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtQtdProd.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha a quantidade", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (!txtQtdProd.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "A quantidade só aceita números inteiros", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            inserirProduto();
        }
    }//GEN-LAST:event_jButtonInserirItemVendaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LimpaPesquisaProduto;
    private javax.swing.JToggleButton jButtonInserirItemVenda;
    private javax.swing.JButton jButtonLimpaPesquisaCliente;
    private javax.swing.JToggleButton jButtonNovaVenda;
    private javax.swing.JPanel jDetalhamentoCupom;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTotalCupom;
    private javax.swing.JList<String> jListClientes;
    private javax.swing.JList<String> jListaProdutos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelTabela;
    private javax.swing.JPanel jPanelVenda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jTotal;
    private javax.swing.JTable tblCupomFiscal;
    private org.jdesktop.swingx.JXDatePicker txtDataVenda;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtNumeroVenda;
    private javax.swing.JTextField txtPesquisaCliente;
    private javax.swing.JTextField txtPesquisaProduto;
    private javax.swing.JTextField txtPrecoProduto;
    private javax.swing.JTextField txtQtdProd;
    // End of variables declaration//GEN-END:variables
}
