package info.saladlam.example.wicket.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import info.saladlam.example.wicket.domain.Cheese;
import info.saladlam.example.wicket.service.CheesrService;
import info.saladlam.example.wicket.service.CheesrServiceDefaultImpl;

public class CheesrDetails extends CheesrPage {

	private static final long serialVersionUID = -7003634191756305814L;

	private CheesrService cheesrService = new CheesrServiceDefaultImpl(getCheeses());

	public CheesrDetails(PageParameters pageParameters) {
		super(pageParameters);

		Cheese cheese = null;

		String name = pageParameters.get("name").toOptionalString();
		if (name != null) {
			cheese = cheesrService.findByName(name);
		}
		createComponents(cheese);
	}

	public CheesrDetails(Cheese cheese) {
		createComponents(cheese);
	}

	private void createComponents(Cheese cheese) {

		CompoundPropertyModel<Cheese> cheeseModel = new CompoundPropertyModel<Cheese>(cheese);
		setDefaultModel(cheeseModel);

		add(new Label("name"));
		add(new Label("description"));
		add(new Label("price"));

		add(new ShoppingCartPanel("shoppingcart", getCart()));

		add(new Link<>("checkout") {
			private static final long serialVersionUID = -4632075091255719302L;

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
