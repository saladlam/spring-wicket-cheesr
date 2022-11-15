package info.saladlam.example.wicket.session;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import info.saladlam.example.wicket.domain.Cart;

public class CheesrSession extends WebSession {

	private static final long serialVersionUID = 7331971150671232184L;

	private final Cart cart = new Cart();

	public CheesrSession(Request request) {
		super(request);
	}

	public Cart getCart() {
		return cart;
	}

}
