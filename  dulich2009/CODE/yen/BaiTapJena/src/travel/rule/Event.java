package travel.rule;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import sun.security.jca.GetInstance.Instance;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * An example program that tests the DL-safe rules example from Table 3 in the
 * paper: B. Motik, U. Sattler, R. Studer. Query Answering for OWL-DL with
 * Rules. Proc. of the 3rd International Semantic Web Conference (ISWC 2004),
 * Hiroshima, Japan, November, 2004, pp. 549-563
 * 
 * @author Evren Sirin
 */
public class Event {
	public static void main(String[] args) {
		String ont = "http://www.owl-ontologies.com/Ontology1253719048.owl";

		OntModel model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC, null );
		try {
			model.read( new FileInputStream(new File("data/Event.owl")), "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//String pre = "";
		ObjectProperty beginPoint = model.getObjectProperty( ont + "#hasBeginPoint" );
		ObjectProperty endPoint = model.getObjectProperty( ont + "#hasEndPoint" );
		OntClass cl = model.getOntClass( ont + "#Conference_Lecture" );
		
		model.prepare();

		//Print individual inferred
		
		OntResource resource = cl.listInstances().next();			
		System.out.println("Dich vu ket hop: " + cl);
		System.out.println("Ten: " + resource.getLocalName());
		// print properties of resource
		Individual individual = model.getIndividual( ont + "#" + resource.getLocalName() );
		printPropertyValues( individual, beginPoint );
		printPropertyValues( individual, endPoint );
	}

	public static void printPropertyValues(Individual ind, Property prop) {
		System.out.print( ind.getLocalName() + " has " + prop.getLocalName() + "(s): " );
		printIterator( ind.listPropertyValues( prop ) );
	}

	public static void printInstances(OntClass cls) {
		System.out.print( cls.getLocalName() + " instances: " );
		printIterator( cls.listInstances() );
	}

	public static void printIterator(ExtendedIterator i) {
		if( !i.hasNext() ) {
			System.out.print( "none" );
		}
		else {
			while( i.hasNext() ) {
				Resource val = (Resource) i.next();
				System.out.print( val.getLocalName() );
				if( i.hasNext() )
					System.out.print( ", " );
			}
		}
		System.out.println();
	}
}

