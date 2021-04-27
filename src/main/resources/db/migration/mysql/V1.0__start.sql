
USE miniprocurements;



CREATE TABLE BidStatus(
                          status_id SMALLINT AUTO_INCREMENT,
                          status_name VARCHAR(50) NOT NULL UNIQUE,
                          CONSTRAINT pk_status_id PRIMARY KEY (status_id)
);

CREATE TABLE ProcurementStatus(
                                  procurement_status_ID SMALLINT AUTO_INCREMENT NOT NULL UNIQUE,
                                  procurement_status_name VARCHAR(50) NOT NULL UNIQUE,
                                  CONSTRAINT pk_procurement_status_ID PRIMARY KEY (procurement_status_ID)

);


CREATE TABLE Contract(
                         contract_id MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
                         contract_reference_number BIGINT NOT NULL,
                         contract_name TEXT NOT NULL,
                         procurement_template_id MEDIUMINT,
                         bid_template_id MEDIUMINT,
                         date_added DATETIME NOT NULL DEFAULT NOW(),
                         CONSTRAINT pk_contract_id PRIMARY KEY (contract_id)
);

CREATE TABLE Person(
                       person_id MEDIUMINT AUTO_INCREMENT NOT NULL,
                       person_name VARCHAR(100),
                       e_mail VARCHAR(100) NOT NULL UNIQUE,
                       time_of_register DATETIME NOT NULL DEFAULT NOW(),
                       CONSTRAINT pk_person_id PRIMARY KEY (person_id)
);

CREATE TABLE Partner(
                        partner_id MEDIUMINT,
                        reg_nr BIGINT,
                        partner_info TEXT,
                        CONSTRAINT pk_partner_id PRIMARY KEY (partner_id),
                        CONSTRAINT fk_partner_person FOREIGN KEY (partner_id)
                            REFERENCES Person(person_id)
);


CREATE TABLE Employee(
                         employee_id MEDIUMINT,
                         CONSTRAINT pk_employee_id PRIMARY KEY (employee_id),
                         CONSTRAINT fk_employee_person FOREIGN KEY (employee_id)
                             REFERENCES Person(person_id)
);

CREATE TABLE ContractPartners(
                                 contract_partner_id MEDIUMINT,
                                 contract_id MEDIUMINT NOT NULL,
                                 partner_id MEDIUMINT NOT NULL,
                                 CONSTRAINT pk_contract_partner_id PRIMARY KEY (contract_partner_id),
                                 CONSTRAINT fk_contract_id FOREIGN KEY (contract_id)
                                     REFERENCES Contract(contract_id),
                                 CONSTRAINT fk_contract_partner_id FOREIGN KEY (partner_id)
                                     REFERENCES Partner(partner_id),
                                 CONSTRAINT UC_partner_contract UNIQUE (contract_id, partner_id)
);


CREATE TABLE Miniprocurement
(
    procurement_id MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    procurement_name VARCHAR(50) NOT NULL,
    amount INT,
    description TEXT,
    requirements TEXT,
    has_contract BOOLEAN default(0) NOT NULL,
    contract_id MEDIUMINT,
    contract_sub_id MEDIUMINT,
    time_added DATETIME NOT NULL DEFAULT NOW(),
    added_by MEDIUMINT NOT NULL,
    deadline DATETIME,
    status SMALLINT NOT NULL default(1),
    time_finished DATETIME,
    CONSTRAINT pk_procurement_id PRIMARY KEY (procurement_id),
    CONSTRAINT fk_procurement_status FOREIGN KEY (status)
        REFERENCES ProcurementStatus(procurement_status_ID),
    CONSTRAINT fk_procurement_contract_id FOREIGN KEY (contract_id)
        REFERENCES Contract(contract_id),
    CONSTRAINT fk_procurement_added_by FOREIGN KEY (added_by)
        REFERENCES Employee(employee_id),
    CONSTRAINT uq_contract_contract_sub_id UNIQUE (contract_id, contract_sub_id)
);

CREATE TABLE Procurement_Winners(
                                    procurement_id MEDIUMINT NOT NULL,
                                    winner_id MEDIUMINT NOT NULL,
                                    judge_id MEDIUMINT NOT NULL,
                                    reason TEXT,
                                    judgement_time DATETIME DEFAULT NOW(),
                                    constraint pk_procurement_winner PRIMARY KEY (procurement_id),
                                    CONSTRAINT procurement_id FOREIGN KEY (procurement_id)
                                        REFERENCES Miniprocurement(procurement_id),
                                    CONSTRAINT fk_procurement_winner_id FOREIGN KEY (winner_id)
                                        REFERENCES Partner(partner_id),
                                    CONSTRAINT fk_procurement_judge_id FOREIGN KEY (judge_id)
                                        REFERENCES Employee(employee_id)
);

