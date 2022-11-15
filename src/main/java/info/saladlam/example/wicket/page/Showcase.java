package info.saladlam.example.wicket.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;

public class Showcase extends WebPage {

	private static final long serialVersionUID = -6910017997077733236L;

	public Showcase() {
		add(new Label("simplelabel", "Just a very simple label."));
		add(new MultiLineLabel("multilinelabel", "Hello\nMy name is\nRussell P. Pitre"));
		add(new Label("formattedlabel", "A <b><i>formatted</i></b> label.").setEscapeModelStrings(false));
		add(new ExternalLink("externallink", "http://wicket.apache.org", "Apache Wicket Website"));
		add(new BookmarkablePageLink<>("bookmarkablelink", CheckOut.class));
	}

}
