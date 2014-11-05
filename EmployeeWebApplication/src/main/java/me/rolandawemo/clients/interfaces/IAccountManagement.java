package me.rolandawemo.clients.interfaces;

public interface IAccountManagement {
	
	public boolean add(int clientId);
	public boolean credit(int clientId, int amount);
	public boolean debit(int clientId, int amount);
}
