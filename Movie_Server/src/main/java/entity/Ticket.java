package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    private String ticketNumber;
    private String seat;
    private Type type;
    private double price;
    private LocalDate bookingDate;
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
