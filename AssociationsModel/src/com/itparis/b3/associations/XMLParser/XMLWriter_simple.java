package com.itparis.b3.associations.XMLParser;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.beans.AssociationDesc;
import com.itparis.b3.associations.beans.AssociationEvent;
import com.itparis.b3.associations.beans.FicheParticipant;
import com.itparis.b3.associations.beans.ParticipantEvents;
import com.itparis.b3.associations.beans.TypeUser;
import com.itparis.b3.associations.beans.User;

public class XMLWriter_simple {

	private String fname = "";
	private String dateGen = "";
	private String authorGen = "";
	private String descGen = "";
	private Object wrapper;
	private List<HashMap<String,String>> lstDat;
  	private ArrayList <String> lstNames;
	
	public XMLWriter_simple (Object o) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date date = new Date();
	    fname = dateFormat.format(date);
	    dateGen = date.toString();
	    authorGen = System.getProperty("user.name");
	    descGen = "simple xml generator in study project";
		wrapper = o;
		lstDat = new ArrayList<HashMap<String,String>>();
		lstNames = new ArrayList <String> ();
	}
	
	private HashMap <String,String> generateData (Object o) {

		HashMap <String,String> hmap = new HashMap <String,String> ();
		Field fields [] = o.getClass().getDeclaredFields();
		lstNames.add(o.getClass().getSimpleName());
		for (Field f : fields) {
			f.setAccessible(true);
				try {
				    if (f.getType().isPrimitive() || f.getType() == String.class) {
						Object val = f.get(o);
						hmap.put(f.getName(), val+"");
				    }
				} 
				catch (IllegalArgumentException | IllegalAccessException e) {e.printStackTrace();}
		}
		return hmap; 
	}
	
	
	private void LoadList () {
		
        if (wrapper instanceof Association) {
          	lstDat.add(generateData((Association) wrapper));
        	lstDat.add(generateData(((Association) wrapper).desc));
        }
        
        if (wrapper instanceof AssociationDesc) {
          	lstDat.add(generateData((AssociationDesc) wrapper));
        }
        
        if (wrapper instanceof AssociationEvent) {
          	lstDat.add(generateData((AssociationEvent) wrapper));
          	
          	for (ParticipantEvents p : (((AssociationEvent) wrapper)).lstParticipant) {
          		lstDat.add(generateData (p));
          	}
        }
        
        if (wrapper instanceof FicheParticipant) {
          	lstDat.add(generateData((FicheParticipant) wrapper));
          	lstDat.add(generateData(((FicheParticipant) wrapper).utilisateur));
        }
        
        if (wrapper instanceof ParticipantEvents) {
          	lstDat.add(generateData((ParticipantEvents) wrapper));
          	lstDat.add(generateData(((ParticipantEvents) wrapper).utilisateur));
          	lstDat.add(generateData(((ParticipantEvents) wrapper).userType));
        }
        
        if (wrapper instanceof TypeUser) {
          	lstDat.add(generateData((TypeUser) wrapper));
        }
        
        if (wrapper instanceof User) {
          	lstDat.add(generateData((User) wrapper));
        	lstDat.add(generateData(((User) wrapper).type));
        	
          	for (Association a : (((User) wrapper)).assoc) {
          		lstDat.add(generateData (a));
          	}
        } 
	}
	
	public String generateFile ()  {
		  try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("root");
			doc.appendChild(root);
			
			// header
			Element head = doc.createElement("DocInfo");
			root.appendChild(head);
			
			Element filename = doc.createElement("filename");
			filename.appendChild(doc.createTextNode(fname));
			head.appendChild(filename);
            
			Element dategen = doc.createElement("date");
			dategen.appendChild(doc.createTextNode(dateGen));
			head.appendChild(dategen);
            
			Element author = doc.createElement("author");
			author.appendChild(doc.createTextNode(authorGen));
            head.appendChild(author);
            
			Element desc = doc.createElement("description");
			desc.appendChild(doc.createTextNode(descGen));
            head.appendChild(desc);
            //end header
            
            //Body
            LoadList ();
            
            for (int i = 0; i<lstDat.size(); i++ ) {
                    Element body = doc.createElement(lstNames.get(i));
                	root.appendChild(body);
                	    
	                for (Map.Entry<String,String> entry : lstDat.get(i).entrySet()){
	                	Element elem = doc.createElement(entry.getKey());
	                	elem.appendChild(doc.createTextNode(entry.getValue()));
	                	body.appendChild(elem);
	                }
             }
            //end body

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(new File("xml/"+fname+".xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		  return fname;
		}

	public static void main(String argv[]) { 
		
		Association a = new Association ();
		a.setId(1);
		a.setLibelle("test");

		AssociationEvent as = new AssociationEvent ();
		as.setId(1);
		ParticipantEvents p = new ParticipantEvents ();
		p.setIdAssoc(1);
		p.setIdUser(2);
		p.setPresence(1);
		p.setIdEvent(1);
		p.utilisateur.setAdresse("test");
		p.userType.setId(1);
		p.utilisateur.setNom("test");

		as.lstParticipant.add(p);
		as.lstParticipant.add(p);
		as.lstParticipant.add(p);
		
		XMLWriter_simple writer =  new XMLWriter_simple (as);
		writer.generateFile();
		
	}
}

