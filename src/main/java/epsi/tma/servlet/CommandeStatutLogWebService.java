/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.servlet;

import epsi.tma.dao.ICommandeStatutLogDAO;
import epsi.tma.service.IDatabaseVersioningService;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author utilisateur
 */
@Path("/commandeLog")
public class CommandeStatutLogWebService {

    private static final Logger LOG = LogManager.getLogger(MonitorWebService.class);

    private ICommandeStatutLogDAO commandeStatutLogDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read")
    public Response read(@Context ServletContext servletContext) {
        LOG.info("read log call");
        try {
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            this.commandeStatutLogDAO = appContext.getBean(ICommandeStatutLogDAO.class);
            return Response.ok(commandeStatutLogDAO.read()).build();
        } catch (Exception e) {
            LOG.error("Catch error during databaseVersioningService running by monitor web service", e);
            return Response.ok(e).build();
        }
    }
}
