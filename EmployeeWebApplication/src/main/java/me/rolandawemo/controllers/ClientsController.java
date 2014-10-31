package me.rolandawemo.controllers;

import java.util.ArrayList;
import java.util.Map;

import me.rolandawemo.clients.implementations.ClientManagementClient;
import me.rolandawemo.datatypes.Query;
import me.rolandawemo.models.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clients/")
public class ClientsController {

	private ArrayList<String> clientsTypes;
	@Autowired
	private ClientManagementClient clientClient;
	private ArrayList<String> prefixes;

	public ClientsController() {
		this.clientsTypes = new ArrayList<String>();
		this.clientsTypes.add("supplier");
		this.clientsTypes.add("consumer");
		this.prefixes = new ArrayList<String>();
		this.prefixes.add("Mr");
		this.prefixes.add("Mrs");
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model,
			@ModelAttribute Query query,
			@ModelAttribute("clients") ArrayList<Client> clients,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		// If client was searched, populate with the results of search instead
		ArrayList<Client> displayedClients = new ArrayList<Client>();
		if (!clients.isEmpty()) {
			displayedClients = clients;
		} else {
			displayedClients = this.clientClient.list();
		}
		model.addAttribute("clients", displayedClients);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "clients/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		Client client = new Client();
		model.addAttribute("client", client);
		model.addAttribute("clientsTypes", this.clientsTypes);
		model.addAttribute("prefixes", this.prefixes);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "clients/add";
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public String addClient(@ModelAttribute Client client, Model model,
			RedirectAttributes attributes) {
		if (this.clientClient.add(client.getPrefix(), client.getFirstName(),
				client.getLastName(), client.getCompany(), client.getType())) {
			attributes.addFlashAttribute("flashMessage",
					"Client was successfully added.");
			attributes.addFlashAttribute("flashClass", "alert-success");
			return "redirect:/clients/list";
		} else {
			attributes.addFlashAttribute("flashMessage",
					"Client could not be added.");
			attributes.addFlashAttribute("flashClass", "alert-danger");
			return "redirect:/clients/add";
		}
	}

	@RequestMapping(value = "/searchClients", method = RequestMethod.POST)
	public String searchClients(@ModelAttribute Query query, Model model,
			RedirectAttributes attributes) {
		ArrayList<Client> clients = this.clientClient.getClientsByQuery(query.getString());
		attributes.addFlashAttribute("clients", clients);
		System.out.println(clients.get(0).getFirstName());
		return "redirect:/clients/list";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model,
			@RequestParam Map<String, String> parameters,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		int id = Integer.parseInt(parameters.get("id"));
		Client client = this.clientClient.getClientById(id);
		model.addAttribute("client", client);
		model.addAttribute("clientsTypes", this.clientsTypes);
		model.addAttribute("prefixes", this.prefixes);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "clients/edit";
	}

	@RequestMapping(value = "/editClient", method = RequestMethod.POST)
	public String editClient(@ModelAttribute Client client, Model model,
			RedirectAttributes attributes) {
		if (this.clientClient.edit(client.getId(), client.getPrefix(),
				client.getFirstName(), client.getLastName(),
				client.getCompany(), client.getType())) {
			attributes.addFlashAttribute("flashMessage",
					"Client was successfully edited.");
			attributes.addFlashAttribute("flashClass", "alert-success");
			return "redirect:/clients/list";
		} else {
			attributes.addFlashAttribute("flashMessage",
					"Client could not be edited.");
			attributes.addFlashAttribute("flashClass", "alert-danger");
			return "redirect:/clients/edit?id=" + client.getId();
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model,
			@RequestParam Map<String, String> parameters,
			RedirectAttributes attributes) {
		int id = Integer.parseInt(parameters.get("id"));
		boolean deleted = this.clientClient.delete(id);
		if (deleted) {
			attributes.addFlashAttribute("flashMessage",
					"Client was successfully deleted.");
			attributes.addFlashAttribute("flashClass", "alert-success");
		} else {
			attributes.addFlashAttribute("flashMessage",
					"An error occurred while deleting the client.");
			attributes.addFlashAttribute("flashClass", "alert-danger");
		}
		return "redirect:/clients/list";
	}
}
