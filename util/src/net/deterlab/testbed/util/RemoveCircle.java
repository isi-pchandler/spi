package net.deterlab.testbed.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import net.deterlab.testbed.api.Attribute;
import net.deterlab.testbed.api.DeterFault;

import net.deterlab.testbed.client.CirclesDeterFault;
import net.deterlab.testbed.client.CirclesStub;

/**
 * Remove a circle and its profile.
 * @author DETER Team
 * @version 1.0
 */
public class RemoveCircle extends Utility {

    static public void usage() {
	fatal("Usage: RemoveCircle circlename");
    }

    /**
     * Do the profile editing.  Call getProfileDescription to get all valid
     * attributes, then pop up a dialog and allow editing.  Make sure that the
     * required fields are initialized and send the request
     * @param args the uid to create is the first parameter.
     */
    static public void main(String[] args) {
	try {

	    // Set trusted certificates.
	    loadTrust();
	    loadID();

	    String name = (args.length > 0 ) ? args[0] : null;

	    if ( name == null ) 
		usage();

	    CirclesStub stub = new CirclesStub(getServiceUrl("Circles"));
	    CirclesStub.RemoveCircle req = new CirclesStub.RemoveCircle();

	    req.setCircleid(name);
	    stub.removeCircle(req);

	} catch (CirclesDeterFault e) {
	    DeterFault df = getDeterFault(e);
	    fatal(df.getErrorMessage() + ": " + df.getDetailMessage());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
