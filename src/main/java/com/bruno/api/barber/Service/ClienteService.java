package com.bruno.api.barber.Service;

import com.bruno.api.barber.Dto.ClienteRespose;
import com.bruno.api.barber.Entity.Cliente;
import com.bruno.api.barber.Repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteRespose> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> new ClienteRespose(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail()
                ))
                .toList();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public Cliente atualizar(Long id, Cliente dados) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dados.getNome());
        cliente.setEmail(dados.getEmail());
        cliente.setTelefone(dados.getTelefone());

        return clienteRepository.save(cliente);



    }

}
