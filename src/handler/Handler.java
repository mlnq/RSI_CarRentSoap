package handler;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
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

              //  SOAPMessage soapMsg = context.getMessage();
              //  SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
//                SOAPHeader soapHeader = soapEnv.getHeader();

//                //Get client mac address from SOAP header
//                Iterator it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
//
//                //if no header block for next actor found? throw exception
//                if (it == null || !it.hasNext()){
//                    generateSOAPErrMessage(soapMsg, "No header block for next actor.");
//                }
//
//                //if no mac address found? throw exception
//                Node macNode = (Node) it.next();
//                String macValue = (macNode == null) ? null : macNode.getValue();
//
//                if (macValue == null){
//                    generateSOAPErrMessage(soapMsg, "No mac address in header block.");
//                }

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write(dtf.format(now) );
                bw.newLine();
                bw.close();
                System.out.println("Success!");

            } catch (IOException e) {
                System.err.println(e);
            }

        }

        return true;
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            throw new SOAPFaultException(soapFault);
        }
        catch(SOAPException e) { }
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