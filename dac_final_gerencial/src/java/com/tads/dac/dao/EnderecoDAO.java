package com.tads.dac.dao;

import com.tads.dac.beans.Endereco;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EnderecoDAO {
    public static int insertEndereco(Endereco endereco){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idEndereco = s.save(endereco);
            t.commit();

            s.close();

            return Integer.parseInt(idEndereco.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir cidade: " + ex.getMessage());
        }
    }
    
    public static Endereco getEnderecoById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Endereco e = (Endereco) s.get(Endereco.class, id);
            
            t.commit();
            s.close();
            
            if (e != null)
                return e;
            else throw new RuntimeException(
                "Erro: Endereço não foi encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar cidade: " + ex.getMessage());
        }
    }
    
    public static List<Endereco> listEnderecos(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Endereco");
            
            List<Endereco> lista = q.list();
            t.commit();
            s.close();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhum endereço encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar cidades: " + ex.getMessage());
        }
    }
    
    public static boolean updateEndereco(Endereco endereco){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(endereco);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar cidade: " + ex.getMessage());
        }
    }
    
    public static boolean deleteEndereco(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Endereco e = (Endereco) s.get(Endereco.class, id);
            
            s.delete(e);
            
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
