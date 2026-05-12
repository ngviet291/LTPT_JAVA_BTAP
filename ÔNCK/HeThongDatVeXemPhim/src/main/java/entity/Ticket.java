package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tickets")
@Entity
public class Ticket implements Serializable {
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
    @JoinColumn(name="show_id")
    private Show show;
//
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}