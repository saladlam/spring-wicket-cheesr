package info.saladlam.example.wicket.page;

import java.text.NumberFormat;
import java.util.Optional;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import info.saladlam.example.wicket.domain.Cart;
import info.saladlam.example.wicket.domain.Cheese;

public class ShoppingCartPanel extends Panel {

	private static final long serialVersionUID = 7878035876226792030L;

	private final Cart cart;

	public ShoppingCartPanel(String id, Cart cart) {
		super(id);

		this.cart = cart;

		add(new ListView<Cheese>("cart", new PropertyModel<>(this, "cart.cheeses")) {

			private static final long serialVersionUID = 2182485674875020026L;

			@Override
			protected void populateItem(ListItem<Cheese> item) {
				Cheese cheese = item.getModelObject();
				final ShoppingCartPanel panel = item.findParent(ShoppingCartPanel.class);

				item.add(new Label("name", cheese.getName()));
				item.add(new Label("price", "$" + cheese.getPrice()));
				item.add(new AjaxFallbackLink<Cheese>("remove", item.getModel()) {
					private static final long serialVersionUID = 1403503044284349545L;

					@Override
					public void onClick(Optional<AjaxRequestTarget> oTarget) {
						Cheese selected = getModelObject();
						getCart().getCheeses().remove(selected);

						if (oTarget.isPresent()) {
							AjaxRequestTarget target = oTarget.get();
							target.add(target.getPage().get("shoppingcart"));
						}
					}
				});
			}
		});

		add(new Label("total", new Model<String>() {
			private static final long serialVersionUID = -8350693641236285330L;

			@Override
			public String getObject() {
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				return nf.format(getCart().getTotal());
			}
		}));
	}

	public Cart getCart() {
		return cart;
	}

}
