package me.rolandawemo.clients.implementations;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IClientManagement;
import me.rolandawemo.models.Client;
import ws.services.ClientManagementService;

public class ClientManagementClient implements IClientManagement{

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/clients?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMClientManagementPort";
	
	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,"DMClientManagementService");
	private Service service;
	private ClientManagementService client;
	
	public ClientManagementClient() {
		try {
			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		client = service.getPort(ClientManagementService.class);
	}

	@Override
	public boolean add(String prefix, String firstName, String lastName,
			String company, String type) {
		return this.client.addClient(prefix, firstName, lastName, company, type);
	}

	@Override
	public ArrayList<Client> list() {
		return this.client.searchClients("", 0, "");
	}

	@Override
	public boolean edit(int id, String prefix, String firstName,
			String lastName, String company, String type) {
		return this.client.editClient(id, prefix, firstName, lastName, company, type);
	}

	@Override
	public Client getClientById(int id) {
		return this.client.searchClients("", id, "").get(0);
	}

	@Override
	public boolean delete(int id) {
		return this.client.deleteClient(id);
	}

	@Override
	public ArrayList<Client> getClientsByQuery(String query) {
		return this.client.searchClients(query, 0, "");
	}

	@Override
	public ArrayList<Client> getClientsByType(String type) {
		return this.client.searchClients("", 0, type);
	}
	
}
