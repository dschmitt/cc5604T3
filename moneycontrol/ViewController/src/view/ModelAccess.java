package view;

import java.util.Hashtable;

import javax.naming.Context;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.SessionEJB;

public class ModelAccess {
    public static SessionEJB getSessionEJB(){
           SessionEJB sessionEJB = null;
           try {
               final Context context = getInitialContext();
               sessionEJB = (SessionEJB)context.lookup("moneycontrol-Model-SessionEJB#model.SessionEJB");
           } catch (NamingException e) {
               e.printStackTrace();
           }
           return sessionEJB;
       }
       
       private static Context getInitialContext() throws NamingException {
           Hashtable env = new Hashtable();
           // WebLogic Server 10.x connection details
           env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
           env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
           return new InitialContext( env );
       }
}
