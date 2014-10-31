package me.rolandawemo.clients.implementations;


import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IAccountManagement;
import ws.services.AccountManagementService;

public class AccountManagementClient implements IAccountManagement{

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/accounts?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMAccountManagementPort";
	
	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,"DMAccountManagementService");
	private Service service;
	private AccountManagementService client;
	
	public AccountManagementClient() {
		try {
			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		client = service.getPort(AccountManagementService.class);
	}

	@Override
	public boolean add(int clientId) {
		return this.client.addAccount(clientId);
	}

	@Override
	public boolean credit(int clientId, int amount) {
		return this.client.creditAccount(clientId, amount);
	}

	@Override
	public boolean debit(int clientId, int amount) {
		return this.client.debitAccount(clientId, amount);
	}

}
