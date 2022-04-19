package handler;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class Handler implements SOAPHandler<SOAPMessageContext> {

    String file = "logs.txt";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        Boolean outBoundProperty = (Boolean) context
                .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (!outBoundProperty) {

            try {
                System.out.println("Server executing SOAP Handler");

                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write(dtf.format(now));
                bw.newLine();
                bw.close();
                System.out.println("Success!");

            } catch (SOAPException | IOException e) {
                System.err.println(e);
            }

        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

}