INSERT INTO budget (id, name, amount_value, amount_exponent, start_date, end_date)
VALUES
    ('22a2b9a8-a538-4a2a-ad2d-5e2dfca9a972', 'Marketing Campaign', 100000, 2, now(), now() - interval '1 month'),
    ('b67c0651-0aff-4f0b-a19c-051c5dc4b8f6', 'IT Infrastructure Upgrade', 50000, 2, now(), now() - interval '1 month'),
    ('03e660c6-d150-44e8-9861-0b9568c0ea90', 'Office Supplies', 1500, 0, now(), now() - interval '1 month'),
    ('f6fee818-dbf6-4424-bad8-d47e7e73c806', 'Employee Training', 20000, 2, now(), now() - interval '1 month');
