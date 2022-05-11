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
import java.util.*;

public class Handler implements SOAPHandler<SOAPMessageContext> {

    String file = "logs.txt";


    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        System.out.println("Server : handleMessage()......");

        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        //for response message only, true for outbound messages, false for inbound
        if (!isRequest) {
            try {
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();

                //if no header, add one
                if (soapHeader == null) {
                    //soapHeader = soapEnv.addHeader();
                    //throw exception
                    generateSOAPErrMessage(soapMsg, "No SOAP header.");
                }

                //Get client mac address from SOAP header
                Iterator it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);

                //if no header block for next actor found? throw exception
                if (it == null || !it.hasNext()) {
                    generateSOAPErrMessage(soapMsg, "No header block for next actor.");
                }

                //if no mac address found? throw exception
                Node macNode = (Node) it.next();
                String macValue = (macNode == null) ? null : macNode.getValue();

                if (macValue == null) {
                    generateSOAPErrMessage(soapMsg, "No mac address in header block.");
                }

                List<String> macAddresses = Arrays.asList("0A:00:27:00:00:04", "2C:F0:5D:0D:FB:50"); // Mati PC, Gabi PC

                //if mac address is not match, throw exception
                if (!macAddresses.contains(macValue)) {
                    generateSOAPErrMessage(soapMsg, "Invalid mac address, access is denied.");
                }

                //tracking
                soapMsg.writeTo(System.out);

            } catch (SOAPException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        //continue other handler chain
        return true;
    }

   /* @Override
    public boolean handleMessage(SOAPMessageContext context) {

        String count="";

        Boolean outBoundProperty = (Boolean) context
                .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (!outBoundProperty) {

            try {
                System.out.println("Server executing SOAP Handler");

                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();

            //Jak będzie działał header!!!
              *//*  Map<String,List<String>> reqheaders = (Map<String,List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
                List<String> testHeader = reqheaders.get("testHeader");

                if(testHeader != null && !testHeader.isEmpty()) {
                    count = testHeader.get(0);
                }else return true;

                if(Integer.parseInt(count)>0){
                    return true;
                }
                else{

                }*//*
               SOAPHeader soapHeader = soapEnv.getHeader();

                if (soapHeader == null){
                    soapHeader = soapEnv.addHeader();
                    generateSOAPErrMessage(soapMsg, "No SOAP header.");
                }

                //Get client mac address from SOAP header
                Iterator it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);

                //if no header block for next actor found? throw exception
                if (it == null || !it.hasNext()){
                    generateSOAPErrMessage(soapMsg, "No header block for next actor.");
                }

                //if no mac address found? throw exception
                Node macNode = (Node) it.next();
                String macValue = (macNode == null) ? null : macNode.getValue();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write(dtf.format(now) + " " + soapHeader.toString() + " " + macValue );

*//*
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write(dtf.format(now) );*//*
                bw.newLine();
                bw.close();
                System.out.println("Success!");

            } catch (IOException e) {
                System.err.println(e);
            } catch (SOAPException e) {
                throw new RuntimeException(e);
            }

        }

        return true;
    }*/

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
        System.out.println("Server: handleFalut()....");
        return true;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("Server: close()....");
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

}