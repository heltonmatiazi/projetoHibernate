/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Endereco;
import model.PessoaFisica;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Gerador;

/**
 *
 * @author helton
 */
public class PessoaFisicaDaoImplTest {
    
    private PessoaFisica pessoaFisica;
    private PessoaFisicaDao pessoaFisicaDao;
    public PessoaFisicaDaoImplTest() {
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
    }
    private Endereco endereco= new Endereco(null, "endereco_1", "bairro_1", 
            "cidade_1", "estado_1");
    private Session session;
     
   @Test
    public void testeSalvar() {
        session = HibernateUtil.openSession();
        Date date = new Date();
        pessoaFisica
                = new PessoaFisica(null, (Gerador.randomString() + "_salvo"), 
                Gerador.randomString(), Gerador.randomString(), 
                        Gerador.randomString(), Gerador.randomString(), 
                        endereco);
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, session);
        session.close();
        assertNotNull(pessoaFisica.getId());
    }

    @Test
    public void testeAlterar() {
       getPessoaFisica();
        session = HibernateUtil.openSession();
        pessoaFisica.setNome(Gerador.randomString() + "_atualizada");
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, session);
        
        PessoaFisica pessoaAtualizada = pessoaFisicaDao.
                pesquisaPorId(pessoaFisica.getId(), session);
        
        assertEquals(pessoaFisica.getNome(), pessoaAtualizada.getNome());
    }

    @Test
    public void testeExcluir() {
        getPessoaFisica();
        session = HibernateUtil.openSession();
        pessoaFisicaDao.excluir(pessoaFisica, session);

        PessoaFisica pFisicaExcluir = pessoaFisicaDao.
                pesquisaPorId(pessoaFisica.getId(), session);
        session.close();
        assertNull(pFisicaExcluir);
    }

    
    @Test
    public void testListarTodos() {
        getPessoaFisica();
        session = HibernateUtil.openSession();
        PessoaFisicaDaoImpl pessoaFisicaDaoImpl = new PessoaFisicaDaoImpl();
        List<PessoaFisica> list;
        list = pessoaFisicaDaoImpl.listarTodos(session);
        
        assertFalse(list.isEmpty());
    }
    public PessoaFisica getPessoaFisica() {
        session = HibernateUtil.openSession();
        Query consulta
                = session.createQuery("select max(id) from PessoaFisica");
        Long id = (Long) consulta.uniqueResult();
        session.close();
        if (id == null) {
            testeSalvar();
        } else {
            session = HibernateUtil.openSession();
            pessoaFisica
                    = pessoaFisicaDao.pesquisaPorId(id, session);
            session.close();
        }
        return pessoaFisica;
    }
}
