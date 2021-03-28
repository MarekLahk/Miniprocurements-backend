INSERT INTO miniprocurements.ProcurementStatus (miniprocurements.ProcurementStatus.procurement_status_name) VALUES ('TestStatus');
INSERT INTO miniprocurements.Person (miniprocurements.Person.person_name, miniprocurements.Person.e_mail) VALUE ('Employee user', 'test@email.com');
INSERT INTO miniprocurements.Person (miniprocurements.Person.person_name, miniprocurements.Person.e_mail) VALUE ('Partner user', 'test1@email.com');
INSERT INTO miniprocurements.Employee (miniprocurements.Employee.employee_id) VALUE (1);
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (2, 12345, 'Hello world');
INSERT INTO miniprocurements.Miniprocurement (miniprocurements.Miniprocurement.procurement_name, miniprocurements.Miniprocurement.amount, miniprocurements.Miniprocurement.description, miniprocurements.Miniprocurement.requirements, miniprocurements.Miniprocurement.contract_id, miniprocurements.Miniprocurement.added_by, miniprocurements.Miniprocurement.deadline, miniprocurements.Miniprocurement.status)
VALUES ('Test', 0, 'Test', 'Test', NULL, 1, ' 2021-04-01 12:00:00 ', 1 );
