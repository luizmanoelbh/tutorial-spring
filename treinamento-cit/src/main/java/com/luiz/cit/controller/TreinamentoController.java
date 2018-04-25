package com.luiz.cit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luiz.cit.model.TipoVinho;
import com.luiz.cit.model.Vinho;
import com.luiz.cit.repository.Vinhos;

@Controller
@RequestMapping("/treinamento")
public class TreinamentoController {

	@Autowired
	private Vinhos vinhos;

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		return novo(vinhos.findOne(id));
	}

	@GetMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro-vinho");

		modelAndView.addObject(vinho);
		modelAndView.addObject("tipos", TipoVinho.values());
		return modelAndView;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(vinho);
		}
		vinhos.save(vinho);

		attributes.addFlashAttribute("mensagem", "Vinho cadastrado com Sucesso!");

		return new ModelAndView("redirect:/treinamento/novo");
	}
}