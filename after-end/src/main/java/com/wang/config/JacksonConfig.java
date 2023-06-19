// package com.wang.config;
//
// import com.fasterxml.jackson.core.JsonGenerator;
// import com.fasterxml.jackson.databind.SerializerProvider;
// import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
// import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
// import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
// import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
// import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
// import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import java.io.IOException;
// import java.math.BigDecimal;
// import java.math.BigInteger;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.TimeZone;
//
// /**
//  * @Author: tengfei
//  * @Create: 2023 04 24 18:36
//  * @Description 用于解决fastjson2中long反序列化精度丢失问题，先用jackson代替着
//  **/
//
// @Configuration
// public class JacksonConfig {
//
//     @Bean
//     public Jackson2ObjectMapperBuilderCustomizer customizer() {
//         return builder -> {
//             // 全局配置序列化返回 JSON 处理
//             JavaTimeModule javaTimeModule = new JavaTimeModule();
//             javaTimeModule.addSerializer(Long.class, BigNumberSerializer.INSTANCE);
//             javaTimeModule.addSerializer(Long.TYPE, BigNumberSerializer.INSTANCE);
//             javaTimeModule.addSerializer(BigInteger.class, BigNumberSerializer.INSTANCE);
//             javaTimeModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
//             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//             javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
//             javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
//             builder.modules(javaTimeModule);
//             builder.timeZone(TimeZone.getDefault());
//         };
//     }
//
//     @JacksonStdImpl
//     static class BigNumberSerializer extends NumberSerializer {
//
//         /**
//          * 根据 JS Number.MAX_SAFE_INTEGER 与 Number.MIN_SAFE_INTEGER 得来
//          */
//         private static final long MAX_SAFE_INTEGER = 9007199254740991L;
//         private static final long MIN_SAFE_INTEGER = -9007199254740991L;
//
//         /**
//          * 提供实例
//          */
//         public static final BigNumberSerializer INSTANCE = new BigNumberSerializer(Number.class);
//
//         public BigNumberSerializer(Class<? extends Number> rawType) {
//             super(rawType);
//         }
//
//         @Override
//         public void serialize(Number value, JsonGenerator gen, SerializerProvider provider) throws IOException {
//             // 超出范围 序列化位字符串
//             if (value.longValue() > MIN_SAFE_INTEGER && value.longValue() < MAX_SAFE_INTEGER) {
//                 super.serialize(value, gen, provider);
//             } else {
//                 gen.writeString(value.toString());
//             }
//         }
//     }
//
// }
