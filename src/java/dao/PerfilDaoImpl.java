/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import model.Perfil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author helton
 */
public class PerfilDaoImpl extends BaseDaoImpl<Perfil, Long>
        implements PerfilDao{

    @Override
    public Perfil pesquisaPorId(Long id, Session session) throws HibernateException {
        Perfil perfil = (Perfil) 
                session.get(Perfil.class, id);
        return perfil;
    }

    @Override
    public List<Perfil> listarTodos(Session session) throws HibernateException {
       Query consulta = session.createQuery("from Perfil");
        return consulta.list();
    }
    
}
