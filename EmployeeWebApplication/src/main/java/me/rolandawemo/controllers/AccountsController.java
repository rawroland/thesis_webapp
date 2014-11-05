package me.rolandawemo.controllers;

import java.util.Map;

import me.rolandawemo.clients.implementations.AccountManagementClient;
import me.rolandawemo.clients.implementations.ClientManagementClient;
import me.rolandawemo.datatypes.Query;
import me.rolandawemo.models.Account;
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
@RequestMapping("/accounts/")
public class AccountsController {

	@Autowired
	private AccountManagementClient accountClient;
	@Autowired
	private ClientManagementClient clientClient;

	public AccountsController() {
	}

	@RequestMapping(value = "/consumers", method = RequestMethod.GET)
	public String consumers(Model model, @ModelAttribute Query query,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		model.addAttribute("clients",
				this.clientClient.getClientsByType("consumer"));
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "accounts/consumers";
	}

	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String company(Model model, @ModelAttribute Query query,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		model.addAttribute("clients",
				this.clientClient.getClientsByType("company"));
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "accounts/company";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@RequestParam Map<String, String> parameters,
			RedirectAttributes attributes) {
		int clientId = Integer.parseInt(parameters.get("clientId"));
		boolean added = this.accountClient.add(clientId);
		String flashClass;
		String flashMessage = flashClass = "";
		if (added) {
			flashMessage = "Account was successfully added";
			flashClass = "alert-success";
		} else {
			flashMessage = "Account was not added";
			flashClass = "alert-danger";
		}
		attributes.addAttribute("flashMessage", flashMessage);
		attributes.addAttribute("flashClass", flashClass);
		return "redirect:/accounts/consumers";
	}

	@RequestMapping(value = "/credit", method = RequestMethod.GET)
	public String credit(Model model,
			@RequestParam Map<String, String> parameters,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		int clientId = Integer.parseInt(parameters.get("clientId"));
		Client client = this.clientClient.getClientById(clientId);
		model.addAttribute("client", client);
		Account account = new Account();
		account.setId(client.getAccount().getId());
		account.setClientId(client.getId());
		account.setAmount(0);
		model.addAttribute("account", account);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "accounts/credit";
	}

	@RequestMapping(value = "/creditAccount", method = RequestMethod.POST)
	public String creditAccount(@ModelAttribute Account account, Model model,
			RedirectAttributes attributes) {
		if (this.accountClient.credit(account.getClientId(),
				account.getAmount())) {
			attributes.addFlashAttribute("flashMessage",
					"Account was successfully credited.");
			attributes.addFlashAttribute("flashClass", "alert-success");
		} else {
			attributes.addFlashAttribute("flashMessage",
					"Account could not be credited.");
			attributes.addFlashAttribute("flashClass", "alert-danger");
		}

		return "redirect:/accounts/credit?clientId=" + account.getClientId();
	}

	@RequestMapping(value = "/debit", method = RequestMethod.GET)
	public String debit(Model model,
			@RequestParam Map<String, String> parameters,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		int clientId = Integer.parseInt(parameters.get("clientId"));
		Client client = this.clientClient.getClientById(clientId);
		model.addAttribute("client", client);
		Account account = new Account();
		account.setId(client.getAccount().getId());
		account.setClientId(client.getId());
		account.setAmount(0);
		model.addAttribute("account", account);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "accounts/debit";
	}

	@RequestMapping(value = "/debitAccount", method = RequestMethod.POST)
	public String debitAccount(@ModelAttribute Account account, Model model,
			RedirectAttributes attributes) {
		if (this.accountClient.debit(account.getClientId(),
				account.getAmount())) {
			attributes.addFlashAttribute("flashMessage",
					"Account was successfully debited.");
			attributes.addFlashAttribute("flashClass", "alert-success");
		} else {
			attributes.addFlashAttribute("flashMessage",
					"Account could not be debited.");
			attributes.addFlashAttribute("flashClass", "alert-danger");
		}
		return "redirect:/accounts/debit?clientId=" + account.getClientId();
	}

}
