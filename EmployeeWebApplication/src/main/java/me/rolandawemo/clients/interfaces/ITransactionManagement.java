package me.rolandawemo.clients.interfaces;

public interface ITransactionManagement {
	
	public boolean saveTransactions(int accountId, int quantity, int productId, String type, int payment, String date);
}
