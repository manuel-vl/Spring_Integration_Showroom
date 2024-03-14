package com.showroom.exercise.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ventas")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long numero;
    LocalDate fecha;
    Double total;
    @Column(name = "medio_pago")
    String medioPago;
    @OneToMany(mappedBy = "venta")
    Set<Prenda> prendas;
}