CREATE TABLE MiniprocurementPartner (
    id MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
    link_id BINARY(16) NOT NULL UNIQUE,
    procurement_id MEDIUMINT NOT NULL,
    partner_id MEDIUMINT NOT NULL,
    time_added DATETIME NOT NULL DEFAULT NOW(),
    link_first_accessed DATETIME DEFAULT NULL,
    CONSTRAINT uq_partner_procurement UNIQUE(procurement_id, partner_id),
    CONSTRAINT pk_link_id PRIMARY KEY (link_id),
    CONSTRAINT fk_procurement_link_partner_id FOREIGN KEY (partner_id)
        REFERENCES Partner(partner_id),
    CONSTRAINT fk_procurement_link_procurement_id FOREIGN KEY (procurement_id)
        REFERENCES Miniprocurement(procurement_id)
);


CREATE TRIGGER before_insert_Miniprocurement_Partners
    BEFORE INSERT ON MiniprocurementPartner
    FOR EACH ROW SET NEW.link_id = UUID_TO_BIN(uuid());


CREATE TABLE Announcement(
                             announcement_id MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
                             procurement_id MEDIUMINT NOT NULL,
                             employee_id MEDIUMINT NOT NULL,
                             announcement TEXT NOT NULL,
                             date_added DATETIME NOT NULL DEFAULT NOW(),
                             CONSTRAINT pk_announcement_id PRIMARY KEY (announcement_id),
                             CONSTRAINT fk_announcement_employee_id FOREIGN KEY (employee_id)
                                 REFERENCES Employee(employee_id)
                                 ON UPDATE CASCADE
                                 ON DELETE NO ACTION,
                             CONSTRAINT fk_announcement_procurement_id FOREIGN KEY (procurement_id)
                                 REFERENCES Miniprocurement(procurement_id)
                                 ON UPDATE CASCADE
                                 ON DELETE NO ACTION
);

CREATE TABLE Bid(
                    bid_id MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
                    bidder MEDIUMINT NOT NULL,
                    bid_value BIGINT NOT NULL,
                    bid_status SMALLINT NOT NULL,
                    description TEXT,
                    procurement_id MEDIUMINT NOT NULL,
                    time_of_register DATETIME NOT NULL DEFAULT NOW(),
                    CONSTRAINT pk_bid_id PRIMARY KEY (bid_id),
                    CONSTRAINT fk_bid_status FOREIGN KEY (bid_status)
                        REFERENCES BidStatus(status_id)
                        ON UPDATE CASCADE
                        ON DELETE NO ACTION,
                    CONSTRAINT fk_bid_procurement FOREIGN KEY (procurement_id)
                        REFERENCES Miniprocurement(procurement_id)
                        ON UPDATE CASCADE
                        ON DELETE NO ACTION,
                    CONSTRAINT fk_bidder FOREIGN KEY (bidder)
                        REFERENCES Partner(partner_id)
                        ON UPDATE CASCADE
                        ON DELETE NO ACTION
);

CREATE TABLE Procurer(
                         procurement_id MEDIUMINT NOT NULL AUTO_INCREMENT,
                         employee_id MEDIUMINT NOT NULL,
                         CONSTRAINT pk_procurement_id PRIMARY KEY (procurement_id),
                         CONSTRAINT fk_procurement_id FOREIGN KEY (procurement_id)
                             REFERENCES Miniprocurement(procurement_id)
                             ON UPDATE CASCADE
                             ON DELETE NO ACTION,
                         CONSTRAINT fk_employee_id FOREIGN KEY (employee_id)
                             REFERENCES Employee(employee_id)
                             ON UPDATE CASCADE
                             ON DELETE NO ACTION,
                         CONSTRAINT UC_procurement_procurer UNIQUE (procurement_id, employee_id)
);

CREATE TABLE Question(
                         question_id MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
                         asker_id VARCHAR(50) NOT NULL,
                         procurement_id MEDIUMINT NOT NULL,
                         question TEXT NOT NULL,
                         time_asked DATETIME NOT NULL DEFAULT NOW(),
                         CONSTRAINT pk_question_id PRIMARY KEY (question_id),
                         CONSTRAINT fk_q_procurement_id FOREIGN KEY (procurement_id)
                             REFERENCES Miniprocurement(procurement_id)
                             ON UPDATE CASCADE
                             ON DELETE NO ACTION
);

