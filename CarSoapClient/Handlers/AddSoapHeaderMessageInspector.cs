using System.ServiceModel;
using System.ServiceModel.Channels;
using System.ServiceModel.Dispatcher;
using System;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Runtime.InteropServices;

namespace CarWpf.Handlers
{
    class AddSoapHeaderMessageInspector : IClientMessageInspector
    {

        //https://stackoverflow.com/questions/40290861/get-mac-address-via-tcplistener-in-c-sharp
        public void AfterReceiveReply(ref Message reply, object correlationState)
        {
            // here you would handle the web service response
        }

        public object BeforeSendRequest(ref Message request, IClientChannel channel)
        {
            // before sending a request, add a new SOAP header, specifying its name, namespace and value

            IPAddress IPAddress = IPAddress.Parse(GetLocalIPAddress());

            byte[] macByte = GetMacAddress(IPAddress);
            string macAddress = string.Join(":", (from z in macByte select z.ToString("X2")).ToArray());


            var header = MessageHeader.CreateHeader("macAddress", "http://ws/", macAddress, false, "http://schemas.xmlsoap.org/soap/actor/next", true);
            request.Headers.Add(header);
            return request;
        }

        [DllImport("iphlpapi.dll", ExactSpelling = true)]
        public static extern int SendARP(uint destIP, uint srcIP, byte[] macAddress, ref uint macAddressLength);

        public static byte[] GetMacAddress(IPAddress address)
        {
            byte[] mac = new byte[6];
            uint len = (uint)mac.Length;
            byte[] addressBytes = address.GetAddressBytes();
            uint dest = ((uint)addressBytes[3] << 24)
              + ((uint)addressBytes[2] << 16)
              + ((uint)addressBytes[1] << 8)
              + ((uint)addressBytes[0]);

            if (SendARP(dest, 0, mac, ref len) != 0)
            {
                throw new Exception("The ARP request failed.");
            }

            return mac;
        }

        public static string GetLocalIPAddress()
        {
            var host = Dns.GetHostEntry(Dns.GetHostName());

            foreach (var ip in host.AddressList)
            {
                if (ip.AddressFamily == AddressFamily.InterNetwork)
                {
                    return ip.ToString();
                }
            }

            throw new Exception("No network adapters with an IPv4 address in the system!");
        }
    }
}