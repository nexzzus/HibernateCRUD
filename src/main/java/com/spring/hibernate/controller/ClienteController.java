package com.spring.hibernate.controller;

import com.spring.hibernate.model.ClienteDTO;
import com.spring.hibernate.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @InitBinder
    public void miBinder(WebDataBinder binder) {
        StringTrimmerEditor recortaEspaciosBlanco = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, recortaEspaciosBlanco);
    }

    @GetMapping
    public String listarClientes(Model model) {
        List<ClienteDTO> clientes = clienteService.listarClientes();
        model.addAttribute("clientes", clientes);
        return "index";
    }

    @GetMapping("/agregar")
    public String formularioAgregar(Model model) {
        model.addAttribute("cliente", new ClienteDTO());
        return "guardarCliente";
    }

    @PostMapping("/guardar")
    public String agregarCliente(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "guardarCliente";
        } else {
            clienteService.agregarCliente(clienteDTO);
            return "redirect:/";
        }
    }

    @GetMapping("/cliente/{id}")
    public String verCliente(@PathVariable("id") int id, Model model) {
        ClienteDTO clienteDTO = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", clienteDTO);
        return "actualizarCliente";
    }

    @PostMapping("/actualizar")
    public String actualizarCliente(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "actualizarCliente";
        }else {
            clienteService.actualizarCliente(clienteDTO);
            return "redirect:/";
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCliente(@RequestParam("id") int id) {
        clienteService.eliminarCliente(id);
        return "redirect:/";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}
