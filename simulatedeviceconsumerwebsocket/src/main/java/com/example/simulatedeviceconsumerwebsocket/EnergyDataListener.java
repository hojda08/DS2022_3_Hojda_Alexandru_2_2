package com.example.simulatedeviceconsumerwebsocket;

import com.example.simulatedeviceconsumerwebsocket.testwebsocket.Greeting;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@Component
@RestController
@Controller
public class EnergyDataListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    long energyConsumed = 0;
    boolean notifyFlag = false;
    EnergyData energyDataProvided = null;
    String user = "";

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(EnergyData energyData){

        System.out.println(energyData);

        energyDataProvided = energyData;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Scheduled(fixedDelay = 1000)
    public void users(){

        if(energyDataProvided != null){
            String url = "http://localhost:8080/devices/" + energyDataProvided.getDeviceId();
            RestTemplate restTemplate = new RestTemplate();
            DeviceDTO deviceDTO = restTemplate.getForObject(url,DeviceDTO.class);

            System.out.println(deviceDTO);

            energyConsumed += Long.parseLong(energyDataProvided.getMeasurementValue().replaceAll("[^0-9]",""));
            System.out.println(energyConsumed);

            if(energyConsumed > Long.parseLong(deviceDTO.getCapacity().replaceAll("[^0-9]",""))){
                System.out.println("Notify " + deviceDTO.getOwner() + "!");


                simpMessagingTemplate.convertAndSend("/topic/greetings",new Greeting("Notify " + deviceDTO.getOwner() + "!"));

                user  = deviceDTO.getOwner();
                energyConsumed = 0;
                notifyFlag = true;
            }

            energyDataProvided = null;
        }

    }

//    @MessageMapping("/hello")
//    //@Scheduled(fixedDelay = 1000)
//    @SendTo("/topic/greetings")
//    public String notifyExceded(){
//        if(notifyFlag){
//            notifyFlag = false;
//            return "ExcededValue for user: " + user;
//        }
//        return null;
//    }
}
