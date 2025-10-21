package br.unipar.devbackend.agendaenderecos;

import br.unipar.devbackend.agendaenderecos.dao.EnderecoDAO;
import br.unipar.devbackend.agendaenderecos.model.Cliente;
import br.unipar.devbackend.agendaenderecos.model.Endereco;
import br.unipar.devbackend.agendaenderecos.service.ViaCepService;
import br.unipar.devbackend.agendaenderecos.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException, JAXBException {
        ViaCepService viaCep = new ViaCepService();
        Scanner sc = new Scanner(System.in);


        System.out.println("Digite o CEP que deseja buscar: ");
        String cep = sc.nextLine();

        System.out.println(ViaCepService.buscarCep(cep));



//        EntityManagerUtil.getEmf();
//
//        EnderecoDAO dao = new EnderecoDAO(EntityManagerUtil.getEm());
//        List<Endereco> enderecos = dao.findByCep("85975-169");
//
//        for (Endereco end : enderecos) {
//            System.out.println("Cep: " + end.getCep() + ", Logradouro: " + end.getLogradouro()
//                    + ", Complemento: " + end.getComplemento() + ", Bairro: " + end.getBairro()
//                    + ", Localidade: " + end.getLocalidade() + "/" + end.getUf());
//        }
//
//        EntityManagerUtil.closeEmf();


//        EntityManagerUtil.getEmf();
//
//        Endereco end = new Endereco();
//        end.setCep("85975-169");
//        end.setLogradouro("Rua da Silva");
//        end.setComplemento("apto 18");
//        end.setBairro("Centro");
//        end.setLocalidade("Cascavel");
//        end.setUf("PR");
//
//        EnderecoDAO dao = new EnderecoDAO(EntityManagerUtil.getEm());
//        dao.gravar(end);
//
//        EntityManagerUtil.closeEmf();



//        EntityManager em = EntityManagerUtil.getEm();
//        Cliente cli = em.find(Cliente.class, 1);
//
//        try {
//            em.getTransaction().begin();
//            em.remove(cli);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            em.getTransaction().rollback();
//            System.out.println("Algo de errado deu errado");
//        } finally {
//            if (em.isOpen()){
//                em.close();
//                System.out.println("EntityManager fechado.");
//            }
//            System.out.println("Cliente removido.");
//        }
//
//
//
//        EntityManagerUtil.closeEmf();
//
//


//        EntityManagerUtil.getEmf();
//
//        EntityManager em = EntityManagerUtil.getEm(); //responsável pela requisição
//        Cliente cli = em.find(Cliente.class, 1); //encontra cliente id=1
//
//        cli.setEmail("zezo@unipar.br");
//
//        try {
//            em.getTransaction().begin();
//            em.merge(cli); // merge = update != perssite = insert
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//            System.out.println("Erro ao tentar persistir o cliente: " + e.getMessage());
//        } finally {
//            if (em.isOpen()) {
//                em.close();
//            }
//        }


//        List<Cliente> clientes = new ArrayList<>();
//        EntityManager em = EntityManagerUtil.getEm();
//        clientes = em.createQuery("select t from Cliente t", Cliente.class).getResultList();
//
//        for(Cliente cli : clientes){
//        System.out.println(cli.getNome());

//        EntityManagerUtil.getEmf();
//
//        EntityManager em = EntityManagerUtil.getEm();
//        Cliente clt = em.find(Cliente.class, 1L);
//
//        System.out.println("Clt: " + clt.getNome() + ", Email: " + clt.getEmail()
//                + ", data de nascimento: " + clt.getDataNascimento());
//
//        System.out.println("Endereços do Cliente: " + clt.getNome());
//
//        for (Endereco end : clt.getEnderecos()) {
//            System.out.println("Cep: " + end.getCep() + ", Logradouro: " + end.getLogradouro()
//                    + ", Complemento: " + end.getComplemento() + ", Bairro: " + end.getBairro()
//                    + ", Localidade: " + end.getLocalidade() + "/" + end.getUf());
//        }


//        System.out.println("Cliente: " + clt);

//        EntityManagerUtil.getEmf(); // abertura da fabrica
//
//        Cliente clt = new Cliente();
//        clt.setNome("Ana");
//        clt.setEmail("ana.ana@gmail.com");
//        clt.setDataNascimento(new Date("2002/02/20"));
//
//        Endereco end = new Endereco();
//        end.setCep("85975-169");
//        end.setLogradouro("Rua da Silva");
//        end.setComplemento("apto 18");
//        end.setBairro("Centro");
//        end.setLocalidade("Cascavel");
//        end.setUf("PR");
//        end.setClt(clt);
//
//        clt.getEnderecos().add(end);
//
//        EntityManager em = EntityManagerUtil.getEm();
//        try {
//            em.getTransaction().begin(); //abrir uma transação com o banco
//            em.persist(clt); //pega o objeto e insere no banco
//            em.getTransaction().commit(); //fecha comitando
//        } catch (Exception ex){
//            em.getTransaction().rollback();
//            System.out.println("Erro ao persistir o endereço: " + ex.getMessage());
//        } finally {
//            if (em.isOpen()) {
//                em.close();
//                System.out.println("EntityManager fechado.");
//            }
//            EntityManagerUtil.closeEmf();
//        }
    }
}
