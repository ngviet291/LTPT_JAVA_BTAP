package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "tickets")
public class Ticket {
    @Id
    @Column(name = "ticket_number")
    private String ticketNumber;
    private String seat;
    @Enumerated(EnumType.STRING)
    private Type type;
    private double price;
    @Column(name = "booking_date")
    private LocalDate bookingDate;
    @ManyToOne()
    @JoinColumn(name = "show_id")
    @ToString.Exclude
    private Show show;
    @ManyToOne()
    @ToString.Exclude
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
