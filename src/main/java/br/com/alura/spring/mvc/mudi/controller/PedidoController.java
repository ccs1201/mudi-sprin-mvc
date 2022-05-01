package br.com.alura.spring.mvc.mudi.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.spring.mvc.mudi.dto.RequisicaoDTO;
import br.com.alura.spring.mvc.mudi.model.Pedido;
import br.com.alura.spring.mvc.mudi.model.User;
import br.com.alura.spring.mvc.mudi.respository.PedidoRepository;
import br.com.alura.spring.mvc.mudi.respository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	
	private PedidoRepository repository;
	
	
	private UserRepository userRepository;
	
	PedidoController(PedidoRepository repository, UserRepository userRepository){
		this.repository =  repository;
		this.userRepository= userRepository;
		
	}

	@GetMapping("formulario")
	public String formulario(RequisicaoDTO requisicao) {
		return "pedido/formulario";
	}

	@PostMapping("novo")
	public String novoPedido(@Valid RequisicaoDTO requisição, BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return "pedido/formulario";
		}
		
		User user = userRepository.findByUsername(principal.getName());

		Pedido pedido = requisição.toPedido();
		pedido.setUser(user);

		repository.save(pedido);

		return "redirect:/home";
	}

}
