package br.com.microservices.orchestrated.orderservice.Core.controller;

import br.com.microservices.orchestrated.orderservice.Core.document.Event;
import br.com.microservices.orchestrated.orderservice.Core.dto.EventFilters;
import br.com.microservices.orchestrated.orderservice.Core.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@AllArgsConstructor
public class EventController {
    public final EventService service;

    @GetMapping
    public Event findByFilters(EventFilters filters){
      return  service.findByFilters(filters);
    }

    @GetMapping("all")
    public List<Event> findAll(){
         return  service.findAll();
    }

    @GetMapping("/palavra")
    public String getPalavra() {
        return "este";
    }

}
