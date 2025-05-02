/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Servico;
import model.dao.ServicoDAO;

/**
 *
 * @author Usuario
 */
public class TelaInicio extends javax.swing.JFrame {
   ServicoDAO servdao = new ServicoDAO();
   ArrayList <Servico> servicos = servdao.listarServicos();
   ArrayList <Object> ids = new ArrayList();
   ArrayList <Object> datas = new ArrayList();
   ArrayList <Object> carros = new ArrayList();
   ArrayList <Object> valores = new ArrayList();
   ArrayList <Object> metodospag = new ArrayList();
   ArrayList<Servico> busca = new ArrayList();
    
   public void preencherArrays(){
       for(int i = 0; i<servicos.size(); i++){
         ids.add(servicos.get(i).getIds());
         datas.add(servicos.get(i).getData());
         carros.add(servicos.get(i).getCarro());
         valores.add(servicos.get(i).getValor());
         metodospag.add(servicos.get(i).getMetodopag());
       }
   }
   public void preencherBusca(){
       for(int i = 0; i<busca.size(); i++){
         ids.add(busca.get(i).getIds());
         datas.add(busca.get(i).getData());
         carros.add(busca.get(i).getCarro());
         valores.add(busca.get(i).getValor());
         metodospag.add(busca.get(i).getMetodopag());
       }
   }
   public void limparArrays(){
      ids.clear();
         datas.clear();
         carros.clear();
         valores.clear();
         metodospag.clear();
   }
   
   public void limparCampos(){
    txtids.setText("");
    txtdata.setText(String.valueOf(LocalDate.now()));
    txtcarro.setText("");
    txtvalor.setText("");
    txtmetodopag.setText("");
    txtvtd.setText("");
    txtvtm.setText("");
    txtbdatad.setText("");
    txtbdatam.setText("");
    txtbdataa.setText("");
    txtbcarro.setText("");
    txtbvalor.setText("");
    txtbmetodopag.setText("");
   }
   
    /*}else if(txtvalor.getText().contains("a") || txtvalor.getText().contains("b") || txtvalor.getText().contains("c") || txtvalor.getText().contains("d") || txtvalor.getText().contains("e") || txtvalor.getText().contains("f") || txtvalor.getText().contains("g") || txtvalor.getText().contains("h") || txtvalor.getText().contains("i") || txtvalor.getText().contains("j") || txtvalor.getText().contains("k") || txtvalor.getText().contains("l") || txtvalor.getText().contains("m") || txtvalor.getText().contains("n") || txtvalor.getText().contains("o") || txtvalor.getText().contains("p") || txtvalor.getText().contains("q") || txtvalor.getText().contains("r") || txtvalor.getText().contains("s") || txtvalor.getText().contains("t") || txtvalor.getText().contains("u") || txtvalor.getText().contains("v") || txtvalor.getText().contains("w") || txtvalor.getText().contains("x") || txtvalor.getText().contains("y") || txtvalor.getText().contains("z") || txtvalor.getText().contains("ç")){
         JOptionPane.showMessageDialog(this, "Coloque um Número Válido!"); 
         return false;
     }else{
         return true;
     }*/
   
