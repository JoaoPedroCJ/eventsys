package eventsys;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Database{
    private String driver;
    private String servidor;
    private String porta;
    private String banco;
    private String username;
    private String password;
    private String dbURL;
    private ResultSet result;
    private Statement comando;
    private PreparedStatement ps;
    private Connection conn;
    
    public Database(){
        loadConfig();
    }
    
    public void configDB(){
        JTextField confServidor = new JTextField(this.servidor), confPorta = new JTextField(this.porta), confBanco = new JTextField(this.banco), confUser = new JTextField(this.username), confPass = new JTextField(this.password);
        JComboBox confDriver = new JComboBox(new String[]{"", "jdbc:mysql", "jdbc:postgresql", "jdbc:microsoft:sqlserver"});
        confDriver.setSelectedItem(this.driver);
        Object[] dados = {"Informe o Driver:", confDriver, "Informe o servidor:", confServidor, "Informe a porta:", confPorta, "Informe o nome do banco", confBanco, "Usuário", confUser, "Senha", confPass};
        int info = JOptionPane.showConfirmDialog(null, dados, "Configuração", JOptionPane.OK_CANCEL_OPTION);
        if(info == JOptionPane.OK_OPTION){
            Properties prop = new Properties();
            OutputStream output = null;

            try {
                File file = new File("lib/config.properties");
                file.getParentFile().mkdirs();
		output = new FileOutputStream(file, false);

		prop.setProperty("driver", confDriver.getSelectedItem().toString());
                prop.setProperty("servidor", confServidor.getText());
		prop.setProperty("porta", confPorta.getText());
		prop.setProperty("banco", confBanco.getText());
                prop.setProperty("username", confUser.getText());
                prop.setProperty("password", confPass.getText());

		prop.store(output, null);
                
            }
            catch (IOException io) {
		System.out.println(io);
            }
            finally {
		if (output != null) {
                    try {
                        loadConfig();
                        output.close();
                    }
                    catch (IOException e) {
                        System.out.println(e);
                    }
		}
            }
        }
    }
    
    public final void loadConfig(){
        Properties prop = new Properties();
	InputStream input = null;
        
        try {
            input = new FileInputStream("lib/config.properties");
	}
        catch (FileNotFoundException e) {
		JOptionPane.showMessageDialog(null, "É necessário criar o arquivo de configuração");
                configDB();
	}
        finally {
            if (input != null) {
                try {
                    prop.load(input);
                    
                    this.driver = prop.getProperty("driver");
                    this.servidor = prop.getProperty("servidor");
                    this.porta = prop.getProperty("porta");
                    this.banco = prop.getProperty("banco");
                    this.username = prop.getProperty("username");
                    this.password = prop.getProperty("password");
                    
                    this.dbURL = this.driver+"://"+this.servidor+":"+this.porta+"/"+this.banco;
                    
                    input.close();
                }
                catch (IOException e) {
                    System.out.println(e);
                }
            }
	}
    }
    
    public Connection conectar(){
        try{
            conn = (Connection) DriverManager.getConnection (dbURL, username, password);
            
            if(conn != null){
                System.out.println("Conectado");
                return conn;
            }
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
    public void desconectar(){
        try {
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        finally {
            System.out.println("Desconectado");
        }
    }
    
    public ResultSet pesquisaSQL(String sql){
        try {
            ps = conectar().prepareStatement(sql);
            result = ps.executeQuery();
            return result;
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public void executaSQL(String sql){
        try {
            comando = conectar().createStatement();
            result = comando.executeQuery(sql);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void backup() throws IOException, InterruptedException{
        String executeCmd;
        String arquivo;
        JFileChooser filePath = new JFileChooser();
        FileNameExtensionFilter sqlfilter = new FileNameExtensionFilter("Arquivo SQL (*.sql)", "sql");
        filePath.setFileFilter(sqlfilter);
        filePath.setVisible(true);
        int escolha = filePath.showSaveDialog(null);
        if(escolha == JFileChooser.APPROVE_OPTION){
            arquivo = filePath.getSelectedFile().toString();
            if(arquivo.endsWith(".sql")==false){
                arquivo = arquivo.concat(".sql");
            }
            File file = new File(arquivo);
            if(file.exists()){
                Object[] options = {"Sim","Não"};
                int opcao = JOptionPane.showOptionDialog(null,"Este arquivo já existe. Deseja substituir este arquivo?", "Atenção!!!",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
                if (opcao == JOptionPane.YES_OPTION) {
                    if(this.password.equalsIgnoreCase("")){
                        executeCmd = "mysqldump.exe -h"+this.servidor+" -u"+this.username+" "+this.banco+" -r "+arquivo;
                    }
                    else{
                        executeCmd = "mysqldump.exe -h"+this.servidor+" -u"+this.username+" -p"+this.password+" "+this.banco+" -r "+arquivo;
                    }
                    Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                    int completo = runtimeProcess.waitFor();
                    if(completo == 0){
                        JOptionPane.showMessageDialog(null, "Backup realizado com sucesso");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Falha no backup");
                    }    
                }
                else {
                    backup();
                }
            }
            else{
                if(this.password.equalsIgnoreCase("")){
                        executeCmd = "mysqldump.exe -h"+this.servidor+" -u"+this.username+" "+this.banco+" -r "+arquivo;
                    }
                    else{
                        executeCmd = "mysqldump.exe -h"+this.servidor+" -u"+this.username+" -p"+this.password+" "+this.banco+" -r "+arquivo;
                    }
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int completo = runtimeProcess.waitFor();
                if(completo == 0){
                    JOptionPane.showMessageDialog(null, "Backup realizado com sucesso");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Falha no backup");
                }
            }
        }
    }
            
    public void restaurar() throws IOException, InterruptedException{
        String arquivo;
        JFileChooser filePath = new JFileChooser();
        FileNameExtensionFilter sqlfilter = new FileNameExtensionFilter("Arquivo SQL (*.sql)", "sql");
        filePath.setFileFilter(sqlfilter);
        filePath.setVisible(true);
        int escolha = filePath.showOpenDialog(null);
        if(escolha == JFileChooser.APPROVE_OPTION){
            arquivo = filePath.getSelectedFile().toString();
            Object[] options = {"Sim","Não"};
            int opcao = JOptionPane.showOptionDialog(null,"O banco de dados irá retornar para a data do arquivo!\nDeseja Continuar?", "Atenção!!!",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
            if (opcao == JOptionPane.YES_OPTION) {
                if(password.equalsIgnoreCase("")){
                    String[] executeCmd = new String[]{"mysql.exe", banco, "-u" + username, "-e", " source " + arquivo};
                    Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                    int completo = runtimeProcess.waitFor();
                    if(completo == 0){
                        JOptionPane.showMessageDialog(null, "Backup restaurado com sucesso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha na restauração");
                    }
                }
            }
        }
    }
}
