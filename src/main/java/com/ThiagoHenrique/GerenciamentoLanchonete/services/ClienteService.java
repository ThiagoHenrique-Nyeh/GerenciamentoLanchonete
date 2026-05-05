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
        return clienteRepository.save(cliente);
    }



    public List<Cliente> listarTudo(){
      return  clienteRepository.findAll();
    }

}
