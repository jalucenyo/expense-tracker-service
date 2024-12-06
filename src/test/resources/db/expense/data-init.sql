INSERT INTO expense (id, amount_value, amount_exponent, description, transaction_date, payment_method, vendor, recurrence, notes, category)
VALUES
    ('b65de560-0d17-4dbf-b4cd-2952082c6ad2', 1500, 2, 'Monthly office cleaning', now() - interval '3 month', 'CREDIT_CARD', 'Cleaning Services Ltd.', 'NONE', 'Regular cleaning service', 'Office'),
    ('0d90c595-b4a9-4650-858e-c190f0da153a', 2200, 2, 'Employee health insurance', now() - interval '3 month', 'TRANSFER', 'HealthCare Inc.', 'NONE', 'Payment for employee insurance', 'Home'),
    ('5f775264-5517-4086-9061-755cf912b02d', 1800, 2, 'Monthly software licenses', now() - interval '3 month', 'DEBIT_CARD', 'Tech Tools Ltd.', 'NONE', 'Payment for office software tools', 'Office'),
    ('1e051299-7bd6-4bb5-bedb-3100abfd955a', 800, 2, 'Monthly pest control', now() - interval '3 month', 'CASH', 'Pest Control Services', 'YEARLY', 'Monthly pest prevention services', 'Home'),
    ('5feae2a4-9eb0-47e1-b237-47ce703a7451', 950, 2, 'Magazine ad placement', now() - interval '3 month', 'TRANSFER', 'Ad Agency', 'NONE', 'Advertisement for product', 'Office'),

    ('e805f9e7-c0ca-4939-8a5c-26fe7706944a', 2500, 2, 'Monthly internet bill', now() - interval '1 month', 'TRANSFER', 'ISP Provider Ltd.', 'NONE', 'Internet payment for last month', 'Home'),
    ('329957d2-59a4-4815-9f5f-d905ee94024f', 1800, 2, 'Monthly electricity bill', now() - interval '1 month', 'DEBIT_CARD', 'Energy Solutions Inc.', 'NONE', 'Electricity charges for last month', 'Office'),
    ('44afea1f-1a6f-410a-8ab3-b44132859b7d', 600, 2, 'Office coffee subscription', now() - interval '1 month', 'CREDIT_CARD', 'Coffee Bean Co.', 'MONTHLY', 'Subscription for office coffee machine', 'Home'),
    ('413b487b-9d4d-48bc-873a-6a24c528443e', 3200, 2, 'Cloud hosting services', now() - interval '1 month', 'TRANSFER', 'Cloud Provider Ltd.', 'NONE', 'Payment for cloud storage and services', 'Office'),
    ('12aa18fa-a8aa-4f04-bdfe-c249e03b5bce', 250, 2, 'Magazine subscription', now() - interval '1 month', 'DEBIT_CARD', 'Business Weekly Mag.', 'NONE', 'Monthly business magazine', 'Office'),

    ('b4623c2b-3ee1-4b5b-996c-874e358e27b0', 420, 2, 'Weekly groceries', now() - interval '1 week', 'DEBIT_CARD', 'Local Grocery Store', 'NONE', 'Groceries for the week', 'Office'),
    ('5d70d10e-803b-49bf-a061-f14342cb5af6', 550, 2, 'Office supplies', now() - interval '1 week', 'CASH', 'Staples Supplies Co.', 'NONE', 'Weekly stationery restocking', 'Office'),
    ('ea5eb2f1-fbe4-40f7-b577-0bdc16eecda0', 350, 2, 'Team lunch', now() - interval '1 week', 'CREDIT_CARD', 'Local Bistro', 'NONE', 'Weekly team-building lunch', 'Office'),
    ('efaed9da-2724-47d0-a9a5-01d98330874d', 800, 2, 'Fuel expenses', now() - interval '1 week', 'TRANSFER', 'Fuel Station', 'WEEKLY', 'Weekly refueling for company cars', 'Home'),
    ('9a7b1a8c-61cc-4466-89c9-1b38ae047418', 600, 2, 'Client meeting refreshments', now() - interval '1 week', 'DEBIT_CARD', 'Catering Services', 'WEEKLY', 'Snacks and drinks for client presentation', 'Home'),

    ('99ef298b-8580-4949-904c-420a5866edea', 150, 2, 'Daily parking fees', now() - interval '1 day', 'CASH', 'City Parking Lot', 'WEEKLY', 'Parking charges for last week', 'Home'),
    ('27521e5b-7f81-445c-a68e-b155b3ac9cb7', 1200, 2, 'Office snacks', now() - interval '1 day', 'CREDIT_CARD', 'Local Grocery Store', 'NONE', 'Weekly restocking of snacks', 'Office'),
    ('0ba4780c-ac66-4ae5-bbad-fc656b09015b', 450, 2, 'Cleaning services', now() - interval '1 day', 'TRANSFER', 'Cleaning Services Inc.', 'NONE', 'Weekly office cleaning', 'Office'),
    ('7025e471-b023-42ee-b8f1-ad038131f9fa', 380, 2, 'Courier charges', now() - interval '1 day', 'DEBIT_CARD', 'Courier Express', 'NONE', 'Weekly delivery charges',  'Home'),
    ('64aa238f-02e2-4c5b-9006-e2bcdd34616d', 240, 2, 'Team coffee', now() - interval '1 day', 'CASH', 'Starbucks', 'NONE', 'Coffee for weekly team meeting', 'Home'),

    ('f8a54f56-9584-4fa8-adf0-2618eb7ba196', 7000, 2, 'Marketing expenses', now() - interval '2 hours', 'CREDIT_CARD', 'Marketing Solutions Ltd.', 'MONTHLY', 'Campaign for Q3 initiatives', 'Office'),
    ('bc3de91e-5a19-4d23-bb23-18e69074c4f0', 6000, 2, 'Software subscription', now() - interval '2 hours', 'TRANSFER', 'Tech Solutions Inc.', 'NONE', 'Payment for software licenses Q3', 'Office'),
    ('e1c36573-382d-4c7a-868c-3ef542a3d8ed', 3200, 2, 'Conference sponsorship', now() - interval '2 hours', 'CREDIT_CARD', 'Event Planners Co.', 'MONTHLY', 'Sponsorship for Q3 industry event', 'Home'),
    ('6b9ba7b7-88fb-40db-9d35-5c1ffde56725', 7000, 2, 'Marketing expenses', now() - interval '2 hours', 'CREDIT_CARD', 'Marketing Solutions Ltd.', 'NONE', 'Campaign for Q3 initiatives', 'Office'),
    ('0989de36-843b-4be8-882a-4ec9b219b1f3', 6000, 2, 'Software subscription', now() - interval '2 hours', 'TRANSFER', 'Tech Solutions Inc.', 'NONE', 'Payment for software licenses Q3', 'Office');