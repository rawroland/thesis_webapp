package me.rolandawemo.clients.implementations;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IProductManagement;
import me.rolandawemo.models.Product;
import ws.services.ProductManagementService;

public class ProductManagementClient implements IProductManagement{

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/products?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMProductManagementPort";
	
	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,"DMProductManagementService");
	private Service service;
	private ProductManagementService client;
	
	public ProductManagementClient() {
		try {
			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		client = service.getPort(ProductManagementService.class);
	}

	@Override
	public boolean add(String name, int price, int clientId) {
		return this.client.addProduct(name, clientId, price);
	}

	@Override
	public ArrayList<Product> list() {
		return this.client.searchProducts(null);
	}

	@Override
	public ArrayList<Product> searchProducts(String name) {
		return this.client.searchProducts(name);
	}

	@Override
	public boolean checkProduct(int id, int amount) {
		int available = this.client.checkProduct(id);
		return amount < available;
	}
	
	
}
