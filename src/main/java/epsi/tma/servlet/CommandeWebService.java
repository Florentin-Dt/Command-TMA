/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.servlet;

import epsi.tma.service.ICommandeService;
import epsi.tma.util.StringUtil;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author florentin
 */
@Path("/order")
public class CommandeWebService {

    private static final Logger LOG = LogManager.getLogger(CommandeWebService.class);

    private ICommandeService commandeService;

    /*
     * Create orders
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/simulate")
    public Response simulate(@Context ServletContext servletContext, @QueryParam("idMagasin") int idMagasin) {
        LOG.info("SIMULATE MAGASIN COMMAND - WS STACK");
        try {
            if (idMagasin != 0) {
                ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
                this.commandeService = appContext.getBean(ICommandeService.class);
                return Response.ok(commandeService.simulateMagasinCommande(1, idMagasin, 1)).build();
            }

        } catch (Exception e) {
            //   LOG.error("Catch error during databaseVersioningService running by monitor web service", e);
            return Response.ok(e).build();
        }
        return Response.ok("UNSPECIFIED idMagasin TO SIMULATE COMMAND").build();
    }

    /*
     * Update command
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateState(@Context ServletContext servletContext, @QueryParam("idCommande") int idCommande, @QueryParam("idEtat") int idEtat) {
        LOG.info("update command call");
        if (idEtat != 0 && idCommande != 0) {
            try {
                ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
                this.commandeService = appContext.getBean(ICommandeService.class);
                String response = commandeService.updateCommand(idEtat, idCommande);
                return Response.ok(response).build();
            } catch (Exception e) {
                //   LOG.error("Catch error during databaseVersioningService running by monitor web service", e);
                return Response.ok(e).build();
            }
        }
        return Response.ok("Unspecify idCommande or idEtat requested").build();
    }

    /*
     * Update command
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/clear")
    public Response clear(@Context ServletContext servletContext) {
        LOG.info("CLEAR ALL ORDER");
        try {
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            this.commandeService = appContext.getBean(ICommandeService.class);
            commandeService.clear();
            return Response.ok("CLEAR").build();
        } catch (Exception e) {
            //   LOG.error("Catch error during databaseVersioningService running by monitor web service", e);
            return Response.ok(e).build();
        }
    }

    /*
     * Update command
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cleartoday")
    public Response clearToday(@Context ServletContext servletContext) {
        LOG.info("UPDATE ALL ORDER");
        try {
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            this.commandeService = appContext.getBean(ICommandeService.class);
            commandeService.clearTodayStatus();
            return Response.ok("CLEAR").build();
        } catch (Exception e) {
            //   LOG.error("Catch error during databaseVersioningService running by monitor web service", e);
            return Response.ok(e).build();
        }
    }
}
