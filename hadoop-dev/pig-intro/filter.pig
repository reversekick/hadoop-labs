-- FOR EACH lab

-- ## TODO : set the name of job
SET job.name 'syedbahm_filter';

billing_data = LOAD 'syedbahm2/billing/in/sample.txt' USING PigStorage(',') AS (timestamp:long,custid:chararray,resourceid:chararray,qty:int,cost:int);
DUMP billing_data;

-- ## TODO : filter only resource_id = 1
A = FILTER billing_data BY resourceid == 1;
DESCRIBE A;
DUMP A;

-- ## TODO : filter free usage (cost = 0)
-- B = FILTER billing_data BY  ???;
-- DESCRIBE B;
-- DUMP B;
