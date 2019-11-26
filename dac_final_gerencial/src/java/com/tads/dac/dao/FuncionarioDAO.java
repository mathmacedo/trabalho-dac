package com.tads.dac.dao;

import com.tads.dac.beans.Funcionario;
import com.tads.dac.util.HibernateUtil;
import com.tads.dac.util.StringToMD5;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FuncionarioDAO {
    public static int insertFuncionario(Funcionario funcionario){
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
    
    public static Funcionario getFuncionarioById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Funcionario f = (Funcionario) s.get(Funcionario.class, id);
                        
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
    
    public static List<Funcionario> listFuncionarios(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Funcionario");
            
            List<Funcionario> lista = q.list();
            
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
    
    public static boolean updateFuncionario(Funcionario funcionario){
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
    
    public static boolean deleteFuncionario(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Funcionario f = (Funcionario) s.get(Funcionario.class, id);
            
            s.delete(f);
            s.delete(f.getEndereco());
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar funcionário: " + ex.getMessage());
        }
    }
    
    public static Funcionario authenticate(String email, String password){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery(
                "from Funcionario where email_funcionario = ?"
                        + "AND senha_funcionario = ?");
            q.setString(0, email);
            q.setString(1, password);
            
            Funcionario f = (Funcionario) q.uniqueResult();
            
            s.close();
            
            return f;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao autenticar: " + ex.getMessage());
        }
    }
}