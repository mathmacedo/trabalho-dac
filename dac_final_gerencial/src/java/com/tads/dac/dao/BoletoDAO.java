package com.tads.dac.dao;

import com.tads.dac.beans.Boleto;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BoletoDAO {
    public static int insertBoleto(Boleto boleto){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idBoleto = s.save(boleto);
            t.commit();

            s.close();

            return Integer.parseInt(idBoleto.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir boleto: " + ex.getMessage());
        }
    }
    
    public static Boleto getBoletoById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Boleto b = (Boleto) s.get(Boleto.class, id);
            
            t.commit();
            s.close();
            
            if (b != null)
                return b;
            else throw new RuntimeException(
                "Erro: Boleto não foi encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar boleto: " + ex.getMessage());
        }
    }
    
    public static List<Boleto> listBoletos(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Boleto");
            
            List<Boleto> lista = q.list();
            t.commit();
            s.close();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhum boleto encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar boletos: " + ex.getMessage());
        }
    }
    
   public static Boleto getBoletoByReserva(int id){
       try{
            return ReservaDAO.getReservaById(id).getBoleto();
       }
       catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar boleto: " + ex.getMessage());
       }
   }
    
    public static boolean updateBoleto(Boleto boleto){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(boleto);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar boleto: " + ex.getMessage());
        }
    }
    
    public static boolean deleteBoleto(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Boleto b = (Boleto) s.get(Boleto.class, id);
            
            s.delete(b);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar boleto: " + ex.getMessage());
        }
    }
}
