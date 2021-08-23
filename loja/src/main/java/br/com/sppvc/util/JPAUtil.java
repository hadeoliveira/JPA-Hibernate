package br.com.sppvc.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

// Interface que faz interface com o banco de dados, antes feito pelo Connection
// Qualquer tipo de operação no banco, é utilizado o EntityManager


    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("loja");

    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }

}