   public boolean isNumeric(String text){
       try {
        Double.parseDouble(text);
        return true;
       } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Prencha um Número válido!"); 
       return false;
       }
  
   }
   public boolean isInteger(String text){
       try {
        Integer.parseInt(text);
        return true;
       } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(this, "Prencha um Número válido!");
       return false;
       }
  
   }
   public boolean isDay(int dia){
    
           if(dia == 0 || dia < 0 || dia > 31){
            JOptionPane.showMessageDialog(this, "Digite um dia válido!");
            return false;
           }else{
               return true;
           }
   }
   public boolean isMonth(int mes){
       
           if(mes == 0 || mes < 0 || mes > 12){
            JOptionPane.showMessageDialog(this, "Digite um mês válido!");
            return false;
           }else{
               return true;
           }
   }
    public boolean isYear(int ano){
     
           if(ano == 0 || ano < 0 ||ano > LocalDate.now().getYear()){
            JOptionPane.showMessageDialog(this, "Digite um ano válido!");
            return false;
           }else{
               return true;
           }
   }
   
   public boolean validarCampos(){
         if(txtcarro.getText().trim().equals("") || txtvalor.getText().equals("") || txtmetodopag.getText().equals("")) {
         JOptionPane.showMessageDialog(this, "Campos não preenchidos!");
         return false;
         }else if(isNumeric(txtvalor.getText()) == false){
           JOptionPane.showMessageDialog(this, "Prencha um Número válido!");  
           return false;
         }else{
       return true;
   } 
   }
   
   public boolean validarCamposB(){
       if( txtbdatad.getText().trim().equals("")&& txtbdatam.getText().trim().equals("") && txtbdataa.getText().trim().equals("") && txtbcarro.getText().trim().equals("") && txtbvalor.getText().equals("") && txtbmetodopag.getText().equals("")) {
         JOptionPane.showMessageDialog(this, "Campos não preenchidos!");
         return false;
   
   }else{
       return true;
   }
   }
   
   public void preencherTabela(){
       DefaultTableModel model = (DefaultTableModel) tbservicos.getModel();
       model.setRowCount(0);
       servicos.clear();
       servicos = servdao.listarServicos();
       limparArrays();  
       preencherArrays();
       
       for(int i = 0; i<servicos.size(); i++){
        Object [] dados = {ids.get(i), datas.get(i), carros.get(i), valores.get(i), metodospag.get(i)};   
        model.addRow(dados); 
        
        }
  limparArrays();
   }
