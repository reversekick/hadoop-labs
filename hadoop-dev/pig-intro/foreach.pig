-- FOR EACH lab

-- ## TODO : set the name of job
SET job.name 'syedbahm2';

billing_data = LOAD 'syedbahm2/billing/in/sample.txt' USING PigStorage(',') AS (timestamp:long,custid:chararray,resourceid:chararray,qty:int,cost:int);
DUMP billing_data;

-- ## TODO : lets extract custid and price
A = FOREACH billing_data GENERATE custid, cost;
DESCRIBE A;
DUMP A;

-- ## TODO : do some manipulation of data
-- B = FOREACH billing_data GENERATE custid, cost*2;
-- DESCRIBE B;
-- DUMP B;
