package layout;

import eventsys.Database;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Checklist extends EditEventos {
    
    private final Database conexao = new Database();
    private Object idEvento, edicaoEvento;
    
    private String dataEvento;
    
    public Checklist() throws SQLException{
        
    }

    public Checklist(Object idEvento, Object edicaoEvento) throws SQLException{
        initComponents();
        preencherTabela("SELECT cod_check, descricao FROM checklist");
        carregarEvento("SELECT * FROM vw_evento WHERE evento = '"+idEvento+"' AND edicao = '"+edicaoEvento+"'");
        this.idEvento = idEvento;
        this.edicaoEvento = edicaoEvento;
    }
    
    private void preencherTabela(String sql) throws SQLException{
        
        DefaultTableModel Val = (DefaultTableModel) tabelaCheck.getModel();
        Val.setNumRows(0);
        
        try(ResultSet result = conexao.pesquisaSQL(sql)) {
            result.first();
            do {
                Val.addRow(new Object[]{result.getString(1), result.getString(2), false, dataEvento});
            }
            while (result.next());
        }
        catch (SQLException ex) {
            System.out.println(ex);   
        }
    }
    
    @Override
    public void carregarEvento(String sql){
        try(ResultSet result = conexao.pesquisaSQL(sql)) {
            while (result.next()){
                boxEvento.setText(result.getString("nome"));
                boxEdicao.setText(result.getString("edicao"));
                dataEvento = result.getTimestamp(4).toString();
            }
        }
        catch (SQLException ex) {
            System.out.println(ex);   
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boxEvento = new javax.swing.JLabel();
        boxEdicao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCheck = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabelaCheck.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Descrição", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCheck.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaCheck);
        if (tabelaCheck.getColumnModel().getColumnCount() > 0) {
            tabelaCheck.getColumnModel().getColumn(0).setMinWidth(0);
            tabelaCheck.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabelaCheck.getColumnModel().getColumn(0).setMaxWidth(0);
            tabelaCheck.getColumnModel().getColumn(1).setResizable(false);
            tabelaCheck.getColumnModel().getColumn(1).setPreferredWidth(380);
            tabelaCheck.getColumnModel().getColumn(2).setResizable(false);
            tabelaCheck.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabelaCheck.getColumnModel().getColumn(3).setMinWidth(0);
            tabelaCheck.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabelaCheck.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        jButton1.setText("Adicionar Item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salvar Checklist");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(boxEvento, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(boxEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(boxEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(3, 3, 3)
                .addComponent(jButton2)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(416, 510));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sql = "INSERT INTO checklist (descricao) VALUES (?)";
        conexao.conectar();
        PreparedStatement comando;
        try {
            comando = conexao.conectar().prepareStatement(sql);
            comando.setString(1, JOptionPane.showInputDialog("Informe o item a ser inserido:"));
            int linhasAlteradas = comando.executeUpdate();
            if (linhasAlteradas > 0) {
                JOptionPane.showMessageDialog(null, "Item inserido com sucesso.");
                preencherTabela("SELECT cod_check, descricao FROM checklist");
            }
            conexao.conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        for(int i=0;i<tabelaCheck.getRowCount();i++){
            if(tabelaCheck.getValueAt(i, 2).equals(true)){
                String sql = "INSERT INTO checklist_evento (cod_evento, edicao, cod_check, prazo) VALUES ("+this.idEvento+", "+this.edicaoEvento+", ?, '2018-12-01')";
                //int selecionado = (int) tabelaCompleta.getValueAt(0, 2);
                PreparedStatement comando;
                try {
                    comando = conexao.conectar().prepareStatement(sql);
                    comando.setString(1, tabelaCheck.getValueAt(i, 0).toString());
                    int linhasAlteradas = comando.executeUpdate();
                    if (linhasAlteradas > 0) {
                        System.out.println("Check list: " + tabelaCheck.getValueAt(i, 1) + " - Inserido com Sucesso");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Checklist.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            new EditEventos(this.idEvento, this.edicaoEvento, true).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Checklist.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Checklist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Checklist().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Checklist.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel boxEdicao;
    private javax.swing.JLabel boxEvento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaCheck;
    // End of variables declaration//GEN-END:variables
}
