package com.tads.dac.dao;

import com.tads.dac.beans.Reserva;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReservaDAO {
    public static int insertReserva(Reserva reserva){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idReserva = s.save(reserva);
            t.commit();

            s.close();

            return Integer.parseInt(idReserva.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir reserva: " + ex.getMessage());
        }
    }
    
    public static Reserva getReservaById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Reserva r = (Reserva) s.get(Reserva.class, id);
            
            t.commit();
            s.close();
            
            if (r != null)
                return r;
            else throw new RuntimeException(
                "Erro: Reserva não foi encontrada!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar reserva: " + ex.getMessage());
        }
    }

    public static List<Reserva> getReservasByCliente(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Reserva where id_cliente = ?");
            
            q.setInteger(0, id);
            
            List<Reserva> lista = q.list();
            
            t.commit();
            s.close();
            
            if (lista != null)
                return lista;
            else return null;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar reserva: " + ex.getMessage());
        }
    }
    
    public static List<Reserva> listReservas(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Reserva");
            
            List<Reserva> lista = q.list();
            t.commit();
            s.close();
            
            if (lista != null)
                return lista;
            else return null;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar reservas: " + ex.getMessage());
        }
    }
    
    public static boolean updateReserva(Reserva reserva){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(reserva);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar reserva: " + ex.getMessage());
        }
    }
    
    public static boolean deleteReserva(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Reserva r = (Reserva) s.get(Reserva.class, id);
            
            s.delete(r);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar reserva: " + ex.getMessage());
        }
    }
}
