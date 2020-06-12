
CREATE TABLE IF NOT EXISTS pizza_order (
    id BINARY(16) PRIMARY KEY,
    pizza_type VARCHAR(25) NOT NULL,
    quantity INT NOT NULL,
    order_status VARCHAR(10) NOT NULL,
    requested_by VARCHAR(25) NOT NULL,
    create_time timestamp default now() NOT NULL,
    update_time timestamp default now() on update now() NOT NULL
);
