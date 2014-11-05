package me.rolandawemo.clients.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IProductManagement;
import me.rolandawemo.models.Product;
import ws.services.ProductManagementService;

public class ProductManagementClient implements IProductManagement {

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/products?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMProductManagementPort";

	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,
			"DMProductManagementService");
	private Service service;
	public ProductManagementClient() {
		super();
	}

	public ProductManagementService connect() throws MalformedURLException {
		service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		return service.getPort(ProductManagementService.class);
	}

	@Override
	public boolean add(String name, int price, int clientId) {
		try {
			return this.connect().addProduct(name, clientId, price);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return false;
		}
	}

	@Override
	public ArrayList<Product> list() {
		try {
			return this.connect().searchProducts(null);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return new ArrayList<Product>();
		}
	}

	@Override
	public ArrayList<Product> searchProducts(String name) {
		try {
			return this.connect().searchProducts(name);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return new ArrayList<Product>();
		}
	}

	@Override
	public boolean checkProduct(int id, int amount) {
		int available = 0;
		try {
			available = this.connect().checkProduct(id);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return false;
		}
		return amount < available;
	}

}
