INSERT INTO ProcurementStatus (ProcurementStatus.procurement_status_name) VALUES ('TestStatus');
INSERT INTO BidStatus (BidStatus.status_name) VALUES ('TestStatus');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Employee user', 'test@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Partner user', 'test1@email.com');
INSERT INTO Employee (Employee.employee_id) VALUE (1);
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (2, 12345, 'Hello world');
INSERT INTO Miniprocurement (Miniprocurement.procurement_name, Miniprocurement.amount, Miniprocurement.description, Miniprocurement.requirements, Miniprocurement.contract_id, Miniprocurement.added_by, Miniprocurement.deadline, Miniprocurement.status)
VALUES ('Test', 0, 'Test', 'Test', NULL, 1, ' 2021-12-12 12:00:00 ', 1 );
INSERT INTO Miniprocurement (Miniprocurement.procurement_name, Miniprocurement.description, Miniprocurement.requirements, Miniprocurement.contract_id, Miniprocurement.added_by, Miniprocurement.deadline)
VALUES ('', '', '', NULL, 1, null);
