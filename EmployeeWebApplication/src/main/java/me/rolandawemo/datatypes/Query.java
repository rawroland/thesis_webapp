package me.rolandawemo.datatypes;

public class Query {
	private String string;

	public Query(String string) {
		super();
		this.string = string;
	}

	public Query() {
		super();
	}
	
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
