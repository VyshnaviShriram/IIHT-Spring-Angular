package com.basics.interfaces;

interface manufacturing {
	void made();
}

interface sales {
	void sold();
}

public class Product implements sales, manufacturing {

	public static void main(String[] args) {
		Product soap = new Product();
		soap.sold();
		soap.made();
	}

	@Override
	public void made() {
		System.out.println("the porduct has been made");
	}

	@Override
	public void sold() {
		System.out.println("the product can be sold ");

	}

}
