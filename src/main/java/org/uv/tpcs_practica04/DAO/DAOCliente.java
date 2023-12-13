/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tpcs_practica04.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.uv.tpcs_practica04.HibernateUtil;
import org.uv.tpcs_practica04.entity.Cliente;
import org.uv.tpcs_practica04.entity.Producto;

/**
 *
 * @author obed
 */
public class DAOCliente implements IDAOGeneral<Cliente, Long>{
    
    @Override
    public Cliente create(Cliente t) {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        
        session.save(t);
        
        transaction.commit();
        session.close();
        
        return t;
    }

    @Override
    public boolean delete(Long id) {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        boolean pase=false;
        Cliente cliente=session.get(Cliente.class, id);
        if(cliente!=null){
            session.delete(cliente);
            transaction.commit();
            pase=true;
        }
        session.close();
        return pase;
    }

    @Override
    public Cliente update(Cliente t, Long id) {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Cliente cliente=session.get(Cliente.class, id);
        if(cliente!=null){
            session.merge(t);
            transaction.commit();
        }
        
        session.close();
        return t;
        
    }

    @Override
    public List<Cliente> findAll() {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        
        List<Cliente> clientes=session.createQuery("From Cliente e order by e.clienteId").list();
        
        transaction.commit();
        session.close();
        
        return clientes;
    }

    @Override
    public Cliente findByID(Long id) {
        Session session=HibernateUtil.getSession();
        
        Cliente cliente=session.get(Cliente.class, id);
        
        session.close();
        
        return cliente;
    }
    
}
