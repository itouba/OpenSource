package com.itouba.teamwork.api;

import com.itouba.teamwork.entry.Team;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class TeamApi {

    @PostMapping("/team")
    @ResponseBody
    public String getTeamName(@RequestBody @Valid Team team){
        return team.toString();
    }
}
