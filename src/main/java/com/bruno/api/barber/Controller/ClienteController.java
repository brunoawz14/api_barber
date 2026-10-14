package com.bruno.api.barber.Controller;

import com.bruno.api.barber.Dto.ClienteRespose;
import com.bruno.api.barber.Entity.Cliente;
import com.bruno.api.barber.Service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    public ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteRespose>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.salvar(cliente);

        return ResponseEntity.status(201).body(clienteSalvo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente clienteId = clienteService.findById(id);

        return ResponseEntity.status(201).body(clienteId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {

        Cliente atualizado = clienteService.atualizar(id, cliente);

        return ResponseEntity.ok(atualizado);
    }




}



