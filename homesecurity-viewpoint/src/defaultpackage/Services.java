package defaultpackage;

import java.util.stream.Collectors;

import io.opencaesar.oml.ConceptInstance;
import io.opencaesar.oml.PropertyRangeRestrictionAxiom;
import io.opencaesar.oml.util.OmlSearch;

/**
 * The services class used by VSM.
 */
public class Services {
    
    /**
    * See http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24 for documentation on how to write service methods.
    */
    public String centerLabel(ConceptInstance instance) {
      return OmlSearch.findAllTypes(instance).stream()
      	.flatMap(t -> OmlSearch.findPropertyRestrictionAxioms(t).stream())
      	.filter(a -> a instanceof PropertyRangeRestrictionAxiom)
      	.filter(a -> a.getProperty().getAbbreviatedIri().equals("system:isTraversedBy"))
      	.map(a -> ((PropertyRangeRestrictionAxiom)a).getRange().getName())
      	.collect(Collectors.joining(","));
    }
}
