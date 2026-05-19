package com.ThiagoHenrique.GerenciamentoLanchonete.services;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Cliente;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){
        if (cliente.getNome()==null || cliente.getNome().trim().isEmpty()){
            throw new RuntimeException("NOME NAO PODE SER VAZIO");
        }
        if(cliente.getTelefone()==null || cliente.getTelefone().trim().isEmpty()){
            throw new RuntimeException("TELEFONE NAO PODE SER VAZIO");
        }
        if(cliente.getEndereco()==null || cliente.getEndereco().trim().isEmpty()){
            throw new RuntimeException("ENDERECO NAO PODE SER VAZIO");
        }
        return clienteRepository.save(cliente);
    }



    public List<Cliente> listarTudo(){
        return clienteRepository.findAll();
    }




    public Cliente buscaId(Long id){
       return clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("CLIENTE DE ID:"+id+ "NAO FOI ENCONTRADO :( "));
    }



    public void deletar(Long id){
     Cliente cliente = buscaId(id);
     clienteRepository.delete(cliente);
    }

}
