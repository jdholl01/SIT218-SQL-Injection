package edu.deakin.sit218.coachwebapp.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.deakin.sit218.coachwebapp.dao.ClientDAO;
import edu.deakin.sit218.coachwebapp.dao.ClientDAOImpl;
import edu.deakin.sit218.coachwebapp.entity.Client;


@Controller
@RequestMapping("/answer")
public class AnswerController {

	@RequestMapping("/processForm")
	public String workout(
			@Valid @ModelAttribute("client") Client client, 
			BindingResult validationErrors, Model model) {

		if (validationErrors.hasErrors())
			return "question-answer";
		
		ClientDAO dao = new ClientDAOImpl();
		
		if (!dao.existsClient(client)) {
			dao.insertClient(client);
		}
		
		client = dao.retrieveClient(client);
		
		model.addAttribute("message", "Your question '" + client.getQuestion() + "' has been submitted. Thank you!");
		dao.updateClient(client);

		return "answer";
	}
	
}