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
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (2, 12345, 'Hello world');
INSERT INTO procurement (name, amount, description, requirements, contract_id, contract_sub_id, created_by, deadline, completion_deadline, completion_deadline_days)
 VALUES ('procurement1', null, 'description', 'requirements', null, null, 1, '2021-07-02 00:06:35', '2021-08-02 00:06:35', null);
INSERT INTO procurement (name, amount, description, requirements, contract_id, contract_sub_id, created_by, deadline, completion_deadline, completion_deadline_days)
 VALUES ('procurement2', null, 'description', 'requirements', null, null, 1, '2022-05-02 00:06:35', '2023-05-02 00:06:35', null);
