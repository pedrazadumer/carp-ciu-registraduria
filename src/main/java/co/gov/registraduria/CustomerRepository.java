package co.gov.registraduria;

import co.gov.registraduria.commoncannonical.v3.schemas.DocumentStatus;
import co.gov.registraduria.commoncannonical.v3.schemas.HeaderResponse;
import co.gov.registraduria.commoncannonical.v3.schemas.MessageHeader;
import co.gov.registraduria.commoncannonical.v3.schemas.MessageInfo;
import co.gov.registraduria.commoncannonical.v3.schemas.MessageKey;
import co.gov.registraduria.commoncannonical.v3.schemas.Status;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class CustomerRepository {
    private static final Map<String, HeaderResponse> headerResponseMap = new HashMap<>();
    private static final Map<String, DocumentStatus> documentStatusMap = new HashMap<>();

    @PostConstruct
    public void initData() {
        MessageKey messageKey = new MessageKey();
        messageKey.setRequestUUID(UUID.randomUUID().toString());

        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setDateTime("2008-09-28T20:49:45");
        messageInfo.setSystemId("OperadorA");
        messageInfo.setOriginatorName("Portal");

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setMessageKey(messageKey);
        messageHeader.setMessageInfo(messageInfo);

        Status status = new Status();
        status.setStatusCode("000");
        status.setServerStatusCode(0);
        status.setSeverity("Info");

        HeaderResponse headerResponse = new HeaderResponse();
        headerResponse.setMessageHeader(messageHeader);
        headerResponse.setStatus(status);

        DocumentStatus documentStatus = new DocumentStatus();
        documentStatus.setDocumentStatusCode(1);
        documentStatus.setUserFolder("/usuario/ABC1234/1234578");
        documentStatus.setInternalFileName("BJASJK- 12345678");
        documentStatus.setFileName("CedulaCiudaniaFirmada");
        documentStatus.setToken(UUID.randomUUID().toString());

        headerResponseMap.put("1234578", headerResponse);
        documentStatusMap.put("1234578", documentStatus);
    }

    public HeaderResponse findHeaderResponse(String issuedIdentValue) {
        Assert.notNull(issuedIdentValue, "The 'issuedIdentValue' must not be null.");
        return headerResponseMap.get(issuedIdentValue);
    }

    public DocumentStatus findDocumentStatus(String issuedIdentValue) {
        Assert.notNull(issuedIdentValue, "The 'issuedIdentValue' must not be null.");
        return documentStatusMap.get(issuedIdentValue);
    }
}
