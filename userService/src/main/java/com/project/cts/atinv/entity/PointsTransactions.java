//package com.project.cts.atinv.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
////points_txns (txID, loyaltyID, txnType, points, referenceId, )
//@Setter
//@Getter
//@Entity
//@Table(name = "points_txns")
//public class PointsTransactions {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int transactionId;
//    private int points;
//    private String createdBy;
//    private LocalDateTime createdOn;
//    private String updatedBy;
//    private LocalDateTime updatedOn;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "txn_type", nullable = false)
//    private TransactionType txnType;
//
//
//    @Version
//    @NotNull
//    private Long version=1L;
//    @ManyToOne
//    @JoinColumn(
//            name="reference_id",
//            referencedColumnName = "bookingId"
//    )
//    private Booking booking;
//
//    @ManyToOne
//    @JoinColumn(
//            name="loyalty_id",
//            referencedColumnName = "loyaltyId"
//    )
//    private LoyaltyAccount loyaltyAccount;
//
//
//    public enum TransactionType {
//        EARN,
//        REDEEM,
//        ADJUST
//    }
//
//
//}
