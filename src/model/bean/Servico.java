/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;


import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class Servico {
 private int ids;
 private Date data;
   private String carro;
   private double valor;
   private String metodopag;
    
  public Servico(){
      
  }  
   
   public Servico(int ids,String carro, double valor, String metodopag){
    this.ids = ids;   
   this.carro = carro;
   this.valor = valor;
   this.metodopag = metodopag;
       
   }
    public Servico(int ids, Date data, String carro, double valor, String metodopag){
    this.ids = ids; 
    this.data = data;
   this.carro = carro;
   this.valor = valor;
   this.metodopag = metodopag;
       
   }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

 

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMetodopag() {
        return metodopag;
    }

    public void setMetodopag(String metodopag) {
        this.metodopag = metodopag;
    }
 
  
  
}
