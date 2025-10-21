package br.unipar.devbackend.agendaenderecos.dao;

import br.unipar.devbackend.agendaenderecos.model.Endereco;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EnderecoDAO {

    private EntityManager em;

    public EnderecoDAO(EntityManager em) {
        this.em = em;
    }

    public EnderecoDAO() {

    }

    public Endereco gravar(Endereco endereco) {
        try {
            em.getTransaction().begin();
            em.persist(endereco);
            em.getTransaction().commit();
            System.out.println("Endereco editado com sucesso!");
            return endereco;
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

    public Endereco editar(Endereco endereco) {
        try {
            em.getTransaction().begin();
            em.merge(endereco);
            em.getTransaction().commit();
            System.out.println("Endereco editado com sucesso!");
            return endereco;
        } catch  (Exception e) {
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

    public Endereco findById(Long id) {
        return em.find(Endereco.class, id);
    }

    public List<Endereco> findByCep(String cep) {
        return em.createQuery("SELECT t FROM Endereco t WHERE t.cep = :p_cep", Endereco.class)
                .setParameter("p_cep", cep).getResultList(); // para retrornar mais de um endereço por CEP
    }

    public List<Endereco> findAll() {
        return em.createQuery("SELECT t FROM Endereco t", Endereco.class)
                .getResultList(); // para pegar todos os endereços
    }

    public Boolean deletar(Endereco endereco) {
        try {
            em.getTransaction().begin();
            em.remove(endereco);
            em.getTransaction().commit();
            System.out.println("Endereco removido com sucesso!");
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
