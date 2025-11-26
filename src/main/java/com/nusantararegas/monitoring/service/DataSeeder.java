//package com.nusantararegas.monitoring.service;
//
//import com.nusantararegas.monitoring.entity.Status;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class DataSeeder {
//
//    private final StatusService statusService;
//
//    @PostConstruct
//    public void init() {
//        if (statusService.countStatuses() == 0) {
//            log.info("Seeding initial status data...");
//
//            Status status = new Status();
//            status.setStatusName("on duty");
//            statusService.createStatus(status);
//            Status status2 = new Status();
//            status2.setStatusName("day off");
//            statusService.createStatus(status2);
//            Status status3 = new Status();
//            status3.setStatusName("cuti");
//            statusService.createStatus(status3);
//            Status status4 = new Status();
//            status4.setStatusName("sakit");
//            statusService.createStatus(status4);
//            Status status5 = new Status();
//            status5.setStatusName("izin");
//            statusService.createStatus(status5);
//            Status status6 = new Status();
//            status6.setStatusName("dinas");
//            statusService.createStatus(status6);
//
//            log.info("Initial status data seeded successfully.");
//        } else {
//            log.info("Status data already exists. Skipping seeding.");
//        }
//    }
//
//}
