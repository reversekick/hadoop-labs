-- GROUPBY lab

-- ## TODO : set the name of job
SET job.name 'syedbahm_groupby';

billing_data = LOAD 'syedbahm2/billing/in' USING PigStorage(',') AS (timestamp:long,custid:chararray,resourceid:chararray,qty:int,cost:int);
DUMP billing_data;

-- ## TODO : get a resource grouped
grouped_by_resource = GROUP billing_data BY resourceid;
--DESCRIBE grouped_by_resource
--DUMP   grouped_by_resource

STORE grouped_by_resource INTO 'syedbahm2/billing/groupby_out
-- ## TODO : special group - ALL
--grpd =GROUP billing_data ALL;
--DESCRIBE grpd;
--DUMP grpd;
