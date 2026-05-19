package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
