package me.rolandawemo.clients.interfaces;

import java.util.ArrayList;

import me.rolandawemo.models.Client;


public interface IAccountManagement {
	
	public boolean add(int clientId);
	public boolean credit(int clientId, int amount);
	public boolean debit(int clientId, int amount);
}