public Servico returnServico(int index){
     return servicos.get(index);   
    }
    public void preencherTBbusca(){
           DefaultTableModel model = (DefaultTableModel) tbservicos.getModel();
       model.setRowCount(0);
       limparArrays();  
       preencherBusca();
       
       for(int i = 0; i<busca.size(); i++){
        Object [] dados = {ids.get(i), datas.get(i), carros.get(i), valores.get(i), metodospag.get(i)};   
        model.addRow(dados); 
          
    }
       busca.clear();
       limparArrays();
    }
    public boolean isDate(String text){
        try {
         Date.valueOf(text);  
         return true;
        } catch (Exception e) {
   JOptionPane.showMessageDialog(this, "Digite uma Data Válida!");
   return false;
        }
  
    }
    public TelaInicio() {
        initComponents();
        
         txtdata.setText(String.valueOf(LocalDate.now()));
     
       //  System.out.println(ids.size() + " " + datas.size() + " " + carros.size() + " " + valores.size() + " " + metodospag.size());
         preencherTabela();
       
       
      
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
        tbservicos = new javax.swing.JTable();
        txtids = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtdata = new javax.swing.JTextField();
        txtcarro = new javax.swing.JTextField();
        txtvalor = new javax.swing.JTextField();
        txtmetodopag = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btncadastrarservico = new javax.swing.JButton();
        btnexcluirservico = new javax.swing.JButton();
        btneditarservico = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtvtd = new javax.swing.JTextField();
        txtbdatad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnlimparcampos = new javax.swing.JButton();
        txtbcarro = new javax.swing.JTextField();
        btnbusca = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtbvalor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtbmetodopag = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btntbcompleta = new javax.swing.JButton();
        txtvtm = new javax.swing.JTextField();
        txtbdatam = new javax.swing.JTextField();
        txtbdataa = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbservicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data", "Carro", "Valor", "Metodo de Pagamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbservicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbservicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbservicos);

        txtids.setEditable(false);
        txtids.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ID");

        txtdata.setEditable(false);
        txtdata.setBackground(new java.awt.Color(204, 204, 204));

        txtvalor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvalorActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Data");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Carro");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Valor");

        jLabel5.setText("Método de Pagamento");

        btncadastrarservico.setText("Cadastrar");
        btncadastrarservico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadastrarservicoActionPerformed(evt);
            }
        });

        btnexcluirservico.setText("Excluir");
        btnexcluirservico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexcluirservicoActionPerformed(evt);
            }
        });

        btneditarservico.setText("Alterar");
        btneditarservico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarservicoActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Valor Total do Dia e do Mês");

        txtvtd.setEditable(false);
        txtvtd.setBackground(new java.awt.Color(10, 78, 131));
        txtvtd.setForeground(new java.awt.Color(255, 255, 255));

        txtbdatad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbdatadActionPerformed(evt);
            }
        });

        jLabel7.setText("Pesquisar Servico");

        btnlimparcampos.setText("Limpar Campos");
        btnlimparcampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparcamposActionPerformed(evt);
            }
        });

        txtbcarro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbcarroActionPerformed(evt);
            }
        });

        btnbusca.setText("Buscar Serviço");
        btnbusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscaActionPerformed(evt);
            }
        });

        jLabel8.setText("Data");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Carro");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Valor");

        jLabel11.setText("Método Pagamento");

        btntbcompleta.setText("Tabela Completa");
        btntbcompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntbcompletaActionPerformed(evt);
            }
        });

        txtvtm.setEditable(false);
        txtvtm.setBackground(new java.awt.Color(10, 78, 131));
        txtvtm.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Dia");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Mês");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Ano");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(btnlimparcampos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntbcompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtids, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmetodopag, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcarro, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdata, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtvtd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtvtm, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtbdatad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtbdatam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtbdataa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtbcarro, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtbvalor, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel11)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtbmetodopag, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(btnbusca)))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(btncadastrarservico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnexcluirservico, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btneditarservico, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(73, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(164, 164, 164))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtids, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtcarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtbcarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbmetodopag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbdatad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbdatam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbdataa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnbusca))
                            .addComponent(jLabel8))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtmetodopag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtvtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtvtm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnexcluirservico, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btneditarservico, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncadastrarservico, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnlimparcampos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btntbcompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneditarservicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarservicoActionPerformed
        int i = tbservicos.getSelectedRow();
       Servico servico = returnServico(i);
    
        if(validarCampos() == true){
         servico.setCarro(txtcarro.getText());
         servico.setValor(Double.parseDouble(txtvalor.getText()));
         servico.setMetodopag(txtmetodopag.getText());
         servdao.editarServico(servico);
         limparCampos();
        preencherArrays();
        preencherTabela();
        limparArrays();
       
     }
        
    }//GEN-LAST:event_btneditarservicoActionPerformed

    private void btncadastrarservicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarservicoActionPerformed
    
 
       
   if(validarCampos() == true){
           String carro = txtcarro.getText();
     Double valor = Double.parseDouble(txtvalor.getText());
     String metodopag = txtmetodopag.getText();
         Servico servico = new Servico(0, carro, valor, metodopag); 
        servdao.cadastrarServico(servico);
        limparCampos();
        preencherArrays();
        preencherTabela();
        limparArrays();
     }
    }//GEN-LAST:event_btncadastrarservicoActionPerformed

    private void txtvalorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvalorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorActionPerformed

    private void txtbdatadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbdatadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbdatadActionPerformed

    private void btnexcluirservicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexcluirservicoActionPerformed
      int i = Integer.parseInt(txtids.getText());
      if(validarCampos() == true){
      servdao.removerServico(i);
       limparCampos();
        preencherArrays();
        preencherTabela();
        limparArrays();
      }
    }//GEN-LAST:event_btnexcluirservicoActionPerformed

    private void tbservicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbservicosMouseClicked
       int i = tbservicos.getSelectedRow();
        Servico servico = returnServico(i);
        txtids.setText(String.valueOf(servico.getIds()));
        txtdata.setText(String.valueOf(servico.getData()));
        txtcarro.setText(servico.getCarro());
        txtvalor.setText(String.valueOf(servico.getValor()));
        txtmetodopag.setText(servico.getMetodopag());
        Double vtd = servdao.calcularVTD(servico);
        txtvtd.setText(String.valueOf(vtd));
       Double vtm = servdao.calcularVTM(servico);
       txtvtm.setText(String.valueOf(vtm));
       /* String dia = String.valueOf(data.getDay());
        String mes = String.valueOf(data.getMonth());
        String ano = String.valueOf(data.getYear());*/
        
    }//GEN-LAST:event_tbservicosMouseClicked

    private void btnlimparcamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparcamposActionPerformed
     limparCampos();
        
    }//GEN-LAST:event_btnlimparcamposActionPerformed

    private void txtbcarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbcarroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbcarroActionPerformed

    private void btnbuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscaActionPerformed
     
        
        String dia;
        String mes;
        String ano;
     String carroc ;
     String valorc;
     String metodopagc;
        if(validarCamposB() == true){
         dia = txtbdatad.getText();
         mes = txtbdatam.getText();
         ano = txtbdataa.getText();
     carroc = txtbcarro.getText();
     valorc = txtbvalor.getText();
     metodopagc = txtbmetodopag.getText();
     if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
    if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true){
         busca = servdao.buscarDia(dia);
        preencherTBbusca(); 
       limparCampos();
    }
     }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
      if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
         busca = servdao.buscarMes(mes);
      preencherTBbusca();
      limparCampos();
      }
     }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
        if(isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
         busca = servdao.buscarAno(ano);
        preencherTBbusca();
        limparCampos();
        }
     }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
      if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){

         busca = servdao.buscarDiaeMes(dia, mes);
       preencherTBbusca();
       limparCampos();
      }
     }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
       if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){

         busca = servdao.buscarDiaeAno(dia, ano);
        preencherTBbusca();
        limparCampos();
       }
     }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
       if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
         busca = servdao.buscarMeseAno(mes, ano);
        preencherTBbusca();
        limparCampos();
       }
        }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
     String datac = ano + "-" + mes + "-" + dia;
         if(isDate(datac) == true){
    Date   data = Date.valueOf(datac);
         busca = servdao.buscarData(data);
     
      preencherTBbusca();
       limparCampos();
     }
    }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true){
           busca = servdao.buscarDiaeCarro(dia, carroc);
           preencherTBbusca();
       limparCampos();
        }
    }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
      if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc) == true){
         Double valor = Double.valueOf(valorc);
          busca = servdao.buscarDiaeValor(dia, valor);
          preencherTBbusca();
          limparCampos();
      }
    }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
       if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true){
         busca = servdao.buscarDiaeMetodopag(dia, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
    }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc)){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarDiaeCarroeValor(dia, carroc, valor);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true ){
           
         busca = servdao.buscarDiaeCarroeMetodopag(dia, carroc, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc)){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarDiaeCarroeValoreMetodopag(dia, carroc, valor, metodopagc);
         preencherTBbusca();
         limparCampos();
       }   
        }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
        if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
           busca = servdao.buscarMeseCarro(mes, carroc);
           preencherTBbusca();
       limparCampos();
        }
    }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
      if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isNumeric(valorc) == true){
         Double valor = Double.valueOf(valorc);
          busca = servdao.buscarMeseValor(mes, valor);
          preencherTBbusca();
          limparCampos();
      }
    }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
       if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
         busca = servdao.buscarMeseMetodopag(mes, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
    }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
        if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isNumeric(valorc)){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarMeseCarroeValor(mes, carroc, valor);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
        if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true ){
           
         busca = servdao.buscarMeseCarroeMetodopag(mes, carroc, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
        if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isNumeric(valorc)){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarMeseCarroeValoreMetodopag(mes, carroc, valor, metodopagc);
         preencherTBbusca();
         limparCampos();
       }
          }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
        if(isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
           busca = servdao.buscarAnoeCarro(ano, carroc);
           preencherTBbusca();
       limparCampos();
        }
    }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
      if(isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true && isNumeric(valorc) == true){
         Double valor = Double.valueOf(valorc);
          busca = servdao.buscarAnoeValor(ano, valor);
          preencherTBbusca();
          limparCampos();
      }
    }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
       if(isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
         busca = servdao.buscarAnoeMetodopag(ano, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
    }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
        if(isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true && isNumeric(valorc)){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarAnoeCarroeValor(ano, carroc, valor);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
        if(isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true ){
           
         busca = servdao.buscarAnoeCarroeMetodopag(ano, carroc, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
        if(isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true && isNumeric(valorc)){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarAnoeCarroeValoreMetodopag(ano, carroc, valor, metodopagc);
         preencherTBbusca();
         limparCampos();
       }
       }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
           busca = servdao.buscarDiaeMeseCarro(dia, mes, carroc);
           preencherTBbusca();
       limparCampos();
        }
    }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
      if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc) == true && isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
         Double valor = Double.valueOf(valorc);
          busca = servdao.buscarDiaeMeseValor(dia, mes, valor);
          preencherTBbusca();
          limparCampos();
      }
    }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
       if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
         busca = servdao.buscarDiaeMeseMetodopag(dia, mes, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
    }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc) && isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarDiaeMeseCarroeValor(dia, mes, carroc, valor);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
           
         busca = servdao.buscarDiaeMeseCarroeMetodopag(dia, mes, carroc, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
           if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc) && isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarDiaeMeseValoreMetodopag(dia,mes, valor, metodopagc);
         preencherTBbusca();
         limparCampos();
       }   
      }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc) && isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarDiaeMeseCarroeValoreMetodopag(dia,mes, carroc, valor, metodopagc);
         preencherTBbusca();
         limparCampos();
       }   
       }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
           busca = servdao.buscarDiaeAnoeCarro(dia, ano, carroc);
           preencherTBbusca();
       limparCampos();
        }
    }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
      if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
         Double valor = Double.valueOf(valorc);
          busca = servdao.buscarDiaeAnoeValor(dia, ano, valor);
          preencherTBbusca();
          limparCampos();
      }
    }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
       if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
         busca = servdao.buscarDiaeAnoeMetodopag(dia, ano, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
    }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc) && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarDiaeAnoeCarroeValor(dia, ano, carroc, valor);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
           
         busca = servdao.buscarDiaeAnoeCarroeMetodopag(dia, ano, carroc, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
          if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc) && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarDiaeAnoeValoreMetodopag(dia,ano, valor, metodopagc);
         preencherTBbusca();
         limparCampos();
       }   
      }else if(dia.trim().length() > 0 && mes.trim().length() == 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
        if(isInteger(dia) == true && isDay(Integer.parseInt(dia)) == true && isNumeric(valorc) && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarDiaeAnoeCarroeValoreMetodopag(dia,ano, carroc, valor, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
       }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
        if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
           busca = servdao.buscarMeseAnoeCarro(mes,ano, carroc);
           preencherTBbusca();
       limparCampos();
        }
    }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
      if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isNumeric(valorc) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
         Double valor = Double.valueOf(valorc);
          busca = servdao.buscarMeseAnoeValor(mes,ano, valor);
          preencherTBbusca();
          limparCampos();
      }
    }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
       if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
         busca = servdao.buscarMeseAnoeMetodopag(mes,ano, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
    }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
        if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isNumeric(valorc) && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarMeseAnoeCarroeValor(mes,ano, carroc, valor);
         preencherTBbusca();
         limparCampos();
       } 
      }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
        if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true ){
           
         busca = servdao.buscarMeseAnoeCarroeMetodopag(mes,ano, carroc, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
        }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
          if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isNumeric(valorc) && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarMeseAnoeValoreMetodopag(mes,ano, valor, metodopagc);
         preencherTBbusca();
         limparCampos();
       }   
      }else if(dia.trim().length() == 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
        if(isInteger(mes) == true && isMonth(Integer.parseInt(mes)) == true && isNumeric(valorc) && isInteger(ano) == true && isYear(Integer.parseInt(ano)) == true){
            Double valor = Double.valueOf(valorc);
         busca = servdao.buscarMeseAnoeCarroeValoreMetodopag(mes,ano, carroc, valor, metodopagc);
         preencherTBbusca();
         limparCampos();
       } 
     }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
        
      busca = servdao.buscarCarro(carroc);
      preencherTBbusca(); 
       limparCampos();
    
     }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
       if(isNumeric(txtbvalor.getText()) == true){
           Double valor = Double.valueOf(valorc);
      busca = servdao.buscarValor(valor);
      preencherTBbusca();
       limparCampos();
       }
      
     }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
         
      busca = servdao.buscarMetodopag(metodopagc);
      preencherTBbusca();
       limparCampos();
     
     }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length()  > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() == 0){
      String datac = ano + "-" + mes + "-" + dia;
         if(isDate(datac) == true){
         Date data = Date.valueOf(datac);
      busca = servdao.buscarDataeCarro(data, carroc);
      preencherTBbusca();
 limparCampos();
       }
     
     }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() > 0  && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
      String datac = ano + "-" + mes + "-" + dia;
         if(isNumeric(txtbvalor.getText()) == true && isDate(datac) == true){
       
         Date data = Date.valueOf(datac);
        
         Double valor = Double.valueOf(valorc);
      busca = servdao.buscarDataeValor(data, valor);
      preencherTBbusca();
       limparCampos();
                }
  
     }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
      String datac = ano + "-" + mes + "-" + dia;
         if(isDate(datac) == true){
         Date data = Date.valueOf(datac);
      busca = servdao.buscarDataeMetodopag(data, metodopagc);
      preencherTBbusca();
       limparCampos();
      }

     }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0) {
        if(isNumeric(txtbvalor.getText()) == true){
         
         Double valor = Double.valueOf(valorc);
      busca = servdao.buscarCarroeValor(carroc, valor);
      preencherTBbusca();
       limparCampos();
        }
        
     }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
    
      busca = servdao.buscarCarroeMetodopag(carroc, metodopagc);
      preencherTBbusca();
       limparCampos();
     
     }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
      if(isNumeric(txtbvalor.getText()) == true){
         Double valor = Double.valueOf(valorc);
      busca = servdao.buscarValoreMetodopag(valor, metodopagc);
      preencherTBbusca();
       limparCampos();
      }
     
     }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() == 0){
     String datac = ano + "-" + mes + "-" + dia;
         if(isNumeric(txtbvalor.getText()) == true && isDate(datac) == true){
         Date data = Date.valueOf(datac);
        Double valor = Double.valueOf(valorc);
      busca = servdao.buscarDataeCarroeValor(data, carroc, valor);
      preencherTBbusca();
       limparCampos();
     }
   
     }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() == 0 && metodopagc.trim().length() > 0){
      String datac = ano + "-" + mes + "-" + dia;
         if(isDate(datac) == true){
         Date data = Date.valueOf(datac);
      busca = servdao.buscarDataeCarroeMetodopag(data, carroc, metodopagc);
      preencherTBbusca();
       limparCampos();
      }
     
     }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() == 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
      String datac = ano + "-" + mes + "-" + dia; 
         if(isNumeric(txtbvalor.getText()) == true && isDate(datac) == true){
         Date data = Date.valueOf(datac);
         Double valor = Double.valueOf(valorc);
      busca = servdao.buscarDataeValoreMetodopag(data, valor, metodopagc);
      preencherTBbusca();
       limparCampos();
       }
       System.out.println("Décimo Terceiro");
     }else if(dia.trim().length() == 0 && mes.trim().length() == 0 && ano.trim().length() == 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
    if(isNumeric(txtbvalor.getText()) == true){
         Double valor = Double.valueOf(valorc);
      busca = servdao.buscarCarroeValoreMetodopag(carroc, valor, metodopagc);
      preencherTBbusca();
       limparCampos();
    }
    System.out.println("Décimo Quarto");
     }else if(dia.trim().length() > 0 && mes.trim().length() > 0 && ano.trim().length() > 0 && carroc.trim().length() > 0 && valorc.trim().length() > 0 && metodopagc.trim().length() > 0){
     String datac = ano + "-" + mes + "-" + dia;
         if(isNumeric(txtbvalor.getText()) == true && isDate(datac) == true){
         Date data = Date.valueOf(datac); 
      Double valor = Double.valueOf(valorc);
      busca = servdao.buscarServicoC(data, carroc, valor, metodopagc);
      preencherTBbusca();
      limparCampos();
     }
     System.out.println("Décimo Quinto");
      limparCampos();
        }
      
        }
    }//GEN-LAST:event_btnbuscaActionPerformed

    private void btntbcompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntbcompletaActionPerformed
       preencherTabela();
    }//GEN-LAST:event_btntbcompletaActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbusca;
    private javax.swing.JButton btncadastrarservico;
    private javax.swing.JButton btneditarservico;
    private javax.swing.JButton btnexcluirservico;
    private javax.swing.JButton btnlimparcampos;
    private javax.swing.JButton btntbcompleta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbservicos;
    private javax.swing.JTextField txtbcarro;
    private javax.swing.JTextField txtbdataa;
    private javax.swing.JTextField txtbdatad;
    private javax.swing.JTextField txtbdatam;
    private javax.swing.JTextField txtbmetodopag;
    private javax.swing.JTextField txtbvalor;
    private javax.swing.JTextField txtcarro;
    private javax.swing.JTextField txtdata;
    private javax.swing.JTextField txtids;
    private javax.swing.JTextField txtmetodopag;
    private javax.swing.JTextField txtvalor;
    private javax.swing.JTextField txtvtd;
    private javax.swing.JTextField txtvtm;
    // End of variables declaration//GEN-END:variables
}
