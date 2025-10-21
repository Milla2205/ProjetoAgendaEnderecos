package br.unipar.devbackend.agendaenderecos.dao;

import br.unipar.devbackend.agendaenderecos.model.Cliente;
import br.unipar.devbackend.agendaenderecos.model.Endereco;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDAO {
    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public ClienteDAO() {}

    public Cliente gravar(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println("Cliente editado com sucesso!");
            return cliente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao gravar: " + e.getMessage());
            return null;
        } finally {
            if (em.isOpen()) {
                em.close();
                System.out.println("EntityManager fechado com sucesso!");
            }
        }
    }

    public Cliente editar(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            System.out.println("Cliente editado com sucesso!");
            return cliente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao editar: " + e.getMessage());
            return null;
        } finally{
            if (em.isOpen()) {
                em.close();
                System.out.println("EntityManager fechado com sucesso!");
            }
        }
    }

    public Cliente findById(Long id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> findByCpf(String cpf) {
        return em.createQuery("SELECT t FROM Cliente t WHERE t.cpf = :p_cpf", Cliente.class)
                .setParameter("p_cpf", cpf).getResultList(); // para retrornar mais de um endereço por CEP
    }

    public List<Cliente> findAll() {
        return em.createQuery("SELECT t FROM Cliente t", Cliente.class)
                .getResultList(); // para pegar todos os clientes
    }

    public Boolean deletar(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
            System.out.println("Cliente removido com sucesso!");
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao remover: " + e.getMessage());
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
