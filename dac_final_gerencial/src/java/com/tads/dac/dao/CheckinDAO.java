package com.tads.dac.dao;

import com.tads.dac.beans.Checkin;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CheckinDAO {
    public static int insertCheckin(Checkin checkin){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idCheckin = s.save(checkin);
            t.commit();

            s.close();

            return Integer.parseInt(idCheckin.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir checkin: " + ex.getMessage());
        }
    }
    
    public static Checkin getCheckinById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Checkin c = (Checkin) s.get(Checkin.class, id);
            
            if (c != null)
                return c;
            else throw new RuntimeException(
                "Erro: Checkin não foi encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar checkin: " + ex.getMessage());
        }
    }
    
    public static List<Checkin> listCheckins(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Checkin");
            
            List<Checkin> lista = q.list();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhum checkin encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar checkins: " + ex.getMessage());
        }
    }
    
    public static boolean updateCheckin(Checkin checkin){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(checkin);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar checkin: " + ex.getMessage());
        }
    }
    
    public static boolean deleteCheckin(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Checkin c = (Checkin) s.get(Checkin.class, id);
            
            s.delete(c);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar checkin: " + ex.getMessage());
        }
    }
}
