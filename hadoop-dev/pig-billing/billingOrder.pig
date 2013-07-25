-- pig-billing.pig

-- ## TODO : set the name of job
SET job.name 'pig_billing_syedbahm_by_total';


-- Parse Input File
billing_data = LOAD 'syedbahm2/billing/in' USING PigStorage(',') AS (timestamp:long,custid:chararray,resourceid:chararray,qty:int,cost:int);

-- ## TODO: Find total cost by custid
-- ## first group by customer
B =  FOREACH billing_data GENERATE custid,cost;
C =    GROUP B BY custid;

DESCRIBE C;

-- ## then sum cost
D = FOREACH C GENERATE group, SUM(B.cost) as total;
E = ORDER D by total DESC;
STORE E INTO 'syedbahm2/billing/billing_out_spending';

-- ## TODO : now process all the files (change LOAD example from sample.txt --> *.log)