CREATE TABLE Reply(
                      reply_id MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
                      replier_id MEDIUMINT NOT NULL,
                      question_id MEDIUMINT NOT NULL,
                      procurement_id MEDIUMINT NOT NULL,
                      reply TEXT,
                      time_replied DATETIME NOT NULL DEFAULT NOW(),
                      CONSTRAINT pk_reply_id PRIMARY KEY (reply_id),
                      CONSTRAINT fk_replier_id FOREIGN KEY (replier_id)
                          REFERENCES Employee(employee_id)
                          ON UPDATE CASCADE
                          ON DELETE NO ACTION,
                      CONSTRAINT fk_question_id FOREIGN KEY (question_id)
                          REFERENCES Question(question_id)
                          ON UPDATE CASCADE
                          ON DELETE NO ACTION,
                      CONSTRAINT fk_r_procurement_id FOREIGN KEY (procurement_id)
                          REFERENCES Miniprocurement(procurement_id)
                          ON UPDATE CASCADE
                          ON DELETE NO ACTION
);

CREATE TABLE Document(
                         document_id MEDIUMINT AUTO_INCREMENT NOT NULL UNIQUE,
                         document_number BIGINT NOT NULL,
                         document_name VARCHAR(100) NOT NULL,
                         procurement_id MEDIUMINT NOT NULL,
                         bid_id MEDIUMINT,
                         person_id MEDIUMINT NOT NULL,
                         document_path TEXT NOT NULL,
                         date_added DATETIME NOT NULL DEFAULT NOW(),
                         CONSTRAINT pk_document_id PRIMARY KEY (document_id),
                         CONSTRAINT fk_document_procurement_id FOREIGN KEY (procurement_id)
                             REFERENCES Miniprocurement(procurement_id)
                             ON UPDATE CASCADE
                             ON DELETE NO ACTION,
                         CONSTRAINT fk_document_person_id FOREIGN KEY (person_id)
                             REFERENCES Person(person_id)
                             ON UPDATE CASCADE
                             ON DELETE NO ACTION,
                         CONSTRAINT fk_document_bid_id FOREIGN KEY (bid_id)
                             REFERENCES Bid(bid_id)
                             ON UPDATE CASCADE
                             ON DELETE NO ACTION
);

CREATE TABLE Role(
                     role_id SMALLINT AUTO_INCREMENT NOT NULL,
                     role_name VARCHAR(50) NOT NULL UNIQUE,
                     CONSTRAINT pk_role_id PRIMARY KEY (role_id)
);

CREATE TABLE Person_Roles(
                             role_id SMALLINT NOT NULL,
                             person_id MEDIUMINT NOT NULL UNIQUE,
                             CONSTRAINT pk_person_roles PRIMARY KEY (role_id, person_id),
                             CONSTRAINT fk_person_roles_person_id FOREIGN KEY (person_id)
                                 REFERENCES Person(person_id)
                                 ON UPDATE CASCADE
                                 ON DELETE CASCADE,
                             CONSTRAINT fk_person_roles_role_id FOREIGN KEY (role_id)
                                 REFERENCES Role(role_id)
                                 ON UPDATE CASCADE
                                 ON DELETE NO ACTION
);

CREATE TABLE Email (

                       email_id MEDIUMINT AUTO_INCREMENT NOT NULL,
                       sent_date DATETIME DEFAULT NULL,
                       procurement_id MEDIUMINT,
                       reply_id MEDIUMINT,
                       announcement_id MEDIUMINT,
                       recipient_id MEDIUMINT NOT NULL,
                       sender_id MEDIUMINT NOT NULL,
                       CONSTRAINT pk_email_sent_status PRIMARY KEY (email_id),
                       CONSTRAINT fk_email_procurement_id FOREIGN KEY (procurement_id)
                           REFERENCES Miniprocurement(procurement_id)
                           ON UPDATE CASCADE
                           ON DELETE NO ACTION,
                       CONSTRAINT fk_email_reply_id FOREIGN KEY (reply_id)
                           REFERENCES Reply(replier_id)
                           ON UPDATE CASCADE
                           ON DELETE NO ACTION,
                       CONSTRAINT fk_email_announcement_id FOREIGN KEY (announcement_id)
                           REFERENCES Announcement(procurement_id)
                           ON UPDATE CASCADE
                           ON DELETE NO ACTION,
                       CONSTRAINT fk_email_recipient_id FOREIGN KEY (recipient_id)
                           REFERENCES Person(person_id)
                           ON UPDATE CASCADE
                           ON DELETE NO ACTION,
                       CONSTRAINT fk_email_sender_id FOREIGN KEY (sender_id)
                           REFERENCES Employee(employee_id)
                           ON UPDATE CASCADE
                           ON DELETE NO ACTION

);
