package com.orderflow;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/hello")
public class GreetingResource {


    @Inject
    MyEntityRepository myEntityRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.SUPPORTS)
    public MyEntity hello() {
        System.out.println("BREAKPOINT TEST");
        return myEntityRepository.findById(1L);
    }
}