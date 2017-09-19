package cn.zy.base.shopping.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalOAuthScopes;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalProfileSharingActivity;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.ShippingAddress;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gtgs on 17/9/18.
 */

public class PayPalUtils {
    /**
     * - Set to PayPalConfiguration.ENVIRONMENT_PRODUCTION to move real money.
     * <p>
     * - Set to PayPalConfiguration.ENVIRONMENT_SANDBOX to use your test credentials
     * from https://developer.paypal.com
     * <p>
     * - Set to PayPalConfiguration.ENVIRONMENT_NO_NETWORK to kick the tires
     * without communicating to PayPal's servers.
     */
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;

    // note that these credentials will differ between live & sandbox environments.
    private static final String CONFIG_CLIENT_ID = "credentials from developer.paypal.com";

    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
            // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Example Merchant")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));

    Context mContext = null;

//    public void initData(Context context) {
//        mContext = context;
//        Intent intent = new Intent(context, PayPalService.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//        context.startService(intent);
//    }
//
//    public void onBuyPressed(View pressed) {
//        /*
//         * PAYMENT_INTENT_SALE will cause the payment to complete immediately.
//         * Change PAYMENT_INTENT_SALE to
//         *   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
//         *   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
//         *     later via calls from your server.
//         *
//         * Also, to include additional payment details and an item list, see getStuffToBuy() below.
//         */
//        PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
//
//        /*
//         * See getStuffToBuy(..) for examples of some available payment options.
//         */
//
//        Intent intent = new Intent(mContext, PaymentActivity.class);
//
//        // send the same configuration for restart resiliency
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//
//        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
//
//        mContext.startActivityForResult(intent, REQUEST_CODE_PAYMENT);
//    }
//
//    private PayPalPayment getThingToBuy(String paymentIntent) {
//        return new PayPalPayment(new BigDecimal("0.01"), "USD", "sample item",
//                paymentIntent);
//    }
//
//    /*
//     * This method shows use of optional payment details and item list.
//     */
//    private PayPalPayment getStuffToBuy(String paymentIntent) {
//        //--- include an item list, payment amount details
//        PayPalItem[] items =
//                {
//                        new PayPalItem("sample item #1", 2, new BigDecimal("87.50"), "USD",
//                                "sku-12345678"),
//                        new PayPalItem("free sample item #2", 1, new BigDecimal("0.00"),
//                                "USD", "sku-zero-price"),
//                        new PayPalItem("sample item #3 with a longer name", 6, new BigDecimal("37.99"),
//                                "USD", "sku-33333")
//                };
//        BigDecimal subtotal = PayPalItem.getItemTotal(items);
//        BigDecimal shipping = new BigDecimal("7.21");
//        BigDecimal tax = new BigDecimal("4.67");
//        PayPalPaymentDetails paymentDetails = new PayPalPaymentDetails(shipping, subtotal, tax);
//        BigDecimal amount = subtotal.add(shipping).add(tax);
//        PayPalPayment payment = new PayPalPayment(amount, "USD", "sample item", paymentIntent);
//        payment.items(items).paymentDetails(paymentDetails);
//
//        //--- set other optional fields like invoice_number, custom field, and soft_descriptor
//        payment.custom("This is text that will be associated with the payment that the app can use.");
//
//        return payment;
//    }
//
//    /*
//     * Add app-provided shipping address to payment
//     */
//    private void addAppProvidedShippingAddress(PayPalPayment paypalPayment) {
//        ShippingAddress shippingAddress =
//                new ShippingAddress().recipientName("Mom Parker").line1("52 North Main St.")
//                        .city("Austin").state("TX").postalCode("78729").countryCode("US");
//        paypalPayment.providedShippingAddress(shippingAddress);
//    }
//
//    /*
//     * Enable retrieval of shipping addresses from buyer's PayPal account
//     */
//    private void enableShippingAddressRetrieval(PayPalPayment paypalPayment, boolean enable) {
//        paypalPayment.enablePayPalShippingAddressesRetrieval(enable);
//    }
//
//    public void onFuturePaymentPressed(View pressed) {
//        Intent intent = new Intent(mContext, PayPalFuturePaymentActivity.class);
//
//        // send the same configuration for restart resiliency
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//
//        mContext.startActivityForResult(intent, REQUEST_CODE_FUTURE_PAYMENT);
//    }
//
//    public void onProfileSharingPressed(View pressed) {
//        Intent intent = new Intent(mContext, PayPalProfileSharingActivity.class);
//
//        // send the same configuration for restart resiliency
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//
//        intent.putExtra(PayPalProfileSharingActivity.EXTRA_REQUESTED_SCOPES, getOauthScopes());
//
//        startActivityForResult(intent, REQUEST_CODE_PROFILE_SHARING);
//    }
//
//    private PayPalOAuthScopes getOauthScopes() {
//        /* create the set of required scopes
//         * Note: see https://developer.paypal.com/docs/integration/direct/identity/attributes/ for mapping between the
//         * attributes you select for this app in the PayPal developer portal and the scopes required here.
//         */
//        Set<String> scopes = new HashSet<String>(
//                Arrays.asList(PayPalOAuthScopes.PAYPAL_SCOPE_EMAIL, PayPalOAuthScopes.PAYPAL_SCOPE_ADDRESS));
//        return new PayPalOAuthScopes(scopes);
//    }
//
//    protected void displayResultText(String result) {
//        ((TextView) findViewById(R.id.txtResult)).setText("Result : " + result);
//        Toast.makeText(
//                getApplicationContext(),
//                result, Toast.LENGTH_LONG)
//                .show();
//    }

}
