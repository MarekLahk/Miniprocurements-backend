CREATE DATABASE IF NOT EXISTS miniprocurements;
COMMIT;
USE miniprocurements;



CREATE TABLE BidStatus
(
    status_id   SMALLINT AUTO_INCREMENT,
    status_name VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT pk_status_id PRIMARY KEY (status_id)
);

CREATE TABLE ProcurementStatus
(
    procurement_status_ID   SMALLINT AUTO_INCREMENT NOT NULL UNIQUE,
    procurement_status_name VARCHAR(50)             NOT NULL UNIQUE,
    CONSTRAINT pk_procurement_status_ID PRIMARY KEY (procurement_status_ID)

);


CREATE TABLE Contract
(
    contract_id               MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    contract_reference_number BIGINT                   NOT NULL,
    contract_name             TEXT                     NOT NULL,
    procurement_template_id   MEDIUMINT,
    bid_template_id           MEDIUMINT,

    created_at                DATETIME DEFAULT NOW(),
    updated_at                DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_contract_id PRIMARY KEY (contract_id)
);

CREATE TABLE Person
(
    person_id   MEDIUMINT AUTO_INCREMENT NOT NULL,
    person_name VARCHAR(100),
    e_mail      VARCHAR(100)             NOT NULL UNIQUE,

    created_at  DATETIME DEFAULT NOW(),
    updated_at  DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_person_id PRIMARY KEY (person_id)

);

CREATE TABLE Partner
(
    partner_id   MEDIUMINT,
    reg_nr       BIGINT,
    partner_info TEXT,
    CONSTRAINT pk_partner_id PRIMARY KEY (partner_id),
    CONSTRAINT fk_partner_person FOREIGN KEY (partner_id)
        REFERENCES Person (person_id)
);


CREATE TABLE Employee
(
    employee_id MEDIUMINT,
    CONSTRAINT pk_employee_id PRIMARY KEY (employee_id),
    CONSTRAINT fk_employee_person FOREIGN KEY (employee_id)
        REFERENCES Person (person_id)
);

CREATE TABLE ContractPartners
(
    contract_partner_id MEDIUMINT,
    contract_id         MEDIUMINT NOT NULL,
    partner_id          MEDIUMINT NOT NULL,

    created_at          DATETIME DEFAULT NOW(),
    updated_at          DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_contract_partner_id PRIMARY KEY (contract_partner_id),
    CONSTRAINT fk_contract_id FOREIGN KEY (contract_id)
        REFERENCES Contract (contract_id),
    CONSTRAINT fk_contract_partner_id FOREIGN KEY (partner_id)
        REFERENCES Partner (partner_id),
    CONSTRAINT UC_partner_contract UNIQUE (contract_id, partner_id)
);


CREATE TABLE Procurement
(
    procurement_id           MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    name                     VARCHAR(50)              NOT NULL,
    amount                   INT,
    description              TEXT,
    requirements             TEXT,
    has_contract             BOOLEAN                  NOT NULL default (0),
    contract_id              MEDIUMINT,
    contract_sub_id          MEDIUMINT,
    created_by               MEDIUMINT                NOT NULL,
    deadline                 DATETIME,
    status                   SMALLINT                 NOT NULL default (1),
    completion_deadline      DATETIME,
    completion_deadline_days DATETIME,

    created_at               DATETIME                          DEFAULT NOW(),
    updated_at               DATETIME                          DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_procurement_id PRIMARY KEY (procurement_id),
    CONSTRAINT fk_procurement_status FOREIGN KEY (status)
        REFERENCES ProcurementStatus (procurement_status_ID),
    CONSTRAINT fk_procurement_contract_id FOREIGN KEY (contract_id)
        REFERENCES Contract (contract_id),
    CONSTRAINT fk_procurement_created_by FOREIGN KEY (created_by)
        REFERENCES Employee (employee_id),
    CONSTRAINT uq_contract_contract_sub_id UNIQUE (contract_id, contract_sub_id)
);

