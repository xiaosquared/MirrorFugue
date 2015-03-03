package warp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
 

public class SurfaceXML {

	public static void initSurfaces(MFSurface[] surfaces) {
		 SAXBuilder builder = new SAXBuilder();
		 File xmlFile = new File("data/surfaces.xml");
		 
		 try {
			 Document document = (Document) builder.build(xmlFile);
			 Element rootNode = document.getRootElement();
			 List list = rootNode.getChildren("surface");
			 
			 for (int i = 0; i < list.size(); i++) {
				 Element s = (Element) list.get(i);
				 int TLx = Integer.parseInt(s.getChild("TL").getAttributeValue("x"));
				 int TLy = Integer.parseInt(s.getChild("TL").getAttributeValue("y"));
				 int TRx = Integer.parseInt(s.getChild("TR").getAttributeValue("x"));
				 int TRy = Integer.parseInt(s.getChild("TR").getAttributeValue("y"));
				 int BLx = Integer.parseInt(s.getChild("BL").getAttributeValue("x"));
				 int BLy = Integer.parseInt(s.getChild("BL").getAttributeValue("y"));
				 int BRx = Integer.parseInt(s.getChild("BR").getAttributeValue("x"));
				 int BRy = Integer.parseInt(s.getChild("BR").getAttributeValue("y"));
				 
				 surfaces[i].setControlPoints(TLx, TLy, TRx, TRy, BLx, BLy, BRx, BRy);
			 }
			 
			 
		 } catch (IOException io) {
				System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
		 
		 
	}
	
	public static void updateSurfaces(MFSurface[] surfaces){
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("data/surfaces.xml");

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			List list = rootNode.getChildren("surface");
			
			for (int i = 0; i < list.size(); i++) {
				MFSurface surface = surfaces[i];
				
				Element s = (Element) list.get(i);
				s.getChild("TL").setAttribute("x", String.valueOf((int) surface.TL.x));
				s.getChild("TL").setAttribute("y", String.valueOf((int) surface.TL.y));
				s.getChild("TR").setAttribute("x", String.valueOf((int) surface.TR.x));
				s.getChild("TR").setAttribute("y", String.valueOf((int) surface.TR.y));
				s.getChild("BL").setAttribute("x", String.valueOf((int) surface.BL.x));
				s.getChild("BL").setAttribute("y", String.valueOf((int) surface.BL.y));
				s.getChild("BR").setAttribute("x", String.valueOf((int) surface.BR.x));
				s.getChild("BR").setAttribute("y", String.valueOf((int) surface.BR.y));
			}
			
			
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data/surfaces.xml"));

			xmlOutput.output(doc, System.out);

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeXML() {
		try {
			Element mapping = new Element("Mapping");
			Document doc = new Document(mapping);
			
			
			//SURFACE 0
			Element s0 = new Element("surface");
			s0.setAttribute(new Attribute("id", "0"));
			
			Element tl0 = new Element("TL");
			tl0.setAttribute(new Attribute("x", "89"));
			tl0.setAttribute(new Attribute("y", "504"));
			s0.addContent(tl0);
			
			Element tr0 = new Element("TR");
			tr0.setAttribute(new Attribute("x", "338"));
			tr0.setAttribute(new Attribute("y", "501"));
			s0.addContent(tr0);
			
			Element bl0 = new Element("BL");
			bl0.setAttribute(new Attribute("x", "-23"));
			bl0.setAttribute(new Attribute("y", "712"));
			s0.addContent(bl0);
			
			Element br0 = new Element("BR");
			br0.setAttribute(new Attribute("x", "290"));
			br0.setAttribute(new Attribute("y", "711"));
			s0.addContent(br0);
			
			doc.getRootElement().addContent(s0);
			
			//SURFACE 1
			Element s1 = new Element("surface");
			s1.setAttribute(new Attribute("id", "1"));
			
			Element tl1 = new Element("TL");
			tl1.setAttribute(new Attribute("x", "331"));
			tl1.setAttribute(new Attribute("y", "503"));
			s1.addContent(tl1);
			
			Element tr1 = new Element("TR");
			tr1.setAttribute(new Attribute("x", "569"));
			tr1.setAttribute(new Attribute("y", "507"));
			s1.addContent(tr1);
			
			Element bl1 = new Element("BL");
			bl1.setAttribute(new Attribute("x", "285"));
			bl1.setAttribute(new Attribute("y", "707"));
			s1.addContent(bl1);
			
			Element br1 = new Element("BR");
			br1.setAttribute(new Attribute("x", "579"));
			br1.setAttribute(new Attribute("y", "712"));
			s1.addContent(br1);
			
			doc.getRootElement().addContent(s1);
		

			//SURFACE 2
			Element s2 = new Element("surface");
			s2.setAttribute(new Attribute("id", "2"));
			
			Element tl2 = new Element("TL");
			tl2.setAttribute(new Attribute("x", "567"));
			tl2.setAttribute(new Attribute("y", "508"));
			s2.addContent(tl2);
			
			Element tr2 = new Element("TR");
			tr2.setAttribute(new Attribute("x", "810"));
			tr2.setAttribute(new Attribute("y", "509"));
			s2.addContent(tr2);
			
			Element bl2 = new Element("BL");
			bl2.setAttribute(new Attribute("x", "580"));
			bl2.setAttribute(new Attribute("y", "713"));
			s2.addContent(bl2);
			
			Element br2 = new Element("BR");
			br2.setAttribute(new Attribute("x", "885"));
			br2.setAttribute(new Attribute("y", "721"));
			s2.addContent(br2);
			
			doc.getRootElement().addContent(s2);
	
			//OUTPUT
			new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data/file.xml"));

			System.out.println("File Saved!");
		} 
		catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}
}
