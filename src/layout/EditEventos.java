package layout;

import eventsys.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EditEventos extends javax.swing.JFrame {

    private final Database conexao = new Database();
    private boolean editavel = false;
    private Object idEvento, edicaoEvento;
    
    private final Calendar c = Calendar.getInstance();
    
    private final DateFormat hf = new SimpleDateFormat("HH");
    private final DateFormat mf = new SimpleDateFormat("mm");
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
    
    private String dataEvento;
    
    public EditEventos() throws SQLException {
        initComponents();
        listaEventos("SELECT * FROM evento");
        dataSelect.setDate(c.getTime());
        boxHoras.setSelectedItem(hf.format(c.getTime()));
        boxMinutos.setSelectedItem(mf.format(c.getTime()));
        btnDeletar.setVisible(false);
    }
    
    public EditEventos(Object idEvento, Object edicaoEvento, boolean editar) throws SQLException {
        initComponents();
        if(editar){
            listaEventos("SELECT * FROM evento");
            carregarEvento("SELECT * FROM vw_evento WHERE evento = '"+idEvento+"' AND edicao = '"+edicaoEvento+"'");
            preencherTabela("SELECT * FROM `vw_check` WHERE evento = '"+idEvento+"' AND edicao = '"+edicaoEvento+"'");
            btnSalvarAlterar.setText("Atualizar");
            this.editavel = true;
            this.idEvento = idEvento;
            this.edicaoEvento = edicaoEvento;
            btnDeletar.setVisible(true);
        }
    }
    
    private void preencherTabela(String sql) throws SQLException{
        
        DefaultTableModel Val = (DefaultTableModel) tabelaChecklist.getModel();
        Val.setNumRows(0);
        
        try(ResultSet result = conexao.pesquisaSQL(sql)) {
            result.first();
            do {
                Val.addRow(new Object[]{result.getBoolean(1), result.getString(2), sdf.format(result.getTimestamp(3).getTime()), result.getString(4), result.getString(5), result.getString(6)});
            }
            while (result.next());
        }
        catch (SQLException ex) {
            System.out.println(ex);   
        }
    }
    
    public void carregarEvento(String sql){
        try(ResultSet result = conexao.pesquisaSQL(sql)) {
            while (result.next()){
                c.setTime(result.getTimestamp(4));
                dataSelect.setDate(c.getTime());
                boxHoras.setSelectedItem(hf.format(c.getTime()));
                boxMinutos.setSelectedItem(mf.format(c.getTime()));
                boxEventos.setSelectedItem(result.getString("nome"));
                boxEdicao.setText(result.getString("edicao"));
                boxEdicao.setEnabled(false);
            }
        }
        catch (SQLException ex) {
            System.out.println(ex);   
        }
        
    }
    
    public void listaEventos(String sql) throws SQLException{
        boxEventos.removeAllItems();
        try(ResultSet result = conexao.pesquisaSQL(sql)) {
            while (result.next()){
                boxEventos.addItem(result.getString("nome"));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boxEventos = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaChecklist = new javax.swing.JTable();
        dataSelect = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        boxHoras = new javax.swing.JComboBox<>();
        boxMinutos = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        btnSalvarAlterar = new javax.swing.JButton();
        boxEdicao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnDeletar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        boxEventos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabelaChecklist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Checklist", "Prazo", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaChecklist.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaChecklist);
        if (tabelaChecklist.getColumnModel().getColumnCount() > 0) {
            tabelaChecklist.getColumnModel().getColumn(0).setResizable(false);
            tabelaChecklist.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabelaChecklist.getColumnModel().getColumn(1).setResizable(false);
            tabelaChecklist.getColumnModel().getColumn(1).setPreferredWidth(295);
            tabelaChecklist.getColumnModel().getColumn(2).setResizable(false);
            tabelaChecklist.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabelaChecklist.getColumnModel().getColumn(3).setMinWidth(0);
            tabelaChecklist.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabelaChecklist.getColumnModel().getColumn(3).setMaxWidth(0);
            tabelaChecklist.getColumnModel().getColumn(4).setMinWidth(0);
            tabelaChecklist.getColumnModel().getColumn(4).setPreferredWidth(0);
            tabelaChecklist.getColumnModel().getColumn(4).setMaxWidth(0);
            tabelaChecklist.getColumnModel().getColumn(5).setMinWidth(0);
            tabelaChecklist.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabelaChecklist.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        jLabel2.setText("Data do Evento:");

        jLabel3.setText("Horário:");

        boxHoras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23" }));
        boxHoras.setPreferredSize(new java.awt.Dimension(60, 20));

        boxMinutos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59" }));
        boxMinutos.setPreferredSize(new java.awt.Dimension(60, 20));

        jButton2.setText("Criar Checklist");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnSalvarAlterar.setText("Salvar");
        btnSalvarAlterar.setMaximumSize(new java.awt.Dimension(101, 23));
        btnSalvarAlterar.setMinimumSize(new java.awt.Dimension(101, 23));
        btnSalvarAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarAlterarActionPerformed(evt);
            }
        });

        boxEdicao.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        jLabel4.setText("Edição:");

        btnDeletar.setText("DELETAR");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalvarAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDeletar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataSelect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(boxHoras, 0, 61, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(boxEdicao))
                            .addComponent(boxEventos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dataSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeletar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(586, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAlterarActionPerformed
        if(this.editavel == true){
            dataEvento = df.format(dataSelect.getDate()) + " " + boxHoras.getSelectedItem() +":"+ boxMinutos.getSelectedItem() +":00" ;
            String sql = "UPDATE edicao SET realizacao = ? WHERE cod_evento = '"+idEvento+"' AND edicao = '"+edicaoEvento+"'";
            PreparedStatement comando;
            try{
                comando = conexao.conectar().prepareStatement(sql);
                comando.setString(1, dataEvento);
                int linhasAlteradas = comando.executeUpdate();
                if (linhasAlteradas > 0) {
                    System.out.println("Atualizado com Sucesso");
                }
            } catch (SQLException ex) {
                    Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(tabelaChecklist.getRowCount() > 0){
                for(int i=0;i<tabelaChecklist.getRowCount();i++){
                    sql = "UPDATE checklist_evento SET concluido = ? WHERE cod_evento = ? AND edicao = ? AND cod_check = ?";
                    boolean tarefa = tabelaChecklist.getValueAt(i, 0).equals(true);
                    try {
                        comando = conexao.conectar().prepareStatement(sql);
                        comando.setBoolean(1, tarefa);
                        comando.setString(2, tabelaChecklist.getValueAt(i, 3).toString());
                        comando.setString(3, tabelaChecklist.getValueAt(i, 4).toString());
                        comando.setString(4, tabelaChecklist.getValueAt(i, 5).toString());
                        int linhasAlteradas = comando.executeUpdate();
                        if (linhasAlteradas > 0) {
                            System.out.println("Atualizado com Sucesso");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else{
            dataEvento = df.format(dataSelect.getDate()) + " " + boxHoras.getSelectedItem() +":"+ boxMinutos.getSelectedItem() +":00" ;
            String sql = "INSERT INTO edicao (cod_evento, edicao, realizacao) VALUES (?, ?, ?)";
            PreparedStatement comando;
            try{
                comando = conexao.conectar().prepareStatement(sql);
                comando.setString(1, Integer.toString(boxEventos.getSelectedIndex()+1));
                comando.setString(1, boxEdicao.getText());
                comando.setString(1, dataEvento);
                int linhasAlteradas = comando.executeUpdate();
                if (linhasAlteradas > 0) {
                    System.out.println("Atualizado com Sucesso");
                }
            } catch (SQLException ex) {
                    Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(tabelaChecklist.getRowCount() > 0){
                for(int i=0;i<tabelaChecklist.getRowCount();i++){
                    sql = "UPDATE checklist_evento SET concluido = ? WHERE cod_evento = ? AND edicao = ? AND cod_check = ?";
                    boolean tarefa = tabelaChecklist.getValueAt(i, 0).equals(true);
                    try {
                        comando = conexao.conectar().prepareStatement(sql);
                        comando.setBoolean(1, tarefa);
                        comando.setString(2, tabelaChecklist.getValueAt(i, 3).toString());
                        comando.setString(3, tabelaChecklist.getValueAt(i, 4).toString());
                        comando.setString(4, tabelaChecklist.getValueAt(i, 5).toString());
                        int linhasAlteradas = comando.executeUpdate();
                        if (linhasAlteradas > 0) {
                            System.out.println("Atualizado com Sucesso");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSalvarAlterarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sql = "INSERT INTO evento (nome) VALUES (?)";
        conexao.conectar();
        PreparedStatement comando;
        try {
            comando = conexao.conectar().prepareStatement(sql);
            comando.setString(1, JOptionPane.showInputDialog("Informe o nome do novo evento:"));
            int linhasAlteradas = comando.executeUpdate();
            if (linhasAlteradas > 0) {
                JOptionPane.showMessageDialog(null, "Evento inserido com sucesso.");
            }
            listaEventos("SELECT * FROM evento");
            conexao.conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(this.editavel == false && boxEdicao.getText().isEmpty() == false){
            dataEvento = df.format(dataSelect.getDate()) + " " + boxHoras.getSelectedItem() +":"+ boxMinutos.getSelectedItem() +":00" ;
            String sql = "INSERT INTO edicao (cod_evento, edicao, realizacao) VALUES (?, ?, ?)";
            System.out.println(Integer.toString(boxEventos.getSelectedIndex()+1));
            System.out.println(boxEdicao.getText());
            PreparedStatement comando;
            try{
                comando = conexao.conectar().prepareStatement(sql);
                comando.setString(1, Integer.toString(boxEventos.getSelectedIndex()+1));
                comando.setString(2, boxEdicao.getText());
                comando.setString(3, dataEvento);
                int linhasAlteradas = comando.executeUpdate();
                if (linhasAlteradas > 0) {
                    System.out.println("Evento Adicionado");
                }
            } catch (SQLException ex) {
                    Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.idEvento = boxEventos.getSelectedIndex()+1;
                this.edicaoEvento = boxEdicao.getText();
                new Checklist(idEvento, edicaoEvento).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        } else{
            try {
                new Checklist(idEvento, edicaoEvento).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        Object[] options = {"Sim","Não"};
        int opcao = JOptionPane.showOptionDialog(null,"Esta opção irá apagar esta do banco de dados\nDeseja Continuar?", "Atenção!!!",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
        if (opcao == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM checklist_evento WHERE cod_evento = ? AND edicao = ?";
            try{
                PreparedStatement comando = conexao.conectar().prepareStatement(sql);
                comando.setString(1, this.idEvento.toString());
                comando.setString(2, this.edicaoEvento.toString());
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
                comando.setString(1, this.idEvento.toString());
                comando.setString(2, this.edicaoEvento.toString());
                int linhasAlteradas = comando.executeUpdate();
                if (linhasAlteradas > 0) {
                    System.out.println("Evento excluído com sucesso");
                }
                conexao.conectar().close();
            }
            catch(SQLException ex){
                System.out.println(ex);
            }
            dispose();
        }
    }//GEN-LAST:event_btnDeletarActionPerformed

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
            java.util.logging.Logger.getLogger(EditEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new EditEventos().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(EditEventos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField boxEdicao;
    private javax.swing.JComboBox<String> boxEventos;
    private javax.swing.JComboBox<String> boxHoras;
    private javax.swing.JComboBox<String> boxMinutos;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnSalvarAlterar;
    private com.toedter.calendar.JDateChooser dataSelect;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaChecklist;
    // End of variables declaration//GEN-END:variables
}
