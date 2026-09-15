package com.bruno.api.barber.Service;

import com.bruno.api.barber.Entity.Servicos;
import com.bruno.api.barber.Repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Servicos salvar(Servicos servicos) {
        return servicoRepository.save(servicos);
    }





}
