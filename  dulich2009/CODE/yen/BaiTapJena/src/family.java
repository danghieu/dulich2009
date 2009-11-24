
/* CVS $Id: $ */

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.ontology.*;
 
/**
 * Vocabulary definitions from file:/C:/Program%20Files/Protege_3.4/schemagen-temp.owl 
 * @author Auto-generated by schemagen on 15 Oct 2009 21:29 
 */
public class family {
    /** <p>The ontology model that holds the vocabulary terms</p> */
    private static OntModel m_model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    public static OntModel getOntModel(){
    	return m_model;
    }
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    public static final ObjectProperty hasAunt = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasAunt" );
    
    public static final ObjectProperty hasBrother = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasBrother" );
    
    public static final ObjectProperty hasChild = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasChild" );
    
    public static final ObjectProperty hasConsort = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasConsort" );
    
    public static final ObjectProperty hasDaughter = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasDaughter" );
    
    public static final ObjectProperty hasFather = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasFather" );
    
    public static final ObjectProperty hasMother = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasMother" );
    
    public static final ObjectProperty hasNephew = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasNephew" );
    
    public static final ObjectProperty hasNiece = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasNiece" );
    
    public static final ObjectProperty hasParent = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasParent" );
    
    public static final ObjectProperty hasSex = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasSex" );
    
    public static final ObjectProperty hasSibling = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasSibling" );
    
    public static final ObjectProperty hasSister = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasSister" );
    
    public static final ObjectProperty hasSon = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasSon" );
    
    public static final ObjectProperty hasUncle = m_model.createObjectProperty( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasUncle" );
    
    public static final OntClass Aunt = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Aunt" );
    
    public static final OntClass Brother = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Brother" );
    
    public static final OntClass Child = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Child" );
    
    public static final OntClass Daugther = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Daugther" );
    
    public static final OntClass Father = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Father" );
    
    public static final OntClass Gender = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Gender" );
    
    public static final OntClass Man = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Man" );
    
    public static final OntClass Mother = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Mother" );
    
    public static final OntClass Nephew = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Nephew" );
    
    public static final OntClass Niece = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Niece" );
    
    public static final OntClass Parent = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Parent" );
    
    public static final OntClass Person = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Person" );
    
    public static final OntClass Relative = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Relative" );
    
    public static final OntClass Sibling = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Sibling" );
    
    public static final OntClass Sister = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Sister" );
    
    public static final OntClass Son = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Son" );
    
    public static final OntClass Uncle = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Uncle" );
    
    public static final OntClass Woman = m_model.createClass( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Woman" );
    
    public static final Individual BaChinh = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#BaChinh", Mother );
    
    public static final Individual BacNhuan = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#BacNhuan", Aunt );
    
    public static final Individual BoHuan = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#BoHuan", Father );
    
    public static final Individual ChuTho = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ChuTho", Brother );
    
    public static final Individual ConHai = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ConHai", Son );
    
    public static final Individual ConYen = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ConYen", Daugther );
    
    public static final Individual ConYeu = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ConYeu", Daugther );
    
    public static final Individual Conhai = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Conhai", Son );
    
    public static final Individual F01 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F01", Woman );
    
    public static final Individual F02 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F02", Woman );
    
    public static final Individual F03 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F03", Woman );
    
    public static final Individual F04 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F04", Woman );
    
    public static final Individual F05 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F05", Woman );
    
    public static final Individual F06 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F06", Woman );
    
    public static final Individual F07 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F07", Woman );
    
    public static final Individual F08 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F08", Woman );
    
    public static final Individual F09 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F09", Woman );
    
    public static final Individual F10 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#F10", Woman );
    
    public static final Individual Female = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Female", Gender );
    
    public static final Individual M01 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M01", Man );
    
    public static final Individual M02 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M02", Man );
    
    public static final Individual M03 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M03", Man );
    
    public static final Individual M04 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M04", Man );
    
    public static final Individual M05 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M05", Man );
    
    public static final Individual M06 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M06", Man );
    
    public static final Individual M07 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M07", Man );
    
    public static final Individual M08 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M08", Man );
    
    public static final Individual M09 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M09", Man );
    
    public static final Individual M10 = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#M10", Man );
    
    public static final Individual Male = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Male", Gender );
    
    public static final Individual MeThao = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#MeThao", Mother );
    
    public static final Individual OngChinh = m_model.createIndividual( "http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#OngChinh", Father );
    
}
