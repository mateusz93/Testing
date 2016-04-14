package edu.iis.mto.staticmock;

import edu.iis.mto.staticmock.reader.NewsReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @Author Mateusz Wieczorek, 11.04.16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( {ConfigurationLoader.class, NewsReaderFactory.class} )
public class NewsLoaderTest {

    private ConfigurationLoader configurationLoader;
    private NewsReaderFactory newsReaderFactory;

    @Before
    public void setUp() {
        mockStatic(ConfigurationLoader.class);
        mockStatic(NewsReaderFactory.class);
        configurationLoader = mock(ConfigurationLoader.class);
        newsReaderFactory = mock(NewsReaderFactory.class);
    }

    @Test
    public void loadPublicNewsTest() {
        PublishableNews publishableNews = getPublishableNews();

        when(ConfigurationLoader.getInstance()).thenReturn(configurationLoader);
        when(NewsReaderFactory.getReader(any(String.class))).thenReturn(any(NewsReader.class));

        NewsLoader newsLoader = new NewsLoader();
        newsLoader.loadNews();

    }

    @Test
    public void loadSubscribedNewsTest() {
        PublishableNews publishableNews = getSubscribedNews();

        when(ConfigurationLoader.getInstance()).thenReturn(configurationLoader);
        when(NewsReaderFactory.getReader(any(String.class))).thenReturn(any(NewsReader.class));

        NewsLoader newsLoader = new NewsLoader();
        newsLoader.loadNews();

    }

    private PublishableNews getPublishableNews() {
        PublishableNews publishableNews = PublishableNews.create();
        publishableNews.addPublicInfo("asd");
        publishableNews.addPublicInfo("fff");
        publishableNews.addPublicInfo("ggg");
        publishableNews.addPublicInfo("hhh");
        return publishableNews;
    }

    private PublishableNews getSubscribedNews() {
        PublishableNews publishableNews = PublishableNews.create();
        publishableNews.addForSubscription("AAA", SubsciptionType.A);
        publishableNews.addForSubscription("BBB", SubsciptionType.B);
        publishableNews.addForSubscription("CCC", SubsciptionType.C);
        publishableNews.addForSubscription("NONE", SubsciptionType.NONE);
        return publishableNews;
    }

}