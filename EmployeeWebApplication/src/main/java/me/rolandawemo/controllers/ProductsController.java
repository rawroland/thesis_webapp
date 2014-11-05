package me.rolandawemo.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import me.rolandawemo.clients.implementations.ClientManagementClient;
import me.rolandawemo.clients.implementations.ProductManagementClient;
import me.rolandawemo.datatypes.Query;
import me.rolandawemo.models.Client;
import me.rolandawemo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products/")
public class ProductsController {

	@Autowired
	private ProductManagementClient productClient;
	@Autowired
	private ClientManagementClient clientClient;

	public ProductsController() {
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute Query query,
			@ModelAttribute("products") ArrayList<Product> products,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		model.addAttribute("products", this.productClient.list());
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "products/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,
			@ModelAttribute("flashMessage") String flashMessage,
			@ModelAttribute("flashClass") String flashClass) {
		Product product = new Product();
		ArrayList<Client> suppliersList = this.clientClient
				.getClientsByType("supplier");
		Map<Integer, String> suppliers = new LinkedHashMap<Integer, String>();
		if (null != suppliersList) {
			for (Client client : suppliersList) {
				suppliers.put(client.getId(), client.getCompany());
			}
		}
		model.addAttribute("product", product);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("flashMessage", flashMessage);
		model.addAttribute("flashClass", flashClass);
		return "products/add";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addClient(@ModelAttribute Product product, Model model,
			RedirectAttributes attributes) {
		if (this.productClient.add(product.getName(), product.getPrice(),
				product.getClientId())) {
			attributes.addFlashAttribute("flashMessage",
					"Product was successfully added.");
			attributes.addFlashAttribute("flashClass", "alert-success");
			return "redirect:/products/list";
		} else {
			attributes.addFlashAttribute("flashMessage",
					"Product could not be added.");
			attributes.addFlashAttribute("flashClass", "alert-danger");
			return "redirect:/products/add";
		}
	}
}
