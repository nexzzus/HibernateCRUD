package com.spring.hibernate.service;

import com.spring.hibernate.model.Cliente;
import com.spring.hibernate.model.ClienteDTO;
import com.spring.hibernate.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> new ClienteDTO(
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public void agregarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        clienteRepository.save(cliente);
    }

    @Override
    public void actualizarCliente(ClienteDTO clienteDTO){
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        clienteRepository.save(cliente);
    }

    @Override
    public ClienteDTO obtenerClientePorId(int id){
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public void eliminarCliente(int id){
        clienteRepository.deleteById(id);
    }
}
