package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;

/**
 * @author Mateusz Wieczorek
 */
public class InvoiceFactory {
    public static Invoice newInstance(Id id, ClientData clientData) {
        return new Invoice(id, clientData);
    }
}
