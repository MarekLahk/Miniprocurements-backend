INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Employee user', 'test@email.com');
INSERT INTO Person (Person.person_name, Person.e_mail) VALUE ('Partner user', 'test1@email.com');
INSERT INTO Employee (Employee.employee_id) VALUE (1);
INSERT INTO Partner (partner_id, reg_nr, partner_info) VALUES (2, 12345, 'Hello world');
INSERT INTO Procurement (name, amount, description, requirements, contract_id, contract_sub_id, created_by, deadline, completion_deadline, completion_deadline_days)
 VALUES ('procurement1', null, 'description', 'requirements', null, null, 1, '2021-07-02 00:06:35', '2021-08-02 00:06:35', null);
INSERT INTO Procurement (name, amount, description, requirements, contract_id, contract_sub_id, created_by, deadline, completion_deadline, completion_deadline_days)
 VALUES ('procurement2', null, 'description', 'requirements', null, null, 1, '2022-05-02 00:06:35', '2023-05-02 00:06:35', null);
