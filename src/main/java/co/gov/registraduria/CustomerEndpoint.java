package co.gov.registraduria;

import co.gov.registraduria.commoncannonical.v3.schemas.GetCustomerStatusRq;
import co.gov.registraduria.commoncannonical.v3.schemas.GetCustomerStatusRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CustomerEndpoint {
    private static final String NAMESPACE_URI = "http://registraduria.gov.co/commoncannonical/v3/schemas";

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerEndpoint(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerStatusRq")
    @ResponsePayload
    public GetCustomerStatusRs getCustomerStatus(@RequestPayload GetCustomerStatusRq request) {
        GetCustomerStatusRs response = new GetCustomerStatusRs();
        response.setHeaderResponse(customerRepository.findHeaderResponse(request.getIssuedIdent().getIssuedIdentValue()));
        response.setDocumentStatus(customerRepository.findDocumentStatus(request.getIssuedIdent().getIssuedIdentValue()));
        return response;
    }
}