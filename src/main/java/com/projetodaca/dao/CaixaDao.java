/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.projetodaca.entities.Caixa;

/**
 *
 * @author renan
 */
public class CaixaDao implements IDAO<Caixa> {

    private EntityManager manager;
   

    public CaixaDao() {    
      
    }

    
    public void insert(Caixa caixa) throws Exception {
        try {
            beginTransaction();
            manager.persist(caixa);
            manager.refresh(caixa);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e);
        } finally {
            manager.close();
        }

    }

    
    public List<Caixa> list() throws Exception {
        List<Caixa> lista = null;
        try {
            beginTransaction();
            lista = manager.createQuery("SELECT e FROM Caixa e", Caixa.class).getResultList();
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e);
        } finally {
            manager.close();
        }

        return lista;
    }

    public List<Caixa> list(String where) throws Exception {
        List<Caixa> list = new ArrayList<Caixa>();
        try {
            beginTransaction();
            list = (List<Caixa>) manager.createQuery("SELECT e FROM Caixa e " + where, Caixa.class).getResultList();
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }

        return list;
    }
    public List<Caixa> listPorPontoDeRecebimentoEData(int idPontoRecebimento,Date dataAbertInicial,Date dataAbertFinal) throws Exception {
        List<Caixa> list = new ArrayList<Caixa>();
        try {
            beginTransaction();
            Query query = manager.createQuery("SELECT e FROM Caixa e WHERE e.pontoDeRecebimento.id = :idPontoRecebimento AND date(e.dataAbertura) BETWEEN :dataAbertInicial AND :dataAbertFinal", Caixa.class);
            query.setParameter("idPontoRecebimento", idPontoRecebimento);
            query.setParameter("dataAbertInicial", dataAbertInicial,TemporalType.DATE);
            query.setParameter("dataAbertFinal", dataAbertFinal,TemporalType.DATE);
            list = query.getResultList();
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }

        return list;
    }

    
    public Caixa getById(int id) throws Exception {
        Caixa caixa = null;
        try {
            beginTransaction();
            caixa = manager.find(Caixa.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }

        return caixa;
    }

    
    public void update(Caixa caixa) throws Exception {
        try {
            beginTransaction();
            manager.merge(caixa);
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex);
        } finally {
            manager.close();
        }
    }

    
    public void delete(Caixa caixa) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Caixa.class, caixa.getID()));
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex);
        } finally {
            manager.close();
        }
    }

    
    public void beginTransaction() {
   
        manager.getTransaction().begin();
    }

    
    public void commitTransaction() {
        manager.getTransaction().commit();
    }

    
    public void rollBack() {
        manager.getTransaction().rollback();
    }
}
