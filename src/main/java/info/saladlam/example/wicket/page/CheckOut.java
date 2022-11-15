package info.saladlam.example.wicket.page;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import info.saladlam.example.wicket.domain.Address;
import info.saladlam.example.wicket.domain.Cart;

public class CheckOut extends CheesrPage {

	private static final long serialVersionUID = 2287261874060237866L;

	public CheckOut() {
		add(new FeedbackPanel("feedback"));

		Form<Cart> form = new Form<>("form");
		add(form);

		Address address = getCart().getBillingAddress();

		form.add(new TextField<>("name", new PropertyModel<>(address, "name")).setRequired(true));
		form.add(new TextField<>("street", new PropertyModel<>(address, "address")).setRequired(true));
		form.add(new TextField<>("zipcode", new PropertyModel<>(address, "zipcode")).setRequired(true));
		form.add(new TextField<>("city", new PropertyModel<>(address, "city")).setRequired(true));

		form.add(new Link<>("cancel") {
			private static final long serialVersionUID = -28023581717910764L;

			@Override
			public void onClick() {
				setResponsePage(Index.class);
			}
		});

		form.add(new Button("order") {
			private static final long serialVersionUID = 4003653015462868777L;

			@Override
			public void onSubmit() {
				Cart cart = getCart();

				cart.getCheeses().clear();

				setResponsePage(Index.class);
			}
		});

		add(new ShoppingCartPanel("shoppingcart", getCart()));
	}

}
