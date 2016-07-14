package kyun.framework.team.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kyun.framework.team.entity.Team;
import kyun.framework.team.service.TeamService;

@Controller
@RequestMapping(value = "/api/team")
public class TeamRestController {

	@Autowired
	TeamService teamService;
	
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Team> getTeams(){
		List<Team> teams = teamService.getTeams();
		
		return teams;
	}
}