CREATE TABLE Procurement_Winners
(
    procurement_id MEDIUMINT NOT NULL,
    winner_id      MEDIUMINT NOT NULL,
    judge_id       MEDIUMINT NOT NULL,
    reason         TEXT,

    created_at     DATETIME DEFAULT NOW(),
    updated_at     DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    constraint pk_procurement_winner PRIMARY KEY (procurement_id),
    CONSTRAINT procurement_id FOREIGN KEY (procurement_id)
        REFERENCES Procurement (procurement_id),
    CONSTRAINT fk_procurement_winner_id FOREIGN KEY (winner_id)
        REFERENCES Partner (partner_id),
    CONSTRAINT fk_procurement_judge_id FOREIGN KEY (judge_id)
        REFERENCES Employee (employee_id)
);

CREATE TABLE ProcurementPartner
(
    id             MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    link_id        BINARY(16)               NOT NULL UNIQUE,
    procurement_id MEDIUMINT                NOT NULL,
    partner_id     MEDIUMINT                NOT NULL,

    created_at     DATETIME DEFAULT NOW(),
    updated_at     DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT uq_partner_procurement UNIQUE (procurement_id, partner_id),
    CONSTRAINT pk_link_id PRIMARY KEY (link_id),
    CONSTRAINT fk_procurement_link_partner_id FOREIGN KEY (partner_id)
        REFERENCES Partner (partner_id),
    CONSTRAINT fk_procurement_link_procurement_id FOREIGN KEY (procurement_id)
        REFERENCES Procurement (procurement_id)
);


CREATE TRIGGER before_insert_Miniprocurement_Partners
    BEFORE INSERT
    ON ProcurementPartner
    FOR EACH ROW SET NEW.link_id = UUID_TO_BIN(uuid());


