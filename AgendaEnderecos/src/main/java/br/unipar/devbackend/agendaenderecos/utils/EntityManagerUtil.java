package br.unipar.devbackend.agendaenderecos.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private EntityManagerUtil() {}

    public static EntityManagerFactory getEmf() { // cria a fabrica

            emf = Persistence
                    .createEntityManagerFactory("agendaEnderecosPU");
            System.out.println("Conexão com o banco de dados estabelecida.");

        return emf;
    }

        public static void closeEmf() { // fecha a fabrica (chamar quando for fechar o sistema)
        if (emf != null && emf.isOpen()){
            emf.close();
            System.out.println("Conexão com o banco de dados encerrada.");
        }
    }

    public static EntityManager getEm() { // retorna o gerenciador de requisições no banco de dados
        if (em == null || !em.isOpen()) {
            em = getEmf().createEntityManager();
            System.out.println("EntityManager criado.");
        }
        return em;
    }


}
