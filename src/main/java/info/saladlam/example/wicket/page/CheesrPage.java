package info.saladlam.example.wicket.page;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import info.saladlam.example.wicket.CheesrApplication;
import info.saladlam.example.wicket.domain.Cart;
import info.saladlam.example.wicket.domain.Cheese;
import info.saladlam.example.wicket.session.CheesrSession;

public abstract class CheesrPage extends WebPage {

	private static final long serialVersionUID = 1308215258425400952L;

	public CheesrPage() {
	}

	public CheesrPage(PageParameters pageParameters) {
		super(pageParameters);
	}

	public CheesrSession getCheesrSession() {
		return (CheesrSession) getSession();
	}

	public Cart getCart() {
		return getCheesrSession().getCart();
	}

	public List<Cheese> getCheeses() {
		return CheesrApplication.get().getCheese();
	}

}
