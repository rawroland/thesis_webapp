package me.rolandawemo.clients.interfaces;

import java.util.ArrayList;

import me.rolandawemo.models.ReportingGroup;
import me.rolandawemo.models.Transaction;
import me.rolandawemo.models.TransactionQuery;

public interface IReportingManagement {
	
	public boolean createReportingGroup(String name, ArrayList<Integer> accounts);
	
	public ArrayList<Transaction> generateReport(TransactionQuery query);
	
	public ArrayList<ReportingGroup> getAllReportingGroups();
}
