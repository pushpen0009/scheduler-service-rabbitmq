package io.egrow.frontend.scheduler_service.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthlyBillingDistributorMessage {

    Long startOfBillingMonth;
    Long endOfBillingMonth;
}
