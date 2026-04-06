# Broker Service

## Description
The core of the retry mechanism. It listens for failed jobs in Kafka and executes a Chain of Responsibility (CoR) to ensure eventual consistency.

## Mechanism
1. **Listener**: Consumes from `order_retry_jobs`, `payments_retry_jobs`, and `product_retry_jobs`.
2. **Persistence**: Saves jobs in PostgreSQL (`retry_jobs` table) with `PENDING` status.
3. **Scheduler**: Runs every 10 seconds to pick up pending jobs.
4. **Chain of Responsibility**:
   - **Step A (Creation)**: Retries the original operation in the target service.
   - **Step B (Email)**: Sends a notification email.
   - **Step C (Log)**: Logs the operation (placeholder for CloudWatch).
   - **Step D (Mongo)**: Saves a final tracking record in MongoDB.

## Port
- Default: `8084`
