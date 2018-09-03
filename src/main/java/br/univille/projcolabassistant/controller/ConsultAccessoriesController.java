package br.univille.projcolabassistant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.repository.ConsultAccessoriesRepository;
import br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel;

@Controller
@RequestMapping("/")
public class ConsultAccessoriesController {

	@Autowired
	private ConsultAccessoriesRepository consultAccessoriesRepository;
	
	@GetMapping("")
	public ModelAndView index() {
		List<AssistiveAccessoryViewModel> listAccessory = this.consultAccessoriesRepository.findAllAssistiveAccessoryViewModel();
				
		return new ModelAndView("catalog/accessoryList", "listAccessory", listAccessory);
	}

}