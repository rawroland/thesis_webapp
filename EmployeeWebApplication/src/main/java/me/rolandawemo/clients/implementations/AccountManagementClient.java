package me.rolandawemo.clients.implementations;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IAccountManagement;
import ws.services.AccountManagementService;

public class AccountManagementClient implements IAccountManagement {

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/accounts?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMAccountManagementPort";

	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,
			"DMAccountManagementService");
	private Service service;
	public AccountManagementClient() {
		super();
	}

	public AccountManagementService connect() throws MalformedURLException {
		service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		return service.getPort(AccountManagementService.class);
	}

	@Override
	public boolean add(int clientId) {
		try {
			return this.connect().addAccount(clientId);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return false;
		}
	}

	@Override
	public boolean credit(int clientId, int amount) {
		try {
			return this.connect().creditAccount(clientId, amount);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return false;
		}
	}

	@Override
	public boolean debit(int clientId, int amount) {
		try {
			return this.connect().debitAccount(clientId, amount);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return false;
		}
	}

}
