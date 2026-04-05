package com.exam.broker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_retry_jobs")
public class ProductRetryJob extends RetryJob {}
