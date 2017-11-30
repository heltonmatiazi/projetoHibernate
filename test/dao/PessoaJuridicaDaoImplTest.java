/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Endereco;
import model.PessoaJuridica;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Gerador;

/**
 *
 * @author helton
 */
public class PessoaJuridicaDaoImplTest {
    
    private PessoaJuridica pessoaJuridica;
    private PessoaJuridicaDao pessoaJuridicaDao;
    private Session session;
    private Endereco endereco= new Endereco(null, "endereco_jur", "bairro_jur", 
            "cidade_jur", "estado_jur");
            
    public PessoaJuridicaDaoImplTest() {
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
    }

   
    @Test
    public void testListarTodos() {
        getPessoaJuridica();
        session = HibernateUtil.openSession();
        PessoaJuridicaDaoImpl pessoaJuridicaDaoImpl = new PessoaJuridicaDaoImpl();
        List<PessoaJuridica> list = pessoaJuridicaDaoImpl.listarTodos(session);
        
        assertFalse(list.isEmpty());
    }
     @Test
    public void testeSalvar() {
        session = HibernateUtil.openSession();
        Date date = new Date();
        pessoaJuridica
                = new PessoaJuridica(null, (Gerador.randomString() + "_salvo"), 
                Gerador.randomString(), Gerador.randomString(), 
                        Gerador.randomString(), Gerador.randomString(), 
                        endereco);
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, session);
        session.close();
        assertNotNull(pessoaJuridica.getId());
    }

    @Test
    public void testeAlterar() {
       getPessoaJuridica();
        session = HibernateUtil.openSession();
        pessoaJuridica.setNome(Gerador.randomString() + "_atualizada");
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, session);
        
        PessoaJuridica pessoaAtualizada = pessoaJuridicaDao.
                pesquisaPorId(pessoaJuridica.getId(), session);
        
        assertEquals(pessoaJuridica.getNome(), pessoaAtualizada.getNome());
    }

    @Test
    public void testeExcluir() {
        getPessoaJuridica();
        session = HibernateUtil.openSession();
        pessoaJuridicaDao.excluir(pessoaJuridica, session);

        PessoaJuridica pJuridicaExcluir = pessoaJuridicaDao.
                pesquisaPorId(pessoaJuridica.getId(), session);
        session.close();
        assertNull(pJuridicaExcluir);
    }
    public PessoaJuridica getPessoaJuridica() {
        session = HibernateUtil.openSession();
        Query consulta
                = session.createQuery("select max(id) from PessoaJuridica");
        Long id = (Long) consulta.uniqueResult();
        session.close();
        if (id == null) {
            testeSalvar();
        } else {
            session = HibernateUtil.openSession();
            pessoaJuridica
                    = pessoaJuridicaDao.pesquisaPorId(id, session);
            session.close();
        }
        return pessoaJuridica;
    }
}
