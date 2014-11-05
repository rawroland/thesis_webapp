package me.rolandawemo.clients.interfaces;

import java.util.ArrayList;

import me.rolandawemo.models.Product;

public interface IProductManagement {
	
	public boolean add(String name, int price, int clientId);
	
	public ArrayList<Product> list();
	
	public ArrayList<Product> searchProducts(String name);
	
	public boolean checkProduct(int id, int amount);
}
