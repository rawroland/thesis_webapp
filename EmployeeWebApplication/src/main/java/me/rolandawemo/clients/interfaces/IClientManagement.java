package me.rolandawemo.clients.interfaces;

import java.util.ArrayList;

import me.rolandawemo.models.Client;

public interface IClientManagement {
	
	public boolean add(String prefix, String firstName, String lastName,
			String company, String type);
	
	public ArrayList<Client> list();
	
	public boolean edit(int id, String prefix, String firstName, String lastName, String company, String type);
	
	public Client getClientById(int id);
	
	public boolean delete(int id);
	
	public ArrayList<Client> getClientsByQuery(String query);
	
	public ArrayList<Client> getClientsByType(String type);
}
