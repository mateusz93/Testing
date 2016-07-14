package pl.com.bottega.ecommerce.sales.domain.payment;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

/**
 * @author Mateusz Wieczorek
 */
public class PaymentFactory {

    public static Payment newInstance(Id aggregateId, ClientData clientData, Money amount) {
        return new Payment(aggregateId, clientData, amount);
    }
}
