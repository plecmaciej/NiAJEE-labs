package org.example.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.datastore.component.DataStore;
import org.example.serialization.component.CloningUtility;

/**
 * Listener started automatically on servlet context initialized. Creates an instance of datasource and puts it in the
 * application (servlet) context.
 */
@WebListener//using annotation does not allow configuring order
public class CreateDataSource implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("datasource", new DataStore(new CloningUtility()));
    }

}
