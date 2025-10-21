package br.unipar.devbackend.agendaenderecos.model;

import jakarta.persistence.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.StringReader;

@Getter
@Setter
@ToString
@Entity
@XmlRootElement(name = "xmlcep") //raiz que vem da api
@XmlAccessorType(XmlAccessType.FIELD) //os campos são os atributos
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    @ManyToOne
    private Cliente cliente;

    //metodo que converte STRING em ENDERECO
    public static Endereco unmarshalFromString(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Endereco.class); //cria o contexto
        Unmarshaller unmarshaller = context.createUnmarshaller(); //objeto que faz a conversão
        StringReader reader = new StringReader(xml); //lê a string
        return (Endereco) unmarshaller.unmarshal(reader); //converte a string em objeto e retorna
    }
}