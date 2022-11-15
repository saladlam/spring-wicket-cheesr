package info.saladlam.example.wicket.service;

import java.io.Serializable;
import java.util.List;

import info.saladlam.example.wicket.domain.Cheese;

public class CheesrServiceDefaultImpl implements CheesrService, Serializable {

	private static final long serialVersionUID = 953472874380206979L;

	private final List<Cheese> cheeses;

	public CheesrServiceDefaultImpl(List<Cheese> cheeses) {
		this.cheeses = cheeses;
	}

	public Cheese findByName(String name) {
		for (Cheese cheese : cheeses) {
			if (cheese.getName().equalsIgnoreCase(name)) {
				return cheese;
			}
		}
		return null;
	}

}
