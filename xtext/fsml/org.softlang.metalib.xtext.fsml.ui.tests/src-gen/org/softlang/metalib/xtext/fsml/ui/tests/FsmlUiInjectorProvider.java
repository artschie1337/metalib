/*
 * generated by Xtext 2.10.0
 */
package org.softlang.metalib.xtext.fsml.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.junit4.IInjectorProvider;
import org.softlang.metalib.xtext.fsml.ui.internal.FsmlActivator;

public class FsmlUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return FsmlActivator.getInstance().getInjector("org.softlang.metalib.xtext.fsml.Fsml");
	}

}