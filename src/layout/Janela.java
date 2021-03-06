package layout;

import eventsys.Database;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Janela extends javax.swing.JFrame {
    
    private final Database conexao = new Database();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy - HH:mm");

    public Janela() {
        initComponents();
        try {
            preencherTabela("SELECT * FROM vw_evento WHERE realizacao > CURRENT_DATE ORDER BY realizacao");
        } catch (SQLException ex) {
            Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEventos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArq = new javax.swing.JMenu();
        menuArqConfig = new javax.swing.JMenuItem();
        menuArqBackup = new javax.swing.JMenuItem();
        menuArqRestaurar = new javax.swing.JMenuItem();
        menuArqSair = new javax.swing.JMenuItem();
        menuEve = new javax.swing.JMenu();
        menuEveGerenciar = new javax.swing.JMenuItem();
        menuEveCadastrar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EventSys - Práticas Integradas 1B | Una Barro Preto");
        setBackground(new java.awt.Color(242, 242, 242));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(600, 450));
        setResizable(false);

        tabelaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
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
        tabelaEventos.getTableHeader().setReorderingAllowed(false);
        tabelaEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaEventosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaEventos);
        if (tabelaEventos.getColumnModel().getColumnCount() > 0) {
            tabelaEventos.getColumnModel().getColumn(0).setResizable(false);
            tabelaEventos.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabelaEventos.getColumnModel().getColumn(1).setResizable(false);
            tabelaEventos.getColumnModel().getColumn(1).setPreferredWidth(327);
            tabelaEventos.getColumnModel().getColumn(2).setResizable(false);
            tabelaEventos.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabelaEventos.getColumnModel().getColumn(3).setResizable(false);
            tabelaEventos.getColumnModel().getColumn(3).setPreferredWidth(140);
        }
        tabelaEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/una.png"))); // NOI18N

        jLabel2.setText("João Pedro Coelho Jácome");

        jLabel3.setText("RA: 318135934");

        jButton1.setText("Recarregar Lista");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        menuArq.setText("Arquivo");

        menuArqConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/config.png"))); // NOI18N
        menuArqConfig.setText("Configurar");
        menuArqConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArqConfigActionPerformed(evt);
            }
        });
        menuArq.add(menuArqConfig);

        menuArqBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backup.png"))); // NOI18N
        menuArqBackup.setText("Fazer Backup");
        menuArqBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArqBackupActionPerformed(evt);
            }
        });
        menuArq.add(menuArqBackup);

        menuArqRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/restore.png"))); // NOI18N
        menuArqRestaurar.setText("Restaurar Backup");
        menuArqRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArqRestaurarActionPerformed(evt);
            }
        });
        menuArq.add(menuArqRestaurar);

        menuArqSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sair.png"))); // NOI18N
        menuArqSair.setText("Sair");
        menuArqSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArqSairActionPerformed(evt);
            }
        });
        menuArq.add(menuArqSair);

        jMenuBar1.add(menuArq);

        menuEve.setText("Evento");

        menuEveGerenciar.setText("Gerenciar");
        menuEveGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEveGerenciarActionPerformed(evt);
            }
        });
        menuEve.add(menuEveGerenciar);

        menuEveCadastrar.setText("Cadastrar");
        menuEveCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEveCadastrarActionPerformed(evt);
            }
        });
        menuEve.add(menuEveCadastrar);

        jMenuBar1.add(menuEve);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(616, 512));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuArqSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArqSairActionPerformed
        conexao.desconectar();
        System.exit(0);
    }//GEN-LAST:event_menuArqSairActionPerformed

    private void menuArqConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArqConfigActionPerformed
        conexao.configDB();
    }//GEN-LAST:event_menuArqConfigActionPerformed

    private void menuArqBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArqBackupActionPerformed
        try {
            conexao.backup();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuArqBackupActionPerformed

    private void menuArqRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArqRestaurarActionPerformed
        try {
            conexao.restaurar();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuArqRestaurarActionPerformed

    private void menuEveGerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEveGerenciarActionPerformed
        new Eventos().setVisible(true);
    }//GEN-LAST:event_menuEveGerenciarActionPerformed

    private void tabelaEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEventosMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                new EditEventos(tabelaEventos.getValueAt(tabelaEventos.getSelectedRow(), 0), tabelaEventos.getValueAt(tabelaEventos.getSelectedRow(), 2), true).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tabelaEventosMouseClicked

    private void menuEveCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEveCadastrarActionPerformed
        try {
            new EditEventos().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuEveCadastrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            preencherTabela("SELECT * FROM vw_evento WHERE realizacao > CURRENT_DATE ORDER BY realizacao");
        } catch (SQLException ex) {
            Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Janela().setVisible(true);
        });
    }
    
    public void preencherTabela(String sql) throws SQLException{
        
        DefaultTableModel Val = (DefaultTableModel) tabelaEventos.getModel();
        Val.setNumRows(0);
        
        try(ResultSet result = conexao.pesquisaSQL(sql)) {
            result.first();
            do {
                Val.addRow(new Object[]{result.getString(1),result.getString(2),result.getString(3),sdf.format(result.getTimestamp(4).getTime())});
            }
            while (result.next());
        }
        catch (SQLException ex) {
            System.out.println(ex);   
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuArq;
    private javax.swing.JMenuItem menuArqBackup;
    private javax.swing.JMenuItem menuArqConfig;
    private javax.swing.JMenuItem menuArqRestaurar;
    private javax.swing.JMenuItem menuArqSair;
    private javax.swing.JMenu menuEve;
    private javax.swing.JMenuItem menuEveCadastrar;
    private javax.swing.JMenuItem menuEveGerenciar;
    private javax.swing.JTable tabelaEventos;
    // End of variables declaration//GEN-END:variables
}
