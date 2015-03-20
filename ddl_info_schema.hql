SET DB;

USE ${hiveconf:DB};
DROP TABLE IF EXISTS ${hiveconf:DB}.information_schema;
CREATE EXTERNAL TABLE ${hiveconf:DB}.information_schema(
 database_name string
,table_name string
,field_name string
,field_type string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '|'
LOCATION '${hiveconf:DB}/information_schema';
