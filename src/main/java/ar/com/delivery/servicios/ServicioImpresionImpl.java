package ar.com.delivery.servicios;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import org.springframework.stereotype.Service;

import ar.com.delivery.modelo.Cliente;
import ar.com.delivery.modelo.Pedido;
import ar.com.delivery.modelo.ProductoCantidad;

@Service("servicioImpresion")

public class ServicioImpresionImpl implements ServicioImpresion {
	
	@Inject
	private ServicioPedido servicioPedido;
	
	public Boolean imprimirComanda(List<ProductoCantidad> productoCantidad, Pedido pedido, Cliente cliente,
			Float pagaCon,Float vuelto, String obs) {

		// HashDocAttributeSet docAttr=new HashDocAttributeSet();
		// HashPrintRequestAttributeSet reqAttr=new
		// HashPrintRequestAttributeSet();
		StringBuilder content = new StringBuilder();

		try {
			PrintService pserv = PrintServiceLookup.lookupDefaultPrintService();
			if (pserv == null) {
				System.out.println("ERROR-01: no default print service");
			}
			System.out.println("Printer: " + pserv);

			DocPrintJob job = pserv.createPrintJob();
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

			// Armado String
			String lista = "";
			for (ProductoCantidad s : productoCantidad) {
				String nombre = s.getNombre();
				if(s.getNombre().length()<15){
					int i = 15 - s.getNombre().length();
					for (int j = 0 ; j< i ; j++){
						nombre += " ";
					}
				}
				lista += "\r\n" + nombre + "      " + s.getCantidad() + "         " + s.getPrecio();
			}
			
			servicioPedido.eliminarProductoCantidad();
			/*String content = cliente.getNombre() + " - " + cliente.getTelefono() + "\r\n" + cliente.getCalle() + " - "
					+ cliente.getLocalidad() + "\r\n" + "\r\n" + "PRODUCTO" + "  CANTIDAD" + "  PRECIO UNIT." +"\r\n" +  "--------"
					+ "  --------" + "  ------------" + lista + "\r\n\r\n" + "TOTAL:               " + pedido.getPrecio() + "\r\n"
					+ "VUELTO:             " + vuelto.toString() +"\r\n";*/
			
			content.append(cliente.getNombre() + " - " + cliente.getTelefono() + "\r\n");
			content.append(cliente.getCalle() + " - " + cliente.getLocalidad() + "\r\n \r\n");
			content.append(obs+"\r\n\r\n");
			content.append(pedido.getFecha()+ "\r\n\r\n");
			content.append("PRODUCTO" + "       CANTIDAD" + "  PRECIO UNIT." +"\r\n");
			content.append("--------"+ "        --------" + "  ------------");
			content.append(lista + "\r\n\r\n");
			content.append("TOTAL:               " + pedido.getPrecio() + "\r\n");
			content.append("PAGA CON:            " + pagaCon.toString()+ "\r\n");
			content.append("VUELTO:              " + vuelto.toString() +"\r\n");
			
			InputStream inputStream = new ByteArrayInputStream(String.valueOf(content).getBytes("UTF-8"));
			
			Doc doc = new SimpleDoc(inputStream, flavor, null);// docAttr);
			
			// PrintJobWatcher pjW = new PrintJobWatcher();
			job.print(doc, null);// reqAttr); //
			
			//corte papel
			DocPrintJob jobCut = pserv.createPrintJob();
			DocFlavor flavorCut = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			byte[] bytes = new byte[] { 0x1B , 'd', 0x0A , 0x1B , 'i' , 0x20 }; //ver codigo
			Doc cut = new SimpleDoc(bytes, flavorCut, null);
			jobCut.print(cut, null);
			
			
			
			// pjW.waitForDone(); inputStream.close();
			inputStream.close();
		} catch (Exception e) {

			System.out.println("ERROR-02:" + e.getMessage());
		}

		return true;

	}

}
