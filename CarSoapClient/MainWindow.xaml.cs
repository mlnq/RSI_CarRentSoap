using CarServer;
using CarWpf.Handlers;
using CarWpf.Pages;
using System.Windows;
using System.Windows.Controls;


namespace CarWpf
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private readonly AppControllerClient carClient=  new AppControllerClient();

        public MainWindow()
        {
            InitializeComponent();
        }

        private void WindowLoaded(object sender, RoutedEventArgs e)
        {
            carClient.Endpoint.EndpointBehaviors.Add(new AddSoapHeaderBehavior());

            MainContent.Navigate(new CarsPage(carClient));
        }
    }
}
