package com.tads.dac.dao;

import com.tads.dac.beans.Assento;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AssentoDAO {
    public static int insertAssento(Assento assento){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idAssento = s.save(assento);
            t.commit();

            s.close();

            return Integer.parseInt(idAssento.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir assento: " + ex.getMessage());
        }
    }
    
    public static Assento getAssentoById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Assento a = (Assento) s.get(Assento.class, id);
            
            t.commit();
            s.close();
            
            if (a != null)
                return a;
            else throw new RuntimeException(
                "Erro: Assento não foi encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar assento: " + ex.getMessage());
        }
    }
    
    public static List<Assento> listAssentos(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Assento");
            
            List<Assento> lista = q.list();
            t.commit();
            s.close();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhum assento encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar assentos: " + ex.getMessage());
        }
    }
    
    public static boolean updateAssento(Assento assento){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(assento);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar assento: " + ex.getMessage());
        }
    }
    
    public static boolean deleteAssento(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Assento a = (Assento) s.get(Assento.class, id);
            
            s.delete(a);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar assento: " + ex.getMessage());
        }
    }
}
    