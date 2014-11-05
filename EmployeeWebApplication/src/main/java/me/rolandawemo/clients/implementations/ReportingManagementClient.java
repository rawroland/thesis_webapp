package me.rolandawemo.clients.implementations;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IReportingManagement;
import me.rolandawemo.models.ReportingGroup;
import me.rolandawemo.models.Transaction;
import me.rolandawemo.models.TransactionQuery;
import ws.services.ReportingManagementService;

public class ReportingManagementClient implements IReportingManagement{

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/reports?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMReportingManagementPort";
	
	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,"DMReportingManagementService");
	private Service service;
	private ReportingManagementService client;
	
	public ReportingManagementClient() {
		try {
			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		client = service.getPort(ReportingManagementService.class);
	}

	@Override
	public boolean createReportingGroup(String name, ArrayList<Integer> accounts) {
		return this.client.createReportingGroup(name, accounts);
	}

	@Override
	public ArrayList<Transaction> generateReport(TransactionQuery query) {
		return this.client.generateReport(query);
	}

	@Override
	public ArrayList<ReportingGroup> getAllReportingGroups() {
		return this.client.searchReportingGroups(0);
	}
	
}
