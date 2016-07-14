package edu.iis.mto.staticmock;

import java.util.ArrayList;
import java.util.List;

public class PublishableNews {

    private final List<String> publicContent = new ArrayList<>();
    private final List<String> subscribentContent = new ArrayList<>();

	public static PublishableNews create() {
		return new PublishableNews();
	}

	public void addPublicInfo(String content) {
		this.publicContent.add(content);
	}

	public void addForSubscription(String content, SubsciptionType subscriptionType) {
        this.subscribentContent.add(content);
	}

    public List<String> getPublicContent() {
        return publicContent;
    }

    public List<String> getSubscribentContent() {
        return subscribentContent;
    }

}
