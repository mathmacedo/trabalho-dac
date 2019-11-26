package com.tads.dac.dao;

import com.tads.dac.beans.Cidade;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CidadeDAO {
    public static int insertCidade(Cidade cidade){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idCidade = s.save(cidade);
            t.commit();

            s.close();

            return Integer.parseInt(idCidade.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir cidade: " + ex.getMessage());
        }
    }
    
    public static Cidade getCidadeById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Cidade c = (Cidade) s.get(Cidade.class, id);
            
            if (c != null)
                return c;
            else throw new RuntimeException(
                "Erro: Cidade não foi encontrada!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar cidade: " + ex.getMessage());
        }
    }
    
    public static List<Cidade> getCidadesByEstado(int idEstado){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Cidade where id_estado = ?");
            
            q.setInteger(0, idEstado);
            
            List<Cidade> lista = q.list();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhuma cidade encontrada!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar cidades: " + ex.getMessage());
        }
    }
    
    public static List<Cidade> listCidades(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Cidade");
            
            List<Cidade> lista = q.list();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhuma cidade encontrada!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar cidades: " + ex.getMessage());
        }
    }
    
    public static boolean updateCidade(Cidade cidade){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(cidade);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar cidade: " + ex.getMessage());
        }
    }
    
    public static boolean deleteCidade(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Cidade c = (Cidade) s.get(Cidade.class, id);
            
            s.delete(c);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar cidade: " + ex.getMessage());
        }
    }
}
