package view;

import database.dao.DirecaoDAO;
import database.model.TB_REPLICACAO_DIRECAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaDirecaoDialog extends JDialog{

    private JTable table;
    private JButton btnSelecionar;
    private JButton btnCancelar;

    private TB_REPLICACAO_DIRECAO selecionado;
    private DirecaoDAO dao;

    public ConsultaDirecaoDialog(JFrame parent, DirecaoDAO dao) throws SQLException {
        super(parent, "Consulta Direçao", ModalityType.APPLICATION_MODAL);
        setSize(700,400);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(null);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("DIRECAO ORIGEM");
        model.addColumn("DIRECAO DESTINO");
        model.addColumn("HABILITADO");

        ArrayList<TB_REPLICACAO_DIRECAO> lista = this.dao.selectAll();
        for (TB_REPLICACAO_DIRECAO p: lista){
            model.addRow(new Object[]{p.getId(), p.getDirecao_destino(), p.getDirecao_origem(), p.isHabilitado()});
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,680,300);
        add(scrollPane);

        btnSelecionar = new JButton("SELECIONAR");
        btnSelecionar.setBounds(10,320,140,30);
        add(btnSelecionar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(170,320,140,30);
        add(btnCancelar);

        btnCancelar.addActionListener(e -> {
            selecionado= null;
            dispose();
        });

        btnSelecionar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1){
                JOptionPane.showMessageDialog(this,"Selecione um registro.");
                return;
            }

            TB_REPLICACAO_DIRECAO p = new TB_REPLICACAO_DIRECAO();
            p.setId(Integer.parseInt(table.getValueAt(row,0).toString()));
            p.setDirecao_origem(table.getValueAt(row, 1).toString());
            p.setDirecao_destino(table.getValueAt(row, 2).toString());
            p.setHabilitado(Boolean.parseBoolean(table.getValueAt(row,3).toString()));
            selecionado = p;
            dispose();
        });

        table.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e ) {
                if (e.getClickCount() == 2) {
                    btnSelecionar.doClick();
                }
            }
        });
    }
    public TB_REPLICACAO_DIRECAO getSelecionado() {
        return selecionado;
    }
}
