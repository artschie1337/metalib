/*
 * generated by Xtext 2.10.0
 */
package org.softlang.metalib.xtext.fsml


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class FsmlStandaloneSetup extends FsmlStandaloneSetupGenerated {

	def static void doSetup() {
		new FsmlStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}