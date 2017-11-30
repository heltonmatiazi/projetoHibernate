/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import model.Cartao;
import model.Endereco;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.Pessoa;
import model.Perfil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author helton
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
             Configuration cfg = new Configuration();
             cfg.addAnnotatedClass(Perfil.class);
             cfg.addAnnotatedClass(Endereco.class);
             cfg.addAnnotatedClass(Pessoa.class);
             cfg.addAnnotatedClass(Cartao.class);
             cfg.addAnnotatedClass(PessoaFisica.class);
             cfg.addAnnotatedClass(PessoaJuridica.class);
             
             cfg.configure("/dao/hibernate.cfg.xml");
             StandardServiceRegistryBuilder build = 
                 new StandardServiceRegistryBuilder().
                               applySettings(cfg.getProperties());
             sessionFactory = cfg.buildSessionFactory(build.build());
             
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Erro ao criar fábrica coneção" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
