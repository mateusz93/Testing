package edu.iis.mto.staticmock;

import edu.iis.mto.staticmock.reader.FileNewsReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * @Author Mateusz Wieczorek, 11.04.16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( {ConfigurationLoader.class, NewsReaderFactory.class, PublishableNews.class} )
public class NewsLoaderTest {

    private ConfigurationLoader configurationLoader;
    private NewsReaderFactory newsReaderFactory;
    private FileNewsReader fileNewsReader;
    private Configuration configuration;
    private PublishableNews publishableNews;

    @Before
    public void setUp() {
        mockStatic(ConfigurationLoader.class);
        mockStatic(NewsReaderFactory.class);

        configurationLoader = mock(ConfigurationLoader.class);
        newsReaderFactory = mock(NewsReaderFactory.class);
        configuration = mock(Configuration.class);
        fileNewsReader = mock(FileNewsReader.class);
        when(configuration.getReaderType()).thenReturn("type");
        when(ConfigurationLoader.getInstance()).thenReturn(configurationLoader);
        when(configurationLoader.loadConfiguration()).thenReturn(configuration);
        when(NewsReaderFactory.getReader(any(String.class))).thenReturn(fileNewsReader);
    }

    @Test
    public void loadPublicNewsTest() {

        when(fileNewsReader.read()).thenReturn(getPublishableIncomingNew());

        NewsLoader newsLoader = new NewsLoader();
        PublishableNews publishableNews = newsLoader.loadNews();

        assertThat(publishableNews.getPublicContent().size(), is(3));
        assertThat(publishableNews.getSubscribentContent().size(), is(0));
    }

    @Test
    public void loadSubscribedNewsTest() {

        when(fileNewsReader.read()).thenReturn(getSubscribedIncomingNew());

        NewsLoader newsLoader = new NewsLoader();
        PublishableNews publishableNews = newsLoader.loadNews();

        assertThat(publishableNews.getPublicContent().size(), is(0));
        assertThat(publishableNews.getSubscribentContent().size(), is(3));
    }

    @Test
    public void theNumberOfAddPublicInfoMethodCalls() {
        mockStatic(PublishableNews.class);
        publishableNews = mock(PublishableNews.class);

        when(fileNewsReader.read()).thenReturn(getPublishableIncomingNew());
        when(PublishableNews.create()).thenReturn(publishableNews);

        NewsLoader newsLoader = new NewsLoader();
        newsLoader.loadNews();

        verify(publishableNews, times(3)).addPublicInfo(any(String.class));
    }

    @Test
    public void theNumberOfAddForSubscriptionMethodCalls() {
        mockStatic(PublishableNews.class);
        publishableNews = mock(PublishableNews.class);

        when(fileNewsReader.read()).thenReturn(getSubscribedIncomingNew());
        when(PublishableNews.create()).thenReturn(publishableNews);

        NewsLoader newsLoader = new NewsLoader();
        newsLoader.loadNews();

        verify(publishableNews, times(3)).addForSubscription(any(String.class), any(SubsciptionType.class));
    }


    private IncomingNews getSubscribedIncomingNew() {
        IncomingNews incomingNews = new IncomingNews();
        incomingNews.add(new IncomingInfo("test1", SubsciptionType.A));
        incomingNews.add(new IncomingInfo("test2", SubsciptionType.B));
        incomingNews.add(new IncomingInfo("test3", SubsciptionType.C));
        return incomingNews;
    }

    private IncomingNews getPublishableIncomingNew() {
        IncomingNews incomingNews = new IncomingNews();
        incomingNews.add(new IncomingInfo("test1", SubsciptionType.NONE));
        incomingNews.add(new IncomingInfo("test2", SubsciptionType.NONE));
        incomingNews.add(new IncomingInfo("test3", SubsciptionType.NONE));
        return incomingNews;
    }

}