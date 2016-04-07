package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @Author Mateusz Wieczorek
 */
public class BookKeeperTest {

    private ClientData clientData;
    private RequestItem requestItem;
    private Money money;
    private ProductData productData;
    private BookKeeper bookKeeper;
    private InvoiceRequest invoiceRequest;
    private InvoiceFactory invoiceFactory;
    private TaxPolicy taxPolicy;
    private Tax tax;

    @Before
    public void setUp() {
        productData = mock(ProductData.class);
        taxPolicy = mock(TaxPolicy.class);

        clientData = new ClientData(Id.generate(), "Dane");
        money = new Money(new BigDecimal("23"));
        tax = new Tax(money, "tax");

        requestItem = new RequestItem(productData, 12, money);
        invoiceRequest = new InvoiceRequest(clientData);
        invoiceRequest.add(requestItem);

        invoiceFactory = new InvoiceFactory();
        bookKeeper = new BookKeeper(invoiceFactory);
    }

    @Test
    public void oneReturnInvoiceTest() {
        when(taxPolicy.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(tax);
        assertThat(bookKeeper.issuance(invoiceRequest, taxPolicy).getItems().size() == 1, is(true));
    }
}
