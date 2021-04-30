INSERT INTO ProcurementStatus (ProcurementStatus.procurement_status_name) VALUES ('TestStatus');
INSERT INTO BidStatus (BidStatus.status_name) VALUES ('TestStatus');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Employee user1', 'employee1@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Employee user2', 'employee2@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Employee user3', 'employee3@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Employee user4', 'employee4@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Employee user5', 'employee5@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Employee user6', 'employee6@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Partner user1', 'partner1@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Partner user2', 'partner2@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Partner user3', 'partner3@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Partner user4', 'partner4@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Partner user5', 'partner5@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Partner user6', 'partner6@email.com');
INSERT INTO Employee (Employee.employee_id) VALUE (1);
INSERT INTO Employee (Employee.employee_id) VALUE (2);
INSERT INTO Employee (Employee.employee_id) VALUE (3);
INSERT INTO Employee (Employee.employee_id) VALUE (4);
INSERT INTO Employee (Employee.employee_id) VALUE (5);
INSERT INTO Employee (Employee.employee_id) VALUE (6);
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (7, 1, 'Partner1');
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (8, 2, 'Partner2');
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (9, 3, 'Partner3');
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (10, 4, 'Partner4');
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (11, 5, 'Partner5');
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (12, 6, 'Partner6');

INSERT INTO Miniprocurement (Miniprocurement.procurement_name, Miniprocurement.amount, Miniprocurement.description, Miniprocurement.requirements, Miniprocurement.contract_id, Miniprocurement.added_by, Miniprocurement.deadline, Miniprocurement.status)
VALUES ('Test', 0, 'Test', 'Test', NULL, 1, ' 2021-12-12 12:00:00 ', 1 );
INSERT INTO Miniprocurement (Miniprocurement.procurement_name, Miniprocurement.description, Miniprocurement.requirements, Miniprocurement.contract_id, Miniprocurement.added_by, Miniprocurement.deadline)
VALUES ('', '', '', NULL, 1, null);
