package com.tads.dac.dao;

import com.tads.dac.beans.Piloto;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PilotoDAO {
    public static int insertPiloto(Piloto piloto){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idPiloto = s.save(piloto);
            t.commit();

            s.close();

            return Integer.parseInt(idPiloto.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir piloto: " + ex.getMessage());
        }
    }
    
    public static Piloto getPilotoById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Piloto p = (Piloto) s.get(Piloto.class, id);
            
            if (p != null)
                return p;
            else throw new RuntimeException(
                "Erro: Piloto não foi encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar piloto: " + ex.getMessage());
        }
    }
    
    public static List<Piloto> listPilotos(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Piloto");
            
            List<Piloto> lista = q.list();
            
            return lista;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar pilotos: " + ex.getMessage());
        }
    }
    
    public static boolean updatePiloto(Piloto piloto){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(piloto);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar piloto: " + ex.getMessage());
        }
    }
    
    public static boolean deletePiloto(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Piloto p = (Piloto) s.get(Piloto.class, id);
            
            s.delete(p);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar piloto: " + ex.getMessage());
        }
    }
}