CREATE TABLE Announcement
(
    announcement_id MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    procurement_id  MEDIUMINT                NOT NULL,
    employee_id     MEDIUMINT                NOT NULL,
    announcement    TEXT                     NOT NULL,

    created_at      DATETIME DEFAULT NOW(),
    updated_at      DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_announcement_id PRIMARY KEY (announcement_id),
    CONSTRAINT fk_announcement_employee_id FOREIGN KEY (employee_id)
        REFERENCES Employee (employee_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_announcement_procurement_id FOREIGN KEY (procurement_id)
        REFERENCES Procurement (procurement_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

CREATE TABLE Bid
(
    bid_id         MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    link_id        BINARY(16)               NOT NULL,
    bid_value      BIGINT,
    bid_status     SMALLINT                 NOT NULL DEFAULT 1,
    description    TEXT,
    procurement_id MEDIUMINT                NOT NULL,

    created_at     DATETIME                          DEFAULT NOW(),
    updated_at     DATETIME                          DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_bid_id PRIMARY KEY (bid_id),
    CONSTRAINT fk_bid_status FOREIGN KEY (bid_status)
        REFERENCES BidStatus (status_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_bid_procurement FOREIGN KEY (procurement_id)
        REFERENCES Procurement (procurement_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_link_id FOREIGN KEY (link_id)
        REFERENCES ProcurementPartner (link_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

CREATE TABLE Procurer
(
    procurement_id MEDIUMINT NOT NULL AUTO_INCREMENT,
    employee_id    MEDIUMINT NOT NULL,

    created_at     DATETIME DEFAULT NOW(),
    updated_at     DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_procurement_id PRIMARY KEY (procurement_id),
    CONSTRAINT fk_procurement_id FOREIGN KEY (procurement_id)
        REFERENCES Procurement (procurement_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_employee_id FOREIGN KEY (employee_id)
        REFERENCES Employee (employee_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT UC_procurement_procurer UNIQUE (procurement_id, employee_id)
);

CREATE TABLE Question
(
    question_id    MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    bidder_link_id BINARY(16)               NOT NULL,
    procurement_id MEDIUMINT                NOT NULL,
    question_text  TEXT                     NOT NULL,

    created_at     DATETIME DEFAULT NOW(),
    updated_at     DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_question_id PRIMARY KEY (question_id),
    CONSTRAINT fk_question_bidder_link_id FOREIGN KEY (bidder_link_id)
        REFERENCES ProcurementPartner (link_id),
    CONSTRAINT fk_q_procurement_id FOREIGN KEY (procurement_id)
        REFERENCES Procurement (procurement_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

CREATE TABLE Reply
(
    reply_id       MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    replier_id     MEDIUMINT                NOT NULL,
    question_id    MEDIUMINT                NOT NULL,
    procurement_id MEDIUMINT                NOT NULL,
    reply          TEXT,

    created_at     DATETIME DEFAULT NOW(),
    updated_at     DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_reply_id PRIMARY KEY (reply_id),
    CONSTRAINT fk_replier_id FOREIGN KEY (replier_id)
        REFERENCES Employee (employee_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_question_id FOREIGN KEY (question_id)
        REFERENCES Question (question_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_r_procurement_id FOREIGN KEY (procurement_id)
        REFERENCES Procurement (procurement_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

CREATE TABLE Document
(
    document_id     MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    document_number BIGINT                   NOT NULL,
    document_name   VARCHAR(100)             NOT NULL,
    procurement_id  MEDIUMINT                NOT NULL,
    bid_id          MEDIUMINT,
    person_id       MEDIUMINT                NOT NULL,
    document_path   TEXT                     NOT NULL,

    created_at      DATETIME DEFAULT NOW(),
    updated_at      DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_document_id PRIMARY KEY (document_id),
    CONSTRAINT fk_document_procurement_id FOREIGN KEY (procurement_id)
        REFERENCES Procurement (procurement_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_document_person_id FOREIGN KEY (person_id)
        REFERENCES Person (person_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_document_bid_id FOREIGN KEY (bid_id)
        REFERENCES Bid (bid_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

CREATE TABLE Role
(
    role_id   SMALLINT AUTO_INCREMENT NOT NULL,
    role_name VARCHAR(50)             NOT NULL UNIQUE,
    CONSTRAINT pk_role_id PRIMARY KEY (role_id)
);

CREATE TABLE Person_Roles
(
    role_id    SMALLINT  NOT NULL,
    person_id  MEDIUMINT NOT NULL UNIQUE,

    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_person_roles PRIMARY KEY (role_id, person_id),
    CONSTRAINT fk_person_roles_person_id FOREIGN KEY (person_id)
        REFERENCES Person (person_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_person_roles_role_id FOREIGN KEY (role_id)
        REFERENCES Role (role_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

CREATE TABLE Email
(

    email_id        MEDIUMINT AUTO_INCREMENT NOT NULL,
    sent_date       DATETIME DEFAULT NULL,
    procurement_id  MEDIUMINT,
    reply_id        MEDIUMINT,
    announcement_id MEDIUMINT,
    recipient_id    MEDIUMINT                NOT NULL,
    sent_at         DATETIME,

    created_at      DATETIME DEFAULT NOW(),
    updated_at      DATETIME DEFAULT NOW()
        ON UPDATE NOW(),

    CONSTRAINT pk_email_sent_status PRIMARY KEY (email_id),
    CONSTRAINT fk_email_procurement_id FOREIGN KEY (procurement_id)
        REFERENCES Procurement (procurement_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_email_reply_id FOREIGN KEY (reply_id)
        REFERENCES Reply (replier_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_email_announcement_id FOREIGN KEY (announcement_id)
        REFERENCES Announcement (procurement_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_email_recipient_id FOREIGN KEY (recipient_id)
        REFERENCES Person (person_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION

);

DELIMITER //
CREATE TRIGGER a
    BEFORE INSERT
    ON Email
    FOR EACH ROW
BEGIN
    IF NOT (((NEW.procurement_id is not null) and (NEW.reply_id is null) and (NEW.announcement_id is null))
        OR ((NEW.procurement_id is null) and (NEW.reply_id is not null) and (NEW.announcement_id is null))
        OR ((NEW.procurement_id is null) and (NEW.reply_id is null) and (NEW.announcement_id is not null)))
    THEN

        SIGNAL SQLSTATE '23000';
    end if;
end//
DELIMITER ;

COMMIT;
