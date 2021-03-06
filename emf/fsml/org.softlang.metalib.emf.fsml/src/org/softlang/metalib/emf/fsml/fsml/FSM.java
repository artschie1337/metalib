/**
 */
package org.softlang.metalib.emf.fsml.fsml;

import java.util.Map;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FSM</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.softlang.metalib.emf.fsml.fsml.FSM#getStates <em>States</em>}</li>
 * </ul>
 *
 * @see org.softlang.metalib.emf.fsml.fsml.FsmlPackage#getFSM()
 * @model
 * @generated
 */
public interface FSM extends EObject {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link org.softlang.metalib.emf.fsml.fsml.FSMState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see org.softlang.metalib.emf.fsml.fsml.FsmlPackage#getFSM_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<FSMState> getStates();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean hasExactOneInitialState(DiagnosticChain diagnostics, Map<Object, Object> context);

} // FSM
