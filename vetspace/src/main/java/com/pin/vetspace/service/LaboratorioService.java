package com.pin.vetspace.service;

import java.util.List;
import com.pin.vetspace.model.Laboratorio;

public interface LaboratorioService {
	
    Laboratorio cadastrarLaboratorio(Laboratorio laboratorio);
    
    List<Laboratorio> listarLaboratorios();
    
    Laboratorio atualizarLaboratorio(Long laboratorioId, Laboratorio laboratorio) throws Exception;
    
    void excluirLaboratorio(Long laboratorioId) throws Exception;
}


