package br.com.alura.spring.mvc.mudi.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.spring.mvc.mudi.model.Pedido;
import br.com.alura.spring.mvc.mudi.model.StatusPedido;
import br.com.alura.spring.mvc.mudi.respository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PedidoRepository repository;

	@GetMapping
	public String home(Model model, Principal principal) {

		model.addAttribute("pedidos", repository.findByUser(principal.getName()));
		model.addAttribute("status", "todos");

		return "home";
	}

	@GetMapping("/{status}")
	public String findByStatus(@PathVariable("status") String status, Model model, Principal principal) {

		Collection<Pedido> pedidos = repository.findByStatusPedido(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);

		return "home";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";

	}

}
