/*
 * generated by Xtext 2.10.0
 */
package org.softlang.metalib.xtext.fsml.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public abstract class AbstractFsmlValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(org.softlang.metalib.xtext.fsml.fsml.FsmlPackage.eINSTANCE);
		return result;
	}
	
}