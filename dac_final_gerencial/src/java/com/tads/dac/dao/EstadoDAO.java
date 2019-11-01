package com.tads.dac.dao;

import com.tads.dac.beans.Estado;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EstadoDAO {
    public int insertEstado(Estado estado){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idEstado = s.save(estado);
            t.commit();

            s.close();

            return Integer.parseInt(idEstado.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir estado: " + ex.getMessage());
        }
    }
    
    public Estado getEstadoById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Estado e = (Estado) s.get(Estado.class, id);
            
            t.commit();
            s.close();
            
            return e;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar estado: " + ex.getMessage());
        }
    }
    
    public List<Estado> listEstados(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Estado");
            
            List<Estado> lista = q.list();
            t.commit();
            s.close();
            
            return lista;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar estados: " + ex.getMessage());
        }
    }
    
    public boolean updateEstado(Estado estado){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(estado);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar estado: " + ex.getMessage());
        }
    }
    
    public boolean deleteEstado(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Estado e = (Estado) s.get(Estado.class, id);
            s.delete(e);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar estado: " + ex.getMessage());
        }
    }
}
