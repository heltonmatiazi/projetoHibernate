/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Perfil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Gerador;

/**
 *
 * @author helton
 */
public class PerfilDaoImplTest {
    private Perfil perfil;
    private PerfilDao perfilDao;
    private Session session;

    public PerfilDaoImplTest() {
        perfilDao = new PerfilDaoImpl();
    }

   @Test
    public void testeSalvar() {
        session = HibernateUtil.openSession();
        Date date = new Date();
        perfil
                = new Perfil(null, Gerador.randomString() + "_salvar", 
                        date, "obs");
        perfilDao.salvarOuAlterar(perfil, session);
        session.close();
        assertNotNull(perfil.getId());
    }

    @Test
    public void testeAlterar() {
        getPerfil();
        session = HibernateUtil.openSession();
        perfil.setNome(Gerador.randomString() + "_atualizado");
        perfilDao.salvarOuAlterar(perfil, session);
        
        Perfil updatedPerfil = perfilDao.
                pesquisaPorId(perfil.getId(), session);
        
        assertEquals(perfil.getNome(), updatedPerfil.getNome());
        
    }

    @Test
    public void testeListarTodos() {
        getPerfil();
        session = HibernateUtil.openSession();
        PerfilDaoImpl profileDaoImpl = new PerfilDaoImpl();
        List<Perfil> list = profileDaoImpl.listarTodos(session);
        
        assertFalse(list.isEmpty());
    }
 @Test
    public void testeExcluir() {
        getPerfil();
        session = HibernateUtil.openSession();
        perfilDao.excluir(perfil, session);

        Perfil perfilExcluir = perfilDao.
                pesquisaPorId(perfil.getId(), session);
        session.close();
        assertNull(perfilExcluir);
    }
    private void getPerfil() {
        session = HibernateUtil.openSession();
        Query consulta
                = session.createQuery("select max(id) from Perfil");
        Long id = (Long) consulta.uniqueResult();
        session.close();
        if (id == null) {
            testeSalvar();
        } else {
            session = HibernateUtil.openSession();
            perfil = 
                    perfilDao.pesquisaPorId(id, session);
            session.close();
        }

    }

}
