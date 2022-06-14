package com.example.kafka;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.functions.ProcessFunction;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;


import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.common.serialization.StringDeserializer;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;

import java.util.Properties;
import java.io.*;


public class flink_kafka {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.9:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "divolte.collector");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SimpleStringSchema schema = new SimpleStringSchema();
        FlinkKafkaConsumer010 consumer = new FlinkKafkaConsumer010<>("test1", new SimpleStringSchema(), props);

        DataStream msgStream = env.addSource(consumer);





        msgStream.flatMap(new Deserailizer())
                .keyBy(x -> {
                    GenericRecord a = (GenericRecord) x ;
                    String x1 = a.get("remoteHost").toString();
                    return x1;
                })
                .process(new food())
                .print();



        env.execute();
    }
    public static class Deserailizer implements FlatMapFunction<String,GenericRecord>{
        @Override
        public void flatMap(String o , Collector<GenericRecord> out) throws  Exception{
            byte[] ob = o.getBytes();
            String path = System.getenv("DIVOLTE_SCHEMA");

            File file = new File(path);
            Schema schema = new Schema.Parser().parse(file);
            Decoder decoder = DecoderFactory.get().binaryDecoder(ob,null);
            DatumReader reader = new SpecificDatumReader<>(schema,schema);
            GenericRecord payload = (GenericRecord) reader.read(null,decoder);

            out.collect((GenericRecord) payload);
        }
    }
    public static class food extends ProcessFunction<GenericRecord,GenericRecord> {
        ValueState<Integer> count;
        private static final long ONE_SECOND = 1000;
        ValueState<Long> timerState;
        @Override
        public void open(Configuration c){
            ValueStateDescriptor<Integer> descriptor = new ValueStateDescriptor<>("count",Types.INT);
            count = getRuntimeContext().getState(descriptor);
            ValueStateDescriptor<Long> descriptor1 = new ValueStateDescriptor<Long>("timer",Types.LONG);
            timerState = getRuntimeContext().getState(descriptor1);
        }
        @Override
        public void processElement(GenericRecord in, Context context, Collector<GenericRecord> out) throws Exception {
            String current = in.get("eventType").toString();
            if (current.equals("Gao") || current.equals("Rau muong")){
                System.out.println(current);
                if( count.value() == null){
                    long timer = context.timerService().currentProcessingTime() + 15 * ONE_SECOND;
                    count.update(1);
                    context.timerService().registerProcessingTimeTimer(timer);
                    timerState.update(timer);

                }
                else{
                    count.update(count.value() + 1);
                }
            }
            if(count.value() != null && count.value() >= 2){
                System.out.println("Need to buy meet");
            }
        }
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<GenericRecord> out) {
            // remove flag after 1 minute
            System.out.println("<-------------------------------15 second out------------------------------------------->");
            timerState.clear();
            count.clear();
        }
    }

}