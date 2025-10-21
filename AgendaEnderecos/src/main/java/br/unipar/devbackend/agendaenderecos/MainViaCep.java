package br.unipar.devbackend.agendaenderecos;

import br.unipar.devbackend.agendaenderecos.dao.ClienteDAO;
import br.unipar.devbackend.agendaenderecos.dao.EnderecoDAO;
import br.unipar.devbackend.agendaenderecos.model.Cliente;
import br.unipar.devbackend.agendaenderecos.model.Endereco;
import br.unipar.devbackend.agendaenderecos.service.ViaCepService;
import br.unipar.devbackend.agendaenderecos.utils.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.xml.bind.JAXBException;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class MainViaCep {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEm();

        try {
            ClienteDAO clienteDAO = new ClienteDAO(em);
            EnderecoDAO enderecoDAO = new EnderecoDAO(em);

            System.out.println("Digite o CEP que deseja buscar: ");
            String cepDigitado = sc.nextLine();

            //Verificando se o endereço já está no banco de dados
            List<Endereco> enderecosExistentes = enderecoDAO.findByCep(cepDigitado);

            if (!enderecosExistentes.isEmpty()) {

                System.out.println("Endereço(s) encontrado(s) para o CEP " + cepDigitado + ":");

                for (Endereco end : enderecosExistentes) {
                    System.out.println("Cliente: " + end.getCliente().getNome() + ", Endereço: " + end.getLogradouro() + ", " + end.getBairro() + ", " + end.getLocalidade() + "-" + end.getUf());

                }
            } else {
                System.out.println("Endereço não encontrado.");
            }

            //buscando cep no viaCep
            ViaCepService viaCep = new ViaCepService();
            Endereco endereco = viaCep.buscarCep(cepDigitado);

//            System.out.println("CEP: " + endereco.getCep() +
//                    ", Logradouro: " + endereco.getLogradouro() +
//                    ", Bairro: " + endereco.getBairro() +
//                    ", Localidade: " + endereco.getLocalidade() +
//                    "/" + endereco.getUf());

            //obtendo endereço e criando cliente
            Endereco enderecoViaCep = ViaCepService.buscarCep(cepDigitado.toString());

            if (enderecoViaCep != null) {
                System.out.println("Endereço encontrado no ViaCEP: " + enderecoViaCep.getLogradouro() + ", " + enderecoViaCep.getBairro() + ", " + enderecoViaCep.getLocalidade() + "-" + enderecoViaCep.getUf());
                System.out.println("Cadastre um novo cliente para este CEP.");

                System.out.println("Digite o nome do cliente:");
                String nomeCliente = sc.nextLine();

                System.out.println("Digite o email do cliente:");
                String emailCliente = sc.nextLine();

                System.out.println("Digite a data de nascimento do cliente:");
                String dataNascimentoCliente = sc.nextLine();

                //Criado o novo cliente
                //ps: criando no cliente pois o clienteDIO será usado para salvar diretamente no banco
                Cliente novoCliente = new Cliente();
                novoCliente.setNome(nomeCliente);
                novoCliente.setEmail(emailCliente);
                novoCliente.setDataNascimento(dataNascimentoCliente);

                LocalDateTime agora = LocalDateTime.now();
                novoCliente.setDataUltimaAlteracao(agora);

                enderecoViaCep.setCliente(novoCliente);
                novoCliente.getEnderecos().add(enderecoViaCep);

                //persistir o cliente e o endereço
                try {

                    em.getTransaction().begin();
                    em.persist(novoCliente);
                    em.persist(enderecoViaCep);
                    em.getTransaction().commit();
                    System.out.println("Novo cliente e endereço cadastrados com sucesso!");
                    System.out.println("Cliente: " + novoCliente.getNome() + ", Endereço: " + enderecoViaCep.getLogradouro());

                } catch (Exception e) {

                    em.getTransaction().rollback();
                    System.err.println("Erro ao salvar cliente e endereço: " + e.getMessage());

                }

            } else {
                System.out.println("Não foi possível encontrar o endereço para o CEP informado.");
            }

        } catch (IOException e) {

            throw new RuntimeException(e);

        } catch (JAXBException e) {

            throw new RuntimeException(e);

        } catch (Exception e) {

        System.out.println("Algo de errado ocorreu: " + e.getMessage());

        } finally {

            if (em != null && em.isOpen()) {

                em.close();
                System.out.println("EntityManager fechado.");
            }

            EntityManagerUtil.closeEmf(); // Fechar o EntityManagerFactory ao final da aplicação
            sc.close();

        }
    }
}