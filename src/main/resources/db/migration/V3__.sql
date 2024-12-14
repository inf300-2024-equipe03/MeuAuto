CREATE TABLE cars
(
    id                UUID NOT NULL,
    value             VARCHAR(255),
    brand             VARCHAR(255),
    model_description VARCHAR(255),
    year              VARCHAR(255),
    fuel              VARCHAR(255),
    owner_id          UUID,
    CONSTRAINT pk_car PRIMARY KEY (id)
);

ALTER TABLE cars
    ADD CONSTRAINT FK_CAR_ON_OWNER FOREIGN KEY (owner_id) REFERENCES users (id);

CREATE TABLE car_repair_shops
(
    id         UUID NOT NULL,
    name       VARCHAR(255),
    rating     DOUBLE PRECISION,
    address_id UUID,
    CONSTRAINT pk_car_repair_shops PRIMARY KEY (id)
);

CREATE TABLE schedulings
(
    id                 UUID NOT NULL,
    owner_id           UUID,
    car_id             UUID,
    car_repair_shop_id UUID,
    scheduled_for      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_schedulings PRIMARY KEY (id)
);

ALTER TABLE schedulings
    ADD CONSTRAINT FK_SCHEDULINGS_ON_CAR FOREIGN KEY (car_id) REFERENCES cars (id);

ALTER TABLE schedulings
    ADD CONSTRAINT FK_SCHEDULINGS_ON_CAR_REPAIR_SHOP FOREIGN KEY (car_repair_shop_id) REFERENCES car_repair_shops (id);

ALTER TABLE schedulings
    ADD CONSTRAINT FK_SCHEDULINGS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES users (id);