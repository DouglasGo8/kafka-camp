package com.udemy.trainning.kafka.camp.section06.starter.jsonapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.camel.Handler;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@Getter
@NoArgsConstructor
@AllArgsConstructor
//@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Employee {

  private String empId;
  private String empName;

  //@JsonSerialize(using = LocalDateSerializer.class)
  //@JsonDeserialize(using = LocalDateDeserializer.class)
  private String birthDate;

  @Handler
  public Employee newEmp() {
    final int day = ThreadLocalRandom.current().nextInt(1, 31);
    final int year = ThreadLocalRandom.current().nextInt(1960, 2010);
    final int month = ThreadLocalRandom.current().nextInt(1, 12);
    //
    return new Employee(UUID.randomUUID().toString(),
            "employee_name_" + ThreadLocalRandom.current().nextInt(100),
            year + "-" + month + "-" + day);
  }
}
