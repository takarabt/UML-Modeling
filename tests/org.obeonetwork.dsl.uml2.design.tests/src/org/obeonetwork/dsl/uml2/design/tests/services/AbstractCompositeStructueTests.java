/*******************************************************************************
 * Copyright (c) 2009, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.uml2.design.tests.services;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Usage;
import org.obeonetwork.dsl.uml2.design.services.UMLServices;

/**
 * Abstract Unit tests facilities to read the UML resource test.
 * 
 * @author <a href="mailto:hugo.marchadour@obeo.fr">Hugo Marchadour</a>
 */
public abstract class AbstractCompositeStructueTests extends TestCase {
	/**
	 * Upper package name.
	 */
	protected static final String UPPER_PACKAGE_OPOSITE_GENERALIZATION_RELATED_CLASS = "CompositeStructureRelatedElement";

	/**
	 * Instance of the services class used during tests.
	 */
	protected final UMLServices services = new UMLServices();

	/**
	 * The root package of the semantic resource.
	 */
	protected Package rootPackage;

	protected StructuredClassifier A, Aa, Ab, Ac, B, C;

	protected Port Ap, Aap, Abp, Acp, Bp, Cp;

	protected Interface I1, I2, I3, I4;

	protected Usage usageI1, usageI2, usageAI2, usageAI3, usageAI4;

	protected InterfaceRealization irAI1, irAaI1, irCI2, irAcI3, irAbI4;

	protected Property AProp1, AProp2;

	protected Collection<EObject> AllItems;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		loadResource();
	}

	public abstract String getRessourceURI();

	private void loadResource() {

		final ResourceSet rset = new ResourceSetImpl();
		final Resource resource = rset
				.getResource(URI.createPlatformPluginURI(getRessourceURI(), true), true);

		rootPackage = (Package)((Model)resource.getContents().get(0));
		A = (StructuredClassifier)rootPackage.getOwnedMember("A");
		Aa = (StructuredClassifier)A.getOwnedMember("Aa");
		Ab = (StructuredClassifier)A.getOwnedMember("Ab");
		Ac = (StructuredClassifier)A.getOwnedMember("Ac");
		AProp1 = (Property)A.getOwnedMember("AProp1");
		AProp2 = (Property)A.getOwnedMember("AProp2");
		B = (StructuredClassifier)rootPackage.getOwnedMember("B");
		C = (StructuredClassifier)rootPackage.getOwnedMember("C");

		I1 = (Interface)rootPackage.getOwnedMember("I1");
		I2 = (Interface)rootPackage.getOwnedMember("I2");
		I3 = (Interface)rootPackage.getOwnedMember("I3");
		I4 = (Interface)rootPackage.getOwnedMember("I4");

		Ap = (Port)A.getOwnedMember("Ap");
		Aap = (Port)Aa.getOwnedMember("Aap");
		Abp = (Port)Ab.getOwnedMember("Abp");
		Acp = (Port)Ac.getOwnedMember("Acp");
		Bp = (Port)B.getOwnedMember("Bp");
		Cp = (Port)C.getOwnedMember("Cp");

		usageI1 = (Usage)rootPackage.getOwnedMember("usageI1");
		usageI2 = (Usage)rootPackage.getOwnedMember("usageI2");
		usageAI2 = (Usage)rootPackage.getOwnedMember("usageAI2");
		usageAI3 = (Usage)rootPackage.getOwnedMember("usageAI3");
		usageAI4 = (Usage)rootPackage.getOwnedMember("usageAI4");

		irAI1 = (InterfaceRealization)A.getRelationships().get(0);
		irAaI1 = (InterfaceRealization)Aa.getRelationships().get(0);
		irCI2 = (InterfaceRealization)C.getRelationships().get(0);
		irAcI3 = (InterfaceRealization)Ac.getRelationships().get(0);
		irAbI4 = (InterfaceRealization)Ab.getRelationships().get(0);

		AllItems = Arrays.asList(new EObject[] {A, Aa, Ab, Ac, B, C, Ap, Aap, Abp, Acp, Bp, Cp, usageI1,
				usageI2, usageAI2, usageAI3, usageAI4, irAI1, irAaI1, irCI2, irAcI3, irAbI4, AProp1, AProp2});

		for (EObject item : AllItems) {
			assertNotNull(item);
		}
		assertNotNull(I1);
		assertNotNull(I2);
		assertNotNull(I3);
		assertNotNull(I4);
	}

	public static String getName(EObject object) {
		if (object instanceof ENamedElement) {
			return ((ENamedElement)object).getName();
		} else if (object instanceof NamedElement) {
			return ((NamedElement)object).getName();
		} else {
			return object.toString();
		}

	}
}
