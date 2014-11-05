package me.rolandawemo.clients.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IClientManagement;
import me.rolandawemo.models.Client;
import ws.services.ClientManagementService;

public class ClientManagementClient implements IClientManagement {

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/clients?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMClientManagementPort";

	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,
			"DMClientManagementService");
	private Service service;

	public ClientManagementClient() {
	}

	private ClientManagementService connect() throws MalformedURLException {
		service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		return service.getPort(ClientManagementService.class);

	}

	@Override
	public boolean add(String prefix, String firstName, String lastName,
			String company, String type) {
		try {
			return this.connect().addClient(prefix, firstName, lastName, company,
					type);
		} catch (MalformedURLException e) {
			// LOG
			return false;
		}
	}

	@Override
	public ArrayList<Client> list() {
		try {
			return this.connect().searchClients("", 0, "");
		} catch (MalformedURLException e) {
			// log
			return new ArrayList<Client>();
		}
	}

	@Override
	public boolean edit(int id, String prefix, String firstName,
			String lastName, String company, String type) {
		try {
			return this.connect().editClient(id, prefix, firstName, lastName,
					company, type);
		} catch (MalformedURLException e) {
			// TODO LOG
			return false;
		}
	}

	@Override
	public Client getClientById(int id) {
		try {
			return this.connect().searchClients("", id, "").get(0);
		} catch (MalformedURLException e) {
			// TODO LOG
			return new Client();
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			return this.connect().deleteClient(id);
		} catch (MalformedURLException e) {
			// TODO LOG
			return false;
		}
	}

	@Override
	public ArrayList<Client> getClientsByQuery(String query) {
		try {
			return this.connect().searchClients(query, 0, "");
		} catch (MalformedURLException e) {
			// TODO LOG
			return new ArrayList<Client>();
		}
	}

	@Override
	public ArrayList<Client> getClientsByType(String type) {
		try {
			return this.connect().searchClients("", 0, type);
		} catch (MalformedURLException e) {
			// TODO LOG
						return new ArrayList<Client>();
		}
	}

}
