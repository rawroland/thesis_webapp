package me.rolandawemo.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import me.rolandawemo.clients.implementations.ClientManagementClient;
import me.rolandawemo.clients.implementations.ProductManagementClient;
import me.rolandawemo.clients.implementations.TransactionManagementClient;
import me.rolandawemo.models.Client;
import me.rolandawemo.models.Product;
import me.rolandawemo.models.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transactions/")
public class TransactionsController {

	@Autowired
	private TransactionManagementClient transactionClient;
	@Autowired
	private ClientManagementClient clientClient;
	@Autowired
	private ProductManagementClient productClient;

	public TransactionsController() {
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public String purchase(Model model,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		Transaction transaction = new Transaction();
		transaction.setType("purchase");
		Client company = this.clientClient.getClientsByType("company").get(0);
		ArrayList<Product> products = this.productClient.list();
		Map<Integer, String> productsList = new LinkedHashMap<Integer, String>();
		if (null != products) {
			for (Product product : products) {
				String productName = new StringBuilder()
						.append(product.getName()).append(" [Supplier: ")
						.append(product.getClient().getCompany()).append("]")
						.toString();
				productsList.put(product.getId(), productName);
			}
		}
		model.addAttribute("transaction", transaction);
		model.addAttribute("company", company);
		model.addAttribute("productsList", productsList);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "transactions/purchase";
	}

	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public String sale(Model model,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		Transaction transaction = new Transaction();
		transaction.setType("sale");
		ArrayList<Client> clients = this.clientClient
				.getClientsByType("consumer");
		Map<Integer, String> clientsList = new LinkedHashMap<Integer, String>();
		if (null != clients) {
			for (Client client : clients) {
				clientsList.put(client.getAccount().getId(),
						client.getCompany());
			}

		}
		ArrayList<Product> products = this.productClient.list();
		Map<Integer, String> productsList = new LinkedHashMap<Integer, String>();
		if (null != products) {
			for (Product product : products) {
				String productName = new StringBuilder()
						.append(product.getName()).append(" [Supplier: ")
						.append(product.getClient().getCompany()).append("]")
						.toString();
				productsList.put(product.getId(), productName);
			}
		}
		model.addAttribute("transaction", transaction);
		model.addAttribute("clientsList", clientsList);
		model.addAttribute("productsList", productsList);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "transactions/sale";
	}

	@RequestMapping(value = "/saveTransaction", method = RequestMethod.POST)
	public String saveTransaction(@ModelAttribute Transaction transaction,
			Model model, RedirectAttributes attributes,
			HttpServletRequest request) {
		if (this.transactionClient.saveTransactions(transaction.getAccountId(),
				transaction.getQuantity(), transaction.getProductId(),
				transaction.getType(), transaction.getCost(),
				transaction.getDate())) {
			attributes.addFlashAttribute("flashMessage",
					"Transaction was successfully saved.");
			attributes.addFlashAttribute("flashClass", "alert-success");
		} else {
			attributes.addFlashAttribute("flashMessage",
					"Transaction could not be saved.");
			attributes.addFlashAttribute("flashClass", "alert-danger");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
