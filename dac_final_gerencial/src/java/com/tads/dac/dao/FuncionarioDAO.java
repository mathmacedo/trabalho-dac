package com.tads.dac.dao;

import com.tads.dac.beans.Funcionario;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FuncionarioDAO {
    public int insertFuncionario(Funcionario funcionario){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idFuncionario = s.save(funcionario);
            t.commit();

            s.close();

            return Integer.parseInt(idFuncionario.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir funcionário: " + ex.getMessage());
        }
    }
    
    public Funcionario getFuncionarioById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Funcionario f = (Funcionario) s.get(Funcionario.class, id);
            
            t.commit();
            s.close();
            
            if (f != null)
                return f;
            else throw new RuntimeException(
                "Erro: funcionário não foi encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar funcionário: " + ex.getMessage());
        }
    }
    
    public List<Funcionario> listFuncionarios(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Funcionario");
            
            List<Funcionario> lista = q.list();
            t.commit();
            s.close();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhum funcionário encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar funcionários: " + ex.getMessage());
        }
    }
    
    public boolean updateFuncionario(Funcionario funcionario){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(funcionario);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar funcionário: " + ex.getMessage());
        }
    }
    
    public boolean deleteFuncionario(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Funcionario f = (Funcionario) s.get(Funcionario.class, id);
            
            s.update(f.getEndereco());
            s.delete(f);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar funcionário: " + ex.getMessage());
        }
    }
}