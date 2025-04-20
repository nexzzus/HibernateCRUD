package com.spring.hibernate.service;

import com.spring.hibernate.model.ClienteDTO;

import java.util.List;

public interface IClienteService {
    List<ClienteDTO> listarClientes();
    void agregarCliente(ClienteDTO cliente);
    void actualizarCliente(ClienteDTO clienteDTO);

    ClienteDTO obtenerClientePorId(int id);

    void eliminarCliente(int id);
}
