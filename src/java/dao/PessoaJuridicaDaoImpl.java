/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import model.PessoaJuridica;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author helton
 */
public class PessoaJuridicaDaoImpl extends BaseDaoImpl<PessoaJuridica, Long>
        implements PessoaJuridicaDao {

    @Override
    public PessoaJuridica pesquisaPorId(Long id, Session session) throws HibernateException {
         PessoaJuridica pessoaJuridica = (PessoaJuridica) 
                session.get(PessoaJuridica.class, id);
        return pessoaJuridica;
    }

    @Override
    public List<PessoaJuridica> listarTodos(Session session) throws HibernateException {
         Query consulta = session.createQuery("from PessoaJuridica");
        return consulta.list();
    }
    
}
