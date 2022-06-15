# click-stream-using-kafka-flink

1. Using divolte to collect click contents from web and pass to kafka 
  
  ![image](https://user-images.githubusercontent.com/83798953/173731263-238646a3-0bde-449f-9334-3327a47f6121.png)
  ![image](https://user-images.githubusercontent.com/83798953/173731360-79675411-974b-4a26-a2da-634b51bb3935.png)

2. Pass message from kafka to flink and analysis 
  ![image](https://user-images.githubusercontent.com/83798953/173732109-470f9374-ca76-420b-9216-7b08060cb4c8.png)

 2.1 : read message 
 
 2.2 : decode message using avro 
 
 2.3 : analysis realtime  
    If you click vegetables or rice twice, it will suggest buying meat 
    
