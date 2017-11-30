/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cartao;
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
public class CartaoDaoImplTest {
    
    private Cartao cartao;
    private CartaoDao cartaoDao;
    private PessoaFisica pessoaFisica;
    private PessoaFisicaDaoImplTest psImpl;
    
    public CartaoDaoImplTest() {
        cartaoDao = new CartaoDaoImpl();
    }
   
    private Session session;
     
   @Test
    public void testeSalvar() {
        session = HibernateUtil.openSession();
        psImpl = new PessoaFisicaDaoImplTest();
        pessoaFisica = psImpl.getPessoaFisica();
        cartao = new Cartao();
        cartao.setAnoVencimento(Gerador.randomString());
        cartao.setBandeira(Gerador.randomString()+ " salvo");
        cartao.setNumero(Gerador.randomString());
        cartao.setPessoaFisica(pessoaFisica);
        
        cartaoDao.salvarOuAlterar(cartao, session);
        session.close();
        assertNotNull(cartao.getId());
    }

    @Test
    public void testeAlterar() {
       getCartao();
        session = HibernateUtil.openSession();
        cartao.setBandeira(Gerador.randomString() + "_atualizada");
        cartaoDao.salvarOuAlterar(cartao, session);
        
        Cartao cartaoAtualizado = cartaoDao.
                pesquisaPorId(cartao.getId(), session);
        
        assertEquals(cartao.getBandeira(), cartaoAtualizado.getBandeira());
    }

    @Test
    public void testeExcluir() {
        getCartao();
        session = HibernateUtil.openSession();
        cartaoDao.excluir(cartao, session);

        Cartao cartaoExcluir = cartaoDao.
                pesquisaPorId(cartao.getId(), session);
        session.close();
        assertNull(cartaoExcluir);
    }

    
    @Test
    public void testListarTodos() {
        getCartao();
        session = HibernateUtil.openSession();
        CartaoDaoImpl cartaoDaoImpl = new CartaoDaoImpl();
        List<Cartao> list;
        list = cartaoDaoImpl.listarTodos(session);
        
        assertFalse(list.isEmpty());
    }
    public Cartao getCartao() {
        session = HibernateUtil.openSession();
        Query consulta
                = session.createQuery("select max(id) from Cartao");
        Long id = (Long) consulta.uniqueResult();
        session.close();
        if (id == null) {
            testeSalvar();
        } else {
            session = HibernateUtil.openSession();
            cartao
                    = cartaoDao.pesquisaPorId(id, session);
            session.close();
        }
        return cartao;
    }
    
}
