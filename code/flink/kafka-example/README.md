# click-stream-using-kafka-flink

1. Using divolte to collect click contents from web and pass to kafka 
  
  ![image](https://user-images.githubusercontent.com/83798953/173731263-238646a3-0bde-449f-9334-3327a47f6121.png)
  ![image](https://user-images.githubusercontent.com/83798953/173731360-79675411-974b-4a26-a2da-634b51bb3935.png)

2. Pass message from kafka to flink and analysis 
  ![image](https://user-images.githubusercontent.com/83798953/173732109-470f9374-ca76-420b-9216-7b08060cb4c8.png)

 2.1 : read message 
      ![image](https://user-images.githubusercontent.com/83798953/173733033-e19af3e6-a5c4-4f7e-9609-f9c363829e7a.png)

 2.2 : decode message using avro 
 
      ![image](https://user-images.githubusercontent.com/83798953/173733094-872742d7-692f-49cb-9830-8a3804c81545.png)

 2.3 : analysis realtime  
    If you click vegetables or rice twice, it will suggest buying meat 
    ![image](https://user-images.githubusercontent.com/83798953/173732800-57317417-0aba-4309-be30-954cc17b26ac.png)
3 : Build jars and submit to flink cluster : 
    ![image](https://user-images.githubusercontent.com/83798953/173733607-9790a685-9dca-46b3-9c0c-203cc4369f50.png)
    ![image](https://user-images.githubusercontent.com/83798953/173733683-22bc7dec-b9f1-4b02-91c7-814c88295a09.png)
