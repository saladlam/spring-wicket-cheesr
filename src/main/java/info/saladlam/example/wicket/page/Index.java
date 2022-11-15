package info.saladlam.example.wicket.page;

import java.util.Optional;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import info.saladlam.example.wicket.domain.Cheese;

public class Index extends CheesrPage {

	private static final long serialVersionUID = -4256914322710347067L;

	private final ShoppingCartPanel shoppingCartPanel;

	public Index(final PageParameters parameters) {

		PageableListView<Cheese> cheeses = new PageableListView<>("cheeses", getCheeses(), 5) {
			private static final long serialVersionUID = -4040473412197944702L;

			@Override
			protected void populateItem(ListItem<Cheese> item) {
				Cheese cheese = item.getModelObject();
				item.add(new Label("name", cheese.getName()));
				item.add(new Label("description", cheese.getDescription()));
				item.add(new Label("price", "$" + cheese.getPrice()));

				item.add(new AjaxFallbackLink<>("add", item.getModel()) {
					private static final long serialVersionUID = -4166330836362788344L;

					@Override
					public void onClick(Optional<AjaxRequestTarget> oTarget) {
						Cheese selected = (Cheese) getModelObject();
						getCart().getCheeses().add(selected);
						if (oTarget.isPresent()) {
							AjaxRequestTarget target = oTarget.get();
							target.add(shoppingCartPanel);
						}
					}
				});

				PageParameters params = new PageParameters();
				params.add("name", cheese.getName().toLowerCase());
				item.add(new BookmarkablePageLink<>("details", CheesrDetails.class, params));
			}
		};

		add(cheeses);
		add(new PagingNavigator("navigator", cheeses));

		shoppingCartPanel = new ShoppingCartPanel("shoppingcart", getCart());
		shoppingCartPanel.setOutputMarkupId(true);
		add(shoppingCartPanel);

		add(new Link<>("checkout") {
			private static final long serialVersionUID = 6359688990501819266L;

			@Override
			public void onClick() {
				setResponsePage(new CheckOut());
			}

			@Override
			public boolean isVisible() {
				return !getCart().getCheeses().isEmpty();
			}
		});
	}

}
