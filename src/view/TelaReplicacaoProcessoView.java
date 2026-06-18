package view;

import database.dao.ReplicacaoProcessoDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TelaReplicacaoProcessoView extends JFrame {

    private enum ModoTela {NENHUM, INSERT, UPDATE}
    private ModoTela modoTela = ModoTela.NENHUM;

    private final Connection conn;
    private final ReplicacaoProcessoDAO dao;

    private JTextField txfId;
    private JTextField txfProcesso;
    private JTextField txfDescricao;
    private JCheckBox chkHabilitado;

    private JButton btnSalvar;
    private JButton btnAdicionar;
    private JButton btnBuscar;
    private JButton btnExcluir;

    public TelaReplicacaoProcessoView(Connection conn) throws SQLException {

        this.conn = conn;
        this.dao = new ReplicacaoProcessoDAO(conn);

        setTitle("Cadastro de Processos");
        setSize(620, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        btnBuscar = new JButton("BUSCAR");
        btnAdicionar = new JButton("ADCIONAR");
        btnSalvar = new JButton("SALVAR");
        btnExcluir = new JButton("EXCLUIR");

        btnBuscar.setBounds(10, 10, 130, 30);
        btnAdicionar.setBounds(150, 10, 130, 30);
        btnSalvar.setBounds(290, 10, 130, 30);
        btnExcluir.setBounds(430, 10, 130, 30);

        getContentPane().add(btnBuscar);
        getContentPane().add(btnAdicionar);
        getContentPane().add(btnSalvar);
        getContentPane().add(btnExcluir);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(10,70,120,25);
        getContentPane().add(lblId);

        txfId = new JTextField();
        txfId.setBounds(140,70,200,25);
        getContentPane().add(txfId);

        JLabel lblProcesso = new JLabel("PROCESSO:");
        lblProcesso.setBounds(10,105,120,25);
        getContentPane().add(lblProcesso);

        txfProcesso = new JTextField();
        txfProcesso.setBounds(140,105,420,25);
        getContentPane().add(txfProcesso);

        JLabel lblDescrcao = new JLabel("DESCRICAO");
        lblDescrcao.setBounds(10,140,120,25);
        getContentPane().add(lblDescrcao);

        txfDescricao = new JTextField();
        txfDescricao.setBounds(140, 140, 420, 25);
        getContentPane().add(txfDescricao);

        chkHabilitado = new JCheckBox("HABILITADO");
        chkHabilitado.setBounds(10,175,120,25);
        getContentPane().add(chkHabilitado);

        txfId.setEnabled(false);
        txfProcesso.setEnabled(false);
        txfDescricao.setEnabled(false);
        chkHabilitado.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);

        btnAdicionar.addActionListener(e -> {
            modoTela = ModoTela.INSERT;

            txfId.setText("");
            txfProcesso.setText("");
            txfDescricao.setText("");
            chkHabilitado.setSelected(true);

            txfId.setEnabled(true);
            txfProcesso.setEnabled(true);
            txfDescricao.setEnabled(true);
            chkHabilitado.setEnabled(true);
            btnSalvar.setEnabled(true);
            btnExcluir.setEnabled(true);
        });

        btnSalvar.addActionListener(e -> {
            try{

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showConfirmDialog(null, "Erro ao salvar: " + ex.getMessage());            }
        });

    }
}
