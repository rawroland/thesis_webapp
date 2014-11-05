package me.rolandawemo.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import me.rolandawemo.clients.implementations.ClientManagementClient;
import me.rolandawemo.clients.implementations.ProductManagementClient;
import me.rolandawemo.clients.implementations.ReportingManagementClient;
import me.rolandawemo.clients.implementations.TransactionManagementClient;
import me.rolandawemo.datatypes.Query;
import me.rolandawemo.models.Client;
import me.rolandawemo.models.Product;
import me.rolandawemo.models.ReportingGroup;
import me.rolandawemo.models.Transaction;
import me.rolandawemo.models.TransactionQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reports/")
public class ReportsController {

	@Autowired
	private ReportingManagementClient reportingClient;
	@Autowired
	private ClientManagementClient clientClient;

	public ReportsController() {
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		ReportingGroup reportingGroup = new ReportingGroup();
		ArrayList<Client> clients = this.clientClient.list();
		Map<Integer, String> consumersList = new LinkedHashMap<Integer, String>();
		Map<Integer, String> suppliersList = new LinkedHashMap<Integer, String>();
		for (Client client : clients) {
			if ("supplier".equals(client.getType())) {
				suppliersList.put(client.getId(), client.getCompany());
			} else {
				consumersList.put(client.getId(), client.getCompany());
			}

		}
		Map<String, Map<Integer, String>> clientsList = new LinkedHashMap<String, Map<Integer, String>>();
		clientsList.put("Consumers", consumersList);
		clientsList.put("Suppliers", suppliersList);
		model.addAttribute("reportingGroup", reportingGroup);
		model.addAttribute("clientsList", clientsList);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "reports/create";
	}

	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	public String createGroup(@ModelAttribute ReportingGroup reportingGroup,
			Model model, RedirectAttributes attributes,
			HttpServletRequest request) {
		if (this.reportingClient.createReportingGroup(reportingGroup.getName(),
				reportingGroup.getClients())) {
			attributes.addFlashAttribute("flashMessage",
					"Group was successfully created.");
			attributes.addFlashAttribute("flashClass", "alert-success");
		} else {
			attributes.addFlashAttribute("flashMessage",
					"Group could not be created.");
			attributes.addFlashAttribute("flashClass", "alert-danger");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/saleReport", method = RequestMethod.GET)
	public String saleReport(Model model,
			@ModelAttribute TransactionQuery query,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		ArrayList<ReportingGroup> reportingGroups = this.reportingClient
				.getAllReportingGroups();
		ArrayList<Client> clients = this.clientClient
				.getClientsByType("consumer");
		Map<Integer, String> consumersList = new LinkedHashMap<Integer, String>();
		for (Client client : clients) {
			consumersList.put(client.getAccount().getId(), client.getCompany());

		}
		Map<Integer, String> groupsList = new LinkedHashMap<Integer, String>();
		if (null != reportingGroups) {
			for (ReportingGroup group : reportingGroups) {
				groupsList.put(group.getId(), group.getName());

			}
		}
		query.setType("sale");
		model.addAttribute("accounts", consumersList);
		model.addAttribute("query", query);
		model.addAttribute("groups", groupsList);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "reports/saleReport";
	}

	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public String report(Model model, @ModelAttribute TransactionQuery query,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass,
			RedirectAttributes attributes) {
		ArrayList<Transaction> transactions = this.reportingClient
				.generateReport(query);
		attributes.addFlashAttribute("transactions", transactions);
		// model.addAttribute("query", query);
		model.addAttribute("transactions", transactions);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "reports/report";
	}
	//
	// @RequestMapping(value = "/view", method = RequestMethod.GET)
	// public String view(
	// Model model,
	// @ModelAttribute("transactions") ArrayList<Transaction> transactions,
	// @ModelAttribute("flashMessage") String flashMessage,
	// @ModelAttribute("flashClass") String flashClass) {
	// model.addAttribute("transactions", transactions);
	// model.addAttribute("flashMessage", flashMessage);
	// model.addAttribute("flashClass", flashClass);
	// return "reports/view";
	// }
}
