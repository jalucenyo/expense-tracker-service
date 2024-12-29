INSERT INTO accounts (id, version, email, password)
VALUES
    ('b46340c1-010a-4e57-8b2a-56599c382068', 0, 'test1@test.local','{bcrypt}$2a$10$UBz2a/amnU4gymD7Htsn/unNGQojLALcIshZ2aMldIIMkIZ6943K.'),
    ('00f7689d-215c-45fd-8971-160f6a223ae2', 0, 'test2@test.local', '{bcrypt}$2a$10$UBz2a/amnU4gymD7Htsn/unNGQojLALcIshZ2aMldIIMkIZ6943K.')
;

INSERT INTO budget (id, version, name, amount_value, amount_exponent, start_date, end_date, category)
VALUES
    ('22a2b9a8-a538-4a2a-ad2d-5e2dfca9a972', 0, 'Marketing Campaign', 100000, 2, now() - interval '1 month', now() + interval '1 month', 'Food'),
    ('b67c0651-0aff-4f0b-a19c-051c5dc4b8f6', 0, 'IT Infrastructure Upgrade', 50000, 2, now(), now() - interval '1 month', 'Office'),
    ('03e660c6-d150-44e8-9861-0b9568c0ea90', 0, 'Office Supplies', 1500, 0, now(), now() - interval '1 month', 'Office'),
    ('f6fee818-dbf6-4424-bad8-d47e7e73c806', 0, 'Employee Training', 20000, 2, now(), now() - interval '1 month', 'Food');
