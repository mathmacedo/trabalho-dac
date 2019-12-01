package com.tads.dac.dao;

import com.tads.dac.beans.Cliente;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClienteDAO {
    public static int insertCliente(Cliente cliente){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idCliente = s.save(cliente);
            t.commit();

            s.close();

            return Integer.parseInt(idCliente.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir cliente: " + ex.getMessage());
        }
    }
    
    public static Cliente getClienteById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Cliente c = (Cliente) s.get(Cliente.class, id);
            
            t.commit();
            s.close();
            
            if (c != null)
                return c;
            else throw new RuntimeException(
                "Erro: cliente não foi encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar cliente: " + ex.getMessage());
        }
    }
    
    public static List<Cliente> listClientes(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Cliente");
            
            List<Cliente> lista = q.list();
            t.commit();
            s.close();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhum cliente encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar clientes: " + ex.getMessage());
        }
    }
    
    public static boolean updateCliente(Cliente cliente){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(cliente);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar cliente: " + ex.getMessage());
        }
    }
    
    public static boolean deleteCliente(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Cliente c = (Cliente) s.get(Cliente.class, id);
            
            s.delete(c);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar cliente: " + ex.getMessage());
        }
    }
    
    public static Cliente authenticate(String email, String password){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery(
                "from Cliente where email_cliente = ?"
                        + "AND senha_cliente = ?");
            q.setString(0, email);
            q.setString(1, password);
            
            Cliente c = (Cliente) q.uniqueResult();
            
            s.close();
            
            return c;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao autenticar: " + ex.getMessage());
        }
    }
}