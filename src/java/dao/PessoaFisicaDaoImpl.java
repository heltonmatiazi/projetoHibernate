/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import model.PessoaFisica;

 /*
 * @author helton
 */

public class PessoaFisicaDaoImpl extends BaseDaoImpl<PessoaFisica, Long>
        implements PessoaFisicaDao{
      
    @Override
    public PessoaFisica pesquisaPorId(Long id, Session session) throws HibernateException {
        PessoaFisica pessoaFisica = (PessoaFisica) 
                session.get(PessoaFisica.class, id);
        return pessoaFisica;
    }

    @Override
    public List<PessoaFisica> listarTodos(Session session) throws HibernateException {
        Query consulta = session.createQuery("from PessoaFisica");
        return consulta.list();
    }
}
