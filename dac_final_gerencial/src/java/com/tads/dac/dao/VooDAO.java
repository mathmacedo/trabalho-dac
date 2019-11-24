package com.tads.dac.dao;

import com.tads.dac.beans.Assento;
import com.tads.dac.beans.Voo;
import com.tads.dac.facade.AssentoFacade;
import com.tads.dac.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VooDAO {
    public static int insertVoo(Voo voo){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Serializable idVoo = s.save(voo);
            
            for (char c = 'A'; c < 'G'; c++){
                for (int i = 1; i < 34; i++){
                    if (c == 'C' && i < 5) continue;
                    Assento a = new Assento();
                    
                    a.setVoo(voo);
                    
                    if (i < 10) a.setNome(c + "0" + Integer.toString(i));
                    else a.setNome(c + Integer.toString(i));
                    
                    a.setStatus('L');
                    
                    if (i < 5){
                        a.setTipo('P');
                        a.setValor(voo.getPrecoPrimeiraClasse());
                    }
                    else {
                        a.setTipo('E');
                        a.setValor(voo.getPrecoClasseEconomica());
                    }
                    
                    s.save(a);
                }
            }
            
            t.commit();

            s.close();

            return Integer.parseInt(idVoo.toString());
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao inserir voo: " + ex.getMessage());
        }
    }
    
    public static Voo getVooById(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Voo v = (Voo) s.get(Voo.class, id);
            
            if (v != null)
                return v;
            else throw new RuntimeException(
                "Erro: Voo não foi encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao buscar voo: " + ex.getMessage());
        }
    }
    
    public static List<Voo> listVoos(){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Voo");
            
            List<Voo> lista = q.list();
            
            if (lista != null)
                return lista;
            else throw new RuntimeException(
                "Erro: Nenhum voo encontrado!");
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao listar voos: " + ex.getMessage());
        }
    }
    
    public static boolean updateVoo(Voo voo){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            s.update(voo);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao atualizar voo: " + ex.getMessage());
        }
    }
    
    public static boolean deleteVoo(int id){
        try{
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            Voo v = (Voo) s.get(Voo.class, id);
            
            List<Assento> lista = AssentoFacade.getAssentosByVoo(v.getId());
            
            for(Assento a : lista){
                s.delete(a);
            }
            
            s.delete(v);
            
            t.commit();
            s.close();
            
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException(
                "Erro ao deletar voo: " + ex.getMessage());
        }
    }
}
