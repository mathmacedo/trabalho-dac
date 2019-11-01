package com.tads.dac.dao;

import com.tads.dac.beans.Cidade;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CidadeDAO {
    public int insertCidade(Cidade cidade){
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
    
    public Cidade getCidadeById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Cidade c = (Cidade) s.get(Cidade.class, id);
            
            t.commit();
            s.close();
            
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
    
    public List<Cidade> listCidades(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Cidade");
            
            List<Cidade> lista = q.list();
            t.commit();
            s.close();
            
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
    
    public boolean updateCidade(Cidade cidade){
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
    
    public boolean deleteCidade(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Cidade c = (Cidade) s.get(Cidade.class, id);
            s.update(c.getEstado());
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
