package br.unicamp.ic.inf335.meuauto.controller;

import br.unicamp.ic.inf335.meuauto.dto.CreateSchedulingRequest;
import br.unicamp.ic.inf335.meuauto.entity.User;
import br.unicamp.ic.inf335.meuauto.service.SchedulingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping
    public ResponseEntity<Object> createScheduling(
            @RequestBody CreateSchedulingRequest request
    ){
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        schedulingService.create(user, request.repairShopId(), request.datetime());

        return ResponseEntity.ok().build();
    }
}
