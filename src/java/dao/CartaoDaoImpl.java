/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cartao;
import model.PessoaFisica;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author helton
 */
public class CartaoDaoImpl extends BaseDaoImpl<Cartao, Long>
        implements CartaoDao{
    
    @Override
    public Cartao pesquisaPorId(Long id, Session session) throws HibernateException {
        Cartao cartao = (Cartao) 
                session.get(Cartao.class, id);
        return cartao;
    }

    @Override
    public List<Cartao> listarTodos(Session session) throws HibernateException {
        Query consulta = session.createQuery("from Cartao");
        return consulta.list();
    }
}
