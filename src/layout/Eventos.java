package layout;

import eventsys.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Eventos extends javax.swing.JFrame {

    private final Database conexao = new Database();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy - HH:mm");
    
    public Eventos() {
        initComponents();
        try {
            preencherTabela("SELECT * FROM vw_evento ORDER BY realizacao");
        } catch (SQLException ex) {
            Logger.getLogger(Eventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCompleta = new javax.swing.JTable();
        eventosInserir = new javax.swing.JButton();
        eventosEditar = new javax.swing.JButton();
        eventosExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tabelaCompleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Evento", "Edição", "Realização"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCompleta.getTableHeader().setReorderingAllowed(false);
        tabelaCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCompletaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCompleta);
        if (tabelaCompleta.getColumnModel().getColumnCount() > 0) {
            tabelaCompleta.getColumnModel().getColumn(0).setResizable(false);
            tabelaCompleta.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabelaCompleta.getColumnModel().getColumn(1).setResizable(false);
            tabelaCompleta.getColumnModel().getColumn(1).setPreferredWidth(246);
            tabelaCompleta.getColumnModel().getColumn(2).setResizable(false);
            tabelaCompleta.getColumnModel().getColumn(2).setPreferredWidth(49);
            tabelaCompleta.getColumnModel().getColumn(3).setResizable(false);
            tabelaCompleta.getColumnModel().getColumn(3).setPreferredWidth(125);
        }
        tabelaCompleta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        eventosInserir.setText("Inserir");
        eventosInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventosInserirActionPerformed(evt);
            }
        });

        eventosEditar.setText("Editar");
        eventosEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventosEditarActionPerformed(evt);
            }
        });

        eventosExcluir.setText("Excluir");
        eventosExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventosExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eventosInserir, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(eventosEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eventosExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(eventosInserir)
                .addGap(18, 18, 18)
                .addComponent(eventosEditar)
                .addGap(18, 18, 18)
                .addComponent(eventosExcluir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(618, 489));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void eventosInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventosInserirActionPerformed
        try {
            new EditEventos().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Eventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_eventosInserirActionPerformed

    private void eventosExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventosExcluirActionPerformed
        Object[] options = {"Sim","Não"};
        int opcao = JOptionPane.showOptionDialog(null,"Esta opção irá apagar esta do banco de dados\nDeseja Continuar?", "Atenção!!!",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
        if (opcao == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM checklist_evento WHERE cod_evento = ? AND edicao = ?";
            try{
                PreparedStatement comando = conexao.conectar().prepareStatement(sql);
                comando.setString(1, tabelaCompleta.getValueAt(tabelaCompleta.getSelectedRow(), 0).toString());
                comando.setString(2, tabelaCompleta.getValueAt(tabelaCompleta.getSelectedRow(), 2).toString());
                int linhasAlteradas = comando.executeUpdate();
                if (linhasAlteradas > 0) {
                    System.out.println("Etapa 1/2");
                }
                conexao.conectar().close();
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            sql = "DELETE FROM edicao WHERE cod_evento = ? AND edicao = ?";
            try{
                PreparedStatement comando = conexao.conectar().prepareStatement(sql);
                comando.setString(1, tabelaCompleta.getValueAt(tabelaCompleta.getSelectedRow(), 0).toString());
                comando.setString(2, tabelaCompleta.getValueAt(tabelaCompleta.getSelectedRow(), 2).toString());
                int linhasAlteradas = comando.executeUpdate();
                if (linhasAlteradas > 0) {
                    System.out.println("Evento excluído com sucesso");
                }
                conexao.conectar().close();
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            try {
                preencherTabela("SELECT * FROM vw_evento ORDER BY realizacao");
            } catch (SQLException ex) {
                Logger.getLogger(Eventos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_eventosExcluirActionPerformed

    private void tabelaCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCompletaMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                new EditEventos(tabelaCompleta.getValueAt(tabelaCompleta.getSelectedRow(), 0), tabelaCompleta.getValueAt(tabelaCompleta.getSelectedRow(), 2), true).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Eventos.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }
    }//GEN-LAST:event_tabelaCompletaMouseClicked

    private void eventosEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventosEditarActionPerformed
        if(tabelaCompleta.getSelectedRow() != -1){
            try {
                new EditEventos(tabelaCompleta.getValueAt(tabelaCompleta.getSelectedRow(), 0), tabelaCompleta.getValueAt(tabelaCompleta.getSelectedRow(), 2), true).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Eventos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um evento");
        }
        dispose();
    }//GEN-LAST:event_eventosEditarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Eventos().setVisible(true);
        });
    }
    
    private void preencherTabela(String sql) throws SQLException{
        
        DefaultTableModel Val = (DefaultTableModel) tabelaCompleta.getModel();
        Val.setNumRows(0);
        
        try(ResultSet result = conexao.pesquisaSQL(sql)) {
            result.first();
            do {
                Val.addRow(new Object[]{result.getString(1), result.getString(2), result.getString(3), sdf.format(result.getTimestamp(4).getTime())});
            }
            while (result.next());
        }
        catch (SQLException ex) {
            System.out.println(ex);   
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eventosEditar;
    private javax.swing.JButton eventosExcluir;
    private javax.swing.JButton eventosInserir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaCompleta;
    // End of variables declaration//GEN-END:variables
}
