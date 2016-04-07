package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @Author Mateusz Wieczorek
 */
public class BookKeeperTest {

    private RequestItem requestItem;
    private BookKeeper bookKeeper;
    private InvoiceRequest invoiceRequest;
    private TaxPolicy taxPolicy;
    private Tax tax;

    @Before
    public void setUp() {
        ProductData productData = mock(ProductData.class);
        taxPolicy = mock(TaxPolicy.class);

        Money money = new Money(new BigDecimal("23"));
        tax = new Tax(money, "tax");

        requestItem = new RequestItem(productData, 12, money);
        invoiceRequest = new InvoiceRequest(new ClientData(Id.generate(), "Dane"));

        bookKeeper = new BookKeeper(new InvoiceFactory());
    }

    @Test
    public void calledInvoiceWithOneItemTest() {
        invoiceRequest.add(requestItem);
        when(taxPolicy.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(tax);
        assertThat(bookKeeper.issuance(invoiceRequest, taxPolicy).getItems().size() == 1, is(true));
    }

    @Test
    public void calledInvoiceWithZeroItemTest() {
        when(taxPolicy.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(tax);
        assertThat(bookKeeper.issuance(invoiceRequest, taxPolicy).getItems().size() == 0, is(true));
    }

    @Test
    public void twiceCalledCalculateTaxMethodTest() {
        invoiceRequest.add(requestItem);
        invoiceRequest.add(requestItem);
        when(taxPolicy.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(tax);
        bookKeeper.issuance(invoiceRequest, taxPolicy);
        verify(taxPolicy, times(2)).calculateTax(any(ProductType.class), any(Money.class));
    }

    @Test
    public void zeroCalledCalculateTaxMethodTest() {
        when(taxPolicy.calculateTax(any(ProductType.class), any(Money.class))).thenReturn(tax);
        bookKeeper.issuance(invoiceRequest, taxPolicy);
        verify(taxPolicy, times(0)).calculateTax(any(ProductType.class), any(Money.class));
    }

}
