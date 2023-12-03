package com.udemy.trainning.kafka.camp.section07.message.filter.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarLocation {
  private String carId;
  private long timeStamp;
  private long distance;
}
