package com.tads.dac.dao;

import com.tads.dac.beans.Voo;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VooDAO {
    public static int insertVoo(Voo voo){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idVoo = s.save(voo);
            t.commit();

            s.close();

            return Integer.parseInt(idVoo.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir voo: " + ex.getMessage());
        }
    }
    
    public static Voo getVooById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Voo v = (Voo) s.get(Voo.class, id);
            
            t.commit();
            s.close();
            
            if (v != null)
                return v;
            else throw new RuntimeException(
                "Erro: Voo não foi encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar voo: " + ex.getMessage());
        }
    }
    
    public static List<Voo> listVoos(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Voo");
            
            List<Voo> lista = q.list();
            t.commit();
            s.close();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhum voo encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar voos: " + ex.getMessage());
        }
    }
    
    public static boolean updateVoo(Voo voo){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(voo);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar voo: " + ex.getMessage());
        }
    }
    
    public static boolean deleteVoo(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Voo v = (Voo) s.get(Voo.class, id);
            
            s.delete(v);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar voo: " + ex.getMessage());
        }
    }
}
