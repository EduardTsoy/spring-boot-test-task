CREATE SEQUENCE seq_product START WITH 1 INCREMENT BY 1;

CREATE TABLE product (
  id       NUMBER(19) DEFAULT seq_product.NEXTVAL NOT NULL,
  name     VARCHAR2(1000)                         NOT NULL,
  brand    VARCHAR2(1000),
  price    NUMBER(20, 2),
  quantity NUMBER(10)
);

ALTER TABLE product
  ADD CONSTRAINT product_pk PRIMARY KEY (id);
