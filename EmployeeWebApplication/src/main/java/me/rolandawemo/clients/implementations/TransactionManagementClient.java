package me.rolandawemo.clients.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.ITransactionManagement;
import ws.services.TransactionManagementService;

public class TransactionManagementClient implements ITransactionManagement {

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/transactions?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMTransactionManagementPort";

	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,
			"DMTransactionManagementService");
	private Service service;
	public TransactionManagementClient() {
	}

	private TransactionManagementService connect() throws MalformedURLException {
		service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		return service.getPort(TransactionManagementService.class);
	}

	@Override
	public boolean saveTransactions(int accountId, int quantity, int productId,
			String type, int payment, String date) {
		try {
			return this.connect().saveTransaction(accountId, quantity, productId,
					type, payment, date);
		} catch (ParseException e) {
			return false;
		} catch (MalformedURLException e) {
			// TODO LOG
			return false;
		}
	}

}
