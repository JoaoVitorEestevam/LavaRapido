/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.bean.Servico;

/**
 *
 * @author Usuario
 */
public class ServicoDAO {
    
    public void cadastrarServico(Servico servico){
        try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("INSERT INTO servico(data, carro, valor, metodopag) VALUES(?,?,?,?)");
            Date data = Date.valueOf(LocalDate.now());
         ps.setDate(1, data);
         ps.setString(2, servico.getCarro());
         ps.setDouble(3, servico.getValor());
         ps.setString(4, servico.getMetodopag());
         ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    
    public void removerServico(int ids){
        try {
        Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement("DELETE FROM servico WHERE idservico = ?");
        ps.setInt(1, ids);
        ps.executeUpdate();
        ps.close();
        con.close();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
      /*System.out.println("UPDATE servico SET carro = " + servico.getCarro() + ", valor ="+ servico.getValor() + ", metodopag =" + servico.getMetodopag() + "WHERE idservico ="+ servico.getIds()+ ";" );
        try {
         Connection con = Conexao.conectar();
        Statement ps = con.createStatement() ;
        ps.execute("UPDATE servico SET carro = " + servico.getCarro() + ", valor ="+ servico.getValor() + ", metodopag =" + servico.getMetodopag() +  " " + "WHERE idservico ="+ servico.getIds() + ";");
       
        ps.close();
        con.close();
         
        } catch (SQLException e) {
        e.printStackTrace();
        }*/
    public void editarServico(Servico servico){
        try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("UPDATE servico SET carro = ?, valor = ?, metodopag = ? WHERE idservico = ?");
        ps.setString(1, servico.getCarro());
        ps.setDouble(2, servico.getValor());
        ps.setString(3, servico.getMetodopag());
        ps.setInt(4, servico.getIds());
        ps.executeUpdate();
        ps.close();
        con.close();
         
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
 
    public ArrayList<Servico> listarServicos(){
        ArrayList <Servico> servicos = new ArrayList();
        try {
        Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM servico");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
          Servico servico = new Servico(rs.getInt("idservico"),rs.getDate("data"), rs.getString("carro"), rs.getDouble("valor"), rs.getString("metodopag"));  
         servicos.add(servico);
        }
        rs.close();
        ps.close();
        con.close();
        } catch (Exception e) {
        e.printStackTrace();
        }
     return servicos;
    }
    public Double calcularVTD(Servico servico){
        Double vt = 0.0;
        try {
          Connection con = Conexao.conectar();
          PreparedStatement ps = con.prepareStatement("SELECT valor FROM servico WHERE data = ?");
          ps.setDate(1, servico.getData());
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
            vt = vt + rs.getDouble("valor");
         }
        System.out.println(vt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return vt;
    }
    public Double calcularVTM(Servico servico){
        Double vt = 0.0;
         LocalDate data = LocalDate.parse(String.valueOf(servico.getData()));
        try {
          Connection con = Conexao.conectar();
          PreparedStatement ps = con.prepareStatement("SELECT valor FROM servico WHERE YEAR(data) = ? AND MONTH(data) = ?");
          ps.setInt(1, data.getYear());
          ps.setInt(2, data.getMonthValue());
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
            vt = vt + rs.getDouble("valor");
         }
        System.out.println(vt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return vt;
    }
    
    public ArrayList<Servico> buscarDia(String dia){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ?");
         ps.setInt(1, d);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
     public ArrayList<Servico> buscarMes(String mes){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ?");
         ps.setInt(1, d);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
     public ArrayList<Servico> buscarAno(String ano){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(ano);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE YEAR(data) = ?");
         ps.setInt(1, d);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
      public ArrayList<Servico> buscarDiaeMes(String dia, String mes){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        int m = Integer.parseInt(mes);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND MONTH(data) = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
      
            public ArrayList<Servico> buscarDiaeAno(String dia, String ano){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND YEAR(data) = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
      
                  public ArrayList<Servico> buscarMeseAno(String mes, String ano){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND YEAR(data) = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
         public ArrayList<Servico> buscarDiaeCarro(String dia, String carro){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
    
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND carro = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }   
         public ArrayList<Servico> buscarDiaeValor(String dia, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setDouble(2, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
         
         public ArrayList<Servico> buscarDiaeMetodopag(String dia, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
      
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setString(2, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
         public ArrayList<Servico> buscarDiaeCarroeValor(String dia, String carro, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
      
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND carro = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ps.setDouble(3, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
         
         public ArrayList<Servico> buscarDiaeCarroeMetodopag(String dia, String carro, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
    
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND carro = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
         
         public ArrayList<Servico> buscarDiaeValoreMetodopag(String dia, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
      
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setDouble(2, valor);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
         
         public ArrayList<Servico> buscarDiaeCarroeValoreMetodopag(String dia, String carro, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND carro = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ps.setDouble(3, valor);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
     
         public ArrayList<Servico> buscarMeseCarro(String mes, String carro){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND carro = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
      
            public ArrayList<Servico> buscarMeseValor(String mes, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setDouble(2, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
         
               public ArrayList<Servico> buscarMeseMetodopag(String mes, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setString(2, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
            public ArrayList<Servico> buscarMeseCarroeValor(String mes, String carro, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND carro = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ps.setDouble(3, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
           
              public ArrayList<Servico> buscarMeseCarroeMetodopag(String mes, String carro, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND carro = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
        
                public ArrayList<Servico> buscarMeseValoreMetodopag(String mes, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
       ps.setDouble(2, valor);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
   public ArrayList<Servico> buscarMeseCarroeValoreMetodopag(String mes,String carro, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND carro = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
       ps.setDouble(3, valor);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
   
    public ArrayList<Servico> buscarAnoeCarro(String ano, String carro){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(ano);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE YEAR(data) = ? AND carro = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
   
    public ArrayList<Servico> buscarAnoeValor(String ano, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(ano);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE YEAR(data) = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setDouble(2, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarAnoeMetodopag(String ano, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(ano);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE YEAR(data) = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setString(2, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarAnoeCarroeValor(String ano, String carro, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(ano);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE YEAR(data) = ? AND carro = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ps.setDouble(3, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    public ArrayList<Servico> buscarAnoeCarroeMetodopag(String ano, String carro, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(ano);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE YEAR(data) = ? AND carro = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarAnoeValoreMetodopag(String ano, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(ano);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE YEAR(data) = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setDouble(2, valor);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarAnoeCarroeValoreMetodopag(String ano, String carro, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(ano);   
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE YEAR(data) = ? AND carro = ?, AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setString(2, carro);
         ps.setDouble(3, valor);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    public ArrayList<Servico> buscarDiaeMeseCarro(String dia, String mes, String carro){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);
        int m = Integer.parseInt(mes);
        Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND MONTH(data) = ? AND carro = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarDiaeMeseValor(String dia, String mes, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);
        int m = Integer.parseInt(mes);
        Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND MONTH(data) = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setDouble(3, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarDiaeMeseMetodopag(String dia, String mes, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);
        int m = Integer.parseInt(mes);
        Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND MONTH(data) = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarDiaeMeseCarroeValor(String dia, String mes, String carro, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);
        int m = Integer.parseInt(mes);
        Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND MONTH(data) = ? AND carro = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ps.setDouble(4, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarDiaeMeseCarroeMetodopag(String dia, String mes, String carro, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);
        int m = Integer.parseInt(mes);
        Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND MONTH(data) = ? AND carro = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarDiaeMeseValoreMetodopag(String dia, String mes, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);
        int m = Integer.parseInt(mes);
        Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND MONTH(data) = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setDouble(3, valor);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
    public ArrayList<Servico> buscarDiaeMeseCarroeValoreMetodopag(String dia, String mes, String carro, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);
        int m = Integer.parseInt(mes);
        Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND MONTH(data) = ? AND carro = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ps.setDouble(4, valor);
         ps.setString(5, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
        public ArrayList<Servico> buscarDiaeAnoeCarro(String dia, String ano, String carro){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND YEAR(data) = ? AND carro = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
         public ArrayList<Servico> buscarDiaeAnoeValor(String dia, String ano, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND YEAR(data) = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setDouble(3, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
          public ArrayList<Servico> buscarDiaeAnoeMetodopag(String dia, String ano, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND YEAR(data) = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
          public ArrayList<Servico> buscarDiaeAnoeCarroeValor(String dia, String ano, String carro, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND YEAR(data) = ? AND carro = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ps.setDouble(4, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
         public ArrayList<Servico> buscarDiaeAnoeCarroeMetodopag(String dia, String ano, String carro, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND YEAR(data) = ? AND carro = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
       public ArrayList<Servico> buscarDiaeAnoeValoreMetodopag(String dia, String ano, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND YEAR(data) = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setDouble(3, valor);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
        
        public ArrayList<Servico> buscarDiaeAnoeCarroeValoreMetodopag(String dia, String ano, String carro, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(dia);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE DAY(data) = ? AND YEAR(data) = ? AND carro = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ps.setDouble(4, valor);
         ps.setString(5, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
              public ArrayList<Servico> buscarMeseAnoeCarro(String mes, String ano, String carro){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND YEAR(data) = ? AND carro = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
         public ArrayList<Servico> buscarMeseAnoeValor(String mes, String ano, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND YEAR(data) = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setDouble(3, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
          public ArrayList<Servico> buscarMeseAnoeMetodopag(String mes, String ano, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND YEAR(data) = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
          public ArrayList<Servico> buscarMeseAnoeCarroeValor(String mes, String ano, String carro, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND YEAR(data) = ? AND carro = ? AND valor = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ps.setDouble(4, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
         public ArrayList<Servico> buscarMeseAnoeCarroeMetodopag(String mes, String ano, String carro, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND YEAR(data) = ? AND carro = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    
       public ArrayList<Servico> buscarMeseAnoeValoreMetodopag(String mes, String ano, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND YEAR(data) = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setDouble(3, valor);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
        
        public ArrayList<Servico> buscarMeseAnoeCarroeValoreMetodopag(String mes, String ano, String carro, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       try {
        int d = Integer.parseInt(mes);  
        int m = Integer.parseInt(ano);
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE MONTH(data) = ? AND YEAR(data) = ? AND carro = ? AND valor = ? AND metodopag = ?");
         ps.setInt(1, d);
         ps.setInt(2, m);
         ps.setString(3, carro);
         ps.setDouble(4, valor);
         ps.setString(5, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
          
   public ArrayList<Servico> buscarData(Date data){
       ArrayList<Servico> servicos = new ArrayList();
       
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE data = ?");
         ps.setDate(1, data);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
       
  return servicos;
   }
    public ArrayList<Servico> buscarCarro(String carro){
       ArrayList<Servico> servicos = new ArrayList();
     
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE carro LIKE ?");
         ps.setString(1, carro);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   } 
     public ArrayList<Servico> buscarValor(Double valor){
       ArrayList<Servico> servicos = new ArrayList();
     
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE valor = ?");
         ps.setDouble(1, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
      public ArrayList<Servico> buscarMetodopag(String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
       
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE metodopag LIKE ?");
         ps.setString(1, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
       public ArrayList<Servico> buscarServicoC(Date data, String carro, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
     
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE data = ? AND carro LIKE ? AND valor = ? AND metodopag LIKE ?");
         ps.setDate(1, data);
         ps.setString(2, carro);
         ps.setDouble(3, valor);
         ps.setString(4, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
     public ArrayList<Servico> buscarDataeCarro(Date data, String carro){
       ArrayList<Servico> servicos = new ArrayList();
     
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE data = ? AND carro LIKE ?");
         ps.setDate(1, data);
         ps.setString(2, carro);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
    
     public ArrayList<Servico> buscarDataeValor(Date data, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
     
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE data = ? AND valor = ?");
         ps.setDate(1, data);
         ps.setDouble(2, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
     
     public ArrayList<Servico> buscarDataeMetodopag(Date data, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
      
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE data = ? AND metodopag LIKE ? ");
         ps.setDate(1, data);
         ps.setString(2, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
     
     public ArrayList<Servico> buscarCarroeValor(String carro, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
  
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE carro LIKE ? AND valor = ? ");
         ps.setString(1, carro);
         ps.setDouble(2, valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
      public ArrayList<Servico> buscarValoreMetodopag(Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
     
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE valor = ? AND metodopag LIKE ? ");
         ps.setDouble(1, valor);
         ps.setString(2, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
     public ArrayList<Servico> buscarCarroeMetodopag(String carro, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
  
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE carro LIKE ? AND metodopag LIKE ? ");
         ps.setString(1, carro);
         ps.setString(2, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
     public ArrayList<Servico> buscarDataeCarroeValor(Date data, String carro, Double valor){
       ArrayList<Servico> servicos = new ArrayList();
       
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE data = ? AND carro LIKE ? AND valor = ?");
         ps.setDate(1, data);
         ps.setString(2, carro);
         ps.setDouble(3,valor);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
     public ArrayList<Servico> buscarDataeCarroeMetodopag(Date data, String carro, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
     
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE data = ? AND carro LIKE ? AND metodopag LIKE ?");
         ps.setDate(1, data);
         ps.setString(2, carro);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
     public ArrayList<Servico> buscarDataeValoreMetodopag(Date data, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
     
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE data = ? AND valor = ? AND metodopag LIKE ?");
         ps.setDate(1, data);
         ps.setDouble(2, valor);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
     public ArrayList<Servico> buscarCarroeValoreMetodopag(String carro, Double valor, String metodopag){
       ArrayList<Servico> servicos = new ArrayList();
     
       try {
         Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM servico WHERE carro LIKE ? AND valor = ? AND metodopag LIKE ?");
         ps.setString(1, carro);
         ps.setDouble(2, valor);
         ps.setString(3, metodopag);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             Servico servico = new Servico();
          servico.setIds(rs.getInt("idservico"));
          servico.setData(rs.getDate("data"));
          servico.setCarro(rs.getString("carro"));
          servico.setValor(rs.getDouble("valor"));
          servico.setMetodopag(rs.getString("metodopag"));
          servicos.add(servico);
         }
         
       } catch (Exception e) {
           e.printStackTrace();
       }
  return servicos;
   }
}
