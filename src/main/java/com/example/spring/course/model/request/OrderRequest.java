package com.example.spring.course.model.request;

import com.example.spring.course.model.enums.Stato;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderRequest {
    private String codiceOrdine;
    private String nomeCliente;
    private String cognomeCliente;
    private String emailCliente;
    private String telefonoCliente;
    private String indirizzoSpedizione;
    private String cittaSpedizione;
    private String cap;
    private String prodotto;
    private Integer quantita;
    private Double prezzoUnitario;
    private BigDecimal totale;
    private Stato stato;
    private LocalDateTime dataOrdine;
}
