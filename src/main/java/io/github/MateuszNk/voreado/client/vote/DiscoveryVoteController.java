package io.github.MateuszNk.voreado.client.vote;

import io.github.MateuszNk.voreado.domain.api.DiscoveryVote;
import io.github.MateuszNk.voreado.domain.api.DiscoveryVoteService;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/discovery/vote")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class DiscoveryVoteController extends HttpServlet {
    private DiscoveryVoteService discoveryVoteService = new DiscoveryVoteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DiscoveryVote discoveryVote = createDiscoveryVote(request);
        discoveryVoteService.addVote(discoveryVote);
        response.sendRedirect(request.getContextPath());
    }

    private DiscoveryVote createDiscoveryVote(HttpServletRequest request) {
        Integer discoveryId = Integer.valueOf(request.getParameter("id"));
        String voteType = request.getParameter("type");
        String username = request.getUserPrincipal().getName();
        return new DiscoveryVote(username, discoveryId, voteType);
    }
}