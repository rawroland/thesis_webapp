package me.rolandawemo.clients.implementations;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.ITransactionManagement;
import me.rolandawemo.models.Transaction;
import ws.services.TransactionManagementService;

public class TransactionManagementClient implements ITransactionManagement{

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/transactions?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMTransactionManagementPort";
	
	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,"DMTransactionManagementService");
	private Service service;
	private TransactionManagementService client;
	
	public TransactionManagementClient() {
		try {
			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		client = service.getPort(TransactionManagementService.class);
	}

	@Override
	public boolean saveTransactions(int accountId, int quantity, int productId,
			String type, int payment, String date) {
		try {
			return this.client.saveTransaction(accountId, quantity, productId, type, payment, date);
		} catch (ParseException e) {
			return false;
		}
	}
	
}
