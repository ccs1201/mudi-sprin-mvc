package br.com.ccs.mudi.controller;

import java.security.Principal;

import javax.validation.Valid;

import br.com.ccs.mudi.respository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ccs.mudi.dto.RequisicaoDTO;
import br.com.ccs.mudi.model.Pedido;
import br.com.ccs.mudi.model.User;
import br.com.ccs.mudi.respository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	
	private final PedidoRepository repository;
	
	
	private final UserRepository userRepository;
	
	PedidoController(PedidoRepository repository, UserRepository userRepository){
		this.repository =  repository;
		this.userRepository= userRepository;
		
	}

	@GetMapping("formulario")
	public String formulario(RequisicaoDTO requisicao) {
		return "pedido/formulario";
	}

	@PostMapping("novo")
	public String novoPedido(@Valid RequisicaoDTO requisicaoDTO, BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return "pedido/formulario";
		}
		
		User user = userRepository.findByUsername(principal.getName());

		Pedido pedido = requisicaoDTO.toPedido();
		pedido.setUser(user);

		repository.save(pedido);

		return "redirect:/home";
	}

}
