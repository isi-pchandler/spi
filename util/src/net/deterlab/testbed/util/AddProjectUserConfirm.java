package net.deterlab.testbed.util;

import java.util.Arrays;

import net.deterlab.testbed.api.DeterFault;

import net.deterlab.testbed.client.ProjectsDeterFault;
import net.deterlab.testbed.client.ProjectsStub;

import org.apache.axis2.AxisFault;

/**
 * Add the users to the project with the given permissions.  Print the results.
 * @author DETER Team
 * @version 1.0
 */
public class AddProjectUserConfirm extends Utility {

    static public void usage() {
	fatal("Usage: AddProjectUserConfirm ID");
    }

    /**
     * Collect the users, call add and display results.
     * @param args the projectid permissions and users
     */
    static public void main(String[] args) {
	try {

	    // Set trusted certificates.
	    loadTrust();
	    loadID();


	    if ( args.length < 1) 
		usage();

	    // Pass the parame to addUsersNoConfirm directly and print results
	    ProjectsStub stub = new ProjectsStub(getServiceUrl("Projects"));
	    ProjectsStub.AddUserConfirm req = new ProjectsStub.AddUserConfirm();

	    req.setChallengeId(Long.parseLong(args[0]));
	    ProjectsStub.AddUserConfirmResponse resp = stub.addUserConfirm(req);
	    System.out.println("Confirmed");

	} catch (ProjectsDeterFault e) {
	    DeterFault df = getDeterFault(e);
	    fatal(df.getErrorMessage() + ": " + df.getDetailMessage());
	} catch (NumberFormatException e) {
	    fatal("permissions is not an integer");
	} catch (AxisFault e) {
	    handleAxisFault(e);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
